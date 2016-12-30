package Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

public class Controller {
    @FXML Button createXmlBtn;
    @FXML TextField pathTextField;

    @FXML
    private void handleCreateXmlBtn(ActionEvent event)
    {
        String path = System.getProperty("user.dir");
        Model.Datamanager datamanager = new Model.Datamanager(path);
        datamanager.setAttributeNames();
        datamanager.setLines();
        datamanager.createItems();
        //datamanager.test();
        datamanager.writeWeaponsCsv();
    }

}
