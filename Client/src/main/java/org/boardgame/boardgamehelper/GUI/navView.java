package org.boardgame.boardgamehelper.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.boardgame.boardgamehelper.utils.imageHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class navView {
    private File[] images;
    private HBox mapsComponent;
    private VBox mapComponentV;
    private HashMap<Integer, File>  active = new HashMap<>();
    private Integer current;
    private String cat;

    public HBox scrollview(File[] imgFolder, String category) throws IOException {
        this.mapsComponent = new HBox(10);
        images = imgFolder;
        this.current = 0;
        this.cat = category;

        int mapLength = Math.min(imgFolder.length, 3);
        for (int i=0; i < mapLength; i++) {
            this.active.put(i, imgFolder[i]);
            mapsComponent.getChildren().add(imageHandler.oneImage(images[i], this.cat));
        }

        HBox scroll = new HBox(10);
        scroll.getStylesheets().add("navView.css");
        Button left = new Button("< ");
        Button right = new Button(" >");

        left.setOnMouseClicked(mouseEvent -> {
            try {
                setLeft();
                mouseEvent.consume();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        right.setOnMouseClicked(mouseEvent -> {
            try {
                setRight();
                mouseEvent.consume();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        scroll.getChildren().add(left);
        scroll.getChildren().add(mapsComponent);
        scroll.getChildren().add(right);

        left.getStyleClass().add("nav-button");
        right.getStyleClass().add("nav-button");
        scroll.getStyleClass().add("box");

        scroll.getStylesheets().add("/navView.css");

        return scroll;
    }

    public VBox scrollviewVert(File[] imgFolder, String category) throws IOException {
        this.mapComponentV = new VBox(8);
        images = imgFolder;
        this.current = 0;
        this.cat = category;

        int mapLength = Math.min(imgFolder.length, 3);
        for (int i=0; i < mapLength; i++) {
            this.active.put(i, imgFolder[i]);
            this.mapComponentV.getChildren().add(imageHandler.oneImage(images[i], this.cat));
        }

        VBox scroll = new VBox(5);
        scroll.getStylesheets().add("navView.css");
        Button left = new Button("^");
        Button right = new Button("v");

        left.setOnMouseClicked(mouseEvent -> {
            try {
                setLeftV();
                mouseEvent.consume();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        right.setOnMouseClicked(mouseEvent -> {
            try {
                setRightV();
                mouseEvent.consume();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        scroll.getChildren().add(left);
        scroll.getChildren().add(mapComponentV);
        scroll.getChildren().add(right);

        left.getStyleClass().add("nav-button");
        right.getStyleClass().add("nav-button");
        scroll.getStyleClass().add("box");

        scroll.getStylesheets().add("/navView.css");

        return scroll;
    }

    public void setLeft() throws IOException {
        if (this.current <= 0) {
            System.out.println("cannot go further left");
        }
        else if (images.length <= 3) {
            System.out.println("Not enough images");
        }
        else {
            for (int i=0; i<2; i++) {
                mapsComponent.getChildren().remove(i);
            }
            mapsComponent.getChildren().remove(0);

            current--;
            for (int i=current; i<current+3; i++) {
                mapsComponent.getChildren().add(imageHandler.oneImage(images[i], this.cat));
            }

        }
    }

    public void setRight() throws IOException {
        if (this.current+3 >= images.length) {
            System.out.println("cannot go further right");
        }
        else if (images.length <= 3) {
            System.out.println("Not enough images");
        }
        else {
            for (int i=0; i<2; i++) {
                mapsComponent.getChildren().remove(i);
            }
            mapsComponent.getChildren().remove(0);

            current++;
            for (int i=current; i<current+3; i++) {
                mapsComponent.getChildren().add(imageHandler.oneImage(images[i], this.cat));
            }
        }
    }

    public void setLeftV() throws IOException {
        if (this.current <= 0) {
            System.out.println("cannot go further left");
        }
        else if (images.length <= 3) {
            System.out.println("Not enough images");
        }
        else {
            for (int i=0; i<2; i++) {
                mapComponentV.getChildren().remove(i);
            }
            mapComponentV.getChildren().remove(0);

            current--;
            for (int i=current; i<current+3; i++) {
                mapComponentV.getChildren().add(imageHandler.oneImage(images[i], this.cat));
            }

        }
    }

    public void setRightV() throws IOException {
        if (this.current + 3 >= images.length) {
            System.out.println("cannot go further right");
        } else if (images.length <= 3) {
            System.out.println("Not enough images");
        } else {
            for (int i = 0; i < 2; i++) {
                mapComponentV.getChildren().remove(i);
            }
            mapComponentV.getChildren().remove(0);

            current++;
            for (int i = current; i < current + 3; i++) {
                mapComponentV.getChildren().add(imageHandler.oneImage(images[i], this.cat));
            }
        }

    }
}
