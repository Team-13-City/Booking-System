import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TicketingSystems {

    private List<Ticket> tickets;
    private List<Guest> guests;


    public TicketingSystems(List<Ticket> tickets, List<Guest> guests){
        this.tickets = tickets;
        this.guests = guests;
    }

    public void sellTicket(Guest guest, Seat seat){

    }

    public void processRefund(Ticket ticket){

    }

    public void applyDiscount(Guest guest, String discountCode){

    }
    public static double handleDiscount(double original, double discountPercent) throws Exception{
        if (original == 0) {
            throw new Exception("Original price to discount cannot be 0");
        }
        double multiplier =  1 - (discountPercent / 100);
        double discountPrice = original * multiplier;
        BigDecimal rounded = new BigDecimal(discountPrice).setScale(2, RoundingMode.HALF_UP);
        double newPrice = rounded.doubleValue();

        return newPrice;
    }
}
