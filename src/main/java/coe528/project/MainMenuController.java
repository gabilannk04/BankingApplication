package coe528.project;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.scene.text.Text;

/**
 * FXML MainMenuController class
 *
 * @author Gabilann
 */
public class MainMenuController {

    @FXML
    private Text accNotFoundLabel;
    
    @FXML
    private PasswordField passwordIn;

    @FXML
    private TextField usernameIn;
    
    @FXML
    private Text fillInAllFieldsLabel;
    
    @FXML
    private Text invalidInfo;
    
    protected static Customer currCus;
    
    

    @FXML
    void login(MouseEvent event) throws IOException {
        String strUN = "";
        String strPW = "";
        boolean loginMan = false;
        boolean loginCus = false;
        
        strUN = usernameIn.getText();
        strPW = passwordIn.getText();
        
        accNotFoundLabel.setVisible(false);
        fillInAllFieldsLabel.setVisible(false);
        invalidInfo.setVisible(false);
        
        if (strUN.isBlank() || strPW.isBlank()) {
            fillInAllFieldsLabel.setVisible(true);
            invalidInfo.setVisible(false);
            accNotFoundLabel.setVisible(false);
        } else {
            AccountType temp = AccountType.findAccount(strUN, strPW);
            
            if (temp == null) {
                //errorLabel.setText("Account Not Found!");
                fillInAllFieldsLabel.setVisible(false);
                invalidInfo.setVisible(false);
                accNotFoundLabel.setVisible(true);
            } else {
                if (temp.getRole().equals("manager")) {
                    loginMan = Manager.getManager().loginManager(strUN, strPW);
                } else if (temp.getRole().equals("customer")){
                    Customer tempCus = new Customer(temp.getUsername(), temp.getPassword());
                    loginCus = tempCus.loginValid(strUN, strPW, tempCus);
                }
                
                if (loginMan) {
                    Manager.getManager().initLoadCustomers();
                    App.setRoot("Manager");
                } else if (loginCus) {
                    currCus = Customer.loadCustomer(strUN);
                    App.setRoot("Customer");
                    
                } else {
                    invalidInfo.setVisible(true);
                    fillInAllFieldsLabel.setVisible(false);
                    accNotFoundLabel.setVisible(false);
                }
                
            }
            
        }
    }
}
