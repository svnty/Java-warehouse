package controller.Cart;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import model.Exceptions.*;

import java.io.InputStream;
import java.util.logging.*;

public class ViewCartController extends Controller<Cart> {
  @FXML private TableView<Order> cartTv;
  @FXML private ImageView imageView;
  
  @FXML public void initialize() {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("image/cart.png");
    Image image = new Image(inputStream);
    imageView.setImage(image);
    this.cartTv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    this.cartTv.setItems(this.model.getOrders());
  }

  public final Cart getCart() {
    return this.model;
  }

  @FXML private void remove() {
    Order order = this.cartTv.getSelectionModel().getSelectedItem();
    if (order != null) {
      this.model.removeOrder(order);
    }
  }

  @FXML private void close() {
    this.stage.close();
  }
}
