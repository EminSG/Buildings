package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.model.Office;
import io.ezorrio.buildings.model.Room;
import io.ezorrio.buildings.model.Special;
import io.ezorrio.buildings.model.Talk;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by golde on 04.04.2017.
 */
public class ModifyChangeController extends AbsModifyController {
    private Room modified_room;
    @FXML
    private Button modify_input_done;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        if (modify_input_room == null) {
            return;
        }
        modify_input_room.textProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            modified_room = App.getBuilding().getRoomById(newValue);
            if (modified_room != null) {
                updateControls();
            }
        }));
        modify_input_done.setOnAction(event -> modifyRoom());
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
        if (modified_room == null) {
            showErrorDialog("Room" + modify_input_room.getId() + " not found");
        }

        modified_room.setCapacity(Double.parseDouble(modify_input_capacity.getText()));
        modified_room.setCanHaveFire(modify_input_can_have_fire.isSelected());
        modified_room.setFireCount(Integer.parseInt(modify_input_fire_count.getText()));
        switch (type) {
            case Room.RoomType.OFFICE:
                ((Office) modified_room).setName(modify_input_room_name.getText());
                ((Office) modified_room).setOwner(modify_input_is_owner.isSelected());
                break;
            case Room.RoomType.TALK:
                ((Talk) modified_room).setCanPresentate(modify_input_can_presentate.isSelected());
                ((Talk) modified_room).setUsed(modify_input_is_used.isSelected());
            case Room.RoomType.SPECIAL:
                ((Special) modified_room).setType(Integer.parseInt(modify_special_type.getText()));
                break;
        }
        updateLists();
    }
}
