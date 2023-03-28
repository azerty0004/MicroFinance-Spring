package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.infini.Pidev.Repository.GuarantorRepository;
import tn.esprit.infini.Pidev.entities.Guarantor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
@AllArgsConstructor
public class GuarantorService implements IGuarantorService{

    GuarantorRepository guarantorRepository;

    @Override
    public List<Guarantor> retrieveAllGuarantor() {
        return guarantorRepository.findAll();    }


    @Override
    public Guarantor addGuarantor(Guarantor g) throws Exception {

        if (!ValidCin(g.getCinGuarantor())) {
            throw new Exception("Invalid cin format. Must be composed of 8 digits.");
        }
        return guarantorRepository.save(g);
    }

    @Override
    public Guarantor updateGuarantor(Guarantor g) {
        return guarantorRepository.save(g);
    }

    @Override
    public Guarantor retrieveGuarantor(Integer idGurantor) {
        return guarantorRepository.findById(idGurantor).get();
    }

    @Override
    public void deleteGuarantor(Integer idGurantor) {
        guarantorRepository.deleteById(idGurantor);
    }

    @Override
    public boolean VerifyGuarantor(Guarantor guarantor, double amount) {
        double requiredValue = amount / 5;
        if (guarantor.getSalary() >= requiredValue) {
            return true;
        }
    return false;
    }
        //...
    @Override
    public boolean ValidCin(int cin) {
        String cinString = String.valueOf(cin);
        return cinString.matches("\\d{8}");
    }

        @Override
        public boolean checkGuarantorSalary(MultipartFile payslip, double requestedCreditAmount) {

            // Parsing the payslip to extract the salary information
            double guarantorSalary = parsePayslip(payslip);

            // Comparing the guarantor's salary with the requested credit amount
            return guarantorSalary >= requestedCreditAmount;
        }

        @Override // najamch ntesteha f postman
        public double parsePayslip(MultipartFile payslip) {

            // Extracting the salary information from the payslip
            // This is an example of how to parse the payslip, you should implement your own parser based on your file format
            double salary = 0.0;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(payslip.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Salary:")) {
                        salary = Double.parseDouble(line.split(":")[1]);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return salary;
        }
    }




