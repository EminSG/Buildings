package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.db.DBHelper;
import io.ezorrio.buildings.model.Building;
import io.ezorrio.buildings.model.Level;
import io.ezorrio.buildings.model.Office;
import io.ezorrio.buildings.model.Special;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by golde on 30.03.2017.
 */
public class App extends Application {

    public static void main(String[] args) {
        Building building = new Building();
        building.addLevel(new Level(100));
        building.addRoom(new Office(30, true, 12, "LOL", true, 0));
        building.addRoom(new Office(30, true, 12, "LOL", true, 0));
        building.addRoom(new Office(40, true, 12, "LOL", true, 0));
        building.addLevel(new Level(100));
        building.addRoom(new Special(30, true, 0, Special.SERVER));
        building.addRoom(new Special(30, true, 12, Special.SERVER));
        building.addRoom(new Special(40, true, 12, Special.SERVER));
        building.save();
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));

        Scene scene = new Scene(root, 300, 275);

        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        //primaryStage.show();
    }
}
