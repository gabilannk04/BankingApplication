package coe528.project;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


/**
 *
 * @author Gabilann
 */
public class Manager extends AccountType {   
    
    private static Manager manager = null;
    protected ArrayList<String> usernames;

    
    public Manager() {
        super("admin", "admin", "manager");
        usernames = new ArrayList<>();
    }
    
    protected ArrayList<String> getUsernames() {
        return this.usernames;
    }
    
    public static Manager getManager() { // Singleton class allows for only one manager to be created 
        if (manager == null) {
            manager = new Manager();
        } 
        return manager;
    }
    
    protected void initLoadCustomers() throws FileNotFoundException, IOException{
        File myFile = new File ("accounts.txt");
        Scanner myReader = new Scanner(myFile);
        
        while(myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String [] details = line.split(";");
            usernames.add(details[0]);
        }
        myReader.close();
    }
    
    protected boolean addCustomers(String un) {
        boolean accNotFound = true;
        
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equalsIgnoreCase(un) || un.contains(";")) {
                accNotFound = false;
            }
        }
        
        if (accNotFound == true) {
            usernames.add(un);
        }
        
        return accNotFound;    
    }
    
    protected boolean deleteCustomers(String un) {
        boolean accDeleted = false;
        
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equalsIgnoreCase(un)) {
                usernames.remove(i);
                accDeleted = true;
            }
        }
        
        return accDeleted;
    }
    
    
    protected boolean loginManager(String un, String pw) {
        return ((un.equals(manager.getUsername())) && (pw.equals(manager.getPassword())));
    }
    
    protected void saveData() throws FileNotFoundException, IOException{
        File myFile = new File ("accounts.txt");
        FileWriter myWriter = new FileWriter(myFile);
        
        int size = getUsernames().size();
        
        for (int i = 0; i < size; i++) {
            myWriter.write(getUsernames().get(i) + "\n");
        }
        
        myWriter.close();
    }
}