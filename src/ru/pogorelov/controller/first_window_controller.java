package ru.pogorelov.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.pogorelov.Main;
import ru.pogorelov.connector.personals_database_connection;
import ru.pogorelov.model.P_D_join_data;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import ru.pogorelov.connector.first_window_database_connection;
import ru.pogorelov.model.department_data;
import ru.pogorelov.connector.department_database_connection;

public class first_window_controller implements Initializable {

    static ObservableList<P_D_join_data> start_list;
    static ObservableList<department_data> combobox_list;

    public void initialize(URL location, ResourceBundle resources) {
        Main.getStage().setOnShown(event -> {getCurrentData(); System.out.println("Обновлено!");});

        combobox_list = department_database_connection.getDepartmentData();
        combobox123.setItems(combobox_list);


    }

    @FXML
    ComboBox combobox123;

    @FXML
    TableView table;

    @FXML
    TableColumn department;

    @FXML
    TableColumn name;

    @FXML
    TableColumn position;

    @FXML
    TableColumn profit;




    @FXML
    public void showAction(){
        department_data d = (department_data) combobox123.getSelectionModel().getSelectedItem();
        int id = d.getId();
        start_list = first_window_database_connection.getSortedData(id);
        department.setCellValueFactory(new PropertyValueFactory<P_D_join_data, Integer>("name_department"));
        name.setCellValueFactory(new PropertyValueFactory<P_D_join_data, String>("name"));
        position.setCellValueFactory(new PropertyValueFactory<P_D_join_data, String>("name_position"));
        profit.setCellValueFactory(new PropertyValueFactory<P_D_join_data, Integer>("profit"));
        table.setItems(start_list);
    }



    public void getCurrentData(){
        start_list = first_window_database_connection.getData();
        department.setCellValueFactory(new PropertyValueFactory<P_D_join_data, Integer>("name_department"));
        name.setCellValueFactory(new PropertyValueFactory<P_D_join_data, String>("name"));
        position.setCellValueFactory(new PropertyValueFactory<P_D_join_data, String>("name_position"));
        profit.setCellValueFactory(new PropertyValueFactory<P_D_join_data, Integer>("profit"));
        table.setItems(start_list);
    }

    private static Stage department_stage;

    private void setStage(Stage stage) {
        first_window_controller.department_stage = stage;
    }

    public static Stage getStage() {
        return first_window_controller.department_stage;
    }

    @FXML
    public void go_to_department_action() throws Exception{
        Main.getStage().hide();
        Stage department_stage = new Stage();
        setStage(department_stage);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/department.fxml"));
        Scene scene = new Scene(root);
        department_stage.setScene(scene);
        department_stage.initStyle(StageStyle.UNDECORATED);
        department_stage.show();
    }




    private static Stage personal_stage;

    private void setPersonalStage(Stage stage) {
        first_window_controller.personal_stage = stage;
    }

    public static Stage getPersonalStage() {
        return first_window_controller.personal_stage;
    }

    @FXML
    public void go_to_personal_action()throws Exception{
        Main.getStage().hide();
        setPersonalStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/personal.fxml"));
        Scene scene = new Scene(root);
        personal_stage.setScene(scene);
        personal_stage.initStyle(StageStyle.UNDECORATED);
        personal_stage.show();
    }



    private static Stage position_stage;

    private void setPositionStage(Stage stage) {
        first_window_controller.position_stage = stage;
    }

    public static Stage getPositionStage() {
        return first_window_controller.position_stage;
    }

    @FXML
    public void go_to_position_action()throws Exception{
        Main.getStage().hide();
        setPositionStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/position.fxml"));
        Scene scene = new Scene(root);
        position_stage.setScene(scene);
        position_stage.initStyle(StageStyle.UNDECORATED);
        position_stage.show();
    }


    @FXML
    public void exit(){
        Platform.exit();
    }

}
