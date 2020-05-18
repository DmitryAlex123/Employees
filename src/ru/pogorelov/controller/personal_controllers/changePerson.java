package ru.pogorelov.controller.personal_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import ru.pogorelov.connector.personals_database_connection;
import ru.pogorelov.controller.first_window_controller;
import ru.pogorelov.controller.personal_controller;

import java.net.URL;
import java.util.ResourceBundle;

public class changePerson implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fio_tf.setText(personal_controller.getPersonal_name());
        id = personal_controller.getPersonal_id();
    }

    @FXML
    TextField fio_tf;

    int id;

    @FXML
    public void applyChanges(){
        personals_database_connection.updateField(id, fio_tf.getText());
        personal_controller.getChangePersonStage().close();
        first_window_controller.getPersonalStage().show();
    }

    @FXML
    public void exit(){
        personal_controller.getChangePersonStage().close();
        first_window_controller.getPersonalStage().show();
    }
}
