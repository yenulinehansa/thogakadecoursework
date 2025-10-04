package Controller.Order;

import Model.Order;
import Model.OrderDetail;
import Service.Order.OrderServiceImpl;
import Service.Order.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    Stage stage = new Stage();
    OrderService orderService = new OrderServiceImpl();
    ObservableList<Order>orders=FXCollections.observableArrayList();
    ObservableList<OrderDetail>orderDetails=FXCollections.observableArrayList();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnViewDetails;

    @FXML
    private ChoiceBox<String> combCode;

    @FXML
    private ChoiceBox<String> combCust;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;


    @FXML
    void OnBack(ActionEvent event) {

    }

    @FXML
    void onOrder(ActionEvent event) {
        Order order=new Order(
                txtId.getText(),
                datepicker.getValue(),
                combCust.getValue()

        );
        OrderDetail orderDetail=new OrderDetail(
                txtId.getText(),
                combCode.getValue(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtDiscount.getText())
        );
        orderService.AddOrder(order);
        orderService.AddOrderDetail(orderDetail);




    }

    @FXML
    void onViewDetails(ActionEvent event) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/OrderDetailsForm.fxml"))));
        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateOrderId();
        loadCustomerIds();
        loadItemCodes();
    }

    private void generateOrderId() {
        String newId=orderService.generateOrderID();
        txtId.setText(newId);
        txtId.setEditable(false);

    }

    private void loadCustomerIds() {
        ObservableList <String> customers=orderService.loadCustomerDetails();
       combCust.setItems(customers);
    }
    private void loadItemCodes() {
        ObservableList <String> items=orderService.loadItemDetails();
        combCode.setItems(items);


    }
}