package Controller.Order;

import Model.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailController implements Initializable {
    ObservableList <OrderDetail> orderDetails= FXCollections.observableArrayList();
    OrderService orderService=new OrderController();


    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<OrderDetail> tblOrderDetails;

    public void LoadOrderDetails(){
        orderDetails=orderService.loadTable();
        tblOrderDetails.setItems(orderDetails);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        LoadOrderDetails();

    }
}
