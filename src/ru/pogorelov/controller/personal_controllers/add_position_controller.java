package ru.pogorelov.controller.personal_controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ru.pogorelov.Main;
import ru.pogorelov.connector.personal__position_connection;
import ru.pogorelov.controller.first_window_controller;
import ru.pogorelov.controller.personal_controller;
import ru.pogorelov.model.department_data;
import ru.pogorelov.model.position_data;
import ru.pogorelov.connector.position_database_connection;

import java.net.URL;
import java.util.ResourceBundle;

public class add_position_controller implements Initializable {

    static ObservableList<position_data> position_list;
    int personal_id;

    @FXML
    Label fio;

    @FXML
    ComboBox combobox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        position_list = position_database_connection.getPositionData();
        combobox.setItems(position_list);
        fio.setText(personal_controller.getPersonal_name());
        personal_id = personal_controller.getPersonal_id();
    }

    public void applyAction(){
        position_data position = (position_data) combobox.getSelectionModel().getSelectedItem();
        int position_id = position.getId();
        personal__position_connection.setPersonal_Position_to_DB(personal_id, position_id);

        personal_controller.getPositStage().close();
        first_window_controller.getPersonalStage().show();
    }

    @FXML
    public void exit(){
        personal_controller.getPositStage().close();
        first_window_controller.getPersonalStage().show();
    }

}
