package ru.pogorelov.model;

public class personal_data {
    private int id;
    private String name;


    public personal_data(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
