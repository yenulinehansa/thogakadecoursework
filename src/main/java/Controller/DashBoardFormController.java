package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    Stage CustomerManagement=new Stage();
    Stage ItemManagement=new Stage();
    Stage OrderManagement=new Stage();

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnItem;

    @FXML
    private Button btnOrder;

    @FXML
    void CustomerOnClick(ActionEvent event) throws IOException {
        CustomerManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/CustomerForm.fxml"))));
        CustomerManagement.show();

    }

    @FXML
    void ItemOnClick(ActionEvent event) throws IOException {
        ItemManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ItemForm.fxml"))));
        ItemManagement.show();
    }

    @FXML
    void OrderOnClick(ActionEvent event) throws IOException {
        OrderManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/OrderForm.fxml"))));
        OrderManagement.show();

    }

}

