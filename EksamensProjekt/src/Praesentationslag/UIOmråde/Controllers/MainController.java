package Praesentationslag.UIOmrÂde.Controllers;

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
	@FXML private TableColumn<Projekt,Integer> projektNummerKolonne;
	
	//The projekt info
	@FXML private TextField ugeNrProjektStart;
	@FXML private TextField ÂrstalProjektStart;
	@FXML private Text projektInfoNavn;
	
	//The activity table
	@FXML private TableView<Aktivitet> aktivitetTabel;
	@FXML private TableColumn<Aktivitet,String> aktivitetNavnKolonne;
	
	//Aktivitet info
	@FXML private TextField ugeNrAktivitetStart;
	@FXML private TextField ugeNrAktivitetSlut;
	@FXML private TextField ÂrstalAktivitetStart;
	@FXML private TextField ÂrstalAktivitetSlut;
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
        		//System.out.println("Aktivitet tilf¯jet");
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
    	
    	if(p.getStart≈r() != 0) {
    		ÂrstalProjektStart.setText(p.getStart≈r()+"");
    	}else {
    		ÂrstalProjektStart.setText("");
    	}
    	
    	projektInfoNavn.setText(p.getNavn());
    	
    }
    
    
    @FXML
    private void gemProjektStart(ActionEvent event)
    {
    	Projekt p = projektTabel.getSelectionModel().getSelectedItems().get(0);
    	if(p==null) {
    		return;
    	}
    	if(ugeNrProjektStart.getText()!=null) {
    		String ugeText = ugeNrProjektStart.getText();
    		String ÂrText = ÂrstalProjektStart.getText();
    		if(ugeText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeText);
        			if(i<=53&&i>0) {
        				p.setStartUge(i);
        			}else {
        				ugeNrProjektStart.setText("Uger gÂr mellem 1 og 53!");
        			}
        		}catch(Exception e) {
        			ugeNrProjektStart.setText("Nummer tak!");
        		}
    		}
    		
    		if(ÂrText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(ÂrText);
        			if(i>=1900) {
        				p.setStart≈r(i);
        			}else {
        				ÂrstalProjektStart.setText("Laveste Âr 1900");
        			}
        		}catch(Exception e) {
        			ÂrstalProjektStart.setText("Nummer tak!");
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
    		return;
    	}
    	if(ugeNrAktivitetStart.getText()!=null) {
    		String ugeStartText = ugeNrAktivitetStart.getText();
    		String ÂrStartText = ÂrstalAktivitetStart.getText();
    		String ugeSlutText = ugeNrAktivitetSlut.getText();
    		String ÂrSlutText = ÂrstalAktivitetSlut.getText();
    		//StartTid...............
    		if(ugeStartText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeStartText);
        			if(i<=53&&i>0) {
        				a.setStartUge(i);
        			}else {
        				ugeNrAktivitetStart.setText("Uger gÂr mellem 1 og 53!");
        			}
        		}catch(Exception e) {
        			ugeNrAktivitetStart.setText("Nummer tak!");
        		}
    		}
    		
    		if(ÂrStartText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(ÂrStartText);
        			if(i>=1900) {
        				a.setStart≈r(i);
        			}else {
        				ÂrstalAktivitetStart.setText("Laveste Âr 1900");
        			}
        		}catch(Exception e) {
        			ÂrstalAktivitetStart.setText("Nummer tak!");
        		}
    		}
    		//SlutTid ...............
    		if(ugeSlutText.length()>0) {
    			try{
        			int i = Integer.parseInt(ugeSlutText);
        			if(i<=53&&i>0) {
        				a.setSlutUge(i);
        			}else {
        				ugeNrAktivitetSlut.setText("Uger gÂr mellem 1 og 53!");
        			}
        		}catch(Exception e) {
        			ugeNrAktivitetSlut.setText("Nummer tak!");
        		}
    		}
    		
    		if(ÂrSlutText.length()>0) {
    			try{
        			
        			int i = Integer.parseInt(ÂrSlutText);
        			if(i>=1900) {
        				a.setSlut≈r(i);
        			}else {
        				ÂrstalAktivitetSlut.setText("Laveste Âr 1900");
        			}
        		}catch(Exception e) {
        			ÂrstalAktivitetSlut.setText("Nummer tak!");
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
		
		//Start≈r
		if(a.getStart≈r() != 0) {
    		ÂrstalAktivitetStart.setText(a.getStart≈r()+"");
    	}else {
    		ÂrstalAktivitetStart.setText("");
    	}
		
		//SlutUge
		if(a.getSlutUge() != 0) {
    		ugeNrAktivitetSlut.setText(a.getSlutUge()+"");
    	}else {
    		ugeNrAktivitetSlut.setText("");
    	}
		
		//Slut≈r
		if(a.getSlutUge() != 0) {
    		ÂrstalAktivitetSlut.setText(a.getSlut≈r()+"");
    	}else {
    		ÂrstalAktivitetSlut.setText("");
    	}
    	
		//AktivitetNavn
    	aktivitetInfoNavn.setText(a.getNavn());
    	
    }
	
//Brugbare metoder----------------------------------------------------------------------------------------------------------------
	
}
