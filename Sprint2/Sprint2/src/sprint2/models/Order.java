/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.models;

import java.util.ArrayList;

/**
 *
 * @author warre
 */
public class Order {
    
    String id;
    String serverId;
    String TableId;
    ArrayList<Item> items;

    public Order(String id, String serverId, String TableId, ArrayList<Item> items) {
        this.id = id;
        this.serverId = serverId;
        this.TableId = TableId;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getTableId() {
        return TableId;
    }

    public void setTableId(String TableId) {
        this.TableId = TableId;
    }

 

    public Order() {
    }
    
    
    
    
}
