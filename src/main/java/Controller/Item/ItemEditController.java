package Controller.Item;

import DB.DBConnection;
import Model.ItemDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemEditController implements Initializable {
    ObservableList<ItemDetails>itemDetails= FXCollections.observableArrayList();
    ItemService itemService=new ItemController();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnView;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableView<ItemDetails> tblItems;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSize;

    @FXML
    void OnBack(ActionEvent event) {

    }

    @FXML
    void onDelete(ActionEvent event) {
        String code=txtCode.getText();
        itemService.DeleteItem(code);

    }

    @FXML
    void onUpdate(ActionEvent event) {
        String code=txtCode.getText();
        ItemDetails itemDetails1=new ItemDetails(
                txtCode.getText(),
                txtDescription.getText(),
                txtSize.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText())

        );
        itemService.UpdateItems(code,itemDetails1);


    }

    @FXML
    void onView(ActionEvent event) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadItemDetails();

    }
    public void loadItemDetails(){
        itemDetails=itemService.getAllItems();
        tblItems.setItems(itemDetails);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void OnEnter(ActionEvent event) {
        String code=txtCode.getText();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM Item WHERE ItemCode=?";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,code);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){

                txtDescription.setText(resultSet.getString("Description"));
                txtSize.setText(resultSet.getString("PackSize"));
                txtPrice.setText(resultSet.getString("UnitPrice"));
                txtQty.setText(resultSet.getString("QtyOnHand"));



            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
