package controller;


import entities.Appointment;
import service.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AppointmentController {

    private static final AppointmentService appointmentService = new AppointmentService();

    public void gestionAppointment(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Appointment Management ===");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addAppointment(scanner);
                case 2 -> viewAppointments();
                case 3 -> updateAppointment(scanner);
                case 4 -> deleteAppointment(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private static void addAppointment(Scanner scanner) {
        System.out.print("Enter Appointment ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter Technician ID: ");
        String technicianId = scanner.nextLine();
        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine();
        System.out.print("Enter Appointment Date (YYYY-MM-DDTHH:mm): ");
        LocalDateTime appointmentDate = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Enter Technique: ");
        String technique = scanner.nextLine();
        System.out.print("Enter Status (Scheduled/Completed/Cancelled): ");
        String status = scanner.nextLine();

        Appointment appointment = new Appointment(id, patientName, doctorId, technicianId, roomId, appointmentDate, technique, status);
        appointmentService.addAppointment(appointment);
        System.out.println("Appointment added successfully!");
    }

    private static void viewAppointments() {
        System.out.println("=== Appointment List ===");
        for (Appointment appointment : appointmentService.getAllAppointments()) {
            System.out.println(appointment);
        }
    }

    private static void updateAppointment(Scanner scanner) {
        System.out.print("Enter Appointment ID to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Status: ");
        String status = scanner.nextLine();

        Appointment existing = appointmentService.getAllAppointments().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setStatus(status);
            appointmentService.updateAppointment(id, existing);
            System.out.println("Appointment updated successfully!");
        } else {
            System.out.println("Appointment not found!");
        }
    }

    private static void deleteAppointment(Scanner scanner) {
        System.out.print("Enter Appointment ID to delete: ");
        String id = scanner.nextLine();
        appointmentService.deleteAppointment(id);
        System.out.println("Appointment deleted successfully!");
    }
}
