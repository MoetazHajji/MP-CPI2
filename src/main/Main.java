import entities.Patient;
import service.PatientService;

import java.util.Scanner;

public class Main {

    private static final PatientService patientService = new PatientService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("=== Radiology Center Management ===");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addPatient(scanner);
                case 2 -> viewPatients();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }


    private static void addPatient(Scanner scanner) {
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
        System.out.println("Patient added successfully!");
    }

    private static void viewPatients() {
        System.out.println("=== Patient List ===");
        for (Patient patient : patientService.getPatients()) {
            System.out.println(patient.getId() + " - " + patient.getName());
        }
    }
}
