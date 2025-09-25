package Controller.Order;

import DB.DBConnection;
import Model.Order;
import Model.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController implements OrderService{
    ObservableList<OrderDetail> orderDetails= FXCollections.observableArrayList();
    Order order=new Order();
    OrderDetail orderDetail=new OrderDetail();
    @Override
    public ObservableList<OrderDetail> loadTable() {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM OrderDetail";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                orderDetails.add(new OrderDetail(
                                resultSet.getString("OrderID"),
                                resultSet.getString("ItemCode"),
                                resultSet.getInt("OrderQTY"),
                                resultSet.getDouble("Discount")



                        )
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetails;

    }

    @Override
    public void AddOrder(Order order) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            String SQL="INSERT INTO Orders VALUES(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
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
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            String SQL="INSERT INTO OrderDetail VALUES(?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,orderDetail.getOrderId());
            preparedStatement.setObject(2,orderDetail.getItemCode());
            preparedStatement.setObject(3,orderDetail.getQty());
            preparedStatement.setObject(4,orderDetail.getDiscount());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
