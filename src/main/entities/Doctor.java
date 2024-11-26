package entities;

import java.util.List;

public class Doctor {
    String id;
    String name;
    String specialization;
    String phone;
    String email;
    List<String> exams;

    public Doctor(String id, String name, String specialization, String phone, String email, List<String> exams) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.exams = exams;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExams(List<String> exams) {
        this.exams = exams;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getExams() {
        return exams;
    }
}

