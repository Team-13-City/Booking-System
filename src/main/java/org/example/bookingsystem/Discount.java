package org.example.bookingsystem;

import java.time.LocalDate;

public class Discount {
    String code;
    enum Type { PERCENTAGE, FIXED }
    Type type;
    double value;
    LocalDate validFrom;
    LocalDate validUntil;
    String eligibilityCriteria;

    public boolean isValid() {
        LocalDate today = LocalDate.now();
        return today.isAfter(validFrom.minusDays(1)) && today.isBefore(validUntil.plusDays(1));
    }

    public double applyTo(double originalPrice) {
        if (!isValid()) return originalPrice;
        return type == Type.PERCENTAGE ? originalPrice * (1 - value / 100) : Math.max(0, originalPrice - value);
    }
}