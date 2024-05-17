package coe528.project;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.*;
import javafx.scene.control.Button;

/**
 * FXML AccountDeletedController class
 *
 * @author Gabilann
 */
public class AccountDeletedController implements Initializable {

    @FXML
    private Text accToDelete;

    @FXML
    private Text deletedMessage;
    
    @FXML
    private Button confirmButton;
    
    @FXML
    private Text initAction;

    @FXML 
    private Text initPrompt;
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        accToDelete.setText(ManagerController.delCustomer);
        accToDelete.setVisible(true);
        deletedMessage.setVisible(false);
        initAction.setVisible(true);
        initPrompt.setVisible(true);
    }
    
    @FXML
    void deleteAcc(MouseEvent event) throws FileNotFoundException, IOException{
        boolean accDeleted = Manager.getManager().deleteCustomers(ManagerController.delCustomer);
        
        if (accDeleted == true) {
            // prompt acc deleted window
            File myFile = new File (ManagerController.delCustomer + ".txt");
            myFile.delete();
            Manager.getManager().saveData(); // In case user does not sign out properly, save all changes made

            
            confirmButton.setVisible(false);
            initAction.setVisible(false);
            accToDelete.setVisible(false);
            initPrompt.setVisible(false);
            deletedMessage.setText("Account Deleted!");
            deletedMessage.setVisible(true);   
        } 
    }

    @FXML
    void switchToManager(MouseEvent event) throws IOException{
        App.setRoot("Manager");
    }

}
