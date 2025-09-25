package Controller.Item;

import DB.DBConnection;
import Model.ItemDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    ObservableList<ItemDetails>itemDetails= FXCollections.observableArrayList();
    ItemService itemService=new ItemController();
    Stage stage=new Stage();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnEdit;

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

    private void generateItemCode() {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            String query = "SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            String newId = "P001";

            if (resultSet.next()) {
                String lastId = resultSet.getString(1);

                // Extract the numeric part and increment
                int num = Integer.parseInt(lastId.substring(1)) + 1;
                newId = String.format("P%03d", num);
            }

            txtCode.setText(newId);
            txtCode.setEditable(false); // Make the ID field read-only


        } catch (SQLException e) {
            e.printStackTrace();
            txtCode.setText("Error generating ID");
        }


    }

    @FXML
    void OnAdd(ActionEvent event) {
        ItemDetails itemDetails1=new ItemDetails(
                txtCode.getText(),
                txtDescription.getText(),
                txtSize.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText())

        );
        itemService.addItems(itemDetails1);


    }

    @FXML
    void OnBack(ActionEvent event) {

    }

    @FXML
    void OnEdit(ActionEvent event) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ItemEditForm.fxml"))));
        stage.show();

    }

    @FXML
    void OnView(ActionEvent event) {
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
        generateItemCode();
    }
}
