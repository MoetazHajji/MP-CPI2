package service;

import entities.Report;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private List<Report> reports = new ArrayList<>();
    private final JsonUtils<Report> jsonUtils = new JsonUtils<>("src/main/resources/Reports.json");

    public ReportService() {
        try {
            reports.addAll(jsonUtils.readData(Report[].class));
        } catch (Exception e) {
            System.out.printf("Error fetching data: %s%n", e.getMessage());
        }
    }

    public void addReport(Report report) {
        reports.add(report);
        try {
            jsonUtils.writeData(reports);
        } catch (Exception e) {
            System.out.printf("Error writing data: %s%n", e.getMessage());
        }
    }

    public List<Report> getReports() {
        return reports;
    }

    public Report getReportByExamId(String examId) {
        return reports.stream()
                .filter(report -> report.getExamId().equals(examId))
                .findFirst()
                .orElse(null);
    }

    public boolean updateReportById(String id, Report updatedReport) {
        for (int i = 0; i < reports.size(); i++) {
            if (reports.get(i).getId().equals(id)) {
                updatedReport.setId(id);
                reports.set(i, updatedReport);
                try {
                    jsonUtils.writeData(reports);
                } catch (Exception e) {
                    System.out.printf("Error updating report: %s%n", e.getMessage());
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteReportById(String id) {
        boolean isRemoved = reports.removeIf(report -> report.getId().equals(id));
        if (isRemoved) {
            try {
                jsonUtils.writeData(reports);
            } catch (Exception e) {
                System.out.printf("Error deleting report: %s%n", e.getMessage());
            }
        }
        return isRemoved;
    }
}
