package Service.Item;

import DB.DBConnection;
import Model.ItemDetails;
import Repository.ItemRepository;
import Repository.ItemRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository=new ItemRepositoryImpl();
    @Override
    public void addItems(ItemDetails itemDetails1) {
        PreparedStatement preparedStatement=itemRepository.addItems();

        try {
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
            ResultSet resultSet = itemRepository.getAllItems();

            while (resultSet.next()) {
                itemDetails.add(new ItemDetails(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching items", e);
        }

        return itemDetails;


        }





    @Override
    public void UpdateItems(String code, ItemDetails itemDetails1) {
        PreparedStatement preparedStatement=itemRepository.updateItems();
        try {
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

    @Override
    public String generatecode() {
        String newId = "P001";

        try {
            ResultSet resultSet=itemRepository.generatecode();
            if (resultSet.next()) {

                String lastId = resultSet.getString(1);

                // Extract the numeric part and increment
                int num = Integer.parseInt(lastId.substring(1)) + 1;
                newId = String.format("P%03d", num);

            }
            return newId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public ItemDetails enter(String code) {

        try {
            PreparedStatement preparedStatement=itemRepository.enter();
            preparedStatement.setObject(1,code);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){

                ItemDetails itemDetails1=new ItemDetails(
                        code,
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")




                );
                return itemDetails1;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;



    }







}
