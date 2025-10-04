package Service.Order;

import Model.Order;
import Model.OrderDetail;
import javafx.collections.ObservableList;

public interface OrderService {
    ObservableList<OrderDetail> loadTable();

    void AddOrder(Order order);

    void AddOrderDetail(OrderDetail orderDetail);

    String generateOrderID();

    ObservableList<String> loadCustomerDetails();

    ObservableList<String> loadItemDetails();
}
