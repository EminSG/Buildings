package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.search.Searcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static io.ezorrio.buildings.preferences.Preferences.*;

/**
 * Created by golde on 04.04.2017.
 */
public class SearchController extends Controller {
    @FXML
    private MenuButton search_criteria_menu;
    @FXML
    private MenuButton search_criteria_compare;
    @FXML
    private ListView search_results;
    @FXML
    private TextField search_value;
    @FXML
    private Button button_search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        initSearchTab();
    }

    private void initSearchTab() {
        button_search.setOnAction(event -> {
            if (isUnknown(getSearchCriteria()) || isUnknown(getSearchMoreOrLess()) || search_value.getText().isEmpty()) {
                return;
            }
            ArrayList<String> result = getSearchResults(search_value.getText());
            search_results.setItems(FXCollections.observableArrayList(result));
            search_results.refresh();
        });
    }

    private ArrayList<String> getSearchResults(String value) {
        Searcher searcher = new Searcher(App.getBuilding());
        return searcher.filterRooms(getSearchCriteria(), getSearchMoreOrLess(), value, isNeedExtendedInfo());
    }

    @FXML
    private void setByCapacity(ActionEvent event) {
        setSearchCriteria(Searcher.BuildingShowCriteria.BY_CAPACITY);
        search_criteria_menu.setText("By capacity");
    }

    @FXML
    private void setByRoomNumber(ActionEvent event) {
        setSearchCriteria(Searcher.BuildingShowCriteria.BY_ROOM_NUMBER);
        search_criteria_menu.setText("By room number");
    }

    @FXML
    private void setByFireCount(ActionEvent event) {
        setSearchCriteria(Searcher.BuildingShowCriteria.BY_FIRE_SENSOR);
        search_criteria_menu.setText("By fire sensors");
    }

    @FXML
    private void setSearchMore(ActionEvent event) {
        setSearchMoreOrLess(Searcher.FilterType.MORE);
        search_criteria_compare.setText("More than value");
    }

    @FXML
    private void setSearchLess(ActionEvent event) {
        setSearchMoreOrLess(Searcher.FilterType.LESS);
        search_criteria_compare.setText("Less than value");
    }

    @FXML
    private void setSearchEqual(ActionEvent event) {
        setSearchMoreOrLess(Searcher.FilterType.EQUAL);
        search_criteria_compare.setText("Equals this value");
    }
}
