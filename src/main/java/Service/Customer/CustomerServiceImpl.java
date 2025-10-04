package Service.Customer;

import DB.DBConnection;
import Model.CustomerDetails;
import Repository.CustomerRepository;
import Repository.CustomerRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository= new CustomerRepositoryImpl();
    ObservableList<CustomerDetails> customerDetails= FXCollections.observableArrayList();


    @Override
    public ObservableList<CustomerDetails> getAllCustomers() {
        customerDetails.clear();

        try {
            ResultSet resultSet = customerRepository.getAllCustomers();

            while (resultSet.next()) {
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
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Or log properly
        }

        return customerDetails;
    }

    @Override
    public void AddCustomer(CustomerDetails customerDetails1) {
        PreparedStatement preparedStatement=customerRepository.AddCustomer();
        try {
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

    @Override
    public String generatecustomerID() {

        String newId = "C001";


        try {
            ResultSet resultSet=customerRepository.generatecustomerid();
            if (resultSet.next()) {
                String lastId = resultSet.getString(1);

                // Extract the numeric part and increment
                int num = Integer.parseInt(lastId.substring(1)) + 1;
                newId = String.format("C%03d", num); // Formats as C001, C002, etc.
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newId;


    }

    @Override
    public void deleteCustomer(String cusid) {

        PreparedStatement preparedStatement=customerRepository.deleteCustomer();
        try {
            preparedStatement.setObject(1,cusid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateCustomer(CustomerDetails cusdetails) {
        PreparedStatement preparedStatement=customerRepository.updateCustomer();
        try {
            preparedStatement.setObject(1,cusdetails.getTitle());
            preparedStatement.setObject(2,cusdetails.getName());
            preparedStatement.setObject(3,cusdetails.getDob());
            preparedStatement.setObject(4,cusdetails.getSalary());
            preparedStatement.setObject(5,cusdetails.getAddress());
            preparedStatement.setObject(6,cusdetails.getCity());
            preparedStatement.setObject(7,cusdetails.getProvince());
            preparedStatement.setObject(8,cusdetails.getPostalcode());
            preparedStatement.setObject(9,cusdetails.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public CustomerDetails enter(String id) throws SQLException {
        ResultSet resultSet=customerRepository.enter(id);
        if(resultSet.next()){
            CustomerDetails customerDetails1= new CustomerDetails(
                    id,
                    resultSet.getString("CustTitle"),
                    resultSet.getString("CustName"),
                    resultSet.getDate("DOB").toLocalDate(),
                    resultSet.getDouble("salary"),
                    resultSet.getString("CustAddress"),
                    resultSet.getString("City"),
                    resultSet.getString("Province"),
                    resultSet.getInt("PostalCode")







            );
            return customerDetails1;





        }
        return null;





    }


}
