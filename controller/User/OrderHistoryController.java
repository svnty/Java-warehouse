package controller.User;

import au.edu.uts.ap.javafx.*;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import model.Exceptions.*;
import java.util.logging.*;

public class OrderHistoryController extends Controller<User> {
  @FXML private ListView<Cart> orderLv;
  @FXML private Button closeBtn;
  @FXML private ImageView imageView;

  @FXML public void initialize() {
    imageView.setImage(new Image("/image/user.png"));
    orderLv.itemsProperty().bind(new SimpleListProperty<Cart>(this.model.getPurchaseHistory()));
  }

  @FXML public void close() {
    stage.close();
  }
}
