package org.boardgame.boardgamehelper.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.utils.apiHandler;
import org.boardgame.boardgamehelper.utils.pageManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class tokenaddController {
    private Stage stage;
    private File newMap;
    @FXML
    private AnchorPane root;
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

    private pageManager pageM = new pageManager();

    public void initialize() {

    }

    @FXML
    public void pageHandler(MouseEvent e) throws Exception{
        Stage stage;
        Parent root;

        if (e.getSource() == homeButton) {
            stage = (Stage) homeButton.getScene().getWindow();
            pageM.changeScene("homepage.fxml", stage);
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

    @FXML
    public void saveImageServer(MouseEvent e) {
        try {
            if (imageBox.getImage() != null) {
                BufferedImage imageBuffered = SwingFXUtils.fromFXImage(imageBox.getImage(), null);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                ImageIO.write(imageBuffered, "png", bao);
                byte[] bytes = bao.toByteArray();

                String base64Image = Base64.getEncoder().encodeToString(bytes);
                boolean worked = apiHandler.sendUserImage(base64Image, "eyJhbGciOiJIUzM4NCJ9.eyJwYXNzd29yZCI6ImZha2VwYXNzd29yZCIsImlkIjo4LCJ1c2VybmFtZSI6Im5vdGhlcmUiLCJpYXQiOjE3MTg0ODE2OTMsImV4cCI6MTcxODUxNzY5M30.Kl5KfahJRqCPPFGo2V4HeuJ2YOCS1LzbHmag1AkXzOx9J1KPpk2JscFNYJuQby4G");
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
