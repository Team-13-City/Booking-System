package org.example.bookingsystem;

import java.time.LocalDateTime;
import java.util.*;

public class Report {
    String title;
    Map<String, Object> data;
    LocalDateTime generatedAt;

    public String exportAsCSV() {
        StringBuilder sb = new StringBuilder("Key,Value\n");
        data.forEach((k, v) -> sb.append(k).append(",").append(v).append("\n"));
        return sb.toString();
    }

    public String exportAsPDF() {
        return "[PDF Export Placeholder] Title: " + title + ", Data Count: " + data.size();
    }

    public void displaySummary() {
        System.out.println("--- Report Summary: " + title + " ---");
        data.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}