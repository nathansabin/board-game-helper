package org.boardgame.boardgamehelper.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.GUI.navView;
import org.boardgame.boardgamehelper.utils.imageHandler;
import org.boardgame.boardgamehelper.utils.jsonHandler;
import org.boardgame.boardgamehelper.utils.pageManager;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
    @FXML
    private TextField saveName;

    private pageManager pageM = new pageManager();
    private navView mapView = new navView();
    private navView tokenView = new navView();
    private navView ruleView = new navView();
    private List<String> mapsArray = new ArrayList<>();
    private List<String> tokensArray = new ArrayList<>();


    @FXML
    public void initialize() throws IOException, URISyntaxException {
        File[] maps = imageHandler.pullLocalImages("maps");
        File[] tokens = imageHandler.pullLocalImages("tokens");

        HBox mapScroll = mapView.scrollview(maps, "map");
        HBox TokenScroll = tokenView.scrollview(tokens, "token");

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

    @FXML
    public void save(MouseEvent e) throws IOException {
        String staticMaps = mapsArray.toString();
        String staticTokens = tokensArray.toString();
        boolean worked = jsonHandler.writeJSON(saveName.getText(), staticTokens, staticMaps);

        try {
            Stage stage = (Stage) root.getScene().getWindow();
            pageM.changeScene("game.fxml", stage);
        } catch (Exception ignored) {// add warning indicator later
             }
    }

    private void addImageEvents(HBox imageBox) {
        imageBox.getChildren().forEach((e) ->{
            e.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent)-> {
                try {
                    ImageView cur = (ImageView) mouseEvent.getTarget();
                    String currentCat = getImageCategory(cur);
                    String path = (String) cur.getUserData();


                    if (currentCat == "token") {
                        this.tokensArray.add(path);
                    } else if (currentCat == "map") {
                        this.mapsArray.add(path);
                    }
                } catch (Exception err) {}
                mouseEvent.consume();
            });
        });
    }
    private void arrowClickEvent(HBox nav) {
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

    private String getImageCategory(ImageView img) {
        ObservableList<String> classes = img.getStyleClass();
        if (classes.contains("token")) {
            return "token";
        } else if (classes.contains("map")) {
            return "map";
        }
        return null;
    }

}
