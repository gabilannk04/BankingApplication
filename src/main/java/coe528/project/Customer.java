package coe528.project;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Gabilann
 */
public class Customer extends AccountType {
    /**
     * Overview: Customer
     */

    private double balance;
    private CustomerState level;
    private ArrayList<String> transactionType;
    private ArrayList<String> transactionAmount;
    protected static Customer temp;
    
    public Customer (String un, String pw) {
        super(un, pw, "customer");
        this.balance = 100.00;
        this.transactionType = new ArrayList<>();
        this.transactionAmount = new ArrayList<>();
        this.level = new SilverLevel(this);
    }
    
    protected CustomerState getStateLevel() {
        return this.level;
    }

    protected void setStateLevel(CustomerState newLevel) {
        this.level = newLevel;
    }

    protected double getBalance() {
        return this.balance;
    }
    

    
    protected void setBalance(double newAmount) {
        this.balance = newAmount;
    }
    

    
    protected void setTransaction(String transaction, String amount) {
        double amountDbl = Double.parseDouble(amount);
        
        this.transactionType.add(transaction);
        this.transactionAmount.add(String.format("%.2f", amountDbl));
    }
    
    protected ArrayList<String> getTransactionType() {
        return this.transactionType;
    }
    
    protected ArrayList<String> getTransactionAmount() {
        return this.transactionAmount;
    }
    
    public static Customer loadCustomer(String un) throws FileNotFoundException, IOException {
        double accBalance;
        String accLevel;
        
        File myFile = new File(un + ".txt");
        Scanner myReader = new Scanner(myFile);
        
        String initData = myReader.nextLine();
        String[] accDetails = initData.split(";");
        accBalance = Double.parseDouble(accDetails[3]);
        accLevel = accDetails[4];
        
        temp = new Customer(accDetails[0], accDetails[1]);
        temp.setBalance(accBalance);
        temp.defineState(accLevel);

        
        while (myReader.hasNextLine()) {
            String transaction = myReader.nextLine();
            String[] details = transaction.split(";");
            temp.setTransaction(details[0], details[1]);
        }
        
        myReader.close();
        return temp;   
    }


    private void defineState(String accLevel) {
        if (accLevel.equals("Silver")) {
            temp.level = new SilverLevel(this);
        } else if (accLevel.equals("Gold")) {
            temp.setStateLevel(new GoldLevel(this));
        } else {
            temp.setStateLevel(new PlatinumLevel(this));
        }
    }
    
    protected void deposit(double amount) throws IOException {
        double updateAmount = getBalance() + amount;
        setBalance(updateAmount);
        checkState();
    }
    
    protected void withdraw(double amount) throws IOException {
        double updateAmount = getBalance() - amount;
        setBalance(updateAmount);
        checkState();
    }
    
    protected void makeTransaction(String transaction, double amount) throws IOException {
        double updateAmount = getBalance() - amount;
        updateAmount -= getStateLevel().getFee();
        setBalance(updateAmount);
        checkState();
        setTransaction(transaction, String.valueOf(amount));

    }
    
    // Run this method everytime the account balance changes
    protected void checkState() throws IOException{
        if (getBalance() < 10000) {
            level.currToSilver();
        } else if (getBalance() >= 10000 && getBalance() < 20000) {
            level.currToGold();
        } else {
            level.currToPlat();
        }
    }
    
    protected void saveCustomerData() throws IOException, FileNotFoundException {
        File myFile = new File(getUsername() + ".txt");
        FileWriter myWriter = new FileWriter(myFile);
        
        myWriter.write(temp.toString());
        
        for (int i = 0; i < getTransactionType().size(); i++) {
            myWriter.write(getTransactionType().get(i) + ";" + getTransactionAmount().get(i) + "\n");
        }
        
        myWriter.close();
        
    }
    
    
    
    protected boolean loginValid(String enterUN, String enterPW, AccountType acc) {
        return (enterUN.equals(acc.getUsername())) && (enterPW.equals(acc.getPassword()));
    }
    
    
    @Override
    public String toString() {
        return getUsername() + ";" + getPassword() + ";" + getRole() + ";" + String.valueOf(getBalance()) + ";" +  getStateLevel().getLevel() + "\n";
    }
}
