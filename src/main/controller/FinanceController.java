package controller;

import entities.Expense;
import entities.Income;
import service.ExpenseService;
import service.IncomeService;

import java.util.List;
import java.util.Scanner;

public class FinanceController {
    private final ExpenseService expenseService = new ExpenseService();
    private final IncomeService incomeService = new IncomeService();

    public void manageFinance(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Gestion des Finances ===");
            System.out.println("1. Ajouter une Dépense");
            System.out.println("2. Ajouter un Revenu");
            System.out.println("3. Afficher les Dépenses");
            System.out.println("4. Afficher les Revenus");
            System.out.println("0. Retour");
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne

            switch (choice) {
                case 1 -> addExpense(scanner);
                case 2 -> addIncome(scanner);
                case 3 -> displayExpenses();
                case 4 -> displayIncomes();
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide. Essayez encore.");
            }
        } while (choice != 0);
    }

    private void displayExpenses() {
        List<Expense> expenses = expenseService.getExpenses();
        System.out.println("=== Dépenses ===");
        for (Expense expense : expenses) {
            System.out.println("ID: " + expense.getId());
            System.out.println("Description: " + expense.getDescription());
            System.out.println("Montant: " + expense.getAmount() + " DT");
            System.out.println("Catégorie: " + expense.getCategory());
            System.out.println("Date: " + expense.getDate());
            System.out.println("----------------------");
        }
        System.out.println("Total Dépenses: " + expenseService.getTotalExpenses() + " DT");
    }

    private void displayIncomes() {
        List<Income> incomes = incomeService.getIncomes();
        System.out.println("=== Revenus ===");
        for (Income income : incomes) {
            System.out.println("ID: " + income.getId());
            System.out.println("Source: " + income.getSource());
            System.out.println("Montant: " + income.getAmount() + " DT");
            System.out.println("Date: " + income.getDate());
            System.out.println("----------------------");
        }
        System.out.println("Total Revenus: " + incomeService.getTotalIncome() + " DT");
    }

    private void addExpense(Scanner scanner) {
        System.out.println("Ajouter une Dépense");
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Montant: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consommer le saut de ligne
        System.out.print("Catégorie: ");
        String category = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Expense expense = new Expense("E" + (expenseService.getExpenses().size() + 1), description, amount, category, date);
        expenseService.addExpense(expense);
        System.out.println("Dépense ajoutée avec succès!");
    }

    private void addIncome(Scanner scanner) {
        System.out.println("Ajouter un Revenu");
        System.out.print("Source: ");
        String source = scanner.nextLine();
        System.out.print("Montant: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consommer le saut de ligne
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Income income = new Income("I" + (incomeService.getIncomes().size() + 1), source, amount, date);
        incomeService.addIncome(income);
        System.out.println("Revenu ajouté avec succès!");
    }
}
