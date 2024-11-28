package entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private String patientName;
    private String doctorId;
    private String technicianId;
    private String roomId;
    private LocalDateTime appointmentDate;
    private String technique;
    private String status;

    public Appointment(@JsonProperty("id") String id,
                       @JsonProperty("patientName") String patientName,
                       @JsonProperty("doctorId") String doctorId,
                       @JsonProperty("technicianId") String technicianId,
                       @JsonProperty("roomId") String roomId,
                       @JsonProperty("appointmentDate") LocalDateTime appointmentDate,
                       @JsonProperty("technique") String technique,
                       @JsonProperty("status") String status) {
        this.id = id;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.technicianId = technicianId;
        this.roomId = roomId;
        this.appointmentDate = appointmentDate;
        this.technique = technique;
        this.status = status;
    }

    public Appointment() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public String getTechnicianId() { return technicianId; }
    public void setTechnicianId(String technicianId) { this.technicianId = technicianId; }
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }
    public String getTechnique() { return technique; }
    public void setTechnique(String technique) { this.technique = technique; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", patientName='" + patientName + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", technicianId=" + technicianId +
                ", roomId=" + roomId +
                ", appointmentDate=" + appointmentDate +
                ", technique=" + technique +
                ", status=" + status +
                '}';
    }
}
