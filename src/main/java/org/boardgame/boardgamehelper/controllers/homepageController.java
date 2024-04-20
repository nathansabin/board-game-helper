package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class homepageController {
    @FXML
    private Pane headerRoot;

    @FXML
    private Rectangle headerBox;
    @FXML
    private Rectangle menuBox;

    public void initialize() {
        headerBox.widthProperty().bind(headerRoot.widthProperty());
        menuBox.heightProperty().bind(headerRoot.heightProperty());
    }
}
