package ru.pogorelov.controller.position_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import ru.pogorelov.connector.position_database_connection;
import ru.pogorelov.controller.first_window_controller;
import ru.pogorelov.controller.position_controller;

import java.net.URL;
import java.util.ResourceBundle;

public class changePosition implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = position_controller.getPosition_object().getId();
        name_tf.setText(position_controller.getPosition_object().getName());
        String profit = String.valueOf(position_controller.getPosition_object().getProfit());
        profit_tf.setText(profit);

    }

    @FXML
    TextField name_tf;

    @FXML
    TextField profit_tf;

    private int id;
    public int getId(){
        return id;
    }

    @FXML
    public void addNewPosition(){
        position_database_connection.updateField(getId(), name_tf.getText(), Integer.parseInt(profit_tf.getText()));
        position_controller.getChangePositionStage().close();
        first_window_controller.getPositionStage().show();
    }

    @FXML
    public void exit(){
        position_controller.getChangePositionStage().close();
        first_window_controller.getPositionStage().show();
    }
}
