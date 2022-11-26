/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sprint2.utils.PageUtils;

/**
 *
 * @author warre
 */
public class floorPageController {
    
    
    @FXML
    private Button A1;

    @FXML
    private Button A2;

    @FXML
    private Button A3;

    @FXML
    private Button A4;

    @FXML
    private Button logoutButton;
    
    @FXML
    private Label name;
    
    @FXML
    void logout(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        PageUtils page = new PageUtils();
        boolean decision = PageUtils.showAlertConfirmation("DISCONNECTION", "Do you really want to log out?");
        
        if(decision){
            page.loadPage("/sprint2/views/loginPage.fxml");
            PageUtils.closePage(source);
        }

        
    }

    @FXML
    void waiterInteraction(ActionEvent event) {

    }

    
}
