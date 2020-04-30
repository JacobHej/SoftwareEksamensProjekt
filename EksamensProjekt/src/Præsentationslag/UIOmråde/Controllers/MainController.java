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
	@FXML private TableView<Project> ProjectsTable;
	@FXML private TableColumn<Project,String> ProjectNameColumn;
	
	//The activity table
	@FXML private TableView<Project> ActivitiesTable;
	@FXML private TableColumn<Project,String> ActivityNameColoumn;
	
	//The add and remove project features
	@FXML private Button AddProjectButton;
	@FXML private TextField AddProjectName;
	
	//The add and remove activity features
	@FXML private Button AddActivityButton;
	@FXML private TextField AddActivityName;
	
	//Variables used in initialize and smaller tests
	private Project test;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		System.out.println("Initializing main Controller");
		
		initializeProjectsTable();
		
		
	}
	
	public void initializeProjectsTable() {

		//set up the columns in the table
		ProjectNameColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        
        //load dummy data
		ProjectsTable.setItems(getTestProjects());
        
        //This will allow the table to select multiple rows at once
		ProjectsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//adds a listener for when selecting on the table
		ProjectsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	ProjectOnSelectStart();
		    }
		});
	}
	
	public ObservableList<Project>  getTestProjects()
    {
        ObservableList<Project> projects = FXCollections.observableArrayList();
        test = new Project("Stater");
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
        ProjectsTable.getColumns().get(0).setVisible(false);
        ProjectsTable.getColumns().get(0).setVisible(true);
    }
	
	@FXML
    private void AddProject(ActionEvent event)
    {
		
		Project newProject = new Project(AddProjectName.getText());

		//Get all the items from the table as a list, then add the new person to
		//the list
		ProjectsTable.getItems().add(newProject);
    }
	
	@FXML
    private void AddActivity(ActionEvent event)
    {
		System.out.println("You tried to AddActivity");
    }
	
	@FXML
    private void deleteSelectedProjects(ActionEvent event)
    {
        ObservableList<Project> selectedRows, allProjects;
        allProjects = ProjectsTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = ProjectsTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Project p: selectedRows)
        {
            allProjects.remove(p);
        }
    }
	
	@FXML
    private void deleteSelectedActivities(ActionEvent event)
    {
        System.out.println("You tried to add activity");
    }
	
	
	
    private void ProjectOnSelectStart()
    {
        System.out.println("Hi, you selected a row");
        ObservableList<Project> selectedRows;
        selectedRows = ProjectsTable.getSelectionModel().getSelectedItems();
        System.out.println("It contained");
        for (Project p: selectedRows)
        {
            System.out.println(p.getProjectName());
        }
        
    }
	
	
	

}
