package org.boardgame.boardgamehelper.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class pageManager {

    public void changeScene(String pageName, Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/boardgame/boardgamehelper/" + pageName));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 }
