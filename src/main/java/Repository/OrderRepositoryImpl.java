package Repository;

import DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository{
    @Override
    public ResultSet LoadTable() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM OrderDetail";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            ResultSet resultSet=preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PreparedStatement AddOrder() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL="INSERT INTO Orders VALUES(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet generateOrderID() {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet LoadCustomerDetails() {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT CustID FROM Customer";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet loadItemDetails() {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT ItemCode FROM Item";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
