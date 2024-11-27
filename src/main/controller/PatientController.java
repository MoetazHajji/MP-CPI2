package controller;

import entities.Patient;
import service.PatientService;

import java.util.ArrayList;
import java.util.List;
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

        // Check if the patient already exists
        Patient existingPatient = patientService.getById(id);
        if (existingPatient != null) {
            System.out.println("A patient with this ID already exists: " + id);
            return; // Exit if the patient already exists
        }

        // Collect patient details
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Patient Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Patient Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Patient Email: ");
        String email = scanner.nextLine();

        System.out.print("Do you want to add exams for the doctor? (yes/no): ");
        String addExams = scanner.nextLine();
        List<String> prescriptions = new ArrayList<>();

        if (addExams.equalsIgnoreCase("yes")) {
            System.out.println("Enter Prescriptions (type 'done' to finish): ");
            while (true) {
                String prescription = scanner.nextLine();
                if (prescription.equalsIgnoreCase("done")) break;
                prescriptions.add(prescription);
            }
        }

        // Create new patient
        Patient newPatient = new Patient(id, name, address, phone, email, prescriptions);

        // Add the patient to the service
        patientService.addPatient(newPatient);

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

        // Check if the patient exists
        Patient existingPatient = patientService.getById(id);
        if (existingPatient == null) {
            System.out.println("No patient found with ID: " + id);
            return;
        }

        // Prompt the user for new details and update them if provided
        System.out.print("Enter new Patient Name (leave blank to keep unchanged): ");
        String newName = scanner.nextLine();
        newName = newName.isEmpty() ? existingPatient.getName() : newName;

        System.out.print("Enter new Address (leave blank to keep unchanged): ");
        String newAddress = scanner.nextLine();
        newAddress = newAddress.isEmpty() ? existingPatient.getAddress() : newAddress;

        System.out.print("Enter new Phone  (leave blank to keep unchanged): ");
        String newPhone = scanner.nextLine();
        newPhone = newPhone.isEmpty() ? existingPatient.getPhone() : newPhone;

        System.out.print("Enter new Email (leave blank to keep unchanged): ");
        String newEmail = scanner.nextLine();
        newEmail = newEmail.isEmpty() ? existingPatient.getEmail() : newEmail;

        // Collect new prescriptions (or keep old)
        System.out.print("Enter new Prescriptions (comma-separated, leave blank to keep unchanged): ");
        String prescriptionsInput = scanner.nextLine();
        List<String> newPrescriptions = prescriptionsInput.isEmpty() ?
                existingPatient.getPrescriptions() :
                List.of(prescriptionsInput.split(","));



        // Create the updated patient object
        Patient updatedPatient = new Patient(id, newName, newAddress, newPhone, newEmail,newPrescriptions);
        patientService.updatePatient(id, updatedPatient);

        System.out.println("Patient updated successfully.");
    }


    private void deletePatient(Scanner scanner) {
        System.out.print("Enter Patient ID to delete: ");
        String id = scanner.nextLine();

        Patient patient = patientService.getById(id);
        if (patient == null) {
            System.out.println("No patient found with ID: " + id);
            return;
        }

        // Proceed to delete the patient if found
        patientService.deletePatient(id);
        System.out.println("Patient deleted successfully.");
    }
}
