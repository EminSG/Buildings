package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.db.DBHelper;
import io.ezorrio.buildings.exception.IncorrectBuildingException;
import io.ezorrio.buildings.exception.NoSpaceOnLevelException;
import io.ezorrio.buildings.preferences.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static io.ezorrio.buildings.preferences.Preferences.getImportPath;
import static io.ezorrio.buildings.preferences.Preferences.setImportPath;

/**
 * Created by golde on 04.04.2017.
 */
public class MenuController extends Controller {

    @FXML
    private MenuItem menu_save;
    @FXML
    private MenuItem menu_import;
    @FXML
    private MenuItem menu_close;
    @FXML
    private CheckMenuItem menu_show_extended;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        initMenu();
    }

    private void initMenu() {
        menu_show_extended.setOnAction(event -> Preferences.setNeedExtendedInfo(menu_show_extended.isSelected()));
        menu_save.setOnAction(event -> {
            try {
                App.getBuilding().save();
            } catch (IncorrectBuildingException e) {
                showErrorDialog(e.getMessage());
            }
        });
        menu_import.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("Import DB path");
            dialog.setTitle("Import path");
            dialog.setContentText("Enter path to DB");
            dialog.getEditor().setText(getImportPath());
            dialog.showAndWait();
            final Button ok = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            ok.addEventFilter(ActionEvent.ACTION, event1 -> {
                setImportPath(dialog.getEditor().getText());
                DBHelper helper = new DBHelper(getImportPath());
                try {
                    App.setBuilding(helper.importFromDB());
                    updateLists();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSpaceOnLevelException e) {
                    showErrorDialog(e.getMessage());
                }
            }
            );
        });
        menu_close.setOnAction(event -> Platform.exit());
    }
}
