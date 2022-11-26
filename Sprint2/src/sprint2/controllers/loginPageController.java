/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.controllers;

/**
 *
 * @author warren
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sprint2.utils.FileUtils;
import sprint2.utils.PageUtils;

public class loginPageController {

    @FXML
    private Button forgot_button;

    @FXML
    private Button login_button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    

    
    @FXML
    void forgotPassword(ActionEvent event) {
        
        Alert forgot = new Alert(Alert.AlertType.INFORMATION);
        forgot.setHeaderText("CREDENTIALS FORGOTTEN");
        forgot.setContentText("MESSAGE AN ADMIN TO RECOVER YOUR CREDENTIALS");
        forgot.showAndWait();

    }

    @FXML
    void login(ActionEvent event) throws FileNotFoundException, IOException {
        ArrayList<String> lines= FileUtils.getLines("src/sprint2/files/login.txt");
        HashMap<String, String> credentials = FileUtils.getCredentials(lines);
        String user = username.getText();
        String pass = password.getText();
        
        Node source = (Node) event.getSource();
        PageUtils page = new PageUtils();
        
        //username does not exist
        if(!credentials.containsKey(user)){
            Alert forgot = new Alert(Alert.AlertType.ERROR);
            forgot.setHeaderText("LOGIN ERROR");
            forgot.setContentText("Username does not exist!!!");
            forgot.showAndWait();
        }
        
        //username valid but password not valid
        if(credentials.containsKey(user) && !credentials.get(user).equals(pass) ){
            Alert forgot = new Alert(Alert.AlertType.ERROR);
            forgot.setHeaderText("LOGIN ERROR");
            forgot.setContentText("Password does not match!!!");
            forgot.showAndWait();
        }
        
        //username and password valid
        if(credentials.containsKey(user) && credentials.get(user).equals(pass) ){
            
            lines= FileUtils.getLines("src/sprint2/files/employees.txt");
            HashMap<String, String[]> employees = FileUtils.getEmployees(lines);
            
            String[] information = employees.get(user);
            String firstName = information[0];
            String lastName = information[1];
            String Job = information[2];
            
            
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setHeaderText("CONNECTION SUCCESSFUL");
            success.setContentText("Welcome back "+firstName+" !");
            success.showAndWait();
            
            try {
                page.loadPage("/sprint2/views/mainMenuPage.fxml");
            } catch (IOException ex) {
                System.out.println("Page Menu Screen not found!!!");
                Logger.getLogger(loginPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PageUtils.closePage(source);
            
        }
        
    }

}
