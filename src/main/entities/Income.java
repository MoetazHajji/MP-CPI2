package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Income {
    private String id;
    private String source; // Source du revenu (ex : consultation, examen radiologique)
    private double amount;
    private String date;

    public Income(@JsonProperty("id") String id,
                  @JsonProperty("source") String source,
                  @JsonProperty("amount") double amount,
                  @JsonProperty("date") String date) {
        this.id = id;
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
