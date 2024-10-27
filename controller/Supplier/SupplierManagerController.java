package controller.Supplier;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import model.Exceptions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public class SupplierManagerController extends Controller<Supplier> {
  @FXML private Label introLbl;
  @FXML private ImageView imageView;
  @FXML private TableView<Product> tableView;
  @FXML private CheckBox filterCbx;

  @FXML private void initialize() {
    // imageView.setImage(new javafx.scene.image.Image("@/../image/supplier.png"));
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("image/supplier.png");
    Image image = new Image(inputStream);
    imageView.setImage(image);
    introLbl.textProperty().bind(model.profitProperty().asString("Welcome to " + model.getName() + " (Total profit: $%.2f)"));
    tableView.setItems(model.getProducts().getAllProducts());
  }

  @FXML public void filter() {
    if (filterCbx.isSelected()) {
      tableView.setItems(model.getProducts().getAvailableProducts());
    } else {
      tableView.setItems(model.getProducts().getAllProducts());
    }
  }

  public final Supplier getModel() {
    return model;
  }

  @FXML public void manage() throws IOException {
    // Product product = tableView.getSelectionModel().getSelectedItem();
    // if (product != null) {
      ViewLoader.showStage(this.model, "/view/Supplier/ManageProductsView.fxml", "Managing",  new FixedStage("/image/supplier_icon.png"));
    // }
  }

  @FXML public void order() throws IOException {
    Cart c = new Cart(model, Organisation.getLoggedInUser());
    ViewLoader.showStage(c, "/view/Cart/CartView.fxml", "Cart", new FixedStage("/image/cart_icon.png"));
  }

  @FXML public void close() {
    stage.close();
  }
}
