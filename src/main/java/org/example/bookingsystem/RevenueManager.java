package org.example.bookingsystem;

import java.time.LocalDate;
import java.util.*;

public class RevenueManager {
    List<Ticket> salesLog = new ArrayList<>();
    List<Ticket> refundLog = new ArrayList<>();

    public double calculateDailyRevenue(LocalDate date) {
        return salesLog.stream()
                .filter(ticket -> ticket.purchaseDate.toLocalDate().equals(date))
                .mapToDouble(ticket -> ticket.price)
                .sum();
    }

    public Report generateMonthlyReport() {
        Report report = new Report();
        report.title = "Monthly Sales Report";
        report.generatedAt = java.time.LocalDateTime.now();
        Map<String, Object> data = new HashMap<>();
        data.put("totalSales", salesLog.size());
        data.put("totalRevenue", salesLog.stream().mapToDouble(t -> t.price).sum());
        report.data = data;
        return report;
    }

    public double calculateTotalRefunds(LocalDate date) {
        return refundLog.stream()
                .filter(ticket -> ticket.purchaseDate.toLocalDate().equals(date))
                .mapToDouble(ticket -> ticket.price)
                .sum();
    }
}