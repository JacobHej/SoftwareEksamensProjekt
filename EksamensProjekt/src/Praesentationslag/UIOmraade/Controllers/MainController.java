package Praesentationslag.UIOmraade.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;


import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IProjektManager;
import Applikationslag.Redskaber.Managers;
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
	
	//Managers
	IMedarbejderManager medarbejderManager = Managers.FaaMedarbejderManager();
	IProjektManager projektManager = Managers.FaaProjektManager();
	
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
	
	//medarbejderAktiviteterTabel
	@FXML private TableView<Aktivitet> medarbejderAktiviteterTabel;
	@FXML private TableColumn<Aktivitet,String> medarbejderAktiviteterNavnKolonne;
	
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
	@FXML private ComboBox<Medarbejder> aktivitetMedarbejderDropDown;
	
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
		
		lavTommy();
		initializeProjectsTable();
		initializeActivitiesTable();
		initializeMedlemmerTabel();
		initializeMedarbejderActivitiesTable();
		initializeProjektLederDropDown();
		initializeAktivitetMedarbejderDropDown();
	}
	
	public void initializeProjectsTable() {

		//set up the columns in the table
		projektNavnKolonne.setCellValueFactory(new PropertyValueFactory<Projekt, String>("navn"));
		projektNummerKolonne.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("projektnummer"));
		
        //load dummy data
		tilfoejProjekter();
		visProjekter();
		
		
		//adds a listener for when selecting on the table
		projektTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	projectOnSelectStart();
		    }
		});
	}
	
	public void  tilfoejProjekter()
    {
        for(int i = 0;i<10; i++) {
        	Projekt p = new Projekt("Project nr: "+i);
        	p.Gem();
        	tilfoejAktiviteter(p);
        }
    }
	
	//Dummy date aktiviteter
	public void  tilfoejAktiviteter(Projekt p)
    {
        for(int i = 0; i<10; i++) {
        	Aktivitet a = new Aktivitet("Aktivitet "+i+" for "+p.getNavn());
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
		medarbejderInitialKolonne.setCellValueFactory(new PropertyValueFactory<Medarbejder, String>("navn"));
		tilfoejMedarbejdere();
		visMedarbejdere();
		
		medarbejderTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	medarbejderOnSelectStart();
		    }
		});
	}
	
	public void initializeMedarbejderActivitiesTable() {
		medarbejderAktiviteterNavnKolonne.setCellValueFactory(new PropertyValueFactory<Aktivitet, String>("navn"));
	}
	
	public void tilfoejMedarbejdere() {
		Medarbejder m;
		m = new Medarbejder("AAAA");
		m.Gem();
		m = new Medarbejder("AAB");
		m.Gem();
		//nepotisme
		m = new Medarbejder("AAC");
		m.Gem();
		m = new Medarbejder("ADAC");
		m.Gem();
		m = new Medarbejder("WWII");
		m.Gem();
		m = new Medarbejder("EAL");
		m.Gem();
		
	}
	
	private void initializeProjektLederDropDown() {
    	lederDropDown.setConverter(new StringConverter<Medarbejder>() {
            @Override
            public String toString(Medarbejder medarbejder) {
              if (medarbejder== null){
                return null;
              } else {
                return medarbejder.getNavn();
              }
            }

          @Override
          public Medarbejder fromString(String id) {
              return null;
          }
      });
	}
	
	private void initializeAktivitetMedarbejderDropDown() {
		aktivitetMedarbejderDropDown.setConverter(new StringConverter<Medarbejder>() {
            @Override
            public String toString(Medarbejder medarbejder) {
              if (medarbejder== null){
                return null;
              } else {
                return medarbejder.getNavn();
              }
            }

          @Override
          public Medarbejder fromString(String id) {
              return null;
          }
      });
	}
	
	private void lavTommy() {
		Medarbejder m;
		m = new Medarbejder("TJR");
		m.Gem();
		Projekt p = new Projekt("Tommys projekt");
    	p.Gem();
		for(int i = 0; i<19; i++) {
			Aktivitet a = new Aktivitet("Tommys "+i+". aktivitet");
        	if(p.tilfoejAktivitet(a)) {
        		//System.out.println("Aktivitet tilføjet");
        	}
        	a.SaetMedarbejder(m);
		}
	}
	
