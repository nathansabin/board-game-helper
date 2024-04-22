package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class homepageController {
    @FXML
    private Pane headerRoot;
    @FXML
    private Rectangle headerBox;
    @FXML
    private Rectangle menuBox;
    @FXML
    private Button addButton;

    public void initialize() {
        headerBox.widthProperty().bind(headerRoot.widthProperty());
        menuBox.heightProperty().bind(headerRoot.heightProperty());
    }

    @FXML
    public void setScene(MouseEvent e) throws Exception{
        Stage stage;
        Parent root;

        if (e.getSource() == addButton) {
            stage = (Stage) addButton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("/org/boardgame/boardgamehelper/mapadd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
