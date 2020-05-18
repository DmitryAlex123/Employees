package ru.pogorelov.controller.department_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import ru.pogorelov.connector.department_database_connection;
import ru.pogorelov.controller.department_controller;
import ru.pogorelov.controller.first_window_controller;

import java.net.URL;
import java.util.ResourceBundle;

public class change_department implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_current_department = department_controller.get_selected_item().getId();
        name_tf.setText(department_controller.get_selected_item().getName());
        phone_tf.setText(department_controller.get_selected_item().getPhone());
        email_tf.setText(department_controller.get_selected_item().getEmail());
    }

    @FXML
    TextField name_tf;

    @FXML
    TextField phone_tf;

    @FXML
    TextField email_tf;

    private int id_current_department;

    @FXML
    public void changeAction(){
        department_database_connection.update_department(id_current_department, name_tf.getText(), phone_tf.getText(),email_tf.getText());
        department_controller.getChangeStage().close();
        first_window_controller.getStage().show();
    }

    @FXML
    public void exit(){
        department_controller.getChangeStage().close();
        first_window_controller.getStage().show();
    }
}
