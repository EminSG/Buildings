package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.search.Searcher;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static io.ezorrio.buildings.preferences.Preferences.getSortCriteria;
import static io.ezorrio.buildings.preferences.Preferences.isNeedExtendedInfo;

/**
 * Created by golde on 03.04.2017.
 */
public abstract class Controller implements Initializable {
    @FXML
    private ListView building_rooms;
    @FXML
    private ListView building_levels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateLists();
    }

    protected void updateLists() {
        Searcher searcher = new Searcher(App.getBuilding());
        ArrayList<String> rooms = searcher.sortRooms(getSortCriteria(), isNeedExtendedInfo());
        if (building_rooms == null){
            return;
        }
        building_rooms.setItems(FXCollections.observableArrayList(rooms));
        building_levels.setItems(FXCollections.observableArrayList(App.getBuilding().getLevels()));
        building_rooms.refresh();
    }
}
