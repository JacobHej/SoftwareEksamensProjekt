package Praesentationslag.UIOmraade.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;


import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;
import Praesentationslag.UIOmraade.Views.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;

public class MainController implements Initializable {
	
	//FXML Variables
	//The Project Table
	@FXML private TableView<Projekt> projektTabel;
	@FXML private TableColumn<Projekt,String> projektNavnKolonne;
	@FXML private TableColumn<Projekt,Integer> projektNummerKolonne;
	
	//The activity table
	@FXML private TableView<Aktivitet> aktivitetTabel;
	@FXML private TableColumn<Aktivitet,String> aktivitetNavnKolonne;
	
	//Medlemmer tabellen
	@FXML private TableView<Medarbejder> medarbejderTabel;
	@FXML private TableColumn<Medarbejder,String> medarbejderInitialKolonne;
	
	//The projekt info
	@FXML private TextField ugeNrProjektStart;
	@FXML private TextField aarstalProjektStart;
	@FXML private TextField projektInfoNavn;
	@FXML private ComboBox<Medarbejder> lederDropDown;
	
	//Aktivitet info
	@FXML private TextField ugeNrAktivitetStart;
	@FXML private TextField ugeNrAktivitetSlut;
	@FXML private TextField aarstalAktivitetStart;
	@FXML private TextField aarstalAktivitetSlut;
	@FXML private Text aktivitetInfoNavn;
	
	//The add and remove project 
	@FXML private TextField tilfoejProjektNavn;
	
	//The add and remove activity features
	@FXML private TextField tilfoejAktivitetNavn;
	
	//Tilfoej og fjern medarbejder
	@FXML private TextField tilfoejMedarbejderNavn;
	
	//Variables used in initialize and smaller tests
	//empty for now
	
	
	//Initialize ----------------------------------------------------------------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		System.out.println("Initializing main Controller");
		
