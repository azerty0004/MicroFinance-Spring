package tn.esprit.infini.Pidev.Services;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.Repository.SettingsRepository;
import tn.esprit.infini.Pidev.Repository.UserRepository;
import tn.esprit.infini.Pidev.dto.CreditRequestDTO;
import tn.esprit.infini.Pidev.dto.CreditResponseDTO;
import tn.esprit.infini.Pidev.entities.*;
import tn.esprit.infini.Pidev.exceptions.ResourceNotFoundException;
import tn.esprit.infini.Pidev.mappers.CreditMapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;


@Service
@Configuration
@EnableScheduling
@AllArgsConstructor
public class Creditservice implements Icreditservice {
    private CreditMapper creditMapper;
    private Creditrepository creditrepository;
    private SettingsRepository settingsRepository;
    private UserRepository userRepository;

    @Override
    public List<Credit> retrieveAllCredits() {
        return  creditrepository.findAll();

    }

    @Override
    public CreditResponseDTO addCredits(CreditRequestDTO creditDTO) {
        if (creditDTO.getDuration() == null || creditDTO.getDuration() == 0 ) {
            Credit credit = Credit.builder()
                    .amount(creditDTO.getAmount())
                    .duration((int) ChronoUnit.MONTHS.between(creditDTO.getDateofobtaining().withDayOfMonth(1), creditDTO.getDateoffinish().withDayOfMonth(1)))
                    .dateOfApplication(creditDTO.getDateOfApplication())
                    .dateofobtaining(creditDTO.getDateofobtaining())
                    .dateoffinish(creditDTO.getDateoffinish())
                    .statut(creditDTO.getStatut())
                    .typeRemboursement(creditDTO.getTypeRemboursement())
                    .typeCredit(creditDTO.getTypeCredit())
                    .build();
            Credit savedCredit = creditrepository.save(credit);
            CreditResponseDTO creditResponseDTO = creditMapper.fromCredit(savedCredit);
            return creditResponseDTO;

        } else {
            Credit credit = Credit.builder()
                    .amount(creditDTO.getAmount())
                    .duration(creditDTO.getDuration())
                    .dateOfApplication(creditDTO.getDateOfApplication())
                    .dateofobtaining(creditDTO.getDateofobtaining())
                    .dateoffinish(creditDTO.getDateofobtaining().plusMonths(creditDTO.getDuration()))
                    .statut(creditDTO.getStatut())
                    .typeRemboursement(creditDTO.getTypeRemboursement())
                    .typeCredit(creditDTO.getTypeCredit())
                    .build();

            Credit savedCredit = creditrepository.save(credit);
            CreditResponseDTO creditResponseDTO = creditMapper.fromCredit(savedCredit);
            return creditResponseDTO;

        }
    }



    @Override
    public Credit updateCredit(Credit c) {
        return creditrepository.save(c);
    }

    @Override
    public Credit retrieveCredit(Long id) {

        Optional<Credit> credit = creditrepository.findById(id);

        if (credit.isPresent()) {
            return creditrepository.findById(id).get();
        } else {
            throw new ResourceNotFoundException("Credit not found with id " + id);
        }
    }


    @Override
    public void deleteCredit(Long id) {
        Optional<Credit> credit = creditrepository.findById(id);
        if (credit.isPresent()) {
            creditrepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Credit not found with id " + id);


        }

    }

    @Override
    public List<Credit> findCreditsByAttributes(Long id, Double amount, LocalDate dateOfApplication, LocalDate dateofobtaining, LocalDate dateoffinish, Double interestrate, Integer duration, Statut statut, Guarantor guarantor, TypeCredit typeCredit, Insurance insurance) {
        return creditrepository.findCreditsByAttributes(id, amount, dateOfApplication, dateofobtaining, dateoffinish, interestrate, duration, statut, guarantor, typeCredit, insurance);
    }

    @Override
    public List<Credit> getCreditByiduser(Long userid) {
        return creditrepository.getCreditByiduser(userid);

    }


    @Override
    public Float newCredit(Long idCredit) {
        float s = 0;
        int numberOfCreditsIretarde = 0;
        int numberOfCreditsrembourse = 0;

        Long userid = Long.valueOf(userRepository.findUserByCreditId(idCredit).getId());
        if (getCreditByiduser(userid).isEmpty()) {
            return s;
        } else {
            for (Credit credit : getCreditByiduser(userid)
            ) {
                if (credit.getStatut() == Statut.EN_RETARDISSEMENT) {
                    numberOfCreditsIretarde++;
                }
                if (credit.getStatut() == Statut.REMBOURSE) {
                    numberOfCreditsrembourse++;
                }


                s = numberOfCreditsrembourse - numberOfCreditsIretarde;
            }

            return s;

        }


    }


