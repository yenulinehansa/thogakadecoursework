package Controller.Customer;

import Model.CustomerDetails;
import javafx.collections.ObservableList;

public interface CustomerService {





    ObservableList<CustomerDetails> getAllCustomers();


    void AddCustomer(CustomerDetails customerDetails1);


}
