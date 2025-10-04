package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface CustomerRepository {
    ResultSet getAllCustomers();

    PreparedStatement AddCustomer();

    ResultSet generatecustomerid();

    PreparedStatement deleteCustomer();

    PreparedStatement updateCustomer();

    ResultSet enter(String id);
}
