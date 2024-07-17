package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.GUI.navView;
import org.boardgame.boardgamehelper.utils.imageHandler;
import org.boardgame.boardgamehelper.utils.pageManager;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class newGame {
    @FXML
    private VBox root;
    @FXML
    private Button homeButton;
    @FXML
    private HBox savedMaps;
    @FXML
    private HBox savedTokens;
    @FXML
    private HBox savedRules;

    private pageManager pageM = new pageManager();
    private navView mapView = new navView();
    private navView tokenView = new navView();
    private navView ruleView = new navView();

    @FXML
    public void initialize() throws IOException, URISyntaxException {
        File[] maps = imageHandler.pullLocalImages("maps");
        File[] tokens = imageHandler.pullLocalImages("tokens");

        HBox mapScroll = mapView.scrollview(maps);
        HBox TokenScroll = tokenView.scrollview(tokens);

        addImageEvents(mapScroll);
        addImageEvents(TokenScroll);
        arrowClickEvent(mapScroll);
        arrowClickEvent(TokenScroll);

        savedMaps.getChildren().add(mapScroll);
        savedTokens.getChildren().add(TokenScroll);

    }

    @FXML
    public void pageHandler(MouseEvent e) throws IOException {
        var sor = e.getSource();
        Stage stage = (Stage) homeButton.getScene().getWindow();

        if (sor == homeButton) {
            pageM.changeScene("homepage.fxml", stage);
        }
    }

    public void addImageEvents(HBox imageBox) {
        imageBox.getChildren().forEach((e) ->{
            e.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent)-> {
                var img = mouseEvent.getTarget();
                System.out.println(mouseEvent.getTarget());
                mouseEvent.consume();
            });
        });
    }
    public void arrowClickEvent(HBox nav) {
        Button left = (Button) nav.getChildren().get(0);
        HBox images = (HBox) nav.getChildren().get(1);
        Button right = (Button) nav.getChildren().get(2);
        Button[] buttons = { left, right };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
                System.out.println("Clicked: " + mouseEvent.getSource().toString());
                addImageEvents(images);
                mouseEvent.consume();
            });
        }
        addImageEvents(images);
    }
}
