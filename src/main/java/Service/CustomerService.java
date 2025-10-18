package Service;

import Model.CustomerDetails;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CustomerService {





    ObservableList<CustomerDetails> getAllCustomers();


    void AddCustomer(CustomerDetails customerDetails1);


    String generatecustomerID();

    void deleteCustomer(String cusid);

    void updateCustomer(CustomerDetails cusdetails);


    CustomerDetails enter(String id) throws SQLException;
}
