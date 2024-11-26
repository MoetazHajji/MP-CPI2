package entities;

public class Exam {
    String id;
    String patientId;
    String doctorId;
    String type;
    String status;
    String report;

    public Exam(String id, String patientId, String doctorId, String type, String status, String report) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.type = type;
        this.status = status;
        this.report = report;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReport(String report) {
        this.report = report;
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

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getReport() {
        return report;
    }
}

