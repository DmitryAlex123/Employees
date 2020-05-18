package ru.pogorelov.controller.personal_controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ru.pogorelov.Main;
import ru.pogorelov.connector.department_database_connection;
import ru.pogorelov.controller.first_window_controller;
import ru.pogorelov.controller.personal_controller;
import ru.pogorelov.model.department_data;
import ru.pogorelov.connector.personal__department_database_connection;

import java.net.URL;
import java.util.ResourceBundle;

public class setDepartmentToPersonalController implements Initializable {

     static ObservableList<department_data> departments_list;
     static int selectedTableItem;
     static String FIO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departments_list = department_database_connection.getDepartmentData();
        combobox.setItems(departments_list);
        selectedTableItem = personal_controller.getPersonal_id();
        FIO = personal_controller.getPersonal_name();
        fio.setText(FIO);
    }

    @FXML
    ComboBox combobox;

    @FXML
    Label fio;


    @FXML
    public void applyAction(){
        department_data department = (department_data) combobox.getSelectionModel().getSelectedItem();

        personal__department_database_connection.setPersonal_Department(selectedTableItem, department.getId());
        personal_controller.getP_DStage().close();
        first_window_controller.getPersonalStage().show();

    }




    @FXML
    public void exit(){
        personal_controller.getP_DStage().close();
        first_window_controller.getPersonalStage().show();
    }
}
