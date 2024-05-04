package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.utils.pageManager;

public class homepageController {
    @FXML
    private Pane headerRoot;
    @FXML
    private Rectangle headerBox;
    @FXML
    private Rectangle menuBox;
    @FXML
    private Button mapAdd;
    @FXML
    private Button tokenAdd;
    @FXML
    private Button viewAssets;
    @FXML
    private Button launch;

    private pageManager pageM = new pageManager();

    public void initialize() {
        headerBox.widthProperty().bind(headerRoot.widthProperty());
        menuBox.heightProperty().bind(headerRoot.heightProperty());
    }

    @FXML
    public void setScene(MouseEvent e) throws Exception{
        Stage stage;
        stage = (Stage) mapAdd.getScene().getWindow();
        var sor = e.getSource();

        try {
            if (sor == mapAdd) {
                pageM.changeScene("mapadd.fxml", stage);
            } else if (sor == tokenAdd) {
                pageM.changeScene("tokenadd.fxml", stage);
            } else if (sor == viewAssets) {
                pageM.changeScene("view.fxml", stage);
            } else if (sor == launch) {
                pageM.changeScene("launch.fxml", stage);
            }

        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
