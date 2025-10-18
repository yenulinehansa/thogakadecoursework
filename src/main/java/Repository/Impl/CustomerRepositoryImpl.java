package Repository.Impl;

import DB.DBConnection;
import Repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public ResultSet getAllCustomers() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM Customer";
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PreparedStatement AddCustomer() {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            return preparedStatement;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet generatecustomerid() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "SELECT CustID FROM Customer ORDER BY CustID DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public PreparedStatement deleteCustomer() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL="DELETE FROM Customer WHERE CustID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PreparedStatement updateCustomer() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE Customer set CustTitle=?,CustName=?,DOB=?,salary=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustID=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement = connection.prepareStatement(SQL);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public ResultSet enter(String id) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM Customer WHERE CustID=?";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
