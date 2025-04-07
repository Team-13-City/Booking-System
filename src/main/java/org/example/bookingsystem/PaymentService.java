package org.example.bookingsystem;

public class PaymentService {
    public static PaymentResult processPayment(PaymentInfo info, double amount) {
        PaymentResult result = new PaymentResult();
        // For demonstration, if card number starts with "4", payment is successful.
        if (info.cardNumber != null && info.cardNumber.startsWith("4")) {
            result.success = true;
            result.transactionID = "TXN" + System.currentTimeMillis();
            result.errorMessage = "";
        } else {
            result.success = false;
            result.transactionID = "";
            result.errorMessage = "Invalid card number.";
        }
        return result;
    }
}
