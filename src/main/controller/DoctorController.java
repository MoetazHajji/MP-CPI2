package controller;

import entities.Doctor;
import service.DoctorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorController {
    private final DoctorService doctorService = new DoctorService();

    public void manageDoctors(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Manage Doctors ===");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Delete Doctor");
            System.out.println("4. Get Doctor by ID");
            System.out.println("5. Update Doctor");
            System.out.println("6. Assign Exam To Doctor");
            System.out.println("7. Remove Exam From Doctor");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addDoctor(scanner);
                case 2 -> viewAllDoctors();
                case 3 -> deleteDoctor(scanner);
                case 4 -> getDoctorById(scanner);
                case 5 -> updateDoctor(scanner);
                case 6 -> assignExamToDoctor(scanner);
                case 7 -> removeExamFromDoctor(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        }while (choice != 0);
    }

    private void addDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID: ");
        String id = scanner.nextLine();

        if (doctorService.getDoctorById(id) != null) {
            System.out.println("A doctor with this ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter Name: ");
            name = scanner.nextLine();
        }

        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        while (specialization.isEmpty()) {
            System.out.print("Specialization cannot be empty. Enter Specialization: ");
            specialization = scanner.nextLine();
        }

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        while (phone.isEmpty()) {
            System.out.print("Invalid phone number. Enter a valid Phone: ");
            phone = scanner.nextLine();
        }

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        while (email.isEmpty()) {
            System.out.print("Invalid email format. Enter a valid Email: ");
            email = scanner.nextLine();
        }

        System.out.print("Do you want to add exams for the doctor? (yes/no): ");
        String addExams = scanner.nextLine();
        List<String> exams = new ArrayList<>();

        if (addExams.equalsIgnoreCase("yes")) {
            System.out.println("Enter exams (type 'done' to finish): ");
            while (true) {
                String exam = scanner.nextLine();
                if (exam.equalsIgnoreCase("done")) break;
                exams.add(exam);
            }
        }

        Doctor doctor = new Doctor(id, name, specialization, phone, email);
        doctor.setExams(exams);
        doctorService.addDoctor(doctor);

        System.out.println("Doctor added successfully.");
    }


    private void viewAllDoctors() {
        System.out.println("=== Doctor List ===");
        for (Doctor doctor : doctorService.getDoctors()) {
            System.out.println(doctor.getId() + " - " + doctor.getName());
        }
    }

    private void getDoctorById(Scanner scanner) {
        System.out.print("Enter Doctor ID to fetch: ");
        String id = scanner.nextLine();
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            System.out.println("Doctor Details: " + doctor);
        } else {
            System.out.println("No Doctor found with ID: " + id);
        }
    }

    private void updateDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID to update: ");
        String id = scanner.nextLine();

        Doctor existingDoctor = doctorService.getDoctorById(id);
        if (existingDoctor == null) {
            System.out.println("No doctor found with the given ID.");
            return;
        }

        System.out.print("Enter new Doctor Name (leave blank to keep unchanged): ");
        String newName = scanner.nextLine();
        newName = newName.isEmpty() ? existingDoctor.getName() : newName;

        System.out.print("Enter new Specialization (leave blank to keep unchanged): ");
        String newSpecialization = scanner.nextLine();
        newSpecialization = newSpecialization.isEmpty() ? existingDoctor.getSpecialization() : newSpecialization;

        System.out.print("Enter new Phone (leave blank to keep unchanged): ");
        String newPhone = scanner.nextLine();
        newPhone = newPhone.isEmpty() ? existingDoctor.getPhone() : newPhone;

        System.out.print("Enter new Email (leave blank to keep unchanged): ");
        String newEmail = scanner.nextLine();
        newEmail = newEmail.isEmpty() ? existingDoctor.getEmail() : newEmail;

        System.out.print("Do you want to update exams? (yes/no): ");
        String updateExams = scanner.nextLine();
        List<String> newExams = existingDoctor.getExams(); // Keep existing exams by default

        if (updateExams.equalsIgnoreCase("yes")) {
            newExams = new ArrayList<>();
            System.out.println("Enter exams (type 'done' to finish): ");
            while (true) {
                String exam = scanner.nextLine();
                if (exam.equalsIgnoreCase("done")) break;
                newExams.add(exam);
            }
        }

        Doctor updatedDoctor = new Doctor(id, newName, newSpecialization, newPhone, newEmail);
        updatedDoctor.setExams(newExams);

        doctorService.updateDoctor(id, updatedDoctor);
    }


    private void deleteDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID to delete: ");
        String id = scanner.nextLine();

        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            System.out.println("No doctor found with ID: " + id);
            return;
        }

        System.out.println("Doctor Details: " + doctor);
        System.out.print("Are you sure you want to delete this doctor? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            doctorService.deleteDoctor(id);
            System.out.println("Doctor deleted successfully.");
        } else {
            System.out.println("Doctor deletion canceled.");
        }
    }

    private void assignExamToDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID to assign an exam: ");
        String id = scanner.nextLine();

        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            System.out.println("No doctor found with ID: " + id);
            return;
        }

        System.out.print("Enter Exam to assign: ");
        String exam = scanner.nextLine();

        doctorService.assignExamToDoctor(id, exam);
        System.out.println("Exam assigned successfully to Doctor: " + doctor.getName());
    }


    private void removeExamFromDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID to remove an exam: ");
        String id = scanner.nextLine();

        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            System.out.println("No doctor found with ID: " + id);
            return;
        }

        System.out.print("Enter Exam to remove: ");
        String exam = scanner.nextLine();

        doctorService.removeExamFromDoctor(id, exam);
        System.out.println("Exam removed successfully from Doctor: " + doctor.getName());
    }


}
