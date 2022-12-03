/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author warren
 */
public class FileUtils {
    
    
    /**
     * 
     * @param url
     * @return ArrayList lines
     * @throws FileNotFoundException
     * Get the lines of a text File
     */
    public static ArrayList<String> getLines(String url) throws FileNotFoundException{
        
        ArrayList<String> lines = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(url));
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext()){
            String line = scanner.next();
            lines.add(line);
        }
        return lines;      
    }
    
    /**
     * 
     * 
     * @param lines
     * @return HashMap<username,password>
     */
    public static HashMap<String,String> getCredentials(ArrayList<String> lines){
        
        HashMap<String, String> credentials = new HashMap<>();
        
        for (String line: lines){
            String[] values = line.split(",");
            credentials.put(values[0], values[1]);
        }
        return credentials;
    }
    
    /**
     * 
     * @param lines
     * @return HashMap<username, information[]>
     * information are: firstName, lastName,Job
     */
    public static HashMap<String, String[]> getEmployees(ArrayList<String> lines){
        
            HashMap<String, String[]> employees = new HashMap<>();
            
            for (String line: lines){
                //split first into username:information
                String[] values = line.split(":");
                String username = values[0];
                String information [] = values[1].split(",");
                employees.put(username, information);
            }
            
            
            return employees;
        }
    
    public static HashMap<String, String[]> getItems(ArrayList<String> lines){
        
            HashMap<String, String[]> employees = new HashMap<>();
            
            for (String line: lines){
                //split first into username:information               
                String[] values = line.split(":");                
                String username = values[0];
                String information [] = values[1].split(",");
                employees.put(username, information);
            }
            return employees;
        }
    
    
    
    public static HashMap<String, String[]> getTables(ArrayList<String> lines){
        
            HashMap<String, String[]> employeeTable = new HashMap<>();
            
            for (String line: lines){
                //split first into username:information
                String[] values = line.split(":");
                String username = values[0];
                String information [] = values[1].split(",");
                employeeTable.put(username, information);
            }
            return employeeTable;
        }
    
    public static String[] getEmployeeTables(HashMap<String, String[]> t, String username){
        return t.get(username);
    }
    
    /**
     * Get the current employee in a file called current
     * @param username
     * @throws FileNotFoundException 
     */
    public static void writeCurrentEmployee(String username) throws FileNotFoundException{
        java.io.File file = new java.io.File("src/sprint2/files/current.txt");
        java.io.PrintWriter output = new java.io.PrintWriter(file);
        output.print(username);
        output.close();
    }
    
    public static void writeCurrentTable(String tableId, String userId) throws FileNotFoundException{
        java.io.File file = new java.io.File("src/sprint2/files/current.txt");
        java.io.PrintWriter output = new java.io.PrintWriter(file);
        output.println(userId);
        output.print(tableId);
        output.close();
        
    }
    
}


    

