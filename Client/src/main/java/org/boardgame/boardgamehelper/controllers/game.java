package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private AnchorPane base;
    private GridPane gridMap;
    private grid gridState;
    private TextField sizeInput;
    private ImageView backGround = new ImageView();
    private JSONObject sessionSettings;
    private navView nav = new navView();
    private double mapWidth = 500.0;
    private Boolean showMenu = false;

    @FXML
    public void  initialize() throws IOException {
        this.gridState = new grid();
        sessionSettings = metaData.getInstance().getSessionSettings();
        this.gridMap = gridState.createGrid(10, 10);
        Pane menu = menuComponent();

        this.base = new AnchorPane();

        base.getChildren().add(gridMap);
        base.getChildren().add(backGround);
        base.getChildren().add(menu);
        gridMap.toFront();

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
        Label sizeInputLabel = new Label("Enter size");
        this.sizeInput = new TextField();

        menuArea.getChildren().add(new Label("Maps"));
        mapArrowClickEvent(maps);
        menuArea.getChildren().add(maps);
        menuArea.getChildren().add(sizeInputLabel);
        menuArea.getChildren().add(sizeInput);
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
        List<String> imgs = (List<String>) sessionSettings.get(category);
        List<File> imageContainers = new ArrayList<>();

        for (int i=0; i<imgs.size(); i++) {
            imageContainers.add(new File((String)imgs.get(i)));
        }

        VBox con =  nav.scrollviewVert(imageContainers.toArray(new File[0]), "maps");
        return con;
    }

    private void mapArrowClickEvent(VBox mapContainer) {
        Button left = (Button) mapContainer.getChildren().get(0);
        VBox images = (VBox) mapContainer.getChildren().get(1);
        Button right = (Button) mapContainer.getChildren().get(2);

        Button[] buttons = {left, right};

        for (int i=0; i<buttons.length; i++) {
            buttons[i].addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent-> {
                images.getChildren().forEach(e-> {
                    e.addEventHandler(MouseEvent.MOUSE_CLICKED, imageEvent ->{
                        try {
                            addMapImageEvent((ImageView) e);
                        } catch (IOException ignored) {}
                        imageEvent.consume();
                    });
                });
                mouseEvent.consume();
            });
        }
        images.getChildren().forEach(e-> {
            e.addEventHandler(MouseEvent.MOUSE_CLICKED, imageEvent ->{
                try {
                    addMapImageEvent((ImageView) e);
                } catch (IOException ignored) {}
                imageEvent.consume();
            });
        });
    }

    private void addMapImageEvent(ImageView target) throws IOException {
        Integer newsize = Integer.parseInt(sizeInput.getText());
        newsize = newsize - (newsize % 50);
        mapWidth = Math.max(400, newsize);

        backGround.setImage(target.getImage());
        backGround.setPreserveRatio(true);
        backGround.setFitWidth(mapWidth);

        Integer newGridWidth = (int) Math.round(backGround.getFitWidth()/50);
        Integer newGridHeight = (int) Math.round(backGround.getBoundsInLocal().getHeight()/50);

        this.base.getChildren().remove(gridMap);
        this.gridMap = gridState.createGrid(newGridHeight, newGridWidth);
        base.getChildren().add(gridMap);
        gridMap.toFront();
    }
}
