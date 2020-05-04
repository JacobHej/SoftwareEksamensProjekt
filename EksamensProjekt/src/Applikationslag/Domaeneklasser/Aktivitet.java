package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.UUID;

import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Data.Datavedholdelsesklasser.ProjektData;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Dates;
import Applikationslag.Redskaber.GlobaleVariable;
import Applikationslag.Redskaber.Managers;

public class Aktivitet {
	// klassevariable
	private int startUge;
	private int slutUge;
	private int startaar;
	private int slutaar;
	private UUID id = UUID.randomUUID();
	private Projekt projekt;
	private Medarbejder medarbejder;
	private String navn;
	private Boolean faerdig;
	private int budgetTid;
	
	private IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
	private IBrugttidManager brugttidManager = Managers.FaaBrugttidManager();
	private IMedarbejderManager medarbejderManager = Managers.FaaMedarbejderManager();
	
	//Metoder
	public Aktivitet(int startUge, int slutUge, Medarbejder medarbejder, String navn)
	{
		this.startUge = startUge;
		this.slutUge = slutUge;
		this.medarbejder = medarbejder;
		this.navn = navn;
	}
	
	public Aktivitet(int startUge, int slutUge, String navn)
	{
		this.startUge = startUge;
		this.slutUge = slutUge;
		this.navn = navn;
	}
	
	public Aktivitet(String navn)
	{
		this.navn = navn;
	}
	
	
	public Boolean SaetMedarbejder(Medarbejder nyMedarbejder)
	{
		if (medarbejderManager.AktiviteterIDenneUge(Dates.getCurrentWeek(), nyMedarbejder) 
				< GlobaleVariable.MaksimaleVagter()
				&& MedarbejderData.Bibliotek.entrySet().stream()
				.anyMatch(e -> e.getValue().getNavn() == medarbejder.getNavn()))
		{
			this.medarbejder = nyMedarbejder;
			return true;
		}
		else
			return false;
	}
	
	public void SaetBudgetteretTid(int budgetTid)
	{
		this.budgetTid = budgetTid;
	}
	
	public Boolean TilfoejTid(int tid)
	{
		// er det mere tid end aktiviteten har eksisteret
		return brugttidManager.GemBrugttid(new Brugttid(this, this.medarbejder, tid));
	}
	
	public Boolean TilfoejTid(int tid, Medarbejder medarbejder)
	{
		return brugttidManager.GemBrugttid(new Brugttid(this, medarbejder, tid));
	}
	
	public boolean TilfoejTid(Brugttid brugttid) {
		return brugttidManager.GemBrugttid(brugttid);
	}
	
	//Funtioner for returnering af klassevariable
	public int getStartUge()
	{
		return this.startUge;
	}
	
	public void setStartUge(int startUge)
	{
		this.startUge = startUge;
	}
	
	public int getSlutUge()
	{
		return this.slutUge;
	}
	
	public void setSlutUge(int slutUge)
	{
		this.slutUge = slutUge;
	}
	
	public int getStartaar()
	{
		return this.startaar;
	}
	
	public void setStartaar(int startaar)
	{
		this.startaar = startaar;
	}
	
	public int getSlutaar()
	{
		return this.slutaar;
	}
	
	public void setSlutaar(int slutaar)
	{
		this.slutaar = slutaar;
	}
	
	public UUID ID()
	{
		return this.id;
	}
	
	public Projekt getProjekt()
	{
		return this.projekt;
	}
	
	public void setProjekt(Projekt p) {
		if(ProjektData.Bibliotek.entrySet().stream()
		.anyMatch(e -> e.getValue().getNavn() == p.getNavn()))
			this.projekt = p;
	}
	
	public Medarbejder Medarbejder()
	{
		return this.medarbejder;
	}

	public String getNavn()
	{
		return this.navn;
	}
	
	public int getBudgetTid() {
		return this.budgetTid;
	}
	
	public void setNavn(String navn)
	{
		this.navn = navn;
	}

	public Boolean Feardig()
	{
		return this.faerdig;
	}
	
	public String toString() {
    	return navn;
    }

}
