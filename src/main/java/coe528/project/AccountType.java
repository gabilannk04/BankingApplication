package coe528.project;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Gabilann
 */
public class AccountType {
    /**
     * Overview: AccountType is a mutable, bounded object that defines the type of
     * user using the software.
     */

    /**
     * Abstraction Function:
     * The AccountType object creates an object which represents a user of the software. Each
     * object consists of a username, and password for the user and their role within the actual
     * program.
     */

    /**
     * Rep Invariant:
     * for all AccountType, username, password and role are Strings which are neither blank or null.
     */

    protected String username;
    protected String password;
    protected String role;

    public AccountType (String un, String pw, String role) {
        // REQUIRES: un, pw, role must be Strings which are neither blank or null.
        // EFFECTS: Creates a new AccountType object
        this.username = un;
        this.password = pw;
        this.role = role;
    }

    private boolean repOk() {
        // EFFECTS: Returns true if the rep invariant holds for this object;
        //          otherwise returns false.
        if (getUsername() == null || getUsername().isEmpty()) {
            return false;
        } else if (getPassword() == null || getPassword().isEmpty()) {
            return false;
        } else if (getRole() == null || getRole().isEmpty()) {
            return false;
        }

        return true;
    }
    
    protected String getUsername() {
        // EFFECTS: Returns username if the username exists
        return this.username;
    }
    
    protected String getPassword() {
        // EFFECTS: Returns password if the password exists
        return this.password;
    }
    
    protected String getRole() {
        // EFFECTS: Returns role if the role exists
        return this.role;
    }
        
    protected void setUsername(String newUN) {
        // REQUIRES: newUN must be a String that is not blank or null
        // MODIFIES: current instance of username will be changed with newUN
        // EFFECTS: Sets the username to the new name

        this.username = newUN;
    }
    
    protected void setPassword(String newPW) {
        // REQUIRES: newPW must be a String that is not blank or null
        // MODIFIES: current instance of password will be changed with newPW
        // EFFECTS: Sets the password to the new name

        this.password = newPW;
    }
    
    protected static AccountType findAccount(String un, String pw) {
        // REQUIRES: un and pw must be strings that are not blank or null
        // EFFECTS: Return an AccountType that will store a temporary object with the username and password
        //          from the user's file.

        AccountType temp = null;
        
        try {
            File myFile = new File (un + ".txt");
            Scanner myReader = new Scanner(myFile);
            
            String data = myReader.nextLine(); // First line will always be the file name info
            String[] details = data.split(";", 5);
            
            temp = new Customer(details[0], details[1]);
        } catch (FileNotFoundException ex) {
            if (un.equals("admin") || pw.equals("admin")) {
                temp = Manager.getManager();
            }
        }
                
        return temp;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + "\nPassword: " + getPassword() + "\nRole: " + getRole();
    }
}
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  /**
   *   protected AccountType findAccount(String un, String pw) {
        // searches the files for the file containing the account information
        AccountType temp = null;
        
        try {
            File myFile = new File(un + ".txt");
            Scanner myReader = new Scanner(myFile);
            
            String data = myReader.nextLine();
            String[] parts = data.split(";", 5);
            
            
            temp = new AccountType(parts[0], parts[1], parts[2]);
            
            myReader.close();
        } catch (FileNotFoundException e) {
            // check if the un and pw is admin
            if (un.equals("admin") && pw.equals("admin")) {
                temp = new AccountType("admin", "admin", "manager");
            }
            
        }
        
        if (temp == null) {
            // Print error dialogue saying account cant be found
        }
        
        return temp;
    }
    
    protected boolean login(String un, String pw) {
        AccountType temp = findAccount(un, pw);
        
        
    }
   */
    
    /**
     * protected boolean login(String un, String pw, AccountType acc);
     * protected boolean logout();
     */

