package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.search.Searcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;

import static io.ezorrio.buildings.preferences.Preferences.setSearchCriteria;

/**
 * Created by golde on 05.04.2017.
 */
public class BuildingController extends Controller {

    @FXML
    private MenuButton sort_criteria_menu;

    @FXML
    private void setByCapacity(ActionEvent event) {
        setSearchCriteria(Searcher.BuildingShowCriteria.BY_CAPACITY);
        sort_criteria_menu.setText("By capacity");
    }

    @FXML
    private void setByRoomNumber(ActionEvent event) {
        setSearchCriteria(Searcher.BuildingShowCriteria.BY_ROOM_NUMBER);
        sort_criteria_menu.setText("By room number");
    }

    @FXML
    private void setByFireCount(ActionEvent event) {
        setSearchCriteria(Searcher.BuildingShowCriteria.BY_FIRE_SENSOR);
        sort_criteria_menu.setText("By fire sensors");
    }
}
