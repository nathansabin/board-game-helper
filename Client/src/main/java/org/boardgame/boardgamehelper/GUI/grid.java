package org.boardgame.boardgamehelper.GUI;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.boardgame.boardgamehelper.models.token;

import java.io.IOException;
import java.util.List;

public class grid {
    private List<token> tokenState;
    private GridPane grid;

    public GridPane createGrid(Integer width, Integer height) throws IOException {
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

    private void setGridDrops(Node target, Integer x, Integer y){
        target.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.ANY);
                    Rectangle rect = (Rectangle) event.getTarget();
                    rect.setFill(Color.rgb(50,50,50, 0.5));
                }
                event.consume();
            };
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
                    formatted.toFront();
                    formatted.setPreserveRatio(true);
                    formatted.setFitHeight(50);
                    formatted.setFitWidth(50);

                    grid.getChildren().remove(rect);
                    grid.add(formatted, x, y);
                    success = true;

                }

                dragEvent.setDropCompleted(success);
                dragEvent.consume();
            }
        });

    }
    public void putToken(token newToken) {
        try {
            ImageView image = new ImageView(new Image(newToken.getImage().toURI().toString()));

            this.tokenState.add(newToken);
            grid.add(image, newToken.getXCord(), newToken.getYCord());
        } catch (Exception ignored) {}
    }

    public void updateTokenLocation(Integer index, Integer x, Integer y) {
        token curr = tokenState.get(index);

        Rectangle rect = new Rectangle(50, 50);
        rect.setFill(Color.TRANSPARENT);
        grid.add(rect, curr.getXCord(), curr.getYCord());

        curr.setXCord(x);
        curr.setYCord(y);

        ImageView currImage = new ImageView(new Image(curr.getImage().toURI().toString()));
        grid.add(currImage, x, y);
    }
}
