package Controller.Customer;

import Model.CustomerDetails;
import Service.Impl.CustomerServiceImpl;
import Service.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    ObservableList <CustomerDetails>customerDetails=FXCollections.observableArrayList();
    CustomerService customerService=new CustomerServiceImpl();
    Stage stage=new Stage();

    @FXML
    private Button btnEdit;


    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
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
    void OnBack(ActionEvent event) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashBoard.fxml"))));
        stage.show();

    }

    private void generateCustomerId() {
        String newId=customerService.generatecustomerID();
        txtID.setText(newId);
        txtID.setEditable(false);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles= FXCollections.observableArrayList("Mr","Mrs","Miss","Ms");
        cmbTitle.setItems(titles);

        ObservableList<String> provinces=FXCollections.observableArrayList("Western Province","Central Province","Southern Province","Northern Province","Eastern Province","North Western Province","North Central Province","Uva Province","Sabaragamuwa Province");
        cmbProvince.setItems(provinces);

        generateCustomerId();

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
    @FXML
    void OnAdd(ActionEvent event) {
        CustomerDetails customerDetails1=new CustomerDetails(
                txtID.getText(),
                cmbTitle.getValue(),
                txtName.getText(),
                datePicker.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                cmbProvince.getValue(),
                Integer.parseInt(txtPostalCode.getText())
        );
        customerService.AddCustomer(customerDetails1);
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
    void OnEdit(ActionEvent event) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/CustomerEditForm.fxml"))));
        stage.show();
    }

}
