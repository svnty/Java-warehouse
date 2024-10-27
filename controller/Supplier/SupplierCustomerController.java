package controller.Supplier;

import java.io.IOException;
import java.io.InputStream;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.FixedStage;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import model.*;

public class SupplierCustomerController extends Controller<Supplier> {
  @FXML private TableView<Product> productsTv;
  @FXML private ImageView imageView;
  @FXML private Label introLbl;
  @FXML private Button orderBtn;

  @FXML private void initialize() {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream("image/supplier.png");
    Image image = new Image(inputStream);
    imageView.setImage(image);
    // imageView.setImage(new Image("@/../image/supplier.png"));
    productsTv.setItems(getProducts());
    introLbl.textProperty().bind(model.profitProperty().asString("Welcome to " + model.getName() + " (Total profit: $%.2f)"));
    productsTv.getSelectionModel().setCellSelectionEnabled(false);
    productsTv.setSelectionModel(null);
    productsTv.setFocusTraversable(false);
  }

  public final ObservableList<Product> getProducts() {
    return this.model.getProducts().getAllProducts();
  }

  public final void close() {
    this.stage.close();
  }

  public final void order() throws IOException {
    //Product product = productsTv.getSelectionModel().getSelectedItem();
    // if (product != null) {
    Cart c = new Cart(model, Organisation.getLoggedInUser());
    ViewLoader.showStage(c, "/view/Cart/CartView.fxml", "Cart", new FixedStage("/image/cart_icon.png"));
      //this.close();
    // }
  }

}
