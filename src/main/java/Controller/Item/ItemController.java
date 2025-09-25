package Controller.Item;

import DB.DBConnection;
import Model.ItemDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemService {
    @Override
    public void addItems(ItemDetails itemDetails1) {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            String SQL="INSERT INTO Item VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,itemDetails1.getCode());
            preparedStatement.setObject(2,itemDetails1.getDescription());
            preparedStatement.setObject(3,itemDetails1.getSize());
            preparedStatement.setObject(4,itemDetails1.getPrice());
            preparedStatement.setObject(5,itemDetails1.getQty());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    ObservableList<ItemDetails>itemDetails= FXCollections.observableArrayList();

    @Override
    public ObservableList<ItemDetails> getAllItems() {
        itemDetails.clear();
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM Item";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                itemDetails.add(new ItemDetails(
                                resultSet.getString("ItemCode"),
                                resultSet.getString("Description"),
                                resultSet.getString("PackSize"),
                                resultSet.getDouble("UnitPrice"),
                                resultSet.getInt("QtyOnHand")
                        )
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDetails;


    }

    @Override
    public void UpdateItems(String code, ItemDetails itemDetails1) {


        try {
            Connection connection=DBConnection.getInstance().getConnection();
            String SQL="UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode=?";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,itemDetails1.getDescription());
            preparedStatement.setObject(2,itemDetails1.getSize());
            preparedStatement.setObject(3,itemDetails1.getPrice());
            preparedStatement.setObject(4,itemDetails1.getQty());
            preparedStatement.setObject(5,code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void DeleteItem(String code) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            String SQL="DELETE FROM Item WHERE ItemCode=?";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
