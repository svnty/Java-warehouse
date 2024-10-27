package controller.Cart;

import java.io.InputStream;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidQuantityException;

public class AddToCartController extends Controller<Order> {
  @FXML private ImageView imageView;
  @FXML private Label introLbl;
  @FXML private TextField quantityField;

  @FXML public void initialize() {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("image/cart.png");
    Image image = new Image(inputStream);
    imageView.setImage(image);
    this.introLbl.setText("Adding " + model.getProduct().getName() + " to cart");
  }

  public final void add() {
    try {
      this.model.setQuantity(Integer.parseInt(this.quantityField.getText()));
      this.model.getCart().addOrder(this.model);
      this.stage.close();
    } catch (InvalidQuantityException e) {
      ViewLoader.showErrorStage(new ErrorModel(e, "Invalid quantity")); 
    }
  }
}
