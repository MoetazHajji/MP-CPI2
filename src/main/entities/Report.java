package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Report {
    private String id;
    private String examId;
    private String content;
    private String doctorId;
    private String date;

    public Report(@JsonProperty("id") String id,
                  @JsonProperty("examId") String examId,
                  @JsonProperty("content") String content,
                  @JsonProperty("doctorId") String doctorId,
                  @JsonProperty("date") String date) {
        this.id = id;
        this.examId = examId;
        this.content = content;
        this.doctorId = doctorId;
        this.date = date;
    }

    public Report() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getExamId() {
        return examId;
    }
    public void setExamId(String examId) {
        this.examId = examId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
