package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import org.boardgame.boardgamehelper.GUI.grid;
import org.boardgame.boardgamehelper.models.metaData;
import org.json.simple.JSONObject;

import java.io.IOException;

public class game {
    @FXML
    public ScrollPane root;

    private GridPane gridMap;
    private grid gridState;
    private JSONObject sessionSettings;
    @FXML
    public void  initialize() throws IOException {
        this.gridState = new grid();
        sessionSettings = metaData.getInstance().getSessionSettings();
        this.gridMap = gridState.createGrid(10, 10);

        root.setContent(gridMap);
    }

    @FXML
    public void disconnect() {
        metaData.getInstance().setSessionSettings(null);
    }
}
