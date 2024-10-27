package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Exceptions.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends Controller<Organisation>{
    @FXML private TextField usernameTf;
    @FXML private PasswordField passwordPf;
    @FXML private Button loginBtn;
    
    @FXML private void initialize() {
        loginBtn.setDisable(true);
        usernameTf.textProperty().addListener(
            (observable, oldText, newText) -> loginBtn.setDisable(newText.isEmpty() || passwordPf.getText().isEmpty())
        );
        passwordPf.textProperty().addListener(
            (observable, oldText, newText) -> loginBtn.setDisable(newText.isEmpty() || usernameTf.getText().isEmpty())
        );
    }

    @FXML
    public void login() throws IOException {
        try {
            User u = getOrganisation().getUsers().validateUser(usernameTf.getText(), passwordPf.getText());
            Organisation o = getOrganisation();
            Organisation.setLoggedInUser(u);
            stage.close();
            ViewLoader.showStage(o, "/view/User/UserDashboardView.fxml", u.getClass().getName().split("\\.")[1].toString() + " Dashboard", new FixedStage("/image/user_icon.png"));
        } catch (NoSuchUserException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            ViewLoader.showErrorStage(new ErrorModel(e, "Invalid user credentials"));
        }
    }

    public Organisation getOrganisation() {
        return this.model;
    }

    @FXML
    public void close() {
        stage.close();
    }
}
