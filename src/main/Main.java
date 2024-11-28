import controller.*;


import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientController patientController = new PatientController();
        DoctorController doctorController = new DoctorController();
        ExamController examController = new ExamController();
        RoomController roomController = new RoomController();
        AppointmentController appointmentController = new AppointmentController();
        ReportController reportController = new ReportController();
        FinanceController financeController = new FinanceController();

        int mainChoice;
        do {
            System.out.println("=== Radiology Center Management ===");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Exams");
            System.out.println("4. Manage Rooms");
            System.out.println("5. Manage Appointments");
            System.out.println("6. Manage Reports");
            System.out.println("7. Manage Finance");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (mainChoice) {
                case 1 -> patientController.managePatients(scanner);
                case 2 -> doctorController.manageDoctors(scanner);
                case 3 -> examController.gestionExam(scanner);
                case 4 -> roomController.gestionRoom(scanner);
                case 5 -> appointmentController.gestionAppointment(scanner);
                case 6 -> reportController.gestionReports(scanner);
                case 7 -> financeController.manageFinance(scanner);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (mainChoice != 0);
    }

}
