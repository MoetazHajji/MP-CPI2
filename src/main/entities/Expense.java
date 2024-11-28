package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Expense {
    private String id;
    private String description;
    private double amount;
    private String category;
    private String date;

    public Expense(@JsonProperty("id") String id,
                   @JsonProperty("description") String description,
                   @JsonProperty("amount") double amount,
                   @JsonProperty("category") String category,
                   @JsonProperty("date") String date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
