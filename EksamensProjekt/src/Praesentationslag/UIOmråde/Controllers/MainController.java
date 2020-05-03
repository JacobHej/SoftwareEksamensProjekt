package Praesentationslag.UIOmr�de.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Projekt;
import Praesentationslag.UIOmr�de.Views.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MainController implements Initializable {
	
	//FXML Variables
	//The Project Table
	@FXML private TableView<Projekt> projektTabel;
	@FXML private TableColumn<Projekt,String> projektNavnKolonne;
	@FXML private TableColumn<Projekt,Integer> projektNummerKolonne;
	
	//The projekt info
	@FXML private TextField ugeNrProjektStart;
	@FXML private TextField �rstalProjektStart;
	@FXML private Text projektInfoNavn;
	
	//The activity table
	@FXML private TableView<Aktivitet> aktivitetTabel;
	@FXML private TableColumn<Aktivitet,String> aktivitetNavnKolonne;
	
	//Aktivitet info
	@FXML private TextField ugeNrAktivitetStart;
	@FXML private TextField ugeNrAktivitetSlut;
	@FXML private TextField �rstalAktivitetStart;
	@FXML private TextField �rstalAktivitetSlut;
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
        		//System.out.println("Aktivitet tilf�jet");
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
    	
    	if(p.getStart�r() != 0) {
    		�rstalProjektStart.setText(p.getStart�r()+"");
    	}else {
    		�rstalProjektStart.setText("");
    	}
    	
    	projektInfoNavn.setText(p.getNavn());
    	
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
    		String �rText = �rstalProjektStart.getText();
    		if(ugeText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeText);
        			if(i<=53&&i>0) {
        				p.setStartUge(i);
        			}else {
        				popup("V�r s�d at v�lge et ugenr mellem 1 og 53");
        			}
        		}catch(Exception e) {
        			popup("Ugenummeret skal gerne v�re et nummer");
        		}
    		}
    		
    		if(�rText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(�rText);
        			if(i>=1900) {
        				p.setStart�r(i);
        			}else {
        				popup("�rstallet m� lavest v�re 1900");
        			}
        		}catch(Exception e) {
        			popup("�rstallet skal v�re et nummer");
        		}
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
    	
    	if(a==null) {
    		popup("Der er ikke valgt en aktivitet");
    		return;
    	}
    	if(ugeNrAktivitetStart.getText()!=null) {
    		String ugeStartText = ugeNrAktivitetStart.getText();
    		String �rStartText = �rstalAktivitetStart.getText();
    		String ugeSlutText = ugeNrAktivitetSlut.getText();
    		String �rSlutText = �rstalAktivitetSlut.getText();
    		//StartTid...............
    		if(ugeStartText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeStartText);
        			if(i<=53&&i>0) {
        				a.setStartUge(i);
        			}else {
        				popup("Aktivitetens start-ugeNr skal v�re mellem 1 og 53");
        			}
        		}catch(Exception e) {
        			popup("Aktivitetens start-ugeNr skal v�re et nummer");
        		}
    		}
    		
    		if(�rStartText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(�rStartText);
        			if(i>=1900) {
        				a.setStart�r(i);
        			}else {
        				popup("Aktivitetens start-�rstal m� lavest v�re 1900");
        			}
        		}catch(Exception e) {
        			popup("Aktivitetens start-�rstal skal v�re et nummer");
        		}
    		}
    		//SlutTid ...............
    		if(ugeSlutText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeSlutText);
        			if(i<=53&&i>0) {
        				a.setSlutUge(i);
        			}else {
        				popup("Aktivitetens slut-ugeNr skal v�re mellem 1 og 53");
        			}
        		}catch(Exception e) {
        			popup("Aktivitetens slut-ugeNr skal v�re et nummer");
        		}
    		}
    		
    		if(�rSlutText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(�rSlutText);
        			if(i>=1900) {
        				a.setSlut�r(i);
        			}else {
        				popup("Aktivitetens slut-�rstal m� lavest v�re 1900");
        			}
        		}catch(Exception e) {
        			popup("Aktivitetens slut-�rstal skal v�re et nummer");
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
		
		//Start�r
		if(a.getStart�r() != 0) {
    		�rstalAktivitetStart.setText(a.getStart�r()+"");
    	}else {
    		�rstalAktivitetStart.setText("");
    	}
		
		//SlutUge
		if(a.getSlutUge() != 0) {
    		ugeNrAktivitetSlut.setText(a.getSlutUge()+"");
    	}else {
    		ugeNrAktivitetSlut.setText("");
    	}
		
		//Slut�r
		if(a.getSlutUge() != 0) {
    		�rstalAktivitetSlut.setText(a.getSlut�r()+"");
    	}else {
    		�rstalAktivitetSlut.setText("");
    	}
    	
		//AktivitetNavn
    	aktivitetInfoNavn.setText(a.getNavn());
    	
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
//Test tab-------------------------------------------------------------------------------------------------------------------------
	@FXML
    private void popUpTest(ActionEvent event)
	{
		popup("This is a popuptest");
	}
}
