package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.model.Building;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by golde on 30.03.2017.
 */
public class App extends Application {

    private static Building building;

    public static void main(String[] args) {
        building = new Building();
        launch(args);
    }

    public static Building getBuilding() {
        return building;
    }

    public static void setBuilding(Building value) {
        building = value;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));

        Scene scene = new Scene(root, 1200, 600);

        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
