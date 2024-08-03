package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.models.metaData;
import org.boardgame.boardgamehelper.utils.jsonHandler;
import org.boardgame.boardgamehelper.utils.pageManager;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

public class savedGames {
    @FXML
    public ScrollPane root;
    @FXML
    public Button homeButton;
    @FXML
    public FlowPane cardContainer;

    pageManager pageM = new pageManager();

    public void initialize() {
        List<JSONObject> allSaves = jsonHandler.getAll();

        for (int i=0; i< allSaves.size(); i++) {
            Pane card = createCard(allSaves.get(i), i);
            card.setId(String.valueOf(i));

            card.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClick-> {
                Node tar = (Node) mouseClick.getTarget();
                Integer id = Integer.parseInt(tar.getId());

                metaData.getInstance().setSessionSettings(allSaves.get(id));
                try {
                    pageM.changeScene("game.fxml", (Stage) root.getScene().getWindow());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mouseClick.consume();
            });

            cardContainer.getChildren().add(card);
        }
    }

    public void pageHandler(MouseEvent e) throws IOException {
        var sor = e.getSource();
        Stage stage = (Stage) homeButton.getScene().getWindow();

        if (sor == homeButton) {
            pageM.changeScene("homepage.fxml", stage);
        }
    }

    private Pane createCard(JSONObject obj, Integer id) {
        VBox card = new VBox();
        Label name = new Label(obj.get("name").toString());
        Label onlineStatus = new Label((boolean) obj.get("online") ? "Online save" : "Local save");

        card.getStyleClass().add("card");
        card.setPrefWidth(200);
        card.setPrefHeight(65);
        name.getStyleClass().add("card-title");
        onlineStatus.getStyleClass().add("card-subtitle");

        card.getChildren().add(name);
        card.getChildren().add(onlineStatus);
        return card;
    }
}
