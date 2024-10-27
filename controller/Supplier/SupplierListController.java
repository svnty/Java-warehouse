package controller.Supplier;

import java.io.IOException;
import java.io.InputStream;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import model.*;

public class SupplierListController extends Controller<Suppliers> {
  @FXML private ImageView imageView;
  @FXML private ListView<Supplier> supplierLv;
  @FXML private Button shopBtn;
  
  @FXML private void initialize() {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("image/supplier.png");
    Image image = new Image(inputStream);
    imageView.setImage(image);
    supplierLv.setItems(getSuppliers());
    shopBtn.disableProperty().bind(supplierLv.getSelectionModel().selectedItemProperty().isNull());
  }

  public final ObservableList<Supplier> getSuppliers() {
    return model.getSuppliers();
  }

  private Supplier getSelectedSupplier() {
    return supplierLv.getSelectionModel().getSelectedItem();
  }

  @FXML
  public void close() {
    stage.close();
  }

  @FXML
  public void shop() throws IOException {
    Supplier supplier = getSelectedSupplier();
    if (supplier != null) {
      if (Organisation.getLoggedInUser() instanceof Customer) {
        ViewLoader.showStage(supplier, "/view/Supplier/SupplierCustomerView.fxml", "Supplier: " + supplier.getName(), new FixedStage("/image/supplier_icon.png"));
        this.close();
      } else if (Organisation.getLoggedInUser() instanceof Manager) {
        ViewLoader.showStage(supplier, "/view/Supplier/SupplierManagerView.fxml", "Supplier: " + supplier.getRegion(), new FixedStage("/image/supplier_icon.png"));
        this.close();
      }
    }
  }
}
