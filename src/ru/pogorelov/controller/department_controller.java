package ru.pogorelov.controller;

import com.sun.javafx.stage.WindowCloseRequestHandler;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import ru.pogorelov.Main;
import ru.pogorelov.model.department_data;
import ru.pogorelov.connector.department_database_connection;
import sun.plugin2.ipc.windows.WindowsEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class department_controller implements Initializable {

    static ObservableList<department_data> departments_list;

    public static int getIdForNewDepartment(){
        return department_database_connection.getMaxID() + 1;

    }

    static department_data selected_object;

    private void setSelected_Object(){
        selected_object = (department_data) departments_table.getSelectionModel().getSelectedItem();

    }

    public static department_data get_selected_item(){
        return selected_object;
    }


    public void getCurrentDepartments(){
        departments_list = department_database_connection.getDepartmentData();
        id.setCellValueFactory(new PropertyValueFactory<department_data, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<department_data, String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<department_data, String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<department_data, String>("email"));
        departments_table.setItems(departments_list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        first_window_controller.getStage().setOnShown(event -> {getCurrentDepartments(); System.out.println("Обновлено!");});
    }

    @FXML
    public void exit(){
        first_window_controller.getStage().close();
        Main.getStage().show();
    }

    @FXML
    TableView departments_table;

    @FXML
    TableColumn id;

    @FXML
    TableColumn name;

    @FXML
    TableColumn phone;

    @FXML
    TableColumn email;


    private static Stage add_department_stage;

    private void setAddDepartmentStage(Stage stage) {
        department_controller.add_department_stage = stage;
    }

    public static Stage getAddDepartmentStage() {
        return department_controller.add_department_stage;
    }


    @FXML
    public void addDepartmentAction() throws Exception{
        first_window_controller.getStage().hide();
        setAddDepartmentStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/department_fxml/add_department.fxml"));
        Scene scene = new Scene(root);
        add_department_stage.setScene(scene);
        add_department_stage.initStyle(StageStyle.UNDECORATED);
        add_department_stage.show();
    }

    private static Stage change_department_stage;

    private void setChangeStage(Stage stage) {
        department_controller.change_department_stage = stage;
    }

    public static Stage getChangeStage() {
        return department_controller.change_department_stage;
    }

    @FXML
    public void changeDepartmentAction() throws Exception{
        setSelected_Object();
        first_window_controller.getStage().hide();
        setChangeStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/department_fxml/change_department.fxml"));
        Scene scene = new Scene(root);
        change_department_stage.setScene(scene);
        change_department_stage.initStyle(StageStyle.UNDECORATED);
        change_department_stage.show();


    }

    @FXML
    public void deleteDepartmentAction(){
        department_data dd = (department_data)departments_table.getSelectionModel().getSelectedItem();
        int n = dd.getId();
        department_database_connection.deleteDepartment(n);
        getCurrentDepartments();
        System.out.println("Обновлено!");

    }


}
