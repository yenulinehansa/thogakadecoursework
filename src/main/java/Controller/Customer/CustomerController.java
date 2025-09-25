package Controller.Customer;

import DB.DBConnection;
import Model.CustomerDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class CustomerController implements CustomerService {
    ObservableList<CustomerDetails> customerDetails= FXCollections.observableArrayList();


    @Override
    public ObservableList<CustomerDetails> getAllCustomers() {
        customerDetails.clear();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM Customer";
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                customerDetails.add(new CustomerDetails(
                                resultSet.getString("CustId"),
                                resultSet.getString("CustTitle"),
                                resultSet.getString("CustName"),
                                resultSet.getDate("DOB").toLocalDate(),
                                resultSet.getDouble("salary"),
                                resultSet.getString("CustAddress"),
                                resultSet.getString("City"),
                                resultSet.getString("Province"),
                                resultSet.getInt("PostalCode")

                        )
                );
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerDetails;



    }

    @Override
    public void AddCustomer(CustomerDetails customerDetails1) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,customerDetails1.getId());
            preparedStatement.setObject(2,customerDetails1.getTitle());
            preparedStatement.setObject(3,customerDetails1.getName());
            preparedStatement.setObject(4,customerDetails1.getDob());
            preparedStatement.setObject(5,customerDetails1.getSalary());
            preparedStatement.setObject(6,customerDetails1.getAddress());
            preparedStatement.setObject(7,customerDetails1.getCity());
            preparedStatement.setObject(8,customerDetails1.getProvince());
            preparedStatement.setObject(9,customerDetails1.getPostalcode());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
