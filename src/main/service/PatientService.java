package service;

import entities.Patient;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;


public class PatientService{

    List<Patient> patients = new ArrayList<Patient>();
    private final JsonUtils<Patient> jsonUtils = new JsonUtils<>("src/main/resources/Patient.json");

    public PatientService() {
        try {
            patients.addAll(jsonUtils.readData(Patient[].class));
        } catch (Exception e) {
            System.out.printf("Error fetching data: %s%n", e.getMessage());
        }
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        try {
            jsonUtils.writeData(patients);
        } catch (Exception e) {
            System.out.printf("Error writing data: %s%n", e.getMessage());
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public Patient getById(String id) {
        return patients.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public void updatePatient(String id, Patient updatedPatient) {
        Patient patient = getById(id);
        if (patient != null) {
            patient.setName(updatedPatient.getName());
            patient.setAddress(updatedPatient.getAddress());
            patient.setEmail(updatedPatient.getEmail());
            patient.setPhone(updatedPatient.getPhone());
            saveChanges("Patient updated successfully");
        }else {
            System.out.println("Patient not found");
        }
    }

    public void deletePatient(String id) {
        try {
            boolean removed = patients.removeIf(patient -> patient.getId().equals(id));
            if (removed) {
                jsonUtils.writeData(patients);
            } else {
                System.out.println("No Patient found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.printf("Error deleting patient: %s%n", e.getMessage());
        }
    }


    private void saveChanges(String successMessage) {
        try {
            jsonUtils.writeData(patients);
            System.out.println(successMessage);
        } catch (Exception e) {
            logError("Error saving changes", e);
        }
    }

    private void logError(String message, Exception e) {
        System.err.printf("%s: %s%n", message, e.getMessage());
    }

}