    @Override
    public Integer TauxtypeCredit(Credit c) {

        if ((c.getTypeCredit()) == TypeCredit.CREDITConsommation) {

            return 1;
        } else if ((c.getTypeCredit()) == TypeCredit.CREDITInvestissement) {

            return 2;
        } else if ((c.getTypeCredit()) == TypeCredit.CREDITEtudiant) {

            return 3;
        } else {
            return 4;
        }

    }


    @Override
    public float calculateFicoScore(Credit c) {
        float ficoscore = 0;
        ficoscore += (c.getAmount() * 0.3) + (c.getDuration() * 0.15) + (TauxtypeCredit(c) * 0.1);

        return ficoscore;
    }

    @Override
    public double InterestRateCalculator(Credit credit) throws IOException {
        double TMM = Double.parseDouble(getmm());
        double interestrate = 0;
        float score = calculateFicoScore(credit);
        List<Settings> settings = settingsRepository.findAll();

        for (Settings rate : settings) {
            if (score >= rate.getMinScore() && score <= rate.getMaxScore()) {
                interestrate = (TMM + rate.getRate())*0.1;
                credit.setInterestrate(interestrate);
                creditrepository.save(credit);
                return interestrate;
            }
        }

        interestrate = TMM + settings.get(settings.size() - 1).getRate();
        credit.setInterestrate(interestrate);
        creditrepository.save(credit);
        return interestrate;
    }


