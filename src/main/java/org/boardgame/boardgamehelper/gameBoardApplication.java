package org.boardgame.boardgamehelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class gameBoardApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(gameBoardApplication.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add("/homepage.css");
        stage.setTitle("Game helper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}