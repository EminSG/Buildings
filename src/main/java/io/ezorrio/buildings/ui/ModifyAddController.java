package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.model.Office;
import io.ezorrio.buildings.model.Room;
import io.ezorrio.buildings.model.Special;
import io.ezorrio.buildings.model.Talk;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by golde on 04.04.2017.
 */
public class ModifyAddController extends AbsModifyController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    public void addRoom() {
        Room room = null;
        switch (room_type) {

            case Room.RoomType.OFFICE:
                room = new Office(
                        Double.parseDouble(modify_input_capacity.getText()),
                        modify_input_can_have_fire.isSelected(),
                        Integer.parseInt(modify_input_fire_count.getText()),
                        modify_input_room_name.getText(),
                        modify_input_is_owner.isSelected(),
                        0);
                break;
            case Room.RoomType.SPECIAL:
                room = new Special(
                        Double.parseDouble(modify_input_capacity.getText()),
                        modify_input_can_have_fire.isSelected(),
                        Integer.parseInt(modify_input_fire_count.getText()),
                        Integer.parseInt(modify_special_type.getText()));

                break;
            case Room.RoomType.TALK:
                room = new Talk(
                        Double.parseDouble(modify_input_capacity.getText()),
                        modify_input_can_have_fire.isSelected(),
                        Integer.parseInt(modify_input_fire_count.getText()),
                        canPresentate,
                        isUsed);
                break;
        }
        App.getBuilding().addRoom(room);
        updateList();
    }
}
