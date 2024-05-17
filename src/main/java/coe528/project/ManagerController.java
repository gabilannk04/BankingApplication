package coe528.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.*;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

/**
 * FXML ManagerController class
 *
 * @author Gabilann
 */
public class ManagerController implements Initializable{

    @FXML
    private TextField addPW;

    @FXML
    private TextField addUN;

    @FXML
    private ComboBox<String> myCustomers;

    @FXML
    private Text errorText;
    
    protected static String delCustomer;
    protected static String addCustomerUN;
    protected static String addCustomerPW;
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        myCustomers.getItems().addAll(Manager.getManager().getUsernames());
        errorText.setVisible(false);
    }
    
    @FXML
    void addCustomer(MouseEvent event) throws IOException {      
        addCustomerUN = "";
        addCustomerPW = ""; 
        addCustomerUN = addUN.getText();
        addCustomerPW = addPW.getText();
        
        if (addCustomerUN.isBlank() || addCustomerPW.isBlank()) {
            errorText.setText("ERROR: All fields must be filled!");
            errorText.setVisible(true);
        } else {
            App.setRoot("AccountAdded");
        }
    }
    
   
    @FXML
    void deleteCustomer(MouseEvent event) throws IOException, FileNotFoundException {
        //delCustomer = "";
        delCustomer = myCustomers.getSelectionModel().getSelectedItem();
        
        if (delCustomer == null) {
            errorText.setText("ERROR: Select an Account!");
            errorText.setVisible(true);
        } else {
            App.setRoot("AccountDeleted");
        }
    }

    @FXML
    void logoutManager(MouseEvent event) throws FileNotFoundException, IOException {
        Manager.getManager().saveData();
        
        //Reset usernames arraylist
        Manager.getManager().getUsernames().clear();
     
        App.setRoot("MainMenu");
    }
    
}
