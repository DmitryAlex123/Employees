package ru.pogorelov.controller.department_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import ru.pogorelov.controller.department_controller;
import ru.pogorelov.controller.first_window_controller;
import ru.pogorelov.model.department_data;
import ru.pogorelov.connector.department_database_connection;
import java.net.URL;
import java.util.ResourceBundle;

public class add_department implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    TextField nameTF;
    @FXML
    TextField phoneTF;
    @FXML
    TextField emailTF;

    @FXML
    private void addNewDepartmentButtonAction(){
        department_data add_department_object = new department_data(nameTF.getText(), phoneTF.getText(), emailTF.getText());
        department_database_connection.addDepartment(add_department_object);
        department_controller.getAddDepartmentStage().close();
        first_window_controller.getStage().show();
    }

    @FXML
    private void exit(){
        department_controller.getAddDepartmentStage().close();
        first_window_controller.getStage().show();
    }

}
