package Repository.Impl;

import DB.DBConnection;
import Repository.ItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public PreparedStatement addItems() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL="INSERT INTO Item VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet getAllItems() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM Item";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            ResultSet resultSet=preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PreparedStatement updateItems() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL="UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode=?";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet generatecode() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PreparedStatement enter() {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM Item WHERE ItemCode=?";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
