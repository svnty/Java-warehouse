package controller.User;

import java.io.IOException;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import model.*;

public class UserDashboardController extends Controller<Organisation> {
  @FXML private Label welcomeLbl;
  @FXML private ImageView imageView;
  @FXML private Button shopBtn;
  private User u = Organisation.getLoggedInUser();

  @FXML public void initialize() {  
    String name = u.getClass().getName().split("\\.")[1].toString();
    welcomeLbl.setText("Welcome to the " + name + " Dashboard " + u.getFirstName() + "!");
    imageView.setImage(new Image("/image/user.png"));
    shopBtn.setText(name.equals("Customer") ? "Shop" : "Manage");
    shopBtn.setOnAction(e -> {
      try {
        shop();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });
  }

  public void shop() throws IOException {
    if (u instanceof Customer) {
      ViewLoader.showStage(this.model.getSuppliers(), "/view/Supplier/SupplierListView.fxml", "Supplier List", new FixedStage("/image/supplier_icon.png"));
    } else if (u instanceof Manager) {
      ViewLoader.showStage(((Manager) u).getSuppliers(), "/view/Supplier/SupplierListView.fxml", "Supplier List", new FixedStage("/image/supplier_icon.png"));
    }
  }

  @FXML public void orderHistory() throws IOException {
    ViewLoader.showStage(u, "/view/User/OrderHistoryView.fxml", "Order History", new FixedStage("/image/user_icon.png"));
  }

  @FXML public void close() {
    stage.close();
  }
}
