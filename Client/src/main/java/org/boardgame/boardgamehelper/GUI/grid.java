package org.boardgame.boardgamehelper.GUI;

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
                Rectangle rect = new Rectangle(50, 50);
                rect.setFill(Color.TRANSPARENT);
                grid.add(rect, i, j);
            }
        }

        grid.setStyle("-fx-grid-lines-visible: true;");
        return grid;
    }

    public void putToken(token newToken) {
        try {
            // finish add token to map
            this.tokenState.add(newToken);
        } catch (Exception ignored) {}
    }
}
