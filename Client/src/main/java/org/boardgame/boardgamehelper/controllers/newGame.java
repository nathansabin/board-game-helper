package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.GUI.navView;
import org.boardgame.boardgamehelper.utils.imageHandler;
import org.boardgame.boardgamehelper.utils.pageManager;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class newGame {
    @FXML
    private Button homeButton;
    @FXML
    private HBox savedMaps;
    @FXML
    private HBox savedRules;

    private pageManager pageM = new pageManager();
    private navView mapView = new navView();
    private navView ruleView = new navView();

    @FXML
    public void initialize() throws IOException, URISyntaxException {
        File[] maps = imageHandler.pullLocalImages("maps");
        HBox mapScroll = mapView.scrollview(maps);

        savedMaps.getChildren().add(mapScroll);
    }

    @FXML
    public void pageHandler(MouseEvent e) throws IOException {
        var sor = e.getSource();
        Stage stage = (Stage) homeButton.getScene().getWindow();

        if (sor == homeButton) {
            pageM.changeScene("homepage.fxml", stage);
        }
    }
}
