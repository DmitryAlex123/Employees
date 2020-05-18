package ru.pogorelov.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.pogorelov.Main;
import ru.pogorelov.connector.personals_database_connection;
import ru.pogorelov.connector.position_database_connection;
import ru.pogorelov.model.P_D_join_data;
import ru.pogorelov.model.position_data;

import java.net.URL;
import java.util.ResourceBundle;

public class position_controller implements Initializable {

    static ObservableList<position_data> position_list;

    @FXML
    TableView position_table;

    @FXML
    TableColumn id;

    @FXML
    TableColumn name;

    @FXML
    TableColumn profit;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        first_window_controller.getPositionStage().setOnShown(event -> {getCurrentPosition(); System.out.println("Обновлено!");});
    }

    public void getCurrentPosition(){
        position_list = position_database_connection.getPositionData();
        id.setCellValueFactory(new PropertyValueFactory<position_data, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<position_data, String>("name"));
        profit.setCellValueFactory(new PropertyValueFactory<position_data, Integer>("profit"));
        position_table.setItems(position_list);
    }

    private static Stage addNewPositionStage;

    private void setAddNewPositionStage(Stage stage) {
        position_controller.addNewPositionStage = stage;
    }

    public static Stage getAddNewPositionStage() {
        return position_controller.addNewPositionStage;
    }

    @FXML
    public void addNewPositionAction() throws Exception{
        first_window_controller.getPositionStage().hide();
        setAddNewPositionStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/position_fxml/addNewPosition.fxml"));
        Scene scene = new Scene(root);
        addNewPositionStage.setScene(scene);
        addNewPositionStage.initStyle(StageStyle.UNDECORATED);
        addNewPositionStage.show();
    }

    private static Stage changePositionStage;

    private void setChangePositionStage(Stage stage) {
        position_controller.changePositionStage = stage;
    }

    public static Stage getChangePositionStage() {
        return position_controller.changePositionStage;
    }

    @FXML
    public void changePositionAction() throws Exception{
        setPosition_object();
        first_window_controller.getPositionStage().hide();
        setChangePositionStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/position_fxml/changePosition.fxml"));
        Scene scene = new Scene(root);
        changePositionStage.setScene(scene);
        changePositionStage.initStyle(StageStyle.UNDECORATED);
        changePositionStage.show();
    }

    private static position_data position_id;

    private void setPosition_object(){
        position_id = (position_data) position_table.getSelectionModel().getSelectedItem();
    }
    public static position_data getPosition_object(){
        return position_id;
    }

    @FXML
    public void deletePositionAction(){
        setPosition_object();
        position_database_connection.deletePosition(getPosition_object().getId());
        getCurrentPosition();
    }

    @FXML
    public void exit(){
        first_window_controller.getPositionStage().close();
        Main.getStage().show();
    }

}
