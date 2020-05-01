package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.UUID;

import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;
import Applikationslag.Redskaber.Managers;

public class Aktivitet {
	// klassevariable
	private Date start;
	private Date slut;
	private UUID id = UUID.randomUUID();
	private Projekt projekt;
	private Medarbejder medarbejder;
	private String navn;
	private Boolean faerdig;
	
	private IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
	private IBrugttidManager brugttidManager = Managers.FaaBrugttidManager();
	
	//Metoder
	public Aktivitet(Date start, Date slut, Projekt projekt, Medarbejder medarbejder, String navn)
	{
		this.start = start;
		this.slut = slut;
		this.projekt = projekt;
		this.medarbejder = medarbejder;
		this.navn = navn;
	}
	
	public Aktivitet(Date start, Date slut, Projekt projekt, String navn)
	{
		this.start = start;
		this.slut = slut;
		this.projekt = projekt;
		this.navn = navn;
	}
	
	public Aktivitet(String navn, Projekt projekt)
	{
		this.projekt = projekt;
		this.navn = navn;
		
		aktivitetManager.GemAktivitet(this);
	}
	
	public Boolean Gem()
	{
		return aktivitetManager.GemAktivitet(this);
	}
	
	public void SaetMedarbejder(Medarbejder nyMedarbejder)
	{
		
	}
	
	public void SaetBudgetteretTid(Date start, Date slut)
	{
		
	}
	
	public Boolean TilfoejTid(int tid)
	{
		return brugttidManager.GemBrugttid(new Brugttid(this, this.medarbejder, tid));
	}
	
	public Boolean TilfoejTid(int tid, Medarbejder medarbejder)
	{
		return brugttidManager.GemBrugttid(new Brugttid(this, medarbejder, tid));
	}
	
	//Funtioner for returnering af klassevariable
	public Date Start()
	{
		return this.start;
	}
	
	public Date Slut()
	{
		return this.slut;
	}
	
	public UUID ID()
	{
		return this.id;
	}
	
	public Projekt Projekt()
	{
		return this.projekt;
	}
	
	public Medarbejder Medarbejder()
	{
		return this.medarbejder;
	}

	public String getNavn()
	{
		return this.navn;
	}
	
	public void setNavn(String navn)
	{
		this.navn = navn;
	}

	public Boolean Feardig()
	{
		return this.faerdig;
	}
}
