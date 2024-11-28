package service;

import entities.Income;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class IncomeService {
    private List<Income> incomes = new ArrayList<>();
    private final JsonUtils<Income> jsonUtils = new JsonUtils<>("src/main/resources/Incomes.json");

    public IncomeService() {
        try {
            incomes.addAll(jsonUtils.readData(Income[].class));
        } catch (Exception e) {
            System.out.printf("Error fetching incomes data: %s%n", e.getMessage());
        }
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void addIncome(Income income) {
        incomes.add(income);
        try {
            jsonUtils.writeData(incomes); // Sauvegarder dans le fichier JSON
        } catch (Exception e) {
            System.out.println("Error saving income: " + e.getMessage());
        }
    }

    public double getTotalIncome() {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }
}
