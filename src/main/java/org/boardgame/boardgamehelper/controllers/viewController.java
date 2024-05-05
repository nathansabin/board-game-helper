package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.utils.imageHandler;
import org.boardgame.boardgamehelper.utils.pageManager;

import java.io.File;
import java.io.IOException;

public class viewController {
    @FXML
    private Button homeButton;
    @FXML
    private Rectangle headerBox;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane body;
    @FXML
    private HBox Hbody;

    private pageManager pageM;

    private File[] localTokens;
    private File[] localMaps;

    private imageHandler imgHandler = new imageHandler();

    public void initialize() throws IOException {
        headerBox.widthProperty().bind(root.widthProperty());

        try {
            this.localMaps = imgHandler.pullLocalImages("maps/");
            this.localTokens = imgHandler.pullLocalImages("tokens/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.localMaps.length > 0) {
            VBox gro = imgHandler.convertImage(this.localMaps, "Local Maps");
            Hbody.getChildren().add((Node) gro);


        }
        if (this.localTokens.length > 0) {
            VBox gro = imgHandler.convertImage(this.localMaps, "Local Tokens");
            Hbody.getChildren().add((Node) gro);
        }
    }

    @FXML
    public void pageHandler(MouseEvent e) throws Exception {
    Stage stage;
    Parent root;

    if (e.getSource() == homeButton) {
        stage = (Stage) homeButton.getScene().getWindow();
        pageM.changeScene("homepage.fxml", stage);
    }
}
}
