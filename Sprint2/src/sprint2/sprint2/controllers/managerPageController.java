package sprint2.controllers;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import sprint2.utils.PageUtils;

import java.util.ResourceBundle;

import javax.swing.Action;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import sprint2.models.Employee;
import sprint2.utils.FileUtils;
import sprint2.utils.JobType;
import sprint2.utils.JobTypeConverter;

public class managerPageController implements Initializable {
    boolean unsaved_changes = false;

    public static TableView<Employee> table_info_app;
    public static ObservableList<Employee> data_table;

    @FXML
    private TableView<Employee> employeeView;

    @FXML
    private TableColumn<Employee, String>
        column_username, 
        column_password, 
        column_firstName, 
        column_lastName;
        
    @FXML
    private TableColumn<Employee, JobType>
        column_jobTitle;

    @FXML
    private TableColumn<Employee, Button> column_editButton;

    @FXML
    private Text unsavedText;

    @FXML
    private void leavePage(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        PageUtils page = new PageUtils();
        
        if(unsaved_changes) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("UNSAVED CHANGES");
            alert.setContentText("Save before quitting?");

            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, noButton, cancelButton);

            alert.showAndWait().ifPresent(type -> {
                System.out.println(type);
                if (type == okButton) {
                    try {
                        Employee.writeEmployees(data_table);
                    } catch (Exception e) {
                        System.out.println("Could not save to file: " + e);
                        return;
                    }
                }
                else if (type == cancelButton) {
                    return;
                }

                try {
                    page.loadPage("/sprint2/views/mainMenuPage.fxml");
                    PageUtils.closePage(source);
                } catch (Exception e) {
                    return;
                }
            });
        }
        else {
            page.loadPage("/sprint2/views/mainMenuPage.fxml");
            PageUtils.closePage(source);
        }
    }

    @FXML
    private void saveEmployees(ActionEvent event) {
        try {
            Employee.writeEmployees(data_table);
        } catch (Exception e) {
            System.out.println("Could not save to file: " + e);
            return;
        }

        this.unsavedText.setVisible(false);
        this.unsaved_changes = false;
    }

    @FXML
    private void deleteEmployee(ActionEvent event) {
        Employee selectedEmployee = employeeView.getSelectionModel().getSelectedItem();
        employeeView.getItems().remove(selectedEmployee);

        this.unsaved_changes = true;
        this.unsavedText.setVisible(true);
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        employeeView.getItems().add(new Employee());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table_info_app = employeeView;

        unsavedText.setVisible(false);
        
        initializeCols();
        loadData();
    }

    private void initializeCols() {
        column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        column_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        column_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column_jobTitle.setCellValueFactory(new PropertyValueFactory<>("job"));

        JobTypeConverter converter = new JobTypeConverter();
        ObservableList<JobType> jobList = FXCollections.observableArrayList();
        jobList.add(JobType.Cook);
        jobList.add(JobType.Manager);
        jobList.add(JobType.Waiter);    
        column_jobTitle.setCellFactory(ComboBoxTableCell.forTableColumn(converter, jobList));

        editableCols();
    }

    private void editableCols() {
        column_username.setCellFactory(TextFieldTableCell.forTableColumn());
        column_username.setOnEditCommit(e -> {
            e.getTableView()
                .getItems()
                .get(
                    e.getTablePosition()
                        .getRow()
                        
                )
                .setUsername(
                    e.getNewValue()
            );

            this.unsaved_changes = true;
            this.unsavedText.setVisible(true);
        });

        column_password.setCellFactory(TextFieldTableCell.forTableColumn());
        column_password.setOnEditCommit(e -> {
            e.getTableView()
                .getItems()
                .get(
                    e.getTablePosition()
                        .getRow()
                        
                )
                .setPassword(
                    e.getNewValue()
            );

            this.unsaved_changes = true;
            this.unsavedText.setVisible(true);
        });

        column_firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        column_firstName.setOnEditCommit(e -> {
            e.getTableView()
                .getItems()
                .get(
                    e.getTablePosition()
                        .getRow()
                        
                )
                .setFirstName(
                    e.getNewValue()
            );

            this.unsaved_changes = true;
            this.unsavedText.setVisible(true);
        });

        column_lastName.setCellFactory(TextFieldTableCell.forTableColumn());
        column_lastName.setOnEditCommit(e -> {
            e.getTableView()
                .getItems()
                .get(
                    e.getTablePosition()
                        .getRow()
                        
                )
                .setLastName(
                    e.getNewValue()
            );

            this.unsaved_changes = true;
            this.unsavedText.setVisible(true);
        });

        column_jobTitle.setOnEditCommit(e -> {
            e.getTableView()
                .getItems()
                .get(
                    e.getTablePosition()
                        .getRow()
                )
                .setJob(
                    e.getNewValue()
            );

            this.unsaved_changes = true;
            this.unsavedText.setVisible(true);
        });
    }

    private void loadData() {
        try {
             data_table = FXCollections.observableArrayList(Employee.getEmployees());
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
            return;
        }

        employeeView.setItems(data_table);
        employeeView.setEditable(true);
    }
}
