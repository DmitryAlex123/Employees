package ru.pogorelov.model;

public class P_D_join_data {
    private int id; //id персонала/отдела
    private String name; //имя персонала
    private String name_department; //название отдела
    private String name_position; //поназвание позиции
    private int profit; //доход

    public P_D_join_data(int id, String name, String name_department, String name_position) {
        this.id = id;
        this.name = name;
        this.name_department = name_department;
        this.name_position = name_position;
    }

    public P_D_join_data(int id, String name_department, String name , String name_position, int profit) {
        this.id = id;
        this.name_department = name_department;
        this.name = name;
        this.name_position = name_position;
        this.profit = profit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getName_department() {
        return name_department;
    }
    public String getName_position(){
        return name_position;
    }

    public int getProfit(){
        return profit;
    }

}
