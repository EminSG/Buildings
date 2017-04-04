package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.model.*;

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
        switch (type) {
            case TYPE_LEVEL:
                Level level = new Level(Double.parseDouble(modify_input_capacity.getText()));
                App.getBuilding().addLevel(level);
                break;
            case Room.RoomType.OFFICE:
                Room room = new Office(
                        Double.parseDouble(modify_input_capacity.getText()),
                        modify_input_can_have_fire.isSelected(),
                        Integer.parseInt(modify_input_fire_count.getText()),
                        modify_input_room_name.getText(),
                        modify_input_is_owner.isSelected(),
                        0);
                App.getBuilding().addRoom(room);
                break;
            case Room.RoomType.SPECIAL:
                Room room1 = new Special(
                        Double.parseDouble(modify_input_capacity.getText()),
                        modify_input_can_have_fire.isSelected(),
                        Integer.parseInt(modify_input_fire_count.getText()),
                        Integer.parseInt(modify_special_type.getText()));
                App.getBuilding().addRoom(room1);
                break;
            case Room.RoomType.TALK:
                Room room2 = new Talk(
                        Double.parseDouble(modify_input_capacity.getText()),
                        modify_input_can_have_fire.isSelected(),
                        Integer.parseInt(modify_input_fire_count.getText()),
                        canPresentate,
                        isUsed);
                App.getBuilding().addRoom(room2);
                break;
        }

        updateLists();
    }
}
