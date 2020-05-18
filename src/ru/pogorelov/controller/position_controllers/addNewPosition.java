package ru.pogorelov.controller.position_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import ru.pogorelov.connector.position_database_connection;
import ru.pogorelov.controller.first_window_controller;
import ru.pogorelov.controller.position_controller;

import java.net.URL;
import java.util.ResourceBundle;

public class addNewPosition implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    TextField name_tf;

    @FXML
    TextField profit_tf;


    @FXML
    public void addNewPosition(){
        position_database_connection.insertNewPosition(name_tf.getText(), Integer.parseInt(profit_tf.getText()));
        position_controller.getAddNewPositionStage().close();
        first_window_controller.getPositionStage().show();
    }

    @FXML
    public void exit(){
        position_controller.getAddNewPositionStage().close();
        first_window_controller.getPositionStage().show();
    }

}
