package org.boardgame.boardgamehelper.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mapaddController {
    private Stage stage;
    @FXML
    private AnchorPane root;
    @FXML
    private Rectangle headerBox;
    @FXML
    private VBox imageSelector;
    @FXML
    private Button fileButton;
    @FXML
    private ImageView imageBox;
    @FXML
    private Button homeButton;
    @FXML
    private CheckBox large;
    @FXML
    private CheckBox medium;
    @FXML
    private CheckBox small;


    public void initialize() {
        headerBox.widthProperty().bind(root.widthProperty());
    }

    @FXML
    public void pageHandler(MouseEvent e) throws Exception{
        Stage stage;
        Parent root;

        if (e.getSource() == homeButton) {
            stage = (Stage) homeButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/org/boardgame/boardgamehelper/homepage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void checkBoxState(MouseEvent e) {
        List<CheckBox> boxs = new ArrayList<>(Arrays.asList(large, medium, small));

        for (CheckBox b : boxs) {
            if (b != e.getSource()) {
                b.setSelected(false);
            }
        }
    }

    @FXML
    public void insertFile(MouseEvent e) {
        stage = (Stage) fileButton.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose map image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageBox.setImage(image);
        }
    }
}
