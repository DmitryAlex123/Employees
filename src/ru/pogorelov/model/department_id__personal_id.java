package ru.pogorelov.model;

public class department_id__personal_id {
    private int department_id;
    private int personals_id;

    public department_id__personal_id(int department_id, int personals_id) {
        this.department_id = department_id;
        this.personals_id = personals_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public int getPersonals_id() {
        return personals_id;
    }
}
