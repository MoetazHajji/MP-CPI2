package controller;

import entities.Patient;
import service.PatientService;

import java.util.Scanner;

public class PatientController {
    private final PatientService patientService = new PatientService();

    public void managePatients(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Manage Patients ===");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Delete Patient");
            System.out.println("4. Get Patient by ID");
            System.out.println("5. Update Patient");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPatient(scanner);
                case 2 -> viewAllPatients();
                case 3 -> deletePatient(scanner);
                case 4 -> getPatientById(scanner);
                case 5 -> updatePatient(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private void addPatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        Patient patient = new Patient(id, name, address, phone, email);
        patientService.addPatient(patient);
        System.out.println("Patient added successfully.");
    }

    private void viewAllPatients() {
        System.out.println("=== Patient List ===");
        for (Patient patient : patientService.getPatients()) {
            System.out.println(patient.getId() + " - " + patient.getName());
        }
    }

    private void getPatientById(Scanner scanner) {
        System.out.print("Enter Patient ID to fetch: ");
        String id = scanner.nextLine();
        scanner.nextLine();

        Patient patient = patientService.getById(id);
        if (patient != null) {
            System.out.println("Patient Details: " + patient);
        } else {
            System.out.println("No Patient found with ID: " + id);
        }
    }

    private void updatePatient(Scanner scanner) {
        System.out.print("Enter Patient ID to update: ");
        String id = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter new Patient Name: ");
        String newName = scanner.nextLine();

        patientService.updatePatient(id, newName);
    }

    private void deletePatient(Scanner scanner) {
        System.out.print("Enter Patient ID to delete: ");
        String id = scanner.nextLine();
        scanner.nextLine();

        patientService.deletePatient(id);
    }
}
