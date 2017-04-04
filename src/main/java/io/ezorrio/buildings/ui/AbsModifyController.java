package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.model.Room;
import io.ezorrio.buildings.model.Special;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * Created by golde on 05.04.2017.
 */
public abstract class AbsModifyController extends Controller {
    @FXML
    protected MenuButton modify_type;
    @FXML
    protected MenuButton modify_special_type;
    @FXML
    protected TextField modify_input_capacity;
    @FXML
    protected TextField modify_input_fire_count;
    @FXML
    protected TextField modify_input_room_name;
    @FXML
    protected CheckBox modify_input_is_owner;
    @FXML
    protected CheckBox modify_input_is_used;
    @FXML
    protected CheckBox modify_input_can_have_fire;
    @FXML
    protected CheckBox modify_input_can_presentate;

    protected int type;
    protected int special_room_type;
    protected boolean isOwner;
    protected boolean isUsed;
    protected boolean canPresentate;

    public static final int TYPE_LEVEL = -1;

    public void setTypeLevel() {
        type = TYPE_LEVEL;
        modify_type.setText("Level");
        modify_input_can_have_fire.setDisable(true);
        modify_input_fire_count.setDisable(true);
        modify_input_room_name.setDisable(true);
        modify_input_is_used.setDisable(true);
        modify_input_can_presentate.setDisable(true);
        modify_special_type.setDisable(true);
        modify_input_is_owner.setDisable(true);
    }

    public void setTypeOffice() {
        type = Room.RoomType.OFFICE;
        modify_type.setText("Office room");
        modify_input_can_have_fire.setDisable(false);
        modify_input_fire_count.setDisable(false);
        modify_input_room_name.setDisable(false);
        modify_input_is_used.setDisable(true);
        modify_input_can_presentate.setDisable(true);
        modify_special_type.setDisable(true);
        modify_input_is_owner.setDisable(false);
    }

    public void setTypeTalk() {
        type = Room.RoomType.TALK;
        modify_type.setText("Talk room");
        modify_input_can_have_fire.setDisable(false);
        modify_input_fire_count.setDisable(false);
        modify_input_room_name.setDisable(true);
        modify_input_is_used.setDisable(false);
        modify_input_can_presentate.setDisable(false);
        modify_special_type.setDisable(true);
        modify_input_is_owner.setDisable(true);
    }

    public void setTypeSpecial() {
        type = Room.RoomType.OFFICE;
        modify_type.setText("Special room");
        modify_input_can_have_fire.setDisable(false);
        modify_input_fire_count.setDisable(false);
        modify_input_room_name.setDisable(true);
        modify_input_is_used.setDisable(true);
        modify_input_can_presentate.setDisable(true);
        modify_special_type.setDisable(false);
        modify_input_is_owner.setDisable(true);
    }

    public void setTypeSecurity() {
        special_room_type = Special.SECURITY;
        modify_special_type.setText("Security");
    }

    public void setTypeServer() {
        special_room_type = Special.SERVER;
        modify_special_type.setText("Server");
    }

    public void setTypeContainer() {
        special_room_type = Special.CONTAINER;
        modify_special_type.setText("Container");
    }

    public void setIsOwner() {
        isOwner = modify_input_is_owner.isSelected();
    }

    public void setIsUsed() {
        isUsed = modify_input_is_used.isSelected();
    }

    public void setCanPresentate() {
        canPresentate = modify_input_can_presentate.isSelected();
    }
}
