package Controller.Customer;

import Model.CustomerDetails;
import Service.Customer.CustomerServiceImpl;
import Service.Customer.CustomerService;
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
    CustomerService customerService=new CustomerServiceImpl();

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
        customerService.deleteCustomer(Cusid);

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

        CustomerDetails cusdetails=new CustomerDetails(
                Cusid,
                title,
                name,
                dob,
                salary,
                address,
                city,
                province,
                postalcode
        );
        customerService.updateCustomer(cusdetails);

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
    void OnEnter(ActionEvent event) throws SQLException {
        String id=txtID.getText();
        CustomerDetails cusdetails=customerService.enter(id);
        cmbTitle.setValue(cusdetails.getTitle());
        txtName.setText(cusdetails.getName());
        datePicker.setValue(cusdetails.getDob());
        txtSalary.setText(String.valueOf(cusdetails.getSalary()));
        txtAddress.setText(cusdetails.getAddress());
        txtCity.setText(cusdetails.getCity());
        cmbProvince.setValue(cusdetails.getProvince());
        txtPostalCode.setText(String.valueOf(cusdetails.getPostalcode()));








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
