import java.time.*;

public class Sale {

    private String saleID;
    private double price;
    private LocalDateTime date;

    public Sale(String saleID, double price, LocalDateTime date){
        this.saleID = saleID;
        this.price = price;
        this.date = date;
    }
}
