package Service.Impl;

import Model.Order;
import Model.OrderDetail;
import Repository.OrderRepository;
import Repository.Impl.OrderRepositoryImpl;
import Service.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository=new OrderRepositoryImpl();
    ObservableList<OrderDetail> orderDetails= FXCollections.observableArrayList();
    Order order=new Order();
    OrderDetail orderDetail=new OrderDetail();


    @Override
    public ObservableList<OrderDetail> loadTable() {
        orderDetails.clear(); // clear old data

        try {
            ResultSet resultSet = orderRepository.LoadTable();

            while (resultSet.next()) {
                orderDetails.add(new OrderDetail(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("OrderQTY"),
                        resultSet.getDouble("Discount")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error loading order details", e);
        }

        return orderDetails; // return after loop
    }


    @Override
    public void AddOrder(Order order) {
        PreparedStatement preparedStatement=orderRepository.AddOrder();
        try {
            preparedStatement.setObject(1,order.getOrderId());
            preparedStatement.setObject(2,order.getOrderDate());
            preparedStatement.setObject(3,order.getCustId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void AddOrderDetail(OrderDetail orderDetail) {
        PreparedStatement preparedStatement=orderRepository.AddOrderDetail();
        try {

            preparedStatement.setObject(1,orderDetail.getOrderId());
            preparedStatement.setObject(2,orderDetail.getItemCode());
            preparedStatement.setObject(3,orderDetail.getQty());
            preparedStatement.setObject(4,orderDetail.getDiscount());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateOrderID() {
        String newId = "D001";

        try {
            ResultSet resultSet=orderRepository.generateOrderID();
            if (resultSet.next()) {
                String lastId = resultSet.getString(1);

                // Extract the numeric part and increment
                int num = Integer.parseInt(lastId.substring(1)) + 1;
                newId = String.format("D%03d", num);
            }
            return newId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<String> loadCustomerDetails() {

        ObservableList<String> customers = FXCollections.observableArrayList();

            try {
                ResultSet resultSet=orderRepository.LoadCustomerDetails();
                while (resultSet.next()) {
                    customers.add(resultSet.getString("CustID"));

                }
                return customers;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



    }

    @Override
    public ObservableList<String> loadItemDetails() {

        ObservableList<String> codes = FXCollections.observableArrayList();

            try {
                ResultSet resultSet=orderRepository.loadItemDetails();
                while (resultSet.next()){
                    codes.add(resultSet.getString("ItemCode"));
                }
                return codes;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



    }
}
