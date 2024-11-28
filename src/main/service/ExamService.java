package service;
import entities.Exam;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExamService {

    List<Exam> exams = new ArrayList<Exam>();
    private final JsonUtils<Exam> jsonUtils = new JsonUtils<>("src/main/resources/Exam.json");

    public ExamService() {
        try {
            exams.addAll(jsonUtils.readData(Exam[].class));
        } catch (Exception e) {
            System.out.printf("Error fetching data: %s%n", e.getMessage());
        }
    }

    public void addExam(Exam exam) {
        exams.add(exam);
        try {
            jsonUtils.writeData(exams);
        } catch (Exception e) {
            System.out.printf("Error writing data: %s%n", e.getMessage());
        }
    }

    public List<Exam> getExams() {
        return exams;
    }

    public List<Exam> getExamsByTechnique(String technique) {
        return exams.stream()
                .filter(exam -> exam.getTechnique().equalsIgnoreCase(technique))
                .collect(Collectors.toList());
    }

    public boolean updateExamById(String id, Exam updatedExam) {
        for (int i = 0; i < exams.size(); i++) {
            if (exams.get(i).getId().equals(id)) {
                updatedExam.setId(id);
                exams.set(i, updatedExam);
                try {
                    jsonUtils.writeData(exams);
                } catch (Exception e) {
                    System.out.printf("Erreur lors de la mise à jour : %s%n", e.getMessage());
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteExamById(String id) {
        boolean isRemoved = exams.removeIf(exam -> exam.getId().equals(id));
        if (isRemoved) {
            try {
                jsonUtils.writeData(exams);
            } catch (Exception e) {
                System.out.printf("Erreur lors de la suppression : %s%n", e.getMessage());
            }
        }
        return isRemoved;
    }
}
