package controller;

import entities.Report;
import service.ReportService;

import java.util.List;
import java.util.Scanner;

public class ReportController {

    private static final ReportService reportService = new ReportService();

    public void gestionReports(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Manage Reports ===");
            System.out.println("1. Add Report");
            System.out.println("2. View Reports");
            System.out.println("3. View Report by Exam ID");
            System.out.println("4. Update Report");
            System.out.println("5. Delete Report");
            System.out.println("6. Print Report");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addReport(scanner);
                case 2 -> viewAllReports();
                case 3 -> viewReportByExamId(scanner);
                case 4 -> updateReport(scanner);
                case 5 -> deleteReport(scanner);
                case 6 -> printReport(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private void addReport(Scanner scanner) {
        System.out.print("Enter Exam ID: ");
        String examId = scanner.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter Report Content: ");
        String content = scanner.nextLine();
        System.out.print("Enter Report Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        Report report = new Report(examId, examId, content, doctorId, date);
        reportService.addReport(report);
        System.out.println("Report added successfully!");
    }

    private void viewAllReports() {
        System.out.println("=== All Reports ===");
        for (Report report : reportService.getReports()) {
            System.out.println("Report ID: " + report.getId() + ", Exam ID: " + report.getExamId() + ",Content: " + report.getContent() + ", Date: " + report.getDate());
        }
    }

    private void viewReportByExamId(Scanner scanner) {
        System.out.print("Enter Exam ID to get report: ");
        String examId = scanner.nextLine();
        Report report = reportService.getReportByExamId(examId);

        if (report != null) {
            System.out.println("Report for Exam ID " + examId + ": ");
            System.out.println("Doctor: " + report.getDoctorId());
            System.out.println("Content: " + report.getContent());
            System.out.println("Date: " + report.getDate());
        } else {
            System.out.println("No report found for this exam.");
        }
    }

    private void updateReport(Scanner scanner) {
        System.out.print("Enter Report ID to update: ");
        String id = scanner.nextLine();
        Report existingReport = reportService.getReports().stream()
                .filter(report -> report.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingReport == null) {
            System.out.println("Report not found.");
            return;
        }

        System.out.print("Enter New Content: ");
        String content = scanner.nextLine();
        System.out.print("Enter New Date: ");
        String date = scanner.nextLine();

        Report updatedReport = new Report(id, existingReport.getExamId(), content, existingReport.getDoctorId(), date);
        boolean isUpdated = reportService.updateReportById(id, updatedReport);

        if (isUpdated) {
            System.out.println("Report updated successfully!");
        } else {
            System.out.println("Failed to update report.");
        }
    }

    private void deleteReport(Scanner scanner) {
        System.out.print("Enter Report ID to delete: ");
        String id = scanner.nextLine();

        boolean isDeleted = reportService.deleteReportById(id);
        if (isDeleted) {
            System.out.println("Report deleted successfully!");
        } else {
            System.out.println("Report not found.");
        }
    }

    private void printReport(Scanner scanner) {
        System.out.print("Enter Exam ID to print the report: ");
        String examId = scanner.nextLine();
        Report report = reportService.getReportByExamId(examId);

        if (report != null) {
            System.out.println("=== Printing Report ===");
            System.out.println("Doctor: " + report.getDoctorId());
            System.out.println("Content: " + report.getContent());
            System.out.println("Date: " + report.getDate());
        } else {
            System.out.println("No report found for this exam.");
        }
    }
}
