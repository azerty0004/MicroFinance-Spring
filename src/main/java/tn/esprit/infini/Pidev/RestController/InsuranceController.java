package tn.esprit.infini.Pidev.RestController;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.IInsuranceService;
import tn.esprit.infini.Pidev.entities.Insurance;

import java.util.List;

@RestController
@RequestMapping()
@AllArgsConstructor
public class InsuranceController {
    private IInsuranceService insuranceService;


    @PostMapping("/addinsurance")
    public void addinsurance(@RequestBody Insurance insurance) {
        insuranceService.addInsurance(insurance);
    }
    @GetMapping("/displayinsurance")
    public List<Insurance> displayinsurance() {
        return insuranceService.retrieveAllinsurances();
    }

    @GetMapping("/displaywithId/{idinsurance}")
    public Insurance displaywithidinsurance(@PathVariable int idinsurance) {
        return insuranceService.retrieveInsurance( (int) idinsurance);
    }
    @PutMapping("/updateinsurance")
    public Insurance updateinsurance(@RequestBody Insurance insurance) {

        return insuranceService.updateInsurance(insurance);
    }


    @GetMapping("/calculate-cost")
    public double calculateInsuranceCost(@RequestBody Insurance insurance) {
        return insuranceService.calculateInsuranceCost(insurance);
    }

    @PostMapping("/calculateCostWithDiscount")
    public double calculateCostWithDiscount(@RequestBody Insurance insurance) {
        return insuranceService.calculateInsuranceCostWithDiscount(insurance);
    }
}
