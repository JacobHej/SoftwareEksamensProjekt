package Præsentationslag.UIOmråde.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Projekt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;

public class MainController implements Initializable {
	
	//FXML Variables
	//The Project Table
	@FXML private TableView<Projekt> ProjectsTable;
	@FXML private TableColumn<Projekt,String> ProjectNameColumn;
	
	//The activity table
	@FXML private TableView<Aktivitet> ActivitiesTable;
	@FXML private TableColumn<Aktivitet,String> ActivityNameColoumn;
	
	//The add and remove project features
	@FXML private Button AddProjectButton;
	@FXML private TextField AddProjectName;
	@FXML private DatePicker AddProjectDate;
	
	//The add and remove activity features
	@FXML private Button AddActivityButton;
	@FXML private TextField AddActivityName;
	
	//Variables used in initialize and smaller tests
	private Projekt test;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		System.out.println("Initializing main Controller");
		
		initializeProjectsTable();
		
		
	}
	
	public void initializeProjectsTable() {

		//set up the columns in the table
		ProjectNameColumn.setCellValueFactory(new PropertyValueFactory<Projekt, String>("navn"));
        
        //load dummy data
		ProjectsTable.setItems(getTestProjects());
        
        //This will allow the table to select multiple rows at once
		ProjectsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//adds a listener for when selecting on the table
		ProjectsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	projectOnSelectStart();
		    }
		});
	}
	
	public ObservableList<Projekt>  getTestProjects()
    {
        ObservableList<Projekt> projects = FXCollections.observableArrayList();
        test = new Projekt("Stater",new Date());
        projects.add(test);
        for(int i = 0;i<10; i++) {
        	projects.add(new Projekt("Project nr: "+i,new Date()));
        }
        return projects;
    }
	
	@FXML
    private void addProject(ActionEvent event)
    {
		LocalDate date = AddProjectDate.getValue();
		Projekt newProject = new Projekt(AddProjectName.getText(),new Date(date.getYear()-1900,date.getMonthValue()-1,date.getDayOfMonth()));

		//Get all the items from the table as a list, then add the new person to
		//the list
		ProjectsTable.getItems().add(newProject);
    }
	
	@FXML
    private void addActivity(ActionEvent event)
    {
		System.out.println("You tried to AddActivity");
    }
	
	@FXML
    private void deleteSelectedProjects(ActionEvent event)
    {
        ObservableList<Projekt> selectedRows, allProjects;
        allProjects = ProjectsTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = ProjectsTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Projekt p: selectedRows)
        {
            allProjects.remove(p);
        }
    }
	
	@FXML
    private void deleteSelectedActivities(ActionEvent event)
    {
        System.out.println("You tried to add activity");
    }
	
	
	
    private void projectOnSelectStart()
    {
        System.out.println("Hi, you selected a row");
        ObservableList<Projekt> selectedRows;
        selectedRows = ProjectsTable.getSelectionModel().getSelectedItems();
        System.out.println("It contained");
        for (Projekt p: selectedRows)
        {
            System.out.println(p.getNavn()+"   "+p.startTid());
            
        }
        
    }
	
	
	

}
