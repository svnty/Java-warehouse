package controller.Cart;

import java.io.IOException;
import java.io.InputStream;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import model.*;

public class CartController extends Controller<Cart> {
  @FXML private TableView<Product> cartTv;
  @FXML private ImageView imageView;
  @FXML private Label introLbl;
  @FXML private Button addBtn;

  @FXML private void initialize() {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("image/cart.png");
    Image image = new Image(inputStream);
    imageView.setImage(image);
    this.introLbl.setText("Ordering from " + model.getSupplier().getName());
    this.cartTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    this.cartTv.setItems(getProducts());
    this.addBtn.disableProperty().bind(cartTv.getSelectionModel().selectedItemProperty().isNull());
  }

  public final ObservableList<Product> getProducts() {
    return this.model.getCatalogue();
  }

  public final Supplier getSupplier() {
    return this.model.getSupplier();
  }

  public final void close() {
    this.stage.close();
  }

  public final void checkout() {
    this.model.getSupplier().processCart(this.model);
    Organisation.getLoggedInUser().addPurchase(this.model);
    this.stage.close();
  }

  public final void view() throws IOException {
    ViewLoader.showStage(model, "/view/Cart/ViewCartView.fxml", "View Cart", new FixedStage("/image/cart_icon.png"));
  }

  public final void add() throws IOException {
    ObservableList<Product> selectedProducts = cartTv.getSelectionModel().getSelectedItems();
    if (selectedProducts.size() > 0) {
      for (Product p : selectedProducts) {
        Order o = new Order(p, 0, model);
        ViewLoader.showStage(o, "/view/Cart/AddToCartView.fxml", "Adding xd", new FixedStage("/image/cart_icon.png"));
      }
      cartTv.getItems().removeAll(selectedProducts);
      // cart.addOrder(new Order(selectedProducts.get(0), , cart)); 
      // model.addOrder(selectedProducts);
    }
  }

}
