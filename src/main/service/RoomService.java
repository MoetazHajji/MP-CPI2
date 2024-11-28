package service;

import entities.Room;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoomService {
    private final List<Room> rooms = new ArrayList<>();
    private final JsonUtils<Room> jsonUtils = new JsonUtils<>("src/main/resources/Room.json");

    public RoomService() {
        try {
            rooms.addAll(jsonUtils.readData(Room[].class));
        } catch (Exception e) {
            System.out.printf("Erreur de chargement des salles : %s%n", e.getMessage());
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
        saveRooms();
    }

    public boolean updateRoom(String id, Room updatedRoom) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getId().equals(id)) {
                updatedRoom.setId(id);
                rooms.set(i, updatedRoom);
                saveRooms();
                return true;
            }
        }
        return false;
    }

    public boolean deleteRoom(String id) {
        boolean removed = rooms.removeIf(room -> room.getId().equals(id));
        if (removed) saveRooms();
        return removed;
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> getAvailableRoomsByType(String type) {
        return rooms.stream()
                .filter(room -> room.isAvailable() && room.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    private void saveRooms() {
        try {
            jsonUtils.writeData(rooms);
        } catch (Exception e) {
            System.out.printf("Erreur de sauvegarde des salles : %s%n", e.getMessage());
        }
    }
}