//Projekt metoder ----------------------------------------------------------------------------------------------------------
	
	private void visProjekter() {
    	ObservableList<Projekt> projekter = FXCollections.observableArrayList();
        projektTabel.setItems(projekter);
        
        for(Entry<UUID, Projekt> e : projektManager.hentAlleProjekter()) {
        	projektTabel.getItems().add(e.getValue());
        }
	}
	
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
    	for(Entry<UUID, Medarbejder> e : medarbejderManager.hentAlleMedarbejdere()) {
    		medarbejdere.add(e.getValue());
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
    		int ugeStart = a.getStartUge();
    		int aarStart = a.getStartaar();
    		int ugeSlut = a.getSlutUge();
    		int aarSlut = a.getSlutaar();
    		//StartTid...............
    		if(ugeStartText.length()>0) {
    			try{
        			ugeStart = Integer.parseInt(ugeStartText);
        		}catch(Exception e) {
        			popup("Aktivitetens start-ugeNr skal være et nummer");
        			return;
        		}
    		}
    		
    		if(aarStartText.length()>0) {
    			try{
    				aarStart = Integer.parseInt(aarStartText);
        		}catch(Exception e) {
        			popup("Aktivitetens start-aarstal skal være et nummer");
        			return;
        		}
    		}
    		//SlutTid ...............
    		if(ugeSlutText.length()>0) {
    			try{
        			ugeSlut = Integer.parseInt(ugeSlutText);
        		}catch(Exception e) {
        			popup("Aktivitetens slut-ugeNr skal være et nummer");
        			return;
        		}
    		}
    		
    		if(aarSlutText.length()>0) {
    			try{
        			
    				aarSlut = Integer.parseInt(aarSlutText);
        		}catch(Exception e) {
        			popup("Aktivitetens slut-aarstal skal være et nummer");
        			return;
        		}
    		}
    		
    		if(ugeStart<=53&&ugeStart>0) {
				if(ugeStart>ugeSlut) {
					popup("start uge burde være før slut uge");
					return;
				}else if(!a.setStartUge(ugeStart)) {
					popup("Medarbejderen er ikke ledig her");
					return;
				}
			}else {
				popup("Aktivitetens start-ugeNr skal være mellem 1 og 53");
				return;
			}
    		
    		if(aarStart>=1900) {
				if(aarStart<aarSlut) {
					popup("Slut aar burde være efter start");
					return;
				}else if(!a.setSlutaar(aarStart)) {
					popup("Medarbejderen er ikke ledig her");
					return;
				}
			}else {
				popup("Aktivitetens slut-aarstal maa lavest være 1900");
				return;
			}
    		
    		if(ugeSlut<=53&&ugeSlut>0) {
				if(ugeSlut<ugeStart) {
					popup("Slut uge burde være efter start");
					return;
				}else if(!a.setSlutUge(ugeSlut)) {
					popup("Medarbejderen er ikke ledig her");
					return;
				}
			}else {
				popup("Aktivitetens slut-ugeNr skal være mellem 1 og 53");
				return;
			}
    		
    		if(aarSlut>=1900) {
				if(aarSlut<aarStart) {
					popup("Slut aar burde være efter start");
					return;
				}else if(!a.setSlutaar(aarSlut)) {
					popup("Medarbejderen er ikke ledig her");
					return;
				}
			}else {
				popup("Aktivitetens slut-aarstal maa lavest være 1900");
				return;
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
		if(a.getSlutaar() != 0) {
    		aarstalAktivitetSlut.setText(a.getSlutaar()+"");
    	}else {
    		aarstalAktivitetSlut.setText("");
    	}
    	
		//AktivitetNavn
    	aktivitetInfoNavn.setText(a.getNavn());
    	
    	//Indsæt mulige medarbejdere
    	ObservableList<Medarbejder> medarbejdere =  FXCollections.observableArrayList();
    	aktivitetMedarbejderDropDown.setItems(medarbejdere);
    	for(Entry<UUID, Medarbejder> e : medarbejderManager.AlleLedigeMedarbejdere(
    				a.getStartUge(), a.getSlutUge(), a.getStartaar(), a.getSlutaar())) {
    		medarbejdere.add(e.getValue());
    	}
    	
    	//Viser den allerede valgte medarbejder
    	aktivitetMedarbejderDropDown.setValue(a.Medarbejder());
    	
    	
    }
	
	@FXML
	private void gemAktivitetMedarbejder(ActionEvent event) {
    	Aktivitet a = aktivitetTabel.getSelectionModel().getSelectedItems().get(0);
    	if(a==null) {
    		popup("Du burde vaelge en aktivitet foerst");
    		return;
    	}
    	if(!a.SaetMedarbejder(aktivitetMedarbejderDropDown.getValue())) {
    		popup("Medarbejderen er ikke ledig her");
    	}
    	
	}

//Medarbejder metoder -------------------------------------------------------------------------------------------------------------------
	
    //Viser aktiviteter for valgte projekt
    private void visMedarbejdere() {
    	
    	ObservableList<Medarbejder> medarbejdere = FXCollections.observableArrayList();
        medarbejderTabel.setItems(medarbejdere);
        
        for(Entry<UUID, Medarbejder> e : medarbejderManager.hentAlleMedarbejdere()) {
        	medarbejderTabel.getItems().add(e.getValue());
        }
    }
    
    @FXML
    private void tilfoejMedarbejder(ActionEvent event){
    	String navn = tilfoejMedarbejderNavn.getText();
    	if(navn.length()<1) {
    		popup("Vær sød at indtaste et navn");
    		return;
    	}
    	if(navn.length()>4) {
    		popup("Kun 4 initialer");
    		return;
    	}
    	
    	Medarbejder m = new Medarbejder(navn);
    	m.Gem();
    	medarbejderTabel.getItems().add(m);
    }
	
    private void medarbejderOnSelectStart(){
    	System.out.println("der bliv valgt en medarbejder");
    	Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItems().get(0);
    	if(m==null) {
    		popup("Du valgte en medarbejder men ingen medarbejder blev valgt???");
    		return;
    	}
    	visMedarbejderAktiviteter(m);
    }
    
    private void visMedarbejderAktiviteter(Medarbejder m){
    	ObservableList<Aktivitet> aktiviteter = FXCollections.observableArrayList();
    	medarbejderAktiviteterTabel.setItems(aktiviteter);
        for(Entry<UUID, Aktivitet> e : m.getAlleAktiviteter()) {
        	medarbejderAktiviteterTabel.getItems().add(e.getValue());
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
