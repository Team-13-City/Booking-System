public class Payment {

    private String paymentID;
    private Double amount;
    private String status;

    public Payment(String paymentID, Double amount, String status){
        this.paymentID = paymentID;
        this.amount = amount;
        this.status = status;
    }
}
