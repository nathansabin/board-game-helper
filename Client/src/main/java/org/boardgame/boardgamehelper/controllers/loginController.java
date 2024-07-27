package org.boardgame.boardgamehelper.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.boardgame.boardgamehelper.models.metaData;
import org.boardgame.boardgamehelper.utils.apiHandler;
import org.boardgame.boardgamehelper.utils.pageManager;

public class loginController {
    private pageManager pageM = new pageManager();
    @FXML
    private Button homeButton;
    @FXML
    private Button register;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    public void initialize() {

    }
    @FXML
    public void pageHandler(MouseEvent e) throws Exception{
        Stage stage;
        var sor = e.getSource();

        stage = (Stage) homeButton.getScene().getWindow();

        if (sor == homeButton) {
            pageM.changeScene("homepage.fxml", stage);
        } else if (sor == register) {
            pageM.changeScene("register.fxml", stage);
        }
    }
    @FXML
    public void loginUser(MouseEvent e) {
        try {
            String user = username.getText();
            String pass = password.getText();

            String token = apiHandler.login(user, pass);

            if (token == null) {
                return;
            }

            metaData.getInstance().setToken(token);
            pageM.changeScene("homepage.fxml", (Stage) homeButton.getScene().getWindow());

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
