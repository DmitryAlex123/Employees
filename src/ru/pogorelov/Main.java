package ru.pogorelov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.pogorelov.connector.MAIN_CONNECT_DATA;

public class Main extends Application {

    private static Stage primaryStage;

    private void setStage(Stage stage) {
        Main.primaryStage = stage;
    }

    public static Stage getStage() {
        return Main.primaryStage;
    }


    public void start(Stage primaryStage) throws Exception {
        setStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ru/pogorelov/view/first_window.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }



    public static void main(String[] args) {
        MAIN_CONNECT_DATA.setPoolSettings();
        launch(args);
    }

}
