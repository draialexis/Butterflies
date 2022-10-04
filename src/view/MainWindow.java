package view;

import data.Stub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Butterfly;
import model.ButterflyManager;
import model.Color;

import java.io.IOException;

public class MainWindow {

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField butterflyTF;
    @FXML
    private ListView<Color> colorsLV;
    @FXML
    private ListView<Butterfly> butterfliesLV;

    @FXML
    private void clickAddColors(ActionEvent actionEvent) {
        if (colorPicker.getValue() != null) {
            butterfliesLV.getSelectionModel()
                         .getSelectedItem()
                         .addColor((int) (colorPicker.getValue().getRed()),
                                   (int) (colorPicker.getValue().getGreen()),
                                   (int) (colorPicker.getValue().getBlue()));
        }
    }

    @FXML
    private void clickRemoveColor(ActionEvent actionEvent) {

        if (butterfliesLV.getSelectionModel().getSelectedIndex() != -1 &&
            colorsLV.getSelectionModel().getSelectedIndex() != -1)
        {
            butterfliesLV.getSelectionModel()
                         .getSelectedItem()
                         .removeColor(colorsLV.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void clickUnselectColor(ActionEvent actionEvent) {
        colorsLV.getSelectionModel().select(-1);
    }

    @FXML
    private void clickAddButterfly(ActionEvent actionEvent) {
        Stage creationWindowStage = new Stage();
        creationWindowStage.initOwner(butterfliesLV.getScene().getWindow());
        creationWindowStage.initModality(Modality.WINDOW_MODAL);

        CreationWindow controller = initCreationWindow(creationWindowStage);

        if (controller.getButterflyName() != null) {
            mgr.addButterfly(controller.getButterflyName());
        }

    }

    private CreationWindow initCreationWindow(Stage creationWindowStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreationWindow.fxml"));
        CreationWindow controller = new CreationWindow();
        loader.setController(controller);
        try {
            creationWindowStage.setScene(new Scene(loader.load()));
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR,
                      "error while opening creation window",
                      ButtonType.OK).setHeaderText(null);
        }
        return controller;
    }

    @FXML
    private void clickRemoveButterfly(ActionEvent actionEvent) {
        if (butterfliesLV.getSelectionModel().getSelectedIndex() != -1) {
            mgr.removeButterfly(butterfliesLV.getSelectionModel().getSelectedItem());
            colorsLV.getItems().clear();
            butterflyTF.setText("");
        }

    }

    @FXML
    private void clickQuit(ActionEvent actionEvent) {
        butterfliesLV.getScene().getWindow().hide();
    }

    private final ButterflyManager mgr = new Stub().load();

    @FXML
    private void initialize() {

        butterfliesLV.itemsProperty().bind(mgr.butterfliesProperty());

        addListenerButterfliesLV();
        addListenerColorsLV();

        setCellFatoryButterfliesLV();
        setCellFatoryColorsLV();
    }

    private void setCellFatoryButterfliesLV() {
        butterfliesLV.setCellFactory(__ -> new ListCell<>() {
            @Override
            protected void updateItem(Butterfly item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty) {
                    textProperty().bind(item.nameProperty());
                }
                else {
                    textProperty().unbind();
                    setText("");
                }
            }
        });
    }

    private void setCellFatoryColorsLV() {
        colorsLV.setCellFactory(__ -> new ListCell<>() {
            @Override
            protected void updateItem(Color item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty) {
                    HBox hbox = new HBox();
                    initLayout(item, hbox);
                    setGraphic(hbox);
                }
                else {
                    setGraphic(null);
                }
            }
        });
    }

    private void initLayout(Color item, Pane layout) {
        Rectangle rect = new Rectangle();
        rect.fillProperty().bind(item.colorProperty());
        rect.setHeight(20);
        rect.setWidth(100);

        layout.getChildren().add(rect);
    }

    private void addListenerButterfliesLV() {
        butterfliesLV.getSelectionModel().selectedItemProperty().addListener((__, oldV, newV) -> {
            if (oldV != null) {
                butterflyTF.textProperty().unbindBidirectional(oldV.nameProperty());
                colorsLV.itemsProperty().unbind();
            }
            if (newV != null) {
                butterflyTF.textProperty().bindBidirectional(newV.nameProperty());
                colorsLV.itemsProperty().bind(newV.colorsProperty());
            }
        });
    }

    private void addListenerColorsLV() {
        colorsLV.getSelectionModel().selectedItemProperty().addListener((__, oldV, newV) -> {
            if (oldV != null) {
                colorPicker.valueProperty().unbindBidirectional(oldV.colorProperty());
            }
            if (newV != null) {
                colorPicker.valueProperty().bindBidirectional(newV.colorProperty());
            }
        });
    }

}
