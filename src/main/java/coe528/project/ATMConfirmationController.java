package coe528.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import java.io.*;

public class ATMConfirmationController implements Initializable{

    @FXML
    private Text currBalance;

    @FXML
    private Text depAmount;

    @FXML
    private Text initAction;
    
    @FXML
    private Text confirmMessage;
    
    @FXML
    private Text initAmountPrompt;

    @FXML
    private Text initBalPrompt;

    @FXML
    private Text initPrompt;
    
    @FXML
    private Button confirmButton;
    
    private static double amount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        amount = CustomerController.amount;
        initAction.setText(CustomerController.operationATM);
        depAmount.setText(String.format("%.2f", amount));
        currBalance.setText(String.format("%.2f", CustomerController.currBalance));
        confirmMessage.setVisible(false);
    }
    
    @FXML
    void confirmATM(MouseEvent event) throws IOException{
        String amountStr = String.valueOf(amount);
        if (initAction.getText().equals("Deposit")) {
            CustomerController.temp.deposit(amount);
            CustomerController.temp.setTransaction("Deposit", amountStr);
        } else if (initAction.getText().equals("Withdraw")) {
            CustomerController.temp.withdraw(amount);
            CustomerController.temp.setTransaction("Withdrawal", amountStr);
        }
        
        CustomerController.temp.checkState();
        
        confirmMessage.setText("Transaction Completed!");
        confirmMessage.setVisible(true);
        initAction.setVisible(false);
        depAmount.setVisible(false);
        currBalance.setVisible(false);
        confirmButton.setVisible(false);
        initPrompt.setVisible(false);
        initBalPrompt.setVisible(false);
        initAmountPrompt.setVisible(false);
    }

    @FXML
    void swtichToCustomer(MouseEvent event) throws IOException, FileNotFoundException{
        CustomerController.temp.saveCustomerData();
        App.setRoot("Customer");
    }

}
