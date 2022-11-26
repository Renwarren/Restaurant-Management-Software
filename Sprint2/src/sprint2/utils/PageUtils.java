/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author warren
 */
public class PageUtils {
    
    public void loadPage(String FXMLPath) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
    public static void closePage(Node source){
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
