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
}