    @Override
    public String getmm() throws IOException {
        String command = "python C:/Users/zou19/OneDrive/Desktop/pi/scrapping/azz.py";
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public double CalculMensualitefixe(Credit c) {
        double mensualite;
        double a=0 ;
        double b=0 ;
        a = c.getAmount() *((c.getInterestrate() / 12));

        b = 1 - (Math.pow(1 + (c.getInterestrate() / 12), -c.getDuration()));
        mensualite = a / b;

        return mensualite;
    }

    @Override
    public List<Double> CalculMensualitevariable(Credit c) {
        double montantrestant = c.getAmount();
        double amortissement = (c.getAmount() / c.getDuration());
        double interestrateformounth = 0;
        double mensualite = 0;
        List<Double> listmensualité = new ArrayList<>();
        for (int i = 1; i <= c.getDuration(); i++) {
            interestrateformounth = montantrestant * (c.getInterestrate() / 12);
            mensualite = amortissement + interestrateformounth;
            montantrestant = montantrestant - mensualite;
            listmensualité.add(mensualite);
        }
        return listmensualité;
    }

    @Override
    public List<Double> listetauxinterets(Long id) {
        Credit c=creditrepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Credit not found")));
        double montantrestant = c.getAmount();
        double amortissement = (c.getAmount() / c.getDuration());
        double interestrateformounth = 0;
        double mensualite = 0;
        List<Double> listtauxinterets = new ArrayList<>();

        for (double i = 1; i <= c.getDuration(); i++) {
            interestrateformounth = montantrestant * (c.getInterestrate() / 12);
            if (interestrateformounth > 0) {
                listtauxinterets.add(interestrateformounth);
            } else listtauxinterets.add((double) 0);
            mensualite = amortissement + interestrateformounth;
            montantrestant = montantrestant - mensualite;
        }

        return listtauxinterets;

    }

    @Override
    public void ValidateCredit(Long id) throws IOException {
        Credit c=creditrepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Credit not found")));
        float score = calculateFicoScore(c);
         if (Optional.ofNullable(c.getGuarantor()).isPresent()) {
            if (score < 580) {
                c.setStatut(Statut.Non_Approuvé);
                creditrepository.save(c);
            } else if ((score >= 580 && score <= 669) /*bank.amount*/) {
               c.setAmount(c.getAmount() * 0.80);
                c.setStatut(Statut.Approuvé);
                creditrepository.save(c);
            } else if ((score >= 670 && score <= 739)) {
                c.setStatut(Statut.Approuvé);
                c.setAmount(c.getAmount() * 0.90);
                creditrepository.save(c);
            } else if ((score >= 740 && score <= 799)) {
                  c.setAmount(c.getAmount() * 0.95);
                c.setStatut(Statut.Approuvé);
                creditrepository.save(c);
            } else c.setStatut(Statut.Approuvé);
            creditrepository.save(c);
        }
         else c.setStatut(Statut.Non_Approuvé);
        creditrepository.save(c);
    }

    public Double averageInterestRate(List<Credit> credits) {
        Double totalInterestRate = 0.0;
        for (Credit credit : credits) {
            totalInterestRate += credit.getInterestrate();
        }
        return totalInterestRate / credits.size();
    }

    public Integer totalNumberOfLoans(List<Credit> credits) {
        return credits.size();
    }

    public Double totalAmountOfLoans(List<Credit> credits) {
        Double totalAmount = 0.0;
        for (Credit credit : credits) {
            totalAmount += credit.getAmount();
        }
        return totalAmount;
    }

    public Map<Statut, Double> percentageOfCreditsByStatus(List<Credit> credits) {
        Map<Statut, Integer> numberOfCreditsByStatus = new HashMap<>();
        for (Credit credit : credits) {
            Statut status = credit.getStatut();
            numberOfCreditsByStatus.put(status, numberOfCreditsByStatus.getOrDefault(status, 0) + 1);
        }
        Map<Statut, Double> percentageOfCreditsByStatus = new HashMap<>();
        int totalNumberOfCredits = credits.size();
        for (Map.Entry<Statut, Integer> entry : numberOfCreditsByStatus.entrySet()) {
            Statut status = entry.getKey();
            int numberOfCredits = entry.getValue();
            double percentage = (double) numberOfCredits / totalNumberOfCredits * 100;
            percentageOfCreditsByStatus.put(status, percentage);
        }
        return percentageOfCreditsByStatus;
    }

    public Map<TypeRemboursement, Double> averageRepaymentRateByType(List<Credit> credits) {
        Map<TypeRemboursement, Double> results = new HashMap<>();
        for (Credit credit : credits) {
            TypeRemboursement type = credit.getTypeRemboursement();
            Double amount = credit.getAmount();
            Integer duration = credit.getDuration();
            Double interestRate = credit.getInterestrate();
            Double totalRepayment = amount + amount * interestRate * duration / 12;
            results.put(type, results.getOrDefault(type, 0.0) + amount / totalRepayment);
        }
        for (Map.Entry<TypeRemboursement, Double> entry : results.entrySet()) {
            entry.setValue(entry.getValue() / credits.size());
        }
        return results;
    }
     @Override
        public void exportpdf(HttpServletResponse response, Long idCredit) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph paragraph = new Paragraph("Voici les détails de votre crédit.", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        Credit credit = retrieveCredit(idCredit);
        List<Double> mensualites = CalculMensualitevariable(credit);
        List<Double> tauxInterets = listetauxinterets(idCredit);
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell cellNumero = new PdfPCell(new Phrase("Mensualité numéro", fontTitle));
        cellNumero.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellNumero);
        PdfPCell cellMensualites = new PdfPCell(new Phrase("Montant du mensualité", fontTitle));
        cellMensualites.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellMensualites);
        PdfPCell cellTaux = new PdfPCell(new Phrase("montant  d'interets", fontTitle));
        cellTaux.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellTaux);
        for (int i = 0; i < mensualites.size(); i++) {
            PdfPCell cellNumeroValue = new PdfPCell(new Phrase(Integer.toString(i+1), fontParagraph));
            cellNumeroValue.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellNumeroValue);

            PdfPCell cellMensualitesValue = new PdfPCell(new Phrase(Double.toString(mensualites.get(i)), fontParagraph));
            cellMensualitesValue.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellMensualitesValue);

            PdfPCell cellTauxValue = new PdfPCell(new Phrase(Double.toString(tauxInterets.get(i)), fontParagraph));
            cellTauxValue.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellTauxValue);
        }
        document.add(table);
        document.close();
    }
    @Scheduled(cron = "0 0 1 1 * *")
    public void generateCreditReport() {
        List<Credit> credits = creditrepository.findAll();
        Map<TypeCredit, List<Credit>> creditsByType = new HashMap<>();
        for (Credit credit : credits) {
            TypeCredit type = credit.getTypeCredit();
            List<Credit> creditsOfType = creditsByType.get(type);
            if (creditsOfType == null) {
                creditsOfType = new ArrayList<>();
                creditsByType.put(type, creditsOfType);
            }
            creditsOfType.add(credit);
        }
        for (Map.Entry<TypeCredit, List<Credit>> entry : creditsByType.entrySet()) {
            TypeCredit type = entry.getKey();
            List<Credit> creditsOfType = entry.getValue();
            int numCredits = creditsOfType.size();
            double totalAmount = creditsOfType.stream().mapToDouble(Credit::getAmount).sum();
            System.out.println("Type of credit: " + type);
            System.out.println("Number of credits: " + numCredits);
            System.out.println("Total amount: " + totalAmount);
            System.out.println();
        }
    }
}





