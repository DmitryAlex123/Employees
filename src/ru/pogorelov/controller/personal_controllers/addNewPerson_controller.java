package ru.pogorelov.controller.personal_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import ru.pogorelov.connector.personals_database_connection;
import ru.pogorelov.controller.first_window_controller;
import ru.pogorelov.controller.personal_controller;

import java.net.URL;
import java.util.ResourceBundle;

public class addNewPerson_controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    TextField fio_tf;

    @FXML
    public void applyAction(){
        personals_database_connection.insertNewPerson(fio_tf.getText());
        personal_controller.getNewPersonStage().close();
        first_window_controller.getPersonalStage().show();
    }

    @FXML
    public void exit(){
        personal_controller.getNewPersonStage().close();
        first_window_controller.getPersonalStage().show();
    }
}
