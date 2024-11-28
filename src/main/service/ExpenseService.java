package service;

import entities.Expense;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private List<Expense> expenses = new ArrayList<>();
    private final JsonUtils<Expense> jsonUtils = new JsonUtils<>("src/main/resources/Expenses.json");

    public ExpenseService() {
        try {
            expenses.addAll(jsonUtils.readData(Expense[].class));
        } catch (Exception e) {
            System.out.printf("Error fetching expenses data: %s%n", e.getMessage());
        }
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        try {
            jsonUtils.writeData(expenses); // Sauvegarder dans le fichier JSON
        } catch (Exception e) {
            System.out.println("Error saving expense: " + e.getMessage());
        }
    }

    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }
}
