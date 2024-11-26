package service;

import entities.Patient;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;


public class PatientService {

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

}
