package org.boardgame.boardgamehelper.GUI;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;


public class grid {

    private Image background;
    private GridPane grid;
    private static Group map;

    public grid (Integer width, Integer height, String image) throws IOException {
        map = new Group();
        ScrollPane scroll = new ScrollPane();
        grid = new GridPane();

        for (Integer i = 0; i < height; i++) {
            for (Integer j = 0; j < width; j++) {
                Rectangle rect = new Rectangle(50, 50);
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.BLACK);
                grid.add(rect, i, j);
            }
        }

        if (image != "") {
            File file = new File(image);
//            background = SwingFXUtils.toFXImage(ImageIO.read(file));
            ImageView view = new ImageView();
            view.setImage(background);
            view.setStyle("-fx-position: absolute; -fx-top: 0; -fx-left: 0;");

            map.getChildren().add(view);
        }
        map.getChildren().add(scroll);
        map.getChildren().add(grid);
    }
    public static Group getGrid() {
        return map;
    }

}
