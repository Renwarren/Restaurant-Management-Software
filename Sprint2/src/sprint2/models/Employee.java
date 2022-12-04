/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.models;

import sprint2.utils.FileUtils;
import sprint2.utils.JobType;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author warre
 */
public class Employee {
    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private JobType job;

    public Employee(String username, String password, String firstName, String lastName, JobType job) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
    }

    public Employee(String username, String password, String firstName, String lastName, char job) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        setJob(job);
    }

    public Employee() {
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.job = JobType.Cook;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JobType getJob() {
        return this.job;
    }

    public void setJob(JobType job) {
        this.job = job;
    }

    public void setJob(char job) throws IllegalArgumentException {
        switch(job) {
            case 'C':
                this.job = JobType.Cook;
                break;
            case 'M':
                this.job = JobType.Manager;
                break;
            case 'W':
                this.job = JobType.Waiter;
                break;
            default:
                throw new IllegalArgumentException("Job type invalid for " + job);
        }
    }

    public char getJobChar() {
        switch(this.job) {
            case Cook:
                return 'C';
            case Manager:
                return 'M';
            case Waiter:
                return 'W';
            default:
                return 'N';
        }
    }

    public static ArrayList<Employee> getEmployees() throws FileNotFoundException{
        HashMap<String, String> logins = FileUtils.getCredentials();
        HashMap<String, String[]> employees = FileUtils.getEmployees();

        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        for (String login : logins.keySet()) {
            employeeList.add(
                new Employee(
                    login,
                    logins.get(login),
                    employees.get(login)[0],
                    employees.get(login)[1],
                    employees.get(login)[2].charAt(0)
                )
            );
        }

        return employeeList;
    }

    public static void writeEmployees(List<Employee> employeeList) throws FileNotFoundException, IOException {
        HashMap<String, String> logins = new HashMap<String, String>();
        HashMap<String, String[]> employees = new HashMap<String, String[]>();

        for (Employee employee : employeeList) {
            FileUtils.addEmployee(
                employees, 
                logins, 
                employee.getUsername(), 
                employee.getPassword(), 
                employee.getFirstName(), 
                employee.getLastName(), 
                String.valueOf(employee.getJobChar())
            );
        }

        FileUtils.writeEmployeesToFile(employees);
        FileUtils.writeLoginsToFile(logins);
    }

    @Override
    public String toString() {
        return String.format("UN: %-15s | PW: %-15s | FN: %-15s | LN: %-15s | J: %-15s",
            this.getUsername(),
            this.getPassword(),
            this.getFirstName(),
            this.getLastName(),
            this.getJobChar()
        );
    }
}