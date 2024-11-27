package service;

import entities.Doctor;
import utils.JsonUtils;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    List<Doctor> doctors = new ArrayList<Doctor>();
    private final JsonUtils<Doctor> jsonUtils = new JsonUtils<>("src/main/resources/Doctor.json");

    public DoctorService() {
        try {
            doctors.addAll(jsonUtils.readData(Doctor[].class));
        }catch (Exception e){
            System.out.printf("Error fetching data : %s%n", e.getMessage());
        }
    }


    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        try {
            jsonUtils.writeData(doctors);
        } catch (Exception e) {
            System.out.printf("Error fetching data : %s%n", e.getMessage());
        }
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(String id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateDoctor(String id, Doctor updatedDoctor) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null) {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
            doctor.setPhone(updatedDoctor.getPhone());
            doctor.setEmail(updatedDoctor.getEmail());
            doctor.setExams(updatedDoctor.getExams());
            saveChanges("Doctor updated successfully");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void deleteDoctor(String id) {
        try {
            boolean removed = doctors.removeIf(doctor -> doctor.getId().equals(id));
            if (removed) {
                jsonUtils.writeData(doctors);
            } else {
                System.out.println("Doctor not found");
            }
        }catch (Exception e){
            System.out.println("Error fetching data : " + e.getMessage());
        }
    }

    // Assign an exam to a doctor
    public void assignExamToDoctor(String id, String exam) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null) {
            doctor.addExam(exam);
            saveChanges("Exam assigned to doctor.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void removeExamFromDoctor(String id, String exam) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null && doctor.getExams() != null) {
            if (doctor.getExams().remove(exam)) {
                saveChanges("Exam removed from doctor.");
            } else {
                System.out.println("Exam not found in doctor's list.");
            }
        } else {
            System.out.println("Doctor not found or has no exams.");
        }
    }

    private void saveChanges(String successMessage) {
        try {
            jsonUtils.writeData(doctors);
            System.out.println(successMessage);
        } catch (Exception e) {
            logError("Error saving changes", e);
        }
    }

    private void logError(String message, Exception e) {
        System.err.printf("%s: %s%n", message, e.getMessage());
    }

}
