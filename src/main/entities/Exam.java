package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Exam {
    private String id;
    private String patientId;
    private String doctorId;
    private String technique;
    private String status;
    private String report;

    public Exam(@JsonProperty("id") String id,
                @JsonProperty("patientId") String patientId,
                @JsonProperty("doctorId") String doctorId,
                @JsonProperty("technique") String technique,
                @JsonProperty("status") String status,
                @JsonProperty("report") String report) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.technique = technique;
        this.status = status;
        this.report = report;
    }

    public Exam(){}

    public void setId(String id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getStatus() {
        return status;
    }

    public String getReport() {
        return report;
    }

    public String getTechnique() {
        return technique;
    }

}

