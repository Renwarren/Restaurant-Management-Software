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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import sprint2.utils.PageUtils;

public class MainMenuPageController {

    @FXML
    private Button floorButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button profileButton;

    @FXML
    void loadOrder(ActionEvent event) {

    }

    @FXML
    void loadProfile(ActionEvent event) {

    }

    @FXML
    void loadStore(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        
        Node source = (Node) event.getSource();
        PageUtils page = new PageUtils();
        
        try {
                page.loadPage("/sprint2/views/loginPage.fxml");
            } catch (IOException ex) {
                Logger.getLogger(loginPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        PageUtils.closePage(source);
        
        
    }

}
