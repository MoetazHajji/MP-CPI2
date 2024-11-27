package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    String id;
    String name;
    String specialization;
    String phone;
    String email;
    List<String> exams;

    public Doctor(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("specialization")  String specialization,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email
    ) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;

    }

    public Doctor() {}

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

    public void removeExam(String exam) {
        if (this.exams != null) {
            this.exams.remove(exam);
        }
    }

    public void addExam(String exam) {
        if (this.exams == null) {
            this.exams = new ArrayList<>();
        }
        this.exams.add(exam);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", address='" + specialization + '\'' +
                ", phone='" + phone + '\'' +
                ", prescriptions='" + email + '\'' +
                '}';
    }
}

