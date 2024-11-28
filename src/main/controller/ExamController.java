package controller;

import entities.Exam;
import service.ExamService;

import java.util.List;
import java.util.Scanner;

public class ExamController {

    private static final ExamService examService = new ExamService();

    public void gestionExam(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Manage Patients ===");
            System.out.println("1. Add Exam");
            System.out.println("2. View Exams");
            System.out.println("3. View Exams by Technique");
            System.out.println("4. Update Exam");
            System.out.println("5. Delete Exam");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addExam(scanner);
                case 2 -> viewAllExams();
                case 3 -> viewExamsByTechnique(scanner);
                case 4 -> updateExam(scanner);
                case 5 -> deleteExam(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private static void addExam(Scanner scanner) {

        System.out.print("Enter Exam ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter Technique (e.g., MRI, CT Scan, Ultrasound): ");
        String technique = scanner.nextLine();
        System.out.print("Enter Status: ");
        String status = scanner.nextLine();
        System.out.print("Enter Report: ");
        String report = scanner.nextLine();

        Exam exam = new Exam(id, patientId, doctorId, technique, status, report);
        examService.addExam(exam);
        System.out.println("Exam added successfully!");
    }

    private static void viewAllExams() {
        System.out.println("=== All Exams ===");
        for (Exam exam : examService.getExams()) {
            System.out.println(exam.getId() + " - " + exam.getTechnique());
        }
    }

    private static void viewExamsByTechnique(Scanner scanner) {
        System.out.print("Enter Technique to filter by (scanner, IRM, Echographie): ");
        String technique = scanner.nextLine();
        System.out.println("=== Exams by Technique: " + technique + " ===");
        for (Exam exam : examService.getExamsByTechnique(technique)) {
            System.out.println(exam.getId() + " - " + exam.getStatus() + ": " + exam.getReport());
        }
    }

    private static void updateExam(Scanner scanner) {
        System.out.print("Enter Exam ID to update: ");
        String id = scanner.nextLine();
        List<Exam> exams = examService.getExams();
        Exam existingExam = exams.stream()
                .filter(exam -> exam.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingExam == null) {
            System.out.println("Exam not found.");
            return;
        }

        System.out.print("Enter New Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter New Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter New Technique (e.g., MRI, CT Scan, Ultrasound): ");
        String technique = scanner.nextLine();
        System.out.print("Enter New Status: ");
        String status = scanner.nextLine();
        System.out.print("Enter New Report: ");
        String report = scanner.nextLine();
        Exam updatedExam = new Exam(id, patientId, doctorId, technique, status, report);
        boolean isUpdated = examService.updateExamById(id, updatedExam);

        if (isUpdated) {
            System.out.println("Exam updated successfully!");
        } else {
            System.out.println("Failed to update exam.");
        }
    }

    private static void deleteExam(Scanner scanner) {
        System.out.print("Enter Exam ID to delete: ");
        String id = scanner.nextLine();

        boolean isDeleted = examService.deleteExamById(id);
        if (isDeleted) {
            System.out.println("Exam deleted successfully!");
        } else {
            System.out.println("Exam not found.");
        }
    }
}
