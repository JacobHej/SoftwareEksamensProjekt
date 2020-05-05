package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Data.Datavedholdelsesklasser.ProjektData;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAssistanceManager;
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
	private IAssistanceManager assistanceManager = Managers.FaaAssistanceManager();
	
	//Metoder
	public Aktivitet(int startUge, int slutUge, Medarbejder medarbejder, String navn)
	{
		this.startUge = startUge;
		this.slutUge = slutUge;
		this.medarbejder = medarbejder;
		this.navn = navn;
		this.startaar = Dates.getYear();
		this.slutaar = Dates.getYear();
	}
	
	public Aktivitet(int startUge, int slutUge, String navn)
	{
		this.startUge = startUge;
		this.slutUge = slutUge;
		this.navn = navn;
		this.startaar = Dates.getYear();
		this.slutaar = Dates.getYear();
	}
	
	public Aktivitet(String navn)
	{
		this.navn = navn;
		this.startaar = Dates.getYear();
		this.slutaar = Dates.getYear();
		this.startUge = Dates.getCurrentWeek();
		this.slutUge = Dates.getCurrentWeek();
	}
	
	public Boolean soegAssistance()
	{
		Assistance assistance = new Assistance(this);
		return assistanceManager.Gem(assistance);
	}
	
	public Boolean SaetMedarbejder(Medarbejder nyMedarbejder)
	{
		if (medarbejderManager.MedarbejderLedig(this.startUge, this.slutUge, this.startaar, this.slutaar, nyMedarbejder)
				&& MedarbejderData.Bibliotek.entrySet().stream()
				.anyMatch(e -> e.getValue().getNavn().equals(nyMedarbejder.getNavn())))
		{
			if(nyMedarbejder.ledig(startUge, slutUge, startaar, slutaar)) {
				this.medarbejder = nyMedarbejder;
				return true;
			}else {
				return false;
			}
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
	
	public boolean setStartUge(int startUge)
	{
//		if(startUge>this.startUge&&slutUge >= startUge) {
//			this.startUge = startUge;
//			return true;
//		}
//		slutUge >= startUge &&
		if( (medarbejder == null || medarbejder.ledig(startUge, slutUge, startaar, slutaar))) {
			this.startUge = startUge;
			return true;
		}else {
			return false;
		}
		
	}
	
	public int getSlutUge()
	{
		return this.slutUge;
	}
	
	public boolean setSlutUge(int slutUge)
	{
//		if(slutUge<this.slutUge&&slutUge >= startUge) {
//			this.slutUge = slutUge;
//			return true;
//		}
//		slutUge >= startUge && 
		if((medarbejder == null || medarbejder.ledig(startUge, slutUge, startaar, slutaar))) {
			this.slutUge = slutUge;
			return true;
		}else {
			return false;
		}
	}
	
	public int getStartaar()
	{
		return this.startaar;
	}
	
	public boolean setStartaar(int startaar)
	{
//		if(startaar>this.startaar&&slutaar >= startaar) {
//			this.startaar = startaar;
//			return true;
//		}
//		slutaar >= startaar && 
		if((medarbejder == null || medarbejder.ledig(startUge, slutUge, startaar, slutaar))) {
			this.startaar = startaar;
			return true;
		}else {
			return false;
		}
	}
	
	public int getSlutaar()
	{
		return this.slutaar;
	}
	
	public boolean setSlutaar(int slutaar)
	{
//		if(slutaar<this.slutaar&&slutaar >= startaar) {
//			this.slutaar = slutaar;
//			return true;
//		}
//		slutaar >= startaar && 
		if((medarbejder == null || medarbejder.ledig(startUge, slutUge, startaar, slutaar))) {
			this.slutaar = slutaar;
			return true;
		}else {
			return false;
		}
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
		.anyMatch(e -> e.getValue().getNavn().equals( p.getNavn())))
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
	
	public List<Entry<UUID, Brugttid>> getAlleBrugttid(){
		return brugttidManager.AlleBrugttidEfterAktivitet(this);
	}
	
	public boolean setTidsperiode(int startUge, int slutUge, int startaar, int slutaar) {
		if(medarbejder == null || medarbejder.ledig(startUge, slutUge, startaar, slutaar)) {
			this.startUge = startUge;
			this.slutUge = slutUge;
			this.startaar = startaar;
			this.slutaar = slutaar;
			return true;
		}
		return false;
	}
}
