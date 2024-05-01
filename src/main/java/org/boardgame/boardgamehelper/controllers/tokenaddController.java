package org.boardgame.boardgamehelper.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tokenaddController {
    private Stage stage;
    private File newMap;
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
        fileChooser.setTitle("Choose token image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        newMap = fileChooser.showOpenDialog(stage);
        if (newMap != null) {
            Image image = new Image(newMap.toURI().toString());
            imageBox.setImage(image);
        }
    }

    @FXML
    public void saveImage(MouseEvent e) {
        try {
            if (imageBox.getImage() != null) {
                newMap = new File("src/main/resources/tokens/" + imageBox.getImage() + ".png");
                ImageIO.write(SwingFXUtils.fromFXImage(imageBox.getImage(), null), "png", newMap);
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
