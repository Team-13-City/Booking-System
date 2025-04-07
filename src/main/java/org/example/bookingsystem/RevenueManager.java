package org.example.bookingsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RevenueManager {
    private List<Ticket> salesLog = new ArrayList<>();
    private List<Ticket> refundLog = new ArrayList<>();

    public double calculateDailyRevenue(LocalDate date) {
        return salesLog.stream()
                .filter(ticket -> ticket.getPurchaseDate().toLocalDate().equals(date))
                .mapToDouble(ticket -> ticket.getPrice())
                .sum();
    }

    public Report generateMonthlyReport() {
        Report report = new Report();
        report.title = "Monthly Sales Report";
        report.generatedAt = java.time.LocalDateTime.now();
        Map<String, Object> data = new HashMap<>();
        data.put("totalSales", salesLog.size());
        data.put("totalRevenue", salesLog.stream().mapToDouble(t -> t.getPrice()).sum());
        report.data = data;
        return report;
    }

    public double calculateTotalRefunds(LocalDate date) {
        return refundLog.stream()
                .filter(ticket -> ticket.getPurchaseDate().toLocalDate().equals(date))
                .mapToDouble(ticket -> ticket.getPrice())
                .sum();
    }

    // Optional helper methods to log transactions.
    public void logSale(Ticket ticket) {
        salesLog.add(ticket);
    }

    public void logRefund(Ticket ticket) {
        refundLog.add(ticket);
    }
}
