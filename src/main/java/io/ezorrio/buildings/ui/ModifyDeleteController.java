package io.ezorrio.buildings.ui;


import io.ezorrio.buildings.exception.InvalidIdException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static io.ezorrio.buildings.ui.AbsModifyController.TYPE_LEVEL;
import static io.ezorrio.buildings.ui.AbsModifyController.TYPE_ROOM;

/**
 * Created by golde on 04.04.2017.
 */
public class ModifyDeleteController extends Controller {

    @FXML
    private MenuButton modify_input_delete_type;
    @FXML
    private Button modify_input_delete;
    @FXML
    private TextField modify_input_room;

    private int deleteType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        modify_input_delete.setOnAction(event -> {
            switch (deleteType) {
                case TYPE_LEVEL:
                    try {
                        App.getBuilding().removeLevel(Integer.parseInt(modify_input_room.getText()));
                    } catch (InvalidIdException e) {
                        showErrorDialog(e.getMessage());
                    }
                    break;
                case TYPE_ROOM:
                    try {
                        App.getBuilding().removeRoom(modify_input_room.getText());
                    } catch (InvalidIdException e) {
                        showErrorDialog(e.getMessage());
                    }
                    break;
            }
        });
    }

    public void setTypeLevel() {
        deleteType = TYPE_LEVEL;
        modify_input_delete_type.setText("Level");
    }

    public void setTypeRoom() {
        deleteType = TYPE_ROOM;
        modify_input_delete_type.setText("Room");
    }
}
