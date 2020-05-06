package Praesentationslag.UIOmraade.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;


import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Brugttid;
import Applikationslag.Domaeneklasser.Ferie;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IProjektManager;
import Applikationslag.Redskaber.Managers;
import Praesentationslag.UIOmraade.Views.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
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
	IBrugttidManager brugttidManager = Managers.FaaBrugttidManager();
	
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
	@FXML private TableColumn<Aktivitet,String> medarbejderAktiviteterStartKolonne;

	//medarbejderInfo
	@FXML private TextField medarbejderID;
	@FXML private TextField ugeNrAAIU;
	@FXML private TextField aarstalAAIU;
	@FXML private DatePicker hentBrugtTidDag;
	
	//Ferie Stuff
	@FXML private TextField ferieUgeStart;
	@FXML private TextField ferieUgeSlut;
	@FXML private TextField ferieAarStart;
	@FXML private TextField ferieAarSlut;
	@FXML private Text medarbejderFerier;
	@FXML private TableView<Ferie> ferieTabel;
	@FXML private TableColumn<Ferie,String> ferieStartKolonne;
	@FXML private TableColumn<Ferie,String> ferieSlutKolonne;
	
	//TidBrugt ting
	@FXML private TableView<Brugttid> tidBrugtTabel;
	@FXML private TableColumn<Brugttid,String> tidBrugtDatoKolonne;
	@FXML private TableColumn<Brugttid,String> tidBrugtTidKolonne;
	
	@FXML private ComboBox<String> tidsVaelger;
	@FXML private DatePicker tidDagVaelger;

	@FXML private Text tidBrugtProjekt;
	@FXML private Text tidBrugtAktivitet;
	@FXML private Text tidBrugtMedarbejder;
	@FXML private ComboBox<String> tidBrugtTidBrugt;
	
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
	@FXML private TextField budgetMinutter;
	@FXML private TextField budgetTimer;
	@FXML private TextField budgetDage;
	@FXML private Text aktivitetBrugtM;
	@FXML private Text aktivitetBrugtT;
	@FXML private Text aktivitetBrugtD;
	@FXML private ProgressBar aktivitetTidProgress;

	//bed om hjælp
	@FXML private DatePicker tidDagVaelgerHjælper;
	@FXML private ComboBox<String> tidsVaelgerHjælper;
	@FXML private ComboBox<Medarbejder> medarbejderDropDownHjælper;
	
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
		intitializeTidsValg();
		initializeProjectsTable();
		initializeActivitiesTable();
		initializeMedlemmerTabel();
		initializeBrugtTidTabel();
		initializeFerieTabel();
		initializeMedarbejderActivitiesTable();
		initializeProjektLederDropDown();
		initializeAktivitetMedarbejderDropDown();
		initializeAktivitetmedarbejderDropDownHjælper();
		initializeNumbersOnly();
		
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
		medarbejderAktiviteterStartKolonne.setCellValueFactory(new PropertyValueFactory<Aktivitet, String>("start"));
		
		medarbejderAktiviteterTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	medarbejderAktivitetOnSelectStart();
		    }
		});
	
	}
	
	private void initializeBrugtTidTabel() {
		tidBrugtDatoKolonne.setCellValueFactory(new PropertyValueFactory<Brugttid, String>("flotDato"));
		tidBrugtTidKolonne.setCellValueFactory(new PropertyValueFactory<Brugttid, String>("flotTid"));
		
		tidBrugtTabel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	tidBrugtOnSelectStart();
		    }
		});
	}
	
	private void intitializeTidsValg() {
		comboBoxTimePicker(tidsVaelger);
		comboBoxTimePicker(tidsVaelgerHjælper);
		comboBoxTimePicker(tidBrugtTidBrugt);
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
	
	private void initializeAktivitetmedarbejderDropDownHjælper() {
		medarbejderDropDownHjælper.setConverter(new StringConverter<Medarbejder>() {
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
        	a.TilfoejTid(2);
		}
	}
	
	private void initializeNumbersOnly() {
		textFieldNumbersOnly(budgetMinutter);
		textFieldNumbersOnly(budgetTimer);
		textFieldNumbersOnly(budgetDage);
		textFieldNumbersOnly(ugeNrAktivitetStart);
		textFieldNumbersOnly(ugeNrAktivitetSlut);
		textFieldNumbersOnly(aarstalAktivitetStart);
		textFieldNumbersOnly(aarstalAktivitetSlut);
		textFieldNumbersOnly(ugeNrProjektStart);
		textFieldNumbersOnly(aarstalProjektStart);
		textFieldNumbersOnly(ferieUgeStart);
		textFieldNumbersOnly(ferieUgeSlut);
		textFieldNumbersOnly(ferieAarStart);
		textFieldNumbersOnly(ferieAarSlut);
	}

	private void textFieldNumbersOnly(TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            textField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}

	private void comboBoxTimePicker(ComboBox<String> c) {
		for(int i = 1; i<48; i++) {
			int timer = i/2;
			String minutter = ((i%2)*30)+"";
			if(minutter.length()<2) {
				minutter += "0";
			}
			c.getItems().add(timer+":"+minutter);
		}
	}

	private void initializeFerieTabel() {
		ferieStartKolonne.setCellValueFactory(new PropertyValueFactory<Ferie, String>("flotStart"));
		ferieSlutKolonne.setCellValueFactory(new PropertyValueFactory<Ferie, String>("flotSlut"));
	}
	
//	HER
//	BEGYNDER
//	PROJEKT
//	METODER
	
//Projekt metoder ----------------------------------------------------------------------------------------------------------
	
	private void visProjekter() {
    	ObservableList<Projekt> projekter = FXCollections.observableArrayList();
        projektTabel.setItems(projekter);
        
        for(Entry<UUID, Projekt> e : projektManager.hentAlleProjekter()) {
        	projektTabel.getItems().add(e.getValue());
        }
        
        projektTabel.refresh();
	}
	
	@FXML
    private void tilfoejProjekt(ActionEvent event)
    {
		Projekt p = new Projekt(tilfoejProjektNavn.getText());
		if(!p.Gem()) {
			popup("Der skete en fejl. Tjek om et projekt med samme navn eksisterer");
		}
		//Get all the items from the table as a list, then add the new person to
		//the list
		visProjekter();
		
    }
	
	@FXML
    private void sletValgteProjekt(ActionEvent event)
    {
        
        //this gives us the Projekt selected
        Projekt p = projektTabel.getSelectionModel().getSelectedItem();
        if(p==null) {
        	popup("Intet projekt valgt");
        	return;
        }
        if(!p.fjernFraData()) {
        	popup("Tjek om projektet har nogen aktiviteter foer du sletter det");
        	return;
        }
        
        visProjekter();
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
    	
    	if(p==null) {
    		return;
    	}
    	
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
    		projektTabel.refresh();
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
    
//	HER
//	BEGYNDER
//	AKTIVITET
//	METODER
    
//Aktivitet metoder -------------------------------------------------------------------------------------------------------------------
	
	@FXML
    private void tilfoejAktivitet(ActionEvent event)
    {
		//System.out.println("You tried to AddActivity");
        Projekt p = projektTabel.getSelectionModel().getSelectedItems().get(0);
        if(p==null) {
        	popup("Intet projekt valgt");
        	return;
        }

        Aktivitet a = new Aktivitet(tilfoejAktivitetNavn.getText());
        if(!p.tilfoejAktivitet(a)) {
        	popup("Noget gik galt. Tjek om aktivitet med samme navn eksisterer");
        	return;
        }
        visAktiviteter(p);
    }
	
	@FXML
    private void sletValgteAktivitet(ActionEvent event)
	{
		//this gives us the Activity selected
        Aktivitet a = aktivitetTabel.getSelectionModel().getSelectedItem();
        
        if(a==null) {
        	popup("Ingen aktivitet valgt");
        	return;
        }
        
        if(!a.fjernFraData()) {
        	popup("Tjek om aktiviteten har noget brugt tid");
        	return;
        }
        
        Projekt p = projektTabel.getSelectionModel().getSelectedItem();
        visAktiviteter(p);
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
    	
    	//TidBrugt og budget
    	budgetMinutter.setText(a.getMinutterBudget()+"");
    	budgetTimer.setText(a.getTimerBudget()+"");
    	budgetDage.setText(a.getDageBudget()+"");

    	aktivitetBrugtM.setText(a.getMinutterBrugt()+"");
    	aktivitetBrugtT.setText(a.getTimerBrugt()+"");
    	aktivitetBrugtD.setText(a.getDageBrugt()+"");

    	aktivitetTidProgress.setProgress(a.getProcentFaerdig());
    	
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
    	
    	Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItems().get(0);
    	if(m!=null) {
    		visMedarbejderAktiviteter(m);
    	}

    	
	}
	
	@FXML
	private void gemBudgetAendringer() {
		Aktivitet a = aktivitetTabel.getSelectionModel().getSelectedItems().get(0);
		if(a==null) {
			popup("Ingen aktivitet valgt");
			return;
		}
		try {
			int minutter = 0;
			if(budgetMinutter.getText().length()>0) {
				minutter = Integer.parseInt(budgetMinutter.getText());
				minutter = minutter - minutter%30;
				budgetMinutter.setText(minutter+"");
			}
			int timer = 0;
			if(budgetTimer.getText().length()>0) {
				timer = Integer.parseInt(budgetTimer.getText());
			}
			int dage = 0;
			if(budgetDage.getText().length()>0) {
				dage = Integer.parseInt(budgetDage.getText());
			}
			//System.out.println(minutter+" "+timer+" "+dage);

			//målt i halve timer og med 12 timer om dagen
			int samlet = minutter/30+timer*2+dage*24;
			//System.out.println(samlet);
			a.SaetBudgetteretTid(samlet);
		} catch (Exception e) {
			popup("HOW THE FUCK DID YOU DO THAT YOURE NOT EVEN ALLOWED TO ENTER LETTERS LIKE REALLY WTF");
			return;
		}

		visAktivitetInfo(a);

	}

//	HER
//	BEGYNDER
//	METODERNE
//	PÅ
//	MEDARBEJDER
//	TAB

//Medarbejder metoder -------------------------------------------------------------------------------------------------------------------
	
    //Viser aktiviteter for valgte projekt
    
	//Medarbejder funktioner
	
	private void visMedarbejdere() {
    	
    	ObservableList<Medarbejder> medarbejdere = FXCollections.observableArrayList();
        medarbejderTabel.setItems(medarbejdere);
        
        for(Entry<UUID, Medarbejder> e : medarbejderManager.hentAlleMedarbejdere()) {
        	//System.out.println(e.getValue().getNavn());
        	medarbejderTabel.getItems().add(e.getValue());
        }
        
        medarbejderTabel.refresh();
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
	
    @FXML
    private void sletValgteMedarbejder(ActionEvent event) {
    	//this gives us the Activity selected
        Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItem();
        
        if(m==null) {
        	popup("Ingen medarbejder valgt");
        	return;
        }
        
        if(!m.fjernFraData()) {
        	popup("Tjek om medarbejderen har aktiviteter eller brugt tid nogen steder");
        	return;
        }
        
        visMedarbejdere();
    }
    
    private void medarbejderOnSelectStart(){
    	//System.out.println("der bliv valgt en medarbejder");
    	Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItems().get(0);
    	if(m==null) {
    		popup("Du valgte en medarbejder men ingen medarbejder blev valgt???");
    		return;
    	}
    	visMedarbejderAktiviteter(m);
    	visMedarbejderInfo(m);
    	visMuligeHjælpere(m);
    	visBrugtTid(null);
    	visFerier();
    }
    
    private void visMedarbejderAktiviteter(Medarbejder m){
    	ObservableList<Aktivitet> aktiviteter = FXCollections.observableArrayList();
    	medarbejderAktiviteterTabel.setItems(aktiviteter);
        for(Entry<UUID, Aktivitet> e : m.getAlleAktiviteter()) {
        	medarbejderAktiviteterTabel.getItems().add(e.getValue());
        }
    }
    
    private void visMedarbejderInfo(Medarbejder m) {
    	medarbejderID.setText(m.getNavn());
    }
    

    @FXML
    private void gemMedarbejderNavnNyt(ActionEvent event){

    	Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItems().get(0);
    	if(m==null) {
    		popup("Du burde vælge en medarbejder.");
    		return;
    	}
    	String navn = medarbejderID.getText();
    	if(navn.length()<1) {
    		popup("Vær sød at indtaste et navn");
    		return;
    	}
    	if(navn.length()>4) {
    		popup("Kun 4 initialer");
    		return;
    	}
    	if(!m.setNavn(navn)) {
    		popup("Noget gik galt, tjek om der findes en medarbejder med samme navn");
    		return;
    	}

    	for(Entry<UUID, Medarbejder> e : medarbejderManager.hentAlleMedarbejdere()) {
    		//System.out.println(e.getValue().getNavn());
        }

    	visMedarbejdere();

    }
	
    
    private void medarbejderAktivitetOnSelectStart(){
    	Aktivitet a = medarbejderAktiviteterTabel.getSelectionModel().getSelectedItems().get(0);
    	visBrugtTid(a);
    }
    
    private void visBrugtTid(Aktivitet a) {
    	Brugttid b = tidBrugtTabel.getSelectionModel().getSelectedItem();
    	ObservableList<Brugttid> tider = FXCollections.observableArrayList();
    	tidBrugtTabel.setItems(tider);
    	
    	if(a==null) {
    		return;
    	}
    	
        for(Entry<UUID, Brugttid> e : a.getAlleBrugttid()) {
        	tidBrugtTabel.getItems().add(e.getValue());
        }
        
        tidBrugtTabel.refresh();
        if(b!=null&&b.Aktivitet()==a&&brugttidManager.Eksisterer(b)) {
        	tidBrugtTabel.getSelectionModel().select(b);
    	}
    }
    
    @FXML
    private void gemBrugtTid(ActionEvent event) {
    	Aktivitet a = medarbejderAktiviteterTabel.getSelectionModel().getSelectedItems().get(0);
    	if(a==null) {
    		popup("Ingen aktivitet valgt");
    		return;
    	}
    	LocalDate date = tidDagVaelger.getValue();
    	if(date == null) {
    		popup("Ingen dato valgt");
    		return;
    	}
    	WeekFields weekFields = WeekFields.of(Locale.getDefault());
    	int uge = date.get(weekFields.weekOfWeekBasedYear());
    	int aar = date.getYear();
    	boolean d1 = a.getStartaar()<aar || (a.getStartaar()==aar&&a.getStartUge()<=uge);
    	boolean d2 = a.getSlutaar()>aar || (a.getSlutaar()==aar&&a.getSlutUge()>=uge);
    	if(!(d1&&d2)) {
    		popup("Dato valgt ligger ikke i aktivitets ugerne");
    		return;
    	}
    	String tid = tidsVaelger.getValue();
    	if(tid.length()<1) {
    		popup("Ingen tid valgt");
    		return;
    	}
    	//System.out.println(tid);
    	int i;
    	int j;
    	try {
    		i = Integer.parseInt(tid.substring(0, tid.length()-3));
        	j = Integer.parseInt(tid.substring(tid.length()-2, tid.length()));
    	}catch(Exception e){
    		popup("Forkert tid valgt");
    		return;
    	}

    	int tidConvert = i*2 + j/30;

    	a.TilfoejTid(tidConvert, new Date(date.getYear()-1900,date.getMonthValue()-1,date.getDayOfMonth()));

    	visBrugtTid(a);
    }
    

    @FXML
    private void hentAntalAktiviteter(ActionEvent event){
    	Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItems().get(0);
    	if(m==null) {
    		popup("Ingen medarbejder valgt");
    		return;
    	}

    	try {
    		int ugeNr = Integer.parseInt(ugeNrAAIU.getText());
    		int aarstal = Integer.parseInt(aarstalAAIU.getText());
    		if(ugeNr<1||ugeNr>53) {
    			popup("ugenumre går fra 1 til 53");
    			return;
    		}
    		if(aarstal<1900) {
    			popup("laveste år muligt er 1900");
    			return;
    		}
			long i = m.AktiviteterIDenneUge(ugeNr,aarstal);
			popup(m.getNavn()+" har "+i+" aktiviteter i uge "+ugeNr+" år "+aarstal);
		} catch (Exception e) {
			popup("Enten uge eller år var ikke tal");
			return;
		}


    }
    

    private void visMuligeHjælpere(Medarbejder m) {
    	//Indsæt mulige hjælpere
    	ObservableList<Medarbejder> hjælpere =  FXCollections.observableArrayList();
    	medarbejderDropDownHjælper.setItems(hjælpere);
    	for(Entry<UUID, Medarbejder> e : medarbejderManager.hentAlleMedarbejdere()) {
    		if(!e.getValue().equals(m)) {
    			hjælpere.add(e.getValue());
    		}
    	}
    }
    

    @FXML
    private void gemBrugtTidHjælper() {
    	Aktivitet a = medarbejderAktiviteterTabel.getSelectionModel().getSelectedItems().get(0);
    	if(a==null) {
    		popup("Ingen aktivitet valgt");
    		return;
    	}
    	LocalDate date = tidDagVaelgerHjælper.getValue();
    	if(date == null) {
    		popup("Ingen dato valgt");
    		return;
    	}
    	WeekFields weekFields = WeekFields.of(Locale.getDefault());
    	int uge = date.get(weekFields.weekOfWeekBasedYear());
    	int aar = date.getYear();
    	boolean d1 = a.getStartaar()<aar || (a.getStartaar()==aar&&a.getStartUge()<=uge);
    	boolean d2 = a.getSlutaar()>aar || (a.getSlutaar()==aar&&a.getSlutUge()>=uge);
    	if(!(d1&&d2)) {
    		popup("Dato valgt ligger ikke i aktivitets ugerne");
    		return;
    	}
    	String tid = tidsVaelgerHjælper.getValue();
    	if(tid.length()<1) {
    		popup("Ingen tid valgt");
    		return;
    	}
    	//System.out.println(tid);
    	int i;
    	int j;
    	try {
    		i = Integer.parseInt(tid.substring(0, tid.length()-3));
        	j = Integer.parseInt(tid.substring(tid.length()-2, tid.length()));
    	}catch(Exception e){
    		popup("Forkert tid valgt");
    		return;
    	}

    	int tidConvert = i*2 + j/30;

    	Medarbejder m = medarbejderDropDownHjælper.getValue();
    	if(m==null) {
    		popup("Der var ingen medarbejder?");
    		return;
    	}

    	a.TilfoejTid(tidConvert, new Date(date.getYear()-1900,date.getMonthValue()-1,date.getDayOfMonth()),m);

    	visBrugtTid(a);
    }
    

    private void tidBrugtOnSelectStart() {
    	Brugttid b = tidBrugtTabel.getSelectionModel().getSelectedItems().get(0);
    	if(b==null) {
    		popup("Så du selectede en brugt tid, men ingen brugt tid var selected.... Skriv til Erik for hjælp");
    		return;
    	}
    	visTidBrugtInfo(b);
    }
    

    private void visTidBrugtInfo(Brugttid b) {
    	tidBrugtProjekt.setText(b.Aktivitet().getProjekt().getNavn());
    	tidBrugtAktivitet.setText(b.Aktivitet().getNavn());
    	tidBrugtMedarbejder.setText(b.Medarbejder().getNavn());
    	tidBrugtTidBrugt.setValue(b.getFlotTid());
    }
    

    @FXML
    private void gemNyTidBrugt() {
    	Brugttid b = tidBrugtTabel.getSelectionModel().getSelectedItems().get(0);
    	if(b==null) {
    		popup("Du mangler at vælge en brugt tid at ændre");
    		return;
    	}

    	String tid = tidBrugtTidBrugt.getValue();
    	if(tid.length()<1) {
    		popup("Ingen tid valgt");
    		return;
    	}
    	//System.out.println(tid);
    	int i;
    	int j;
    	try {
    		i = Integer.parseInt(tid.substring(0, tid.length()-3));
        	j = Integer.parseInt(tid.substring(tid.length()-2, tid.length()));
    	}catch(Exception e){
    		popup("Forkert tid valgt");
    		return;
    	}

    	int tidConvert = i*2 + j/30;

    	b.AendreTid(tidConvert);

    	visBrugtTid(medarbejderAktiviteterTabel.getSelectionModel().getSelectedItems().get(0));
    }
    

    @FXML
    private void sletTidBrugt() {
    	Brugttid b = tidBrugtTabel.getSelectionModel().getSelectedItems().get(0);
    	if(b==null) {
    		popup("Du mangler at vælge en brugt tid at ændre");
    		return;
    	}
    	brugttidManager.fjern(b);
    	visBrugtTid(medarbejderAktiviteterTabel.getSelectionModel().getSelectedItems().get(0));
    }
    

    @FXML
    private void hentTidBrugtDagKnap(ActionEvent event) {
    	Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItem();
    	if(m==null) {
    		popup("Ingen medarbejder valgt");
    		return;
    	}
    	LocalDate d = hentBrugtTidDag.getValue();
    	int tid = m.tidBrugtIdag(new Date(d.getYear()-1900,d.getMonthValue()-1,d.getDayOfMonth()));
    	int timer = tid/2;
		String minutter = ((tid%2)*30)+"";
		if(minutter.length()<2) {
			minutter += "0";
		}
    	String flottid = timer+":"+minutter;
    	popup(m.getNavn()+" har brugt "+flottid+" Timer på den valgte dag");
    	
    }
    
    @FXML
    private void tagFerieKnap(ActionEvent event) {
    	Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItem();
    	if(m==null) {
    		popup("Ingen medarbejder valgt");
    		return;
    	}
    	int startUge;
		int slutUge;
		int startaar;
		int slutaar;
		try {
			startUge = Integer.parseInt(ferieUgeStart.getText());
			slutUge = Integer.parseInt(ferieUgeSlut.getText());
			startaar = Integer.parseInt(ferieAarSlut.getText());
			slutaar = Integer.parseInt(ferieAarSlut.getText());
		} catch (Exception e) {
			popup("En af de intastede tal var ikke et tal");
			return;
		}
    	
		if(
				startUge<1||startUge>53||
				slutUge<1||slutUge>53||
				startaar<1900||slutaar<1900
			) {
			popup("Uger går kun fra 1 til 53 og aar start på 1900");
			return;
		}
    	if(!m.tagFerie(startUge, slutUge, startaar, slutaar)) {
    		popup("Noget ferie gik galt, tjek at du ikke har arbejde i den uge");
    		return;
    	}
    	visFerier();
    	
    }

    @FXML
    private void sletValgteFerie(ActionEvent event) {
    	//this gives us the Activity selected
        Ferie f = ferieTabel.getSelectionModel().getSelectedItem();
        
        if(f==null) {
        	popup("Ingen ferie valgt");
        	return;
        }
        
        if(!f.fjernFraData()) {
        	popup("Ferien burde altid return true så hvis du er her har du et problem");
        	return;
        }
        
        visFerier();
    }
    
    private void visFerier() {
    	Medarbejder m = medarbejderTabel.getSelectionModel().getSelectedItem();
    	if(m==null) {
    		popup("Ingen medarbejder valgt");
    		return;
    	}
    	medarbejderFerier.setText(m.getNavn()+"'s ferier");
    	
    	ObservableList<Ferie> ferier = FXCollections.observableArrayList();
    	ferieTabel.setItems(ferier);
    	for(Entry<UUID, Ferie> e : m.getFerier()) {
    		ferieTabel.getItems().add(e.getValue());
        }
    	
    }
    
//	HER
//	BEGYNDER
//	NYTTIGE
//	METODER
    
    
//Brugbare metoder----------------------------------------------------------------------------------------------------------------
	private void popup(String s){
        final Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        App.initOwner(popup);
        TextFlow dialogbox = new TextFlow();
        dialogbox.getChildren().add(new Text(s));
        Scene dialogScene = new Scene(dialogbox, 200, 100);
        popup.setScene(dialogScene);
        popup.show();
	}
	

	
//Test tab-------------------------------------------------------------------------------------------------------------------------
	
}
