package io.ezorrio.buildings.ui;

import io.ezorrio.buildings.db.DBHelper;
import io.ezorrio.buildings.preferences.Preferences;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

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
    private CheckMenuItem menu_show_extended;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        initMenu();
    }

    private void initMenu() {
        menu_show_extended.setOnAction(event -> Preferences.setNeedExtendedInfo(menu_show_extended.isSelected()));
        menu_save.setOnAction(event -> App.getBuilding().save());
        menu_import.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("Import DB path");
            dialog.setTitle("Import path");
            dialog.setContentText("Enter path to DB");
            dialog.getEditor().setText(getImportPath());
            dialog.show();
            dialog.setOnCloseRequest(event1 -> {
                setImportPath(dialog.getEditor().getText());
                DBHelper helper = new DBHelper(getImportPath());
                try {
                    App.setBuilding(helper.importFromDB());
                    updateLists();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
