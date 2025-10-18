package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface OrderRepository {
    ResultSet LoadTable();

    PreparedStatement AddOrder();

    ResultSet generateOrderID();

    ResultSet LoadCustomerDetails();

    ResultSet loadItemDetails();

    PreparedStatement AddOrderDetail();
}
