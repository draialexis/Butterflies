package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreationWindow {

    @FXML
    private TextField nameTF;

    private String butterflyName = null;

    @FXML
    private void clickConfirm() {
        butterflyName = nameTF.getText();
        close();
    }

    @FXML
    private void clickCancel() {
        butterflyName = null;
        close();
    }

    private void close() {
        nameTF.getScene().getWindow().hide();
    }

    public String getButterflyName() {
        return null;
    }
}
