package org.example.bookingsystem;

public class OnlineBookingAPI {

    public boolean pushTicket(Ticket ticket) {
        // Simulate pushing to external system
        return true;
    }

    public boolean syncSeatAvailability(Event event) {
        // Simulate external seat sync
        return true;
    }

    public PaymentResult processPayment(Customer customer, PaymentInfo info) {
        // Simulated payment success
        PaymentResult result = new PaymentResult();
        result.success = true;
        result.transactionID = "TXN" + System.currentTimeMillis();
        result.errorMessage = "";
        return result;
    }
}