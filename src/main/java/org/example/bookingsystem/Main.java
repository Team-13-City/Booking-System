// Main.java - Test Harness
package org.example.bookingsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Set up Venue and Seats
        Venue venue = new Venue();
        venue.venueID = "VENUE1";
        venue.name = "Main Theatre";
        venue.capacity = 5;
        venue.layout = new HashMap<>();

        for (int i = 1; i <= 5; i++) {
            Seat seat = new Seat();
            seat.seatID = "A" + i;
            seat.isReserved = false;
            seat.isAccessable = (i == 1);
            seat.viewQuality = (i == 5) ? Seat.ViewQuality.RESTRICTED : Seat.ViewQuality.GOOD;
            venue.layout.put(seat.seatID, seat);
        }

        // Create Event
        EventManager eventManager = new EventManager();
        Event event = eventManager.createEvent("Hamlet", LocalDateTime.now().plusDays(1), venue);

        // Create Customer
        Customer customer = new Customer();
        customer.userID = "CUST1";
        customer.name = "John Doe";
        customer.email = "john@example.com";
        customer.fol = true;

        // Create Discount
        Discount discount = new Discount();
        discount.code = "WELCOME10";
        discount.type = Discount.Type.PERCENTAGE;
        discount.value = 10;
        discount.validFrom = LocalDate.now().minusDays(1);
        discount.validUntil = LocalDate.now().plusDays(5);

        // TicketManager
        TicketManager ticketManager = new TicketManager();
        Seat selectedSeat = venue.getSeat("A1");
        Ticket ticket = ticketManager.sellTicket(customer, event, selectedSeat, discount);
        System.out.println("Ticket Sold: " + ticket.ticketID + " at price £" + ticket.price);

        // Refund the ticket
        boolean cancelled = ticketManager.cancelTicket(ticket.ticketID);
        System.out.println("Ticket Cancelled: " + cancelled);

        boolean refunded = ticketManager.refundTicket(ticket.ticketID);
        System.out.println("Ticket Refunded: " + refunded);

        // Test Group Booking
        GroupBooking groupBooking = new GroupBooking();
        groupBooking.groupID = "GRP1";
        groupBooking.event = event;
        groupBooking.size = 3;
        groupBooking.paymentStatus = "CONFIRMED";
        groupBooking.seatPreferences = Arrays.asList("A2", "A3", "A4");
        groupBooking.assignSeats(new ArrayList<>(venue.layout.values()));
        System.out.println("GroupBooking confirmed: " + groupBooking.isConfirmed());

        // Simulate Online Payment
        OnlineBookingAPI bookingAPI = new OnlineBookingAPI();
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.cardNumber = "1234567890123456";
        paymentInfo.expiryDate = "12/26";
        paymentInfo.cvv = "123";
        paymentInfo.billingAddress = "123 Street";

        PaymentResult result = bookingAPI.processPayment(customer, paymentInfo);
        System.out.println("Payment Success: " + result.success + ", TXN: " + result.transactionID);

        // Revenue Reporting
        RevenueManager revenueManager = new RevenueManager();
        revenueManager.salesLog.add(ticket);
        revenueManager.refundLog.add(ticket);
        System.out.println("Today's Revenue: £" + revenueManager.calculateDailyRevenue(LocalDate.now()));
        System.out.println("Today's Refunds: £" + revenueManager.calculateTotalRefunds(LocalDate.now()));

        Report report = revenueManager.generateMonthlyReport();
        report.displaySummary();
    }
}
