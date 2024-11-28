package controller;


import entities.Room;
import service.RoomService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RoomController {

    private static final RoomService roomService = new RoomService();

    public void gestionRoom(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Manage Patients ===");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. Update Room");
            System.out.println("4. Delete Room");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addRoom(scanner);
                case 2 -> viewRooms();
                case 3 -> updateRoom(scanner);
                case 4 -> deleteRoom(scanner);
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private static void addRoom(Scanner scanner) {
        System.out.print("Enter Room ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Room Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Room Type (Scanner, IRM, Echographie): ");
        String type = scanner.nextLine();
        System.out.print("Is the room available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();
        scanner.nextLine();

        Room room = new Room(id, name, type, isAvailable);
        roomService.addRoom(room);
        System.out.println("Room added successfully!");
    }

    private static void viewRooms() {
        System.out.println("=== Room List ===");
        for (Room room : roomService.getAllRooms()) {
            System.out.println(room);
        }
    }

    private static void updateRoom(Scanner scanner) {
        System.out.print("Enter Room ID to update: ");
        String id = scanner.nextLine();

        Optional<Room> roomOptional = roomService.getAllRooms().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();

        if (roomOptional.isEmpty()) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Enter New Room Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Room Type (Scanner, IRM, Echographie): ");
        String type = scanner.nextLine();
        System.out.print("Is the room available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();
        scanner.nextLine();

        Room updatedRoom = new Room(id, name, type, isAvailable);
        if (roomService.updateRoom(id, updatedRoom)) {
            System.out.println("Room updated successfully!");
        } else {
            System.out.println("Failed to update room.");
        }
    }

    private static void deleteRoom(Scanner scanner) {
        System.out.print("Enter Room ID to delete: ");
        String id = scanner.nextLine();

        if (roomService.deleteRoom(id)) {
            System.out.println("Room deleted successfully!");
        } else {
            System.out.println("Room not found.");
        }
    }

}
