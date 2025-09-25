package Controller.Customer;

import DB.DBConnection;
import Model.CustomerDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerEditController implements Initializable {
    ObservableList<CustomerDetails> customerDetails= FXCollections.observableArrayList();
    CustomerService customerService=new CustomerController();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnView;

    @FXML
    private ChoiceBox<String> cmbProvince;

    @FXML
    private ChoiceBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<CustomerDetails> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtSalary;

    @FXML
    void OnBack(ActionEvent event) {

    }

    @FXML
    void OnDelete(ActionEvent event) {
        String Cusid=txtID.getText();

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            String SQL="DELETE FROM Customer WHERE CustID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,Cusid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadCustomerDetails();

    }

    @FXML
    void OnUpdate(ActionEvent event) {
        String Cusid=txtID.getText();
        String title=cmbTitle.getValue();
        String name=txtName.getText();
        LocalDate dob= datePicker.getValue();
        double salary=Double.parseDouble(txtSalary.getText());
        String address=txtAddress.getText();
        String city=txtCity.getText();
        String province=cmbProvince.getValue();
        int postalcode=Integer.parseInt(txtPostalCode.getText());
        try {
            Connection connection=DBConnection.getInstance().getConnection();


            String SQL = "UPDATE Customer set CustTitle=?,CustName=?,DOB=?,salary=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustID=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,title);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,dob);
            preparedStatement.setObject(4,salary);
            preparedStatement.setObject(5,address);
            preparedStatement.setObject(6,city);
            preparedStatement.setObject(7,province);
            preparedStatement.setObject(8,postalcode);
            preparedStatement.setObject(9,Cusid);
            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadCustomerDetails();
    }

    @FXML
    void OnView(ActionEvent event) {
        System.out.println("View button clicked");

        // Set cell value factories
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalcode"));

        loadCustomerDetails();

    }
    public void loadCustomerDetails(){
        System.out.println("Loading customer details...");
        customerDetails.clear();
        customerDetails = customerService.getAllCustomers();
        System.out.println("Customers loaded: " + customerDetails.size());
        tblCustomer.setItems(customerDetails);
    }
    @FXML
    void OnEnter(ActionEvent event) {
        String id=txtID.getText();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL="SELECT * FROM Customer WHERE CustID=?";
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                cmbTitle.setValue(resultSet.getString("CustTitle"));
                txtName.setText(resultSet.getString("CustName"));
                datePicker.setValue(resultSet.getDate("DOB").toLocalDate());
                txtSalary.setText(resultSet.getString("salary"));
                txtAddress.setText(resultSet.getString("CustAddress"));
                txtCity.setText(resultSet.getString("City"));
                cmbProvince.setValue(resultSet.getString("Province"));
                txtPostalCode.setText(resultSet.getString("PostalCode"));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles= FXCollections.observableArrayList("Mr","Mrs","Miss","Ms");
        cmbTitle.setItems(titles);

        ObservableList<String> provinces=FXCollections.observableArrayList("Western Province","Central Province","Southern Province","Northern Province","Eastern Province","North Western Province","North Central Province","Uva Province","Sabaragamuwa Province");
        cmbProvince.setItems(provinces);



        // Set cell value factories
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
    }
}
