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
        try {
            for (Patient patient : patients) {
                 if (patient.getId().equals(id)) {
                    return patient;
                }
            }
        } catch (Exception e) {
            System.out.printf("Error fetching patient: %s%n", e.getMessage());
        }
        return null;
    }


    public void updatePatient(String id, String newName) {
        try {
            for (Patient patient : patients) {
                if (patient.getId().equals(id)) {
                    patient.setName(newName);
                    jsonUtils.writeData(patients);
                    System.out.println("Patient updated successfully.");
                    return;
                }
            }
            System.out.println("No Patient found with ID: " + id);
        } catch (Exception e) {
            System.out.printf("Error updating patient: %s%n", e.getMessage());
        }
    }

    public void deletePatient(String id) {
        try {
            boolean removed = patients.removeIf(patient -> patient.getId().equals(id));
            if (removed) {
                jsonUtils.writeData(patients);
                System.out.println("Patient deleted successfully.");
            } else {
                System.out.println("No Patient found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.printf("Error deleting patient: %s%n", e.getMessage());
        }
    }
}
