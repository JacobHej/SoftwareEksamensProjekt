package Praesentationslag.UIOmråde.Controllers;

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
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

public class MainController implements Initializable {
	
	//FXML Variables
	//The Project Table
	@FXML private TableView<Projekt> projektTabel;
	@FXML private TableColumn<Projekt,String> projektNavnKolonne;
	
	//The projekt info
	@FXML private TextField ugeNrProjektStart;
	@FXML private Text projektInfoNavn;
	
	//The activity table
	@FXML private TableView<Aktivitet> aktivitetTabel;
	@FXML private TableColumn<Aktivitet,String> aktivitetNavnKolonne;
	
	//Aktivitet info
	@FXML private TextField ugeNrAktivitetStart;
	@FXML private TextField ugeNrAktivitetSlut;
	@FXML private Text aktivitetInfoNavn;
	
	//The add and remove project 
	@FXML private TextField tilfoejProjektNavn;
	@FXML private DatePicker tilfoejProjektDato;
	
	//The add and remove activity features
	@FXML private TextField tilfoejAktivitetNavn;
	
	//Variables used in initialize and smaller tests
	//empty for now
	
	
	//Initialize ----------------------------------------------------------------------------------------------------------
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
		
		aktivitetTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	aktivitetOnSelectStart();
		    }
		});
	}
	
	public ObservableList<Projekt>  getTestProjekter()
    {
        ObservableList<Projekt> projects = FXCollections.observableArrayList();
        for(int i = 0;i<10; i++) {
        	Projekt p = new Projekt("Project nr: "+i);
        	p.Gem();
        	projects.add(p);
        	tilfoejAktiviteter(p);
        }
        return projects;
    }
	
	//Dummy date aktiviteter
	public void  tilfoejAktiviteter(Projekt p)
    {
        for(int i = 0; i<10; i++) {
        	Aktivitet a = new Aktivitet(0, 0, "Aktivitet "+i+" for "+p.getNavn());
        	if(p.tilfoejAktivitet(a)) {
        		//System.out.println("Aktivitet tilføjet");
        	}
        }
    }
	
	
//Projekt metoder ----------------------------------------------------------------------------------------------------------
	@FXML
    private void tilfoejProjekt(ActionEvent event)
    {
		Projekt p = new Projekt(tilfoejProjektNavn.getText());
		p.Gem();
		//Get all the items from the table as a list, then add the new person to
		//the list
		projektTabel.getItems().add(p);
		
    }
	
	@FXML
    private void fjernValgteProjekt(ActionEvent event)
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
        
        projectOnSelectStart();
    }
	
    private void projectOnSelectStart()
    {
        Projekt p = projektTabel.getSelectionModel().getSelectedItems().get(0);
        if(p!=null) {
        	//System.out.println("Hi, you selected a row");
            //System.out.println("It contained");
        	//System.out.println(p.getNavn()+"   "+p.getStartTid());
            
            visAktiviteter(p);
            visProjektInfo(p);
        }
        
    }
    
    //Viser aktiviteter for valgte projekt
    private void visAktiviteter(Projekt p) {
    	
    	ObservableList<Aktivitet> aktiviteter = FXCollections.observableArrayList();
        aktivitetTabel.setItems(aktiviteter);
        
        for(Entry<UUID, Aktivitet> e : p.getAlleAktiviteter()) {
        	aktivitetTabel.getItems().add(e.getValue());
        }
    }
    
    //Viser Projekt Info
    private void visProjektInfo(Projekt p) {
    	
    	if(p.getStartUge() != 0) {
    		ugeNrProjektStart.setText(p.getStartUge()+"");
    	}else {
    		ugeNrProjektStart.setText("");
    	}
    	
    	projektInfoNavn.setText(p.getNavn());
    	
    }
    
    
    @FXML
    private void gemProjektStart(ActionEvent event)
    {
    	Projekt p = projektTabel.getSelectionModel().getSelectedItems().get(0);
    	if(ugeNrProjektStart.getText()!=null) {
    		String s = ugeNrProjektStart.getText();
    		try{
    			int i = Integer.parseInt(s);
    			if(i<=53&&i>0) {
    				p.setStartUge(i);
    			}else {
    				ugeNrProjektStart.setText("Uger går mellem 1 og 53!");
    			}
    			
    		}catch(Exception e) {
    			ugeNrProjektStart.setText("Nummer tak!");
    		}
    		
    	}
    }
    
//Aktivitet metoder -------------------------------------------------------------------------------------------------------------------
	
	@FXML
    private void tilfoejAktivitet(ActionEvent event)
    {
		System.out.println("You tried to AddActivity");
        Projekt p = projektTabel.getSelectionModel().getSelectedItems().get(0);
        if(p!=null) {
        	Aktivitet a = new Aktivitet(tilfoejAktivitetNavn.getText());
        	p.tilfoejAktivitet(a);
        	aktivitetTabel.getItems().add(a);
        }
    }
	
	@FXML
    private void fjernValgteAktivitet(ActionEvent event)
	{
        System.out.println("You tried to remove activity");
        
        ObservableList<Aktivitet> valgteRaekker, alleAktiviteter;
        alleAktiviteter = aktivitetTabel.getItems();
        
        //this gives us the rows that were selected
        valgteRaekker = aktivitetTabel.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Aktivitet a: valgteRaekker)
        {
        	alleAktiviteter.remove(a);
        }
    }
	
	@FXML
    private void gemAktivitetTid(ActionEvent event)
    {
    	Aktivitet a = aktivitetTabel.getSelectionModel().getSelectedItems().get(0);
    	if(ugeNrAktivitetStart.getText()!=null&&ugeNrAktivitetSlut.getText()!=null) {
    		String start = ugeNrAktivitetStart.getText();
    		String slut = ugeNrAktivitetSlut.getText();
    		try{
    			int istart = Integer.parseInt(start);
    			int islut = Integer.parseInt(slut);
    			if(istart<=53&&istart>0 && islut<=53&&islut>0) {
    				a.setStartUge(istart);
    				a.setSlutUge(islut);
    			}else {
    				ugeNrAktivitetStart.setText("Uger går mellem 1 og 53!");
    				ugeNrAktivitetSlut.setText("Uger går mellem 1 og 53!");
    			}
    			
    		}catch(Exception e) {
    			ugeNrAktivitetStart.setText("Nummer tak!");
    			ugeNrAktivitetSlut.setText("Nummer tak!");
    		}
    		
    	}
    }
	
	private void aktivitetOnSelectStart()
    {
        Aktivitet a = aktivitetTabel.getSelectionModel().getSelectedItems().get(0);
        if(a!=null) {
        	//System.out.println("Hi, you selected a row");
            //System.out.println("It contained");
        	//System.out.println(p.getNavn()+"   "+p.getStartTid());
            
            visAktivitetInfo(a);
        }
        
    }
	
	private void visAktivitetInfo(Aktivitet a) {
    	
		if(a.getStartUge() != 0) {
    		ugeNrAktivitetStart.setText(a.getStartUge()+"");
    	}else {
    		ugeNrAktivitetStart.setText("");
    	}
		
		if(a.getSlutUge() != 0) {
    		ugeNrAktivitetSlut.setText(a.getSlutUge()+"");
    	}else {
    		ugeNrAktivitetSlut.setText("");
    	}
    	
    	aktivitetInfoNavn.setText(a.getNavn());
    	
    }
	
//Brugbare metoder----------------------------------------------------------------------------------------------------------------
	
}
