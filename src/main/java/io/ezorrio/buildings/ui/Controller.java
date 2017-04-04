package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.db.DBHelper;
import io.ezorrio.buildings.model.Building;
import io.ezorrio.buildings.search.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringJoiner;

/**
 * Created by golde on 03.04.2017.
 */
public class Controller implements Initializable {
    private Building building;
    @FXML
    private ListView building_rooms;
    @FXML
    private MenuButton search_criteria_menu;
    @FXML
    private MenuButton search_criteria_compare;
    @FXML
    private ListView search_results;
    @FXML
    private TextField search_value;
    @FXML
    private MenuItem menu_save;
    @FXML
    private MenuItem menu_import;
    @FXML
    private CheckMenuItem menu_show_extended;

    @FXML
    private Button button_search;

    private static final int UNKNOWN_VALUE = 100;
    private int searchCriteria = UNKNOWN_VALUE;
    private int searchMoreOrLess = UNKNOWN_VALUE;

    private String importPath = DBHelper.DEFAULT_DB_PATH;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        building = new Building();


        menu_save.setOnAction(event -> building.save());

        menu_import.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("Import DB path");
            dialog.setTitle("Import path");
            dialog.setContentText("Enter path to DB");
            dialog.getEditor().setText(importPath);
            dialog.show();
            dialog.setOnCloseRequest(event1 -> {
                importPath = dialog.getEditor().getText();
                DBHelper helper = new DBHelper(importPath);
                try {
                    building = helper.importFromDB();
                    building_rooms.setItems(FXCollections.observableArrayList(building.getRooms()));
                    building_rooms.refresh();
                    search_results.getItems().clear();
                    search_results.refresh();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
        button_search.setOnAction(event -> {
            if (searchCriteria == UNKNOWN_VALUE || searchMoreOrLess == UNKNOWN_VALUE){
                return;
            }
            Searcher searcher = new Searcher(building);
            ArrayList<String> result = searcher.filterRooms(searchCriteria,
                    searchMoreOrLess,
                    search_value.getText(),
                    menu_show_extended.isSelected());
            search_results.setItems(FXCollections.observableArrayList(result));
            search_results.refresh();
        });

    }

    @FXML
    private void setByCapacity(ActionEvent event){
        searchCriteria = Searcher.BuildingShowCriteria.BY_CAPACITY;
        search_criteria_menu.setText("By capacity");
    }
    @FXML
    private void setByRoomNumber(ActionEvent event){
        searchCriteria = Searcher.BuildingShowCriteria.BY_ROOM_NUMBER;
        search_criteria_menu.setText("By room number");
    }
    @FXML
    private void setByFireCount(ActionEvent event){
        searchCriteria = Searcher.BuildingShowCriteria.BY_FIRE_SENSOR;
        search_criteria_menu.setText("By fire sensors");
    }
    @FXML
    private void setSearchMore(ActionEvent event){
        searchMoreOrLess = Searcher.FilterType.MORE;
        search_criteria_compare.setText("More than value");
    }
    @FXML
    private void setSearchLess(ActionEvent event){
        searchMoreOrLess = Searcher.FilterType.LESS;
        search_criteria_compare.setText("Less than value");
    }
    @FXML
    private void setSearchEqual(ActionEvent event){
        searchMoreOrLess = Searcher.FilterType.EQUAL;
        search_criteria_compare.setText("Equals value");
    }

}
