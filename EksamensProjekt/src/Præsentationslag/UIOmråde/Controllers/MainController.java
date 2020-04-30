package Præsentationslag.UIOmråde.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.event.ActionEvent;

public class MainController implements Initializable {
	
	//FXML Variables
	//The Project Table
	@FXML private TableView<Project> TableView;
	@FXML private TableColumn<Project,String> ProjectNameColumn;
	
	//The add and remove project features
	@FXML private Button AddProjectButton;
	@FXML private TextField AddProjectName;
	
	//Variables used in initialize and smaller tests
	private Project test;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		test = new Project("Stater");
		
		System.out.println("Initializing main Controller");
		
		//set up the columns in the table
		ProjectNameColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        
        //load dummy data
        TableView.setItems(getTestProjects());
        
        //This will allow the table to select multiple rows at once
        TableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}
	
	public ObservableList<Project>  getTestProjects()
    {
        ObservableList<Project> projects = FXCollections.observableArrayList();
        projects.add(test);
        for(int i = 0;i<10; i++) {
        	projects.add(new Project("Project nr: "+i));
        }
        return projects;
    }
	
	@FXML
    private void ChangeOne(ActionEvent event)
    {
        System.out.println("lollolol");
        test.setProjectName("changed");
        TableView.getColumns().get(0).setVisible(false);
        TableView.getColumns().get(0).setVisible(true);
    }
	
	@FXML
    private void AddProject(ActionEvent event)
    {
		
		Project newProject = new Project(AddProjectName.getText());

		//Get all the items from the table as a list, then add the new person to
		//the list
		TableView.getItems().add(newProject);
    }
	
	@FXML
    private void deleteSelected(ActionEvent event)
    {
        ObservableList<Project> selectedRows, allProjects;
        allProjects = TableView.getItems();
        
        //this gives us the rows that were selected
        selectedRows = TableView.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Project p: selectedRows)
        {
            allProjects.remove(p);
        }
    }
	
	
	

}
