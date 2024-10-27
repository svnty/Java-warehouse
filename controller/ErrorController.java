package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import model.Exceptions.*;

public class ErrorController extends Controller<ErrorModel> {
    @FXML private Label errorLbl;
    @FXML private Label errorMsgLbl;
    @FXML private ImageView imageView;

    public final ErrorModel getError() {
        return this.model;
    }

    @FXML
    public void initialize() {
      errorMsgLbl.setText(getError().getMessage());
      errorLbl.setText(getError().getException());
      imageView.setImage(new Image("/image/error.png"));
    }

    @FXML
    public void close() {
        stage.close();
    }
    
}
