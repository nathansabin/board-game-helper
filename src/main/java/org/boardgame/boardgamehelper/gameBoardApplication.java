package org.boardgame.boardgamehelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.GUI.*;

import java.io.IOException;

public class gameBoardApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(gameBoardApplication.class.getResource("hello-view.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load());

        grid map = new grid( 10, 10, "");
        Group page = new Group();
        page.getChildren().add(map.getGrid());

        Scene scene = new Scene(page);
        scene.getStylesheets().add("/board.css");
        stage.setTitle("Game helper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}