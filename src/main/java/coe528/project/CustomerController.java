package coe528.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.ListView;


public class CustomerController implements Initializable {

    
    @FXML
    private Text accBalance;

    @FXML
    private Text accLevel;

    @FXML
    private Text accUsername;

    @FXML
    private TextField atmAmount;

    @FXML
    private Text errorText;
    
    @FXML
    private ListView<String> transactionView;
    
    private ArrayList<String> allTransactions;
    
    protected static Customer temp;
    protected static double amount;
    protected static String operationATM;
    protected static double currBalance;
    
    private void setTemp() {
        temp = MainMenuController.currCus;
    }

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        setTemp();
        errorText.setVisible(false);
        accBalance.setText(String.format("%.2f", temp.getBalance()));
        accLevel.setText(temp.getStateLevel().getLevel());
        accUsername.setText(temp.getUsername());
        this.allTransactions = new ArrayList<String>();
        loadTransactions();
        transactionView.getItems().addAll(allTransactions);
    }
    
    private void loadTransactions(){
        ArrayList<String> type = Customer.temp.getTransactionType();     
        ArrayList<String> amount = Customer.temp.getTransactionAmount();
        
        for (int i = 0; i < type.size(); i++) {
            String transaction = type.get(i) + ": $" + amount.get(i);
            allTransactions.add(transaction);
        }
    }
    
    
    @FXML
    void deposit(MouseEvent event) throws IOException {
        
        String initAmountStr = atmAmount.getText();
        
        if (initAmountStr == null || initAmountStr.isBlank()) {
            errorText.setText("ERROR: Fill in All Fields!.");
            errorText.setVisible(true);
        } else {
            double initAmount = Double.parseDouble(initAmountStr);
            String formatStr = String.format("%.2f", initAmount);
            amount = Double.parseDouble(formatStr);

            if (amount <= 0) {
                errorText.setText("ERROR: Invalid Amount entered!");
                errorText.setVisible(true);
            } else {
                operationATM = "Deposit";
                currBalance = temp.getBalance();
                App.setRoot("ATMConfirmation");
            }
        }
        
    }

    @FXML
    void logoutCustomer(MouseEvent event) throws IOException {
        Customer.temp.saveCustomerData();
        App.setRoot("MainMenu");
    }

    @FXML
    void makeTransaction(MouseEvent event) throws IOException {
        App.setRoot("MakeTransaction");
    }

    
    @FXML
    void withdraw(MouseEvent event) throws IOException {
        String initAmountStr = atmAmount.getText();
        
        if (initAmountStr == null || initAmountStr.isBlank()) {
            errorText.setText("ERROR: Fill in All Fields!.");
            errorText.setVisible(true);
        } else {
            double initAmount = Double.parseDouble(initAmountStr);
            String formatStr = String.format("%.2f", initAmount);
            amount = Double.parseDouble(formatStr);
            double currAccBal = temp.getBalance();

            if (amount <= 0) {
                errorText.setText("ERROR: Invalid Amount entered!");
                errorText.setVisible(true);
            } else if (amount > currAccBal) {
                errorText.setText("ERROR: Insufficient Funds!");
                errorText.setVisible(true);
            } else {
                operationATM = "Withdraw";
                currBalance = temp.getBalance();
                App.setRoot("ATMConfirmation");
            }
        }
        
        
    }

}
