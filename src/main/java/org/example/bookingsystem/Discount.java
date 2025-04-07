package org.example.bookingsystem;

import java.time.LocalDate;

public class Discount {
    String code;
    enum type {PERCENTAGE, FIXED}
    double value;
    LocalDate validFrom;
    LocalDate validUntil;
    String eligibilityCriteria;

    boolean isValid() {

    }

    double applyTo(double originalPrice) {
        
    }
}
