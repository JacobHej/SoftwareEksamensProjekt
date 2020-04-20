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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MainController implements Initializable {
	
	@FXML private TableView<Project> TableView;
	@FXML private TableColumn<Project,String> ProjectNameColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("Initializing main Controller");
		
		//set up the columns in the table
		ProjectNameColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        
        //load dummy data
        TableView.setItems(getTestProjects());
        
        //Update the table to allow for the first and last name fields
        //to be editable
        TableView.setEditable(true);
        ProjectNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //This will allow the table to select multiple rows at once
        TableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}
	
	public ObservableList<Project>  getTestProjects()
    {
        ObservableList<Project> projects = FXCollections.observableArrayList();
        for(int i = 0;i<100; i++) {
        	projects.add(new Project("Project nr: "+i));
        }
        return projects;
    }
	
	
	

}