		initializeProjectsTable();
		initializeActivitiesTable();
		initializeMedlemmerTabel();
		initializeProjektLederDropDown();
	}
	
	public void initializeProjectsTable() {

		//set up the columns in the table
		projektNavnKolonne.setCellValueFactory(new PropertyValueFactory<Projekt, String>("navn"));
		projektNummerKolonne.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("projektnummer"));
		
        //load dummy data
		projektTabel.setItems(getTestProjekter());
		
		//adds a listener for when selecting on the table
		projektTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	projectOnSelectStart();
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
	
	public void initializeActivitiesTable() {

		//set up the columns in the table
		aktivitetNavnKolonne.setCellValueFactory(new PropertyValueFactory<Aktivitet, String>("navn"));
		
		aktivitetTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	aktivitetOnSelectStart();
		    }
		});
	}
	
	public void initializeMedlemmerTabel() {
		//set up the columns in the table
		medarbejderInitialKolonne.setCellValueFactory(new PropertyValueFactory<Medarbejder, String>("initialer"));
		tilfoejMedarbejdere();
		visMedarbejdere();
	}
	
	public void tilfoejMedarbejdere() {
		Medarbejder m;
		m = new Medarbejder("Søren Sven Svensen");
		m.Gem();
		m = new Medarbejder("Erik Aske Laforce");
		m.Gem();
		//nepotisme
		m = new Medarbejder("Milo Karsten Sørensøn Kadaver");
		m.Gem();
		m = new Medarbejder("Dette er et navn");
		m.Gem();
		m = new Medarbejder("Henning Hansen");
		m.Gem();
		m = new Medarbejder("Tommy Jonas Rath");
		m.Gem();
		m = new Medarbejder("JESUS DETTE ER ET VIRKELIGT LANGT NAVN");
		m.Gem();
		
	}
	
	private void initializeProjektLederDropDown() {
    	lederDropDown.setConverter(new StringConverter<Medarbejder>() {
            @Override
            public String toString(Medarbejder medarbejder) {
              if (medarbejder== null){
                return null;
              } else {
                return medarbejder.getInitialer();
              }
            }

          @Override
          public Medarbejder fromString(String id) {
              return null;
          }
      });
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
    	
    	if(p.getStartaar() != 0) {
    		aarstalProjektStart.setText(p.getStartaar()+"");
    	}else {
    		aarstalProjektStart.setText("");
    	}
    	
    	projektInfoNavn.setText(p.getNavn());
    	
    	
    	
    	//Indsæt mulige ledere
    	ObservableList<Medarbejder> medarbejdere =  FXCollections.observableArrayList();
    	lederDropDown.setItems(medarbejdere);
    	for(Entry<UUID, Medarbejder> e : new Medarbejder("funktionsHenter").hentAlle()) {
    		medarbejdere.add(e.getValue());
    		if(e.getValue().equals(p.getLeder())) {
    		}
    	}
    	
    	//Viser den allerede valgte leder
    	lederDropDown.setValue(p.getLeder());
    	
    }
    
    @FXML
    private void gemProjektNavn(ActionEvent event) {
    	String navn = projektInfoNavn.getText();
    	if(navn.length()>0) {
    		Projekt p = projektTabel.getSelectionModel().getSelectedItems().get(0);
    		p.setNavn(navn);
    		updateTable(projektTabel);
    	}
    }
    
    
    
    @FXML
    private void gemProjektStart(ActionEvent event)
    {
    	Projekt p = projektTabel.getSelectionModel().getSelectedItems().get(0);
    	if(p==null) {
    		popup("Der er ikke valgt et projekt");
    		return;
    	}
    	if(ugeNrProjektStart.getText()!=null) {
    		String ugeText = ugeNrProjektStart.getText();
    		String aarText = aarstalProjektStart.getText();
    		if(ugeText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeText);
        			if(i<=53&&i>0) {
        				p.setStartUge(i);
        			}else {
        				popup("Vær sød at vælge et ugenr mellem 1 og 53");
        			}
        		}catch(Exception e) {
        			popup("Ugenummeret skal gerne være et nummer");
        		}
    		}
    		
    		if(aarText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(aarText);
        			if(i>=1900) {
        				p.setStartaar(i);
        			}else {
        				popup("aarstallet maa lavest være 1900");
        			}
        		}catch(Exception e) {
        			popup("aarstallet skal være et nummer");
        		}
    		}
    	}
    }
    
    @FXML
    private void gemLederValg(ActionEvent event) {
    	Projekt p = projektTabel.getSelectionModel().getSelectedItems().get(0);
    	if(p==null) {
    		popup("Du burde vaelge et projekt foerst");
    		return;
    	}
    	p.setLeder(lederDropDown.getValue());
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
    	
    	if(a==null) {
    		popup("Der er ikke valgt en aktivitet");
    		return;
    	}
    	if(ugeNrAktivitetStart.getText()!=null) {
    		String ugeStartText = ugeNrAktivitetStart.getText();
    		String aarStartText = aarstalAktivitetStart.getText();
    		String ugeSlutText = ugeNrAktivitetSlut.getText();
    		String aarSlutText = aarstalAktivitetSlut.getText();
    		//StartTid...............
    		if(ugeStartText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeStartText);
        			if(i<=53&&i>0) {
        				a.setStartUge(i);
        			}else {
        				popup("Aktivitetens start-ugeNr skal være mellem 1 og 53");
        			}
        		}catch(Exception e) {
        			popup("Aktivitetens start-ugeNr skal være et nummer");
        		}
    		}
    		
    		if(aarStartText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(aarStartText);
        			if(i>=1900) {
        				a.setStartaar(i);
        			}else {
        				popup("Aktivitetens start-aarstal maa lavest være 1900");
        			}
        		}catch(Exception e) {
        			popup("Aktivitetens start-aarstal skal være et nummer");
        		}
    		}
    		//SlutTid ...............
    		if(ugeSlutText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeSlutText);
        			if(i<=53&&i>0) {
        				a.setSlutUge(i);
        			}else {
        				popup("Aktivitetens slut-ugeNr skal være mellem 1 og 53");
        			}
        		}catch(Exception e) {
        			popup("Aktivitetens slut-ugeNr skal være et nummer");
        		}
    		}
    		
    		if(aarSlutText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(aarSlutText);
        			if(i>=1900) {
        				a.setSlutaar(i);
        			}else {
        				popup("Aktivitetens slut-aarstal maa lavest være 1900");
        			}
        		}catch(Exception e) {
        			popup("Aktivitetens slut-aarstal skal være et nummer");
        		}
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
    	
		//StartUge
		if(a.getStartUge() != 0) {
    		ugeNrAktivitetStart.setText(a.getStartUge()+"");
    	}else {
    		ugeNrAktivitetStart.setText("");
    	}
		
		//Startaar
		if(a.getStartaar() != 0) {
    		aarstalAktivitetStart.setText(a.getStartaar()+"");
    	}else {
    		aarstalAktivitetStart.setText("");
    	}
		
		//SlutUge
		if(a.getSlutUge() != 0) {
    		ugeNrAktivitetSlut.setText(a.getSlutUge()+"");
    	}else {
    		ugeNrAktivitetSlut.setText("");
    	}
		
		//Slutaar
		if(a.getSlutUge() != 0) {
    		aarstalAktivitetSlut.setText(a.getSlutaar()+"");
    	}else {
    		aarstalAktivitetSlut.setText("");
    	}
    	
		//AktivitetNavn
    	aktivitetInfoNavn.setText(a.getNavn());
    	
    }

//Medarbejder metoder -------------------------------------------------------------------------------------------------------------------
	
    //Viser aktiviteter for valgte projekt
    private void visMedarbejdere() {
    	
    	ObservableList<Medarbejder> medarbejdere = FXCollections.observableArrayList();
        medarbejderTabel.setItems(medarbejdere);
        
        for(Entry<UUID, Medarbejder> e : new Medarbejder("funktionsHenter").hentAlle()) {
        	medarbejderTabel.getItems().add(e.getValue());
        }
    }
    
    @FXML
    private void tilfoejMedarbejder(ActionEvent event){
    	String navn = tilfoejMedarbejderNavn.getText();
    	if(navn.length()<1) {
    		popup("Vær sød at indtaste et navn");
    	}else {
    		Medarbejder m = new Medarbejder(navn);
    		m.Gem();
    		medarbejderTabel.getItems().add(m);
    	}
    }
	
	
	
	
//Brugbare metoder----------------------------------------------------------------------------------------------------------------
	private void popup(String s){
        final Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        App.initOwner(popup);
        VBox dialogVbox = new VBox(10);
        dialogVbox.getChildren().add(new Text(s));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        popup.setScene(dialogScene);
        popup.show();
	}
	
	private void updateTable(TableView table) {
		table.refresh();
	}
	
//Test tab-------------------------------------------------------------------------------------------------------------------------
	@FXML
    private void popUpTest(ActionEvent event)
	{
		popup("This is a popuptest");
	}
}
