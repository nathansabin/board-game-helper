package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.boardgame.boardgamehelper.GUI.grid;
import org.boardgame.boardgamehelper.GUI.navView;
import org.boardgame.boardgamehelper.models.metaData;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class game {
    @FXML
    public ScrollPane root;

    private GridPane gridMap;
    private grid gridState;
    private Boolean showMenu = false;
    private JSONObject sessionSettings;
    private navView nav = new navView();

    @FXML
    public void  initialize() throws IOException {
        this.gridState = new grid();
        sessionSettings = metaData.getInstance().getSessionSettings();
        this.gridMap = gridState.createGrid(10, 10);
        Pane menu = menuComponent();

        AnchorPane base = new AnchorPane();
        base.getChildren().add(gridMap);
        base.getChildren().add(menu);

        root.setContent(base);
    }

    @FXML
    public void disconnect() {
        metaData.getInstance().setSessionSettings(null);
    }

    private Pane menuComponent() throws IOException {
        Pane menu = new Pane();

        VBox maps = setImagsVertical("maps");
        VBox tokens = setImagsVertical("tokens");

        Button viewMenu = new Button("menu");
        VBox menuArea = new VBox();
        menuArea.getChildren().add(new Label("Maps"));
        menuArea.getChildren().add(maps);
        menuArea.getChildren().add(new Label("Tokens"));
        menuArea.getChildren().add(tokens);
        viewMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent)-> {
            showMenu = !showMenu;
            if (showMenu) {
                menu.getChildren().removeLast();
            } else {
                menu.getChildren().add(menuArea);
            }
            mouseEvent.consume();
        });
        menuArea.setTranslateY(40);
        menu.setTranslateX(500);

        menu.getChildren().add(viewMenu);
        menu.getChildren().add(menuArea);

        return menu;
    }

    public VBox setImagsVertical(String category) throws IOException {
        List<String> imgs = (List<String>) sessionSettings.get("maps");
        List<File> imageContainers = new ArrayList<>();

        for (int i=0; i<imgs.size(); i++) {
            imageContainers.add(new File((String)imgs.get(i)));
        }

        File[] test = imageContainers.toArray(new File[0]);

        VBox con =  nav.scrollviewVert(imageContainers.toArray(new File[0]), "maps");
        return con;
    }
}
