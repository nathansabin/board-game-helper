package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.utils.pageManager;

import java.io.IOException;

public class newGame {
    @FXML
    private Button homeButton;

    private pageManager pageM = new pageManager();

    @FXML
    public void pageHandler(MouseEvent e) throws IOException {
        var sor = e.getSource();
        Stage stage = (Stage) homeButton.getScene().getWindow();

        if (sor == homeButton) {
            pageM.changeScene("homepage.fxml", stage);
        }
    }
}
