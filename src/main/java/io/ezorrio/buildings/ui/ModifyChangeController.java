package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.model.Office;
import io.ezorrio.buildings.model.Room;
import io.ezorrio.buildings.model.Special;
import io.ezorrio.buildings.model.Talk;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by golde on 04.04.2017.
 */
public class ModifyChangeController extends AbsModifyController {
    private Object modified_room;
    @FXML
    private Spinner modify_input_room;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        modify_input_room.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory(FXCollections.observableArrayList(App.getBuilding().getRooms())));
        modify_input_room.valueProperty().addListener((observable, oldValue, newValue) -> {
            modified_room = newValue;
            updateControls();
        });
    }

    private void updateControls() {
        if (modified_room instanceof Office) {
            setTypeOffice();
            return;
        }
        if (modified_room instanceof Talk) {
            setTypeTalk();
            return;
        }
        if (modified_room instanceof Special) {
            setTypeSpecial();
            return;
        }
    }

    public void modifyRoom() {
        switch (room_type) {
            case Room.RoomType.OFFICE:
                Office room = new Office(
                        Double.parseDouble(modify_input_capacity.getText()),
                        modify_input_can_have_fire.isSelected(),
                        Integer.parseInt(modify_input_fire_count.getText()),
                        modify_input_room_name.getText(),
                        modify_input_is_owner.isSelected(),
                        0);
                App.getBuilding().addRoom(room);
                updateList();
        }
    }
}
