package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MakeTransactionController implements Initializable {

    @FXML
    private Text accBalance;

    @FXML
    private Text accFee;

    @FXML
    private Text accLevel;

    @FXML
    private Text amountPrompt;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField transactionAmount;

    @FXML
    private Text errorMessage;
    
    @FXML
    private Text transactionMessage;
   
    @FXML
    private Text moneySymbol;

    @FXML
    private ComboBox<String> transactionOptions;

    private String[] legalTransactions = {"Cell Phone", "Credit Card", "Entertainment", "Food","Insurance", "Investments","Rent", "Transportation", "Travel", "Utilities"};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transactionOptions.getItems().addAll(legalTransactions);
        accBalance.setText(String.format("%.2f", CustomerController.temp.getBalance()));
        accFee.setText(String.format("%.2f", CustomerController.temp.getStateLevel().getFee()));
        accLevel.setText(CustomerController.temp.getStateLevel().getLevel());
        errorMessage.setVisible(false);
        
    }

    
    @FXML
    void completeTransaction(MouseEvent event) throws IOException {
        String amountStr = transactionAmount.getText();
        String transaction = transactionOptions.getSelectionModel().getSelectedItem();
        
        if (amountStr == null || amountStr.isBlank() || transaction == null) {
            errorMessage.setText("ERROR: Fill in All Fields!");
            errorMessage.setVisible(true);
        } else {
            double amount = Double.parseDouble(amountStr);
        
            double currAccBal = Double.parseDouble(accBalance.getText());
            double currAccFee = Double.parseDouble(accFee.getText());

            if (amount < 50) {
                errorMessage.setText("ERROR: Amount must be $50 or more.");
                errorMessage.setVisible(true);
            } else if ((amount+currAccFee) > currAccBal) {
                errorMessage.setText("ERROR: Insufficient Funds!");
                errorMessage.setVisible(true);
            } else {
                
                Customer.temp.makeTransaction(transaction, amount);
                accBalance.setText(String.format("%.2f", CustomerController.temp.getBalance()));
                accFee.setText(String.format("%.2f", CustomerController.temp.getStateLevel().getFee()));
                accLevel.setText(CustomerController.temp.getStateLevel().getLevel());
                transactionMessage.setText("Transaction Completed!");
                transactionMessage.setVisible(true);
                amountPrompt.setVisible(false);
                confirmButton.setVisible(false);
                transactionAmount.setVisible(false);
                transactionOptions.setVisible(false);
                errorMessage.setVisible(false);
                moneySymbol.setVisible(false);
        }
        }
        
    }

    @FXML
    void swtichToCustomer(MouseEvent event)  throws IOException {
        Customer.temp.saveCustomerData();
        App.setRoot("Customer");
    }

}
