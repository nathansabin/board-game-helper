package org.boardgame.boardgamehelper.GUI;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.boardgame.boardgamehelper.models.token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class grid {
    private List<token> tokenState;
    private GridPane grid;
    private Double relativeX;
    private Double relativeY;

    public GridPane createGrid(Integer width, Integer height) throws IOException {
        tokenState = new ArrayList<>();

        grid = new GridPane();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Rectangle rect = new Rectangle(50, 50, Color.TRANSPARENT);
                setGridDrops(rect, i, j);
                grid.add(rect, i, j);
            }
        }

        grid.setStyle("-fx-grid-lines-visible: true;");
        return grid;
    }

    private void setGridDrops(Node target, Integer x, Integer y) {
        target.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.ANY);
                    Rectangle rect = (Rectangle) event.getTarget();
                    rect.setFill(Color.rgb(50, 50, 50, 0.5));
                    relativeX = event.getX();
                    relativeY = event.getY();
                }
                event.consume();
            }
        });

        target.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Rectangle rect = (Rectangle) dragEvent.getTarget();
                rect.setFill(Color.TRANSPARENT);
                dragEvent.consume();
            }
        });

        target.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {

                Boolean success = false;
                Dragboard db = dragEvent.getDragboard();


                if (db.hasImage()) {
                    Rectangle rect = (Rectangle) dragEvent.getTarget();
                    Image img = db.getImage();

                    ImageView formatted = new ImageView();
                    formatted.setImage(img);
                    formatted.setPreserveRatio(true);
                    formatted.setFitHeight(50);
                    formatted.setFitWidth(50);

                    grid.getChildren().remove(rect);
                    grid.add(formatted, x, y);
                    success = true;

                    token current = new token(formatted, x, y);
                    tokenMovement(current);
                    tokenState.add(current);
                }

                dragEvent.setDropCompleted(success);
                dragEvent.consume();
            }
        });

    }

    private void tokenMovement(token token) {
        ImageView imageView = token.getImage();

        imageView.setOnDragDetected(event -> {
            event.setDragDetect(true);
            Image img = imageView.getImage();

            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(img);

            db.setContent(content);
            event.consume();
        });

        imageView.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                try {
                    Integer x = token.getXCord();
                    Integer y = token.getYCord();

                    // Checks to make sure position is in bounds
                   if (!(relativeY < 3 || relativeX < 3)) {
                       Rectangle rect = new Rectangle(50, 50, Color.TRANSPARENT);
                       grid.getChildren().remove(token.getImage());
                       grid.add(rect, x, y);
                       setGridDrops(rect, x, y);
                   }
                   dragEvent.setDropCompleted(true);
                } catch (Exception ignored) {}
                dragEvent.consume();
            }
        });
    }
    private boolean cellIsValid(Double x, Double y) {
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        for (var cell : grid.getChildren().filtered(node -> node instanceof Rectangle)) {
            if (cell.getBoundsInParent().contains(x,y)) {
                return true;
            }
        }
        System.out.println("not found");
        return false;
    }
}
