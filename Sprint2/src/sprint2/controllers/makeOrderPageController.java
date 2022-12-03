package sprint2.controllers;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sprint2.models.Item;
import sprint2.models.Order;
import sprint2.utils.FileUtils;

public class makeOrderPageController implements Initializable{

    @FXML
    private TableColumn<Item, String> TypeCol;

    @FXML
    private ListView<String> currentOrder;

    @FXML
    private TableColumn<Item, String> idCol;

    @FXML
    private TableView<Item> itemView;

    @FXML
    private TableColumn<Item, String> nameCol;

    @FXML
    private TableColumn<Item, Double> priceCol;

    
    private Order order;
    private ObservableList<String> itemsOrdered;
    
    @FXML
    void addItem(ActionEvent event) {
        Item item = this.itemView.getSelectionModel().getSelectedItem();
        this.itemsOrdered.add("1x "+item.toString());
//        order.getItems().add(item);

        
    }

    @FXML
    void submitOrder(ActionEvent event) throws FileNotFoundException {
        
    }
    
    @FXML
    void deleteItem(ActionEvent event){
        String item = currentOrder.getSelectionModel().getSelectedItem();
        itemsOrdered.remove(item);
        order.getItems().remove(item);
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            
            HashMap<String,String[]> map = null;
            try {
                map = FileUtils.getItems(FileUtils.getLines("src/sprint2/files/Items.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(makeOrderPageController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("couldnt find item file");
            }
            
            
            List<Item> items = new ArrayList<>();
            for (HashMap.Entry<String,String[]> mapElement : map.entrySet()) {
                String key = mapElement.getKey();
                String values[] = mapElement.getValue();
                items.add(new Item(values[0],key,Double.parseDouble(values[1]),values[2]));
            }
            
            this.itemView.setItems(FXCollections.observableArrayList(items));
            this.itemsOrdered = FXCollections.observableArrayList();
            this.currentOrder.setItems(this.itemsOrdered);
            String waiterId = null;
            try {
                waiterId = FileUtils.getLines("src/sprint2/files/current.txt").get(0);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(makeOrderPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String tableId = FileUtils.getLines("src/sprint2/files/current.txt").get(1);
            this.order = new Order();
            this.order.setServerId(waiterId);
            this.order.setTableId(tableId);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(makeOrderPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Item> itemsOrdered = new ArrayList<>();
        order.setItems(itemsOrdered);
        
    }
    
    

}
