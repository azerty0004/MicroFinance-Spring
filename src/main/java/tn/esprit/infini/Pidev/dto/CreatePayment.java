package tn.esprit.infini.Pidev.dto;



public class CreatePayment {


    private Integer amount;


    private String featureRequest;

    public CreatePayment() {
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getFeatureRequest() {
        return featureRequest;
    }

    public void setFeatureRequest(String featureRequest) {
        this.featureRequest = featureRequest;
    }
}