package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.utils.imageHandler;

import java.io.File;
import java.io.IOException;

public class viewController {
    @FXML
    private Button homeButton;
    @FXML
    private Rectangle headerBox;
    @FXML
    private AnchorPane root;

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
            System.out.println("worked");
            Group gro = imgHandler.convertImage(this.localMaps);
            root.getChildren().add((Node) gro);
            System.out.println(gro);
        }
        if (this.localTokens.length > 0) {
            System.out.println("TOKENS as WELL!??!?");
        }

    }

    @FXML
    public void pageHandler(MouseEvent e) throws Exception {
    Stage stage;
    Parent root;

    if (e.getSource() == homeButton) {
        stage = (Stage) homeButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/org/boardgame/boardgamehelper/homepage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
}
