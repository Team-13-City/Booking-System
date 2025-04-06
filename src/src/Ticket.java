public class Ticket {

    private String ticketID;
    private Seat seat;
    private double price;

    public Ticket(String ticketID, Seat seat, double price){
        this.ticketID = ticketID;
        this.seat = seat;
        this.price = price;
    }
}
