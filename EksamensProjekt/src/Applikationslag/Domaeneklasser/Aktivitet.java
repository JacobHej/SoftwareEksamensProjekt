package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.UUID;

public class Aktivitet {
	// klassevariable
	private Date start;
	private Date slut;
	private UUID id = UUID.randomUUID();
	private Projekt projekt;
	private Medarbejder medarbejder;
	private String navn;
	private Boolean faerdig;
	
	//Metoder
	public Aktivitet(Date start, Date slut, Projekt projekt, Medarbejder medarbejder, String navn)
	{
		
	}
	
	public Aktivitet(String navn, Projekt projekt)
	{
		
	}
	
	public void SaetMedarbejder(Medarbejder nyMedarbejder)
	{
		
	}
	
	public void SeatBudgetteretTid(Date start, Date slut)
	{
		
	}
	
	public void TilfoejTid(int tid)
	{
		
	}
	
	public void TilfoejTid(int tid, Medarbejder medarbejder)
	{
		
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

	public String Navn()
	{
		return this.navn;
	}

	public Boolean Feardig()
	{
		return this.faerdig;
	}
}
