package coe528.project;

import java.io.File;
import java.io.FileWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML AccountAddedController class
 *
 * @author Gabilann
 */
public class AccountAddedController implements Initializable {

    @FXML
    private Text accCreateSuccess;

    @FXML
    private Button confirmButton;

    @FXML
    private Text cusPassword;

    @FXML
    private Text cusUsername;

    @FXML
    private Text initAction;

    @FXML
    private Text usernamePrompt;

    @FXML
    private Text passwordPrompt;

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        cusUsername.setText(ManagerController.addCustomerUN);
        cusPassword.setText(ManagerController.addCustomerPW);
        cusUsername.setVisible(true);
        cusPassword.setVisible(true);
        usernamePrompt.setVisible(true);
        passwordPrompt.setVisible(true);
        initAction.setVisible(true);
        confirmButton.setVisible(true);
        accCreateSuccess.setVisible(false);
    }
    
    @FXML
    void addAcc(MouseEvent event) throws IOException, FileNotFoundException{
        String un = cusUsername.getText();
        String pw = cusPassword.getText();
        boolean customerAdded = Manager.getManager().addCustomers(un);

        cusUsername.setVisible(false);
        cusPassword.setVisible(false);
        usernamePrompt.setVisible(false);
        passwordPrompt.setVisible(false);
        initAction.setVisible(false);
        confirmButton.setVisible(false);
        
        if (customerAdded == true) {
            File myFile = new File (un + ".txt");
            FileWriter myWriter = new FileWriter(myFile);
            
            Customer temp = new Customer(un, pw); // Creating a temp customer to store to file
            myWriter.write(temp.toString());
            myWriter.close();
            
            Manager.getManager().saveData();

            accCreateSuccess.setVisible(true);
        } else {
            accCreateSuccess.setText("ERROR: Username Already Exists!");
            accCreateSuccess.setVisible(true);
        }
        
    }
    
    @FXML
    void switchToManager(MouseEvent event) throws IOException {
        App.setRoot("Manager");
    }
    
    

}
