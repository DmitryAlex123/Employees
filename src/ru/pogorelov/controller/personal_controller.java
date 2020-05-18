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
import ru.pogorelov.connector.department_database_connection;
import ru.pogorelov.model.P_D_join_data;
import ru.pogorelov.model.department_data;
import ru.pogorelov.model.personal_data;

import java.net.URL;
import java.util.ResourceBundle;

public class personal_controller implements Initializable {

    static ObservableList<P_D_join_data> p_d_list;

    @FXML
    TableView personals_table;

    @FXML
    TableColumn id;

    @FXML
    TableColumn name;

    @FXML
    TableColumn department_;

    @FXML
    TableColumn position;


    public void getCurrentDepartments(){

        p_d_list = personals_database_connection.getJOIN__P_D();
        id.setCellValueFactory(new PropertyValueFactory<P_D_join_data, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<P_D_join_data, String>("name"));
        department_.setCellValueFactory(new PropertyValueFactory<P_D_join_data, String>("name_department"));
        position.setCellValueFactory(new PropertyValueFactory<P_D_join_data, String>("name_position"));
        personals_table.setItems(p_d_list);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        first_window_controller.getPersonalStage().setOnShown(event -> {getCurrentDepartments(); System.out.println("Обновлено!");});
    }

    private static Stage NewPersonStage;

    private void setNewPersonStage(Stage stage) {
        personal_controller.NewPersonStage = stage;
    }

    public static Stage getNewPersonStage() {
        return personal_controller.NewPersonStage;
    }


    @FXML
    public void addPersonAction() throws Exception{
        first_window_controller.getPersonalStage().hide();
        setNewPersonStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/personal_fxml/addNewPerson.fxml"));
        Scene scene = new Scene(root);
        NewPersonStage.setScene(scene);
        NewPersonStage.initStyle(StageStyle.UNDECORATED);
        NewPersonStage.show();
    }

    @FXML
    public void deletePersonAction(){
        P_D_join_data pd = (P_D_join_data) personals_table.getSelectionModel().getSelectedItem();
        personals_database_connection.deletePersonal(pd.getId());
        getCurrentDepartments();
    }

    private static Stage ChangePersonStage;

    private void setChangePersonStage(Stage stage) {
        personal_controller.ChangePersonStage = stage;
    }

    public static Stage getChangePersonStage() {
        return personal_controller.ChangePersonStage;
    }

    @FXML
    public void changePersonAction() throws Exception{
        getSelectedItemFromTable();
        first_window_controller.getPersonalStage().hide();
        setChangePersonStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/personal_fxml/changeName.fxml"));
        Scene scene = new Scene(root);
        ChangePersonStage.setScene(scene);
        ChangePersonStage.initStyle(StageStyle.UNDECORATED);
        ChangePersonStage.show();
    }


    private static Stage P_D_stage;

    private void setP_DStage(Stage stage) {
        personal_controller.P_D_stage = stage;
    }

    public static Stage getP_DStage() {
        return personal_controller.P_D_stage;
    }

    @FXML
    public void setDepartmentAction() throws Exception{
        getSelectedItemFromTable();

        first_window_controller.getPersonalStage().hide();
        setP_DStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/personal_fxml/setDepartmentToPersonal.fxml"));
        Scene scene = new Scene(root);
        P_D_stage.setScene(scene);
        P_D_stage.initStyle(StageStyle.UNDECORATED);
        P_D_stage.show();
    }

    private static int personal_id;
    private static String personal_name;

    public static int getPersonal_id(){
        return personal_id;
    }

    public static String getPersonal_name(){
        return personal_name;
    }

    public void getSelectedItemFromTable(){
        P_D_join_data pd = (P_D_join_data) personals_table.getSelectionModel().getSelectedItem();
        personal_id = pd.getId();
        personal_name = pd.getName();
    }


    @FXML
    public void exit(){
        first_window_controller.getPersonalStage().close();
        Main.getStage().show();
    }

    private static Stage settingPositionStage;

    private void setPositStage(Stage stage) {
        personal_controller.settingPositionStage = stage;
    }

    public static Stage getPositStage() {
        return personal_controller.settingPositionStage;
    }


    @FXML
    public void setPositionAction() throws Exception{
        getSelectedItemFromTable();

        first_window_controller.getPersonalStage().hide();
        setPositStage(new Stage());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/personal_fxml/addPosition.fxml"));
        Scene scene = new Scene(root);
        settingPositionStage.setScene(scene);
        settingPositionStage.initStyle(StageStyle.UNDECORATED);
        settingPositionStage.show();
    }


}
