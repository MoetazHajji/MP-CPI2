import controller.DoctorController;
import controller.PatientController;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientController patientController = new PatientController();
        DoctorController doctorController = new DoctorController();

        int mainChoice;
        do {
            System.out.println("=== Radiology Center Management ===");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = scanner.nextInt();
            scanner.nextLine();

            switch (mainChoice) {
                case 1 -> patientController.managePatients(scanner);
                case 2 -> doctorController.manageDoctors(scanner);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (mainChoice != 0);
    }
}
