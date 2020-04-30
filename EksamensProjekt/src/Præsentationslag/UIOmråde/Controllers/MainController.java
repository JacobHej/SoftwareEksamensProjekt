package Præsentationslag.UIOmråde.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.Map.Entry;

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
	@FXML private TableView<Projekt> projektTabel;
	@FXML private TableColumn<Projekt,String> projektNavnKolonne;
	
	//The activity table
	@FXML private TableView<Aktivitet> aktivitetTabel;
	@FXML private TableColumn<Aktivitet,String> aktivitetNavnKolonne;
	
	//The add and remove project 
	@FXML private TextField tilfoejProjektNavn;
	@FXML private DatePicker tilfoejProjektDato;
	
	//The add and remove activity features
	@FXML private TextField tilfoejAktivitetNavn;
	
	//Variables used in initialize and smaller tests
	private Projekt test;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		System.out.println("Initializing main Controller");
		
		initializeProjectsTable();
		initializeActivitiesTable();
		
		
	}
	
	public void initializeProjectsTable() {

		//set up the columns in the table
		projektNavnKolonne.setCellValueFactory(new PropertyValueFactory<Projekt, String>("navn"));
        
        //load dummy data
		projektTabel.setItems(getTestProjekter());
        
        //This will allow the table to select multiple rows at once
		projektTabel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//adds a listener for when selecting on the table
		projektTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	projectOnSelectStart();
		    }
		});
	}
	
	public void initializeActivitiesTable() {

		//set up the columns in the table
		aktivitetNavnKolonne.setCellValueFactory(new PropertyValueFactory<Aktivitet, String>("navn"));
	}
	
	public ObservableList<Projekt>  getTestProjekter()
    {
        ObservableList<Projekt> projects = FXCollections.observableArrayList();
        test = new Projekt("Stater",new Date());
        projects.add(test);
        for(int i = 0;i<10; i++) {
        	Projekt p = new Projekt("Project nr: "+i,new Date());
        	projects.add(p);
        	tilfoejAktiviteter(p);
        }
        return projects;
    }
	
	public void  tilfoejAktiviteter(Projekt p)
    {
        for(int i = 0; i<10; i++) {
        	Aktivitet a = new Aktivitet(new Date(), new Date(),p, "Aktivitet "+i+" for "+p.getNavn());
        }
    }
	
	@FXML
    private void tilfoejProjekt(ActionEvent event)
    {
		LocalDate dato = tilfoejProjektDato.getValue();
		Projekt newProject = new Projekt(tilfoejProjektNavn.getText(),new Date(dato.getYear()-1900,dato.getMonthValue()-1,dato.getDayOfMonth()));

		//Get all the items from the table as a list, then add the new person to
		//the list
		projektTabel.getItems().add(newProject);
    }
	
	@FXML
    private void tilfoejAktivitet(ActionEvent event)
    {
		System.out.println("You tried to AddActivity");
    }
	
	@FXML
    private void fjernValgteProjekter(ActionEvent event)
    {
        ObservableList<Projekt> selectedRows, allProjects;
        allProjects = projektTabel.getItems();
        
        //this gives us the rows that were selected
        selectedRows = projektTabel.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Projekt p: selectedRows)
        {
            allProjects.remove(p);
        }
    }
	
	@FXML
    private void fjernValgteAktiviteter(ActionEvent event)
    {
        System.out.println("You tried to remove activity");
    }
	
	
	
    private void projectOnSelectStart()
    {
        System.out.println("Hi, you selected a row");
        ObservableList<Projekt> selectedRows;
        selectedRows = projektTabel.getSelectionModel().getSelectedItems();
        System.out.println("It contained");
        Projekt p = selectedRows.get(0);
        System.out.println(p.getNavn()+"   "+p.startTid());
        
        ObservableList<Aktivitet> aktiviteter = FXCollections.observableArrayList();
        aktivitetTabel.setItems(aktiviteter);
        
        for(Entry<UUID, Aktivitet> e : p.getAlleAktiviteter()) {
        	aktivitetTabel.getItems().add(e.getValue());
        }
        
    }
	
	
	

}
