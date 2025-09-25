package Controller.Order;

import DB.DBConnection;
import Model.Order;
import Model.OrderDetail;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    Stage stage = new Stage();
    OrderService orderService = new OrderController();
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
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            String newId = "D001";

            if (resultSet.next()) {
                String lastId = resultSet.getString(1);

                // Extract the numeric part and increment
                int num = Integer.parseInt(lastId.substring(1)) + 1;
                newId = String.format("D%03d", num);
            }

            txtId.setText(newId);
            txtId.setEditable(false); // Make the ID field read-only


        } catch (SQLException e) {
            e.printStackTrace();
            txtId.setText("Error generating ID");
        }

    }

    private void loadCustomerIds() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT CustID FROM Customer";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            ObservableList<String> customers = FXCollections.observableArrayList();
            while (resultSet.next()) {
                customers.add(resultSet.getString("CustID"));
            }
            combCust.setItems(customers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadItemCodes() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT ItemCode FROM Item";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            ObservableList<String> codes = FXCollections.observableArrayList();
            while (resultSet.next()) {
                codes.add(resultSet.getString("ItemCode"));
            }
            combCode.setItems(codes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}