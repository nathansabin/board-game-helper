package org.boardgame.boardgamehelper.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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
        gridState = new grid();
        sessionSettings = metaData.getInstance().getSessionSettings();
        gridMap = gridState.createGrid(10, 10);
        Pane menu = menuComponent();

        base = new AnchorPane();

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
        sizeInput = new TextField("400");
        arrowsSetDragable(tokens);

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

        return nav.scrollviewVert(imageContainers.toArray(new File[0]), "maps");
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
                    // error here
                    addMapImageEvent((ImageView) e);
                } catch (IOException ignored) {}
                imageEvent.consume();
            });
        });
    }

    private void addMapImageEvent(ImageView target) throws IOException {
        Integer parsedSize;

        try {
            parsedSize= Integer.parseInt(sizeInput.getText());
        } catch(Exception ignored) {
            parsedSize = 400;
        }

        Integer newSize = parsedSize - (parsedSize % 50);
        mapWidth = Math.max(400, newSize);

        backGround.setImage(target.getImage());
        backGround.setPreserveRatio(true);
        backGround.setFitWidth(mapWidth);

        Integer width = (int) Math.round(backGround.getFitWidth()/50);
        Integer height = (int) Math.round(backGround.getBoundsInLocal().getHeight()/50);

        base.getChildren().remove(gridMap);
        gridMap = gridState.createGrid(height, width);
        base.getChildren().add(gridMap);
        gridMap.toFront();
    }

    private void arrowsSetDragable(VBox container){
        ObservableList children = container.getChildren();
        Button[] buttons = {(Button) children.get(0), (Button) children.get(2)};
        VBox images = (VBox) children.get(1);

        for (int i=0; i<buttons.length; i++){
            buttons[i].addEventHandler(MouseEvent.MOUSE_CLICKED, MouseEvent -> {
                setAllDragable(images);
            });
        }
        setAllDragable(images);
    }
    private void setAllDragable(VBox imageCon){
        imageCon.getChildren().forEach(child -> {
            addTokenDrag((ImageView) child);
        });
    }
    private void addTokenDrag(ImageView tar) {
        tar.setOnDragDetected(event -> {
            Image img = tar.getImage();
            Dragboard db = tar.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putImage(img);
            db.setContent(content);

            event.consume();
        });
    }
}
