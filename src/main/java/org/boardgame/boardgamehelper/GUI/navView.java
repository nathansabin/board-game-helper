package org.boardgame.boardgamehelper.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.boardgame.boardgamehelper.utils.imageHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class navView {
    private File[] images;
    private HBox mapsComponent;
    private HashMap<Integer, File>  active = new HashMap<>();
    private Integer current;


    public HBox scrollview(File[] imgFolder) throws IOException {
        this.mapsComponent = new HBox();
        images = imgFolder;
        this.current = 0;

        int mapLength = Math.min(imgFolder.length, 3);
        for (int i=0; i < mapLength; i++) {
            this.active.put(i, imgFolder[i]);
            mapsComponent.getChildren().add(imageHandler.oneImage(images[i]));
        }

        HBox scroll = new HBox(50);
        Button left = new Button("< ");
        Button right = new Button(" >");

        left.setOnMouseClicked(mouseEvent -> {
            try {
                setLeft();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        right.setOnMouseClicked(mouseEvent -> {
            try {
                setRight();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        scroll.getChildren().add(left);
        scroll.getChildren().add(mapsComponent);
        scroll.getChildren().add(right);

        return scroll;
    }

    public void setLeft() throws IOException {
        if (this.current <= 0) {
            System.out.println("cannot go further left");
            return;
        }
        else if (images.length <= 3) {
            System.out.println("Not enough images");
            return;
        }
        else {
            for (int i=0; i<2; i++) {
                mapsComponent.getChildren().remove(i);
            }
            mapsComponent.getChildren().remove(0);

            current--;
            for (int i=current; i<current+3; i++) {
                mapsComponent.getChildren().add(imageHandler.oneImage(images[i]));
            }

        }
    }

    public void setRight() throws IOException {
        if (this.current+3 >= images.length) {
            System.out.println("cannot go further right");
            return;
        }
        else if (images.length <= 3) {
            System.out.println("Not enough images");
            return;
        }
        else {
            mapsComponent.getChildren().forEach(e->System.out.println(e));

            for (int i=0; i<2; i++) {
                mapsComponent.getChildren().remove(i);
            }
            mapsComponent.getChildren().remove(0);

            current++;
            for (int i=current; i<current+3; i++) {
                mapsComponent.getChildren().add(imageHandler.oneImage(images[i]));
            }
        }
    }
}
