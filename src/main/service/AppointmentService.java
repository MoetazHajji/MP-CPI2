package service;

import entities.Appointment;
import utils.JsonUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {
    private final List<Appointment> appointments = new ArrayList<>();
    private final JsonUtils<Appointment> jsonUtils = new JsonUtils<>("src/main/resources/Appointment.json");

    public AppointmentService() {
        try {
            appointments.addAll(jsonUtils.readData(Appointment[].class));
        } catch (Exception e) {
            System.out.printf("Error fetching appointment data: %s%n", e.getMessage());
        }
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        saveAppointments();
    }

    public void updateAppointment(String id, Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId().equals(id)) {
                appointments.set(i, updatedAppointment);
                saveAppointments();
                return;
            }
        }
        System.out.println("Appointment not found!");
    }

    public void deleteAppointment(String id) {
        appointments.removeIf(a -> a.getId().equals(id));
        saveAppointments();
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointments.stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByDate(LocalDateTime date) {
        return appointments.stream()
                .filter(a -> a.getAppointmentDate().toLocalDate().equals(date.toLocalDate()))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    private void saveAppointments() {
        try {
            jsonUtils.writeData(appointments);
        } catch (Exception e) {
            System.out.printf("Error saving appointments: %s%n", e.getMessage());
        }
    }
}
