package ru.pogorelov.model;
import ru.pogorelov.controller.department_controller;
public class department_data {

    private int id;
    private String name;
    private String phone;
    private String email;

    public department_data(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public department_data(int id, String name, String phone, String email){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
       return id + " " + name;
    }

}
