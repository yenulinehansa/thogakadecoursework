package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ItemRepository {
    PreparedStatement addItems();

    ResultSet getAllItems();

    PreparedStatement updateItems();

    ResultSet generatecode();

    PreparedStatement enter();
}
