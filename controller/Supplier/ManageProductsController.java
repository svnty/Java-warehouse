package controller.Supplier;

import java.io.InputStream;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import model.Exceptions.*;

public class ManageProductsController extends Controller<Supplier> {
  @FXML private ImageView imageView;
  @FXML private ListView<Product> listView;
  @FXML private Button delistBtn;

  @FXML
  private void initialize() {
    listView.setItems(model.getProducts().getAvailableProducts());
    // imageView.setImage(new javafx.scene.image.Image("@/../image/supplier.png"));
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("image/supplier.png");
    Image image = new Image(inputStream);
    imageView.setImage(image);
    listView.getSelectionModel().selectedItemProperty().addListener(
      (observable, oldProduct, newProduct) -> delistBtn.setDisable(!newProduct.isAvailable())
    );
  }

  @FXML
  public void close() {
    stage.close();
  }

  @FXML
  public void remove() {
    Product product = listView.getSelectionModel().getSelectedItem();
    if (product != null) {
      model.getProducts().removeProduct(product);
    }
  }

  @FXML
  public void delist() {
    Product product = listView.getSelectionModel().getSelectedItem();
    if (product != null) {
      product.delist();
      delistBtn.setDisable(true);
    }
  }
}