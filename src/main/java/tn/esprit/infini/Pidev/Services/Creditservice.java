package tn.esprit.infini.Pidev.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.Repository.SettingsRepository;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.Repository.UserRepository;
import tn.esprit.infini.Pidev.entities.*;
import tn.esprit.infini.Pidev.exceptions.ResourceNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class Creditservice implements Icreditservice {

    private Creditrepository creditrepository;
    private TransactionRepository transactionRepository;
    private SettingsRepository settingsRepository;
    private UserRepository userRepository;

    @Override
    public List<Credit> retrieveAllCredits() {
        return (List<Credit>) creditrepository.findAll();

    }

    @Override
    public Credit addCredit(Credit c) {
        return creditrepository.save(c);
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
                interestrate = TMM + rate.getRate();
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
    public double CalculMensualitéfixe(Credit c) {
        double mensualite = 0;
        double a = 0;
        double b = 0;
        a = (c.getAmount() * (c.getInterestrate() / 12));

        b = 1 - Math.pow(1 + (c.getInterestrate() / 12), -c.getDuration());
        mensualite = a / b;

        return mensualite;
    }

    @Override
    public List<Double> CalculMensualitévariable(Credit c) {
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
    public List<Double> listetauxinterets(Credit c) {
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
    public void ValidateCredit(Credit c) throws IOException {
        float score = 0;
        score = calculateFicoScore(c);

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
        } else c.setStatut(Statut.Non_Approuvé);
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
    /*/

    public void generatePDF(List<Double> mensualites, List<Double> tauxInterets) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Tableau d'amortissement");
        contentStream.endText();

        PDTable table = new PDTable();
        int numColumns = 3;
        int numRows = mensualites.size();
        float[] columnWidths = {100f, 100f, 100f};
        float rowHeight = 20f;
        table.addColumnsOfWidth(columnWidths);
        table.addRows(numRows, rowHeight);

        // Populate table cells
        for (int i = 0; i < numRows; i++) {
            table.addCell(createCell(String.valueOf(i + 1)));
            table.addCell(createCell(String.valueOf(mensualites.get(i))));
            table.addCell(createCell(String.valueOf(tauxInterets.get(i))));
        }

        // Add table to content stream
        PDPageContentStream tableContentStream = new PDPageContentStream(document, page);
        table.draw(tableContentStream, 100, 650);

        contentStream.close();
        document.save("Mensualites.pdf");
        document.close();
    }

    private PDCell createCell(String text) {
        PDCell cell = new PDCell();
        cell.setPadding(5);
        cell.setHorizontalAlignment(PDHorizontalAlignment.CENTER);
        cell.setVerticalAlignment(PDVerticalAlignment.CENTER);
        cell.setLineHeight(20f);
        cell.setFont(PDType1Font.HELVETICA);
        cell.addParagraph(new PDParagraphBuilder().addText(text).build());
        return cell;
    }

     */
}


