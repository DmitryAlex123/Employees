package ru.pogorelov.model;

public class position_data {
    private int id;
    private String name;
    private int profit;

    public position_data(int id, String name, int profit) {
        this.id = id;
        this.name = name;
        this.profit = profit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public String toString(){
        return id + " " + name;
    }
}
