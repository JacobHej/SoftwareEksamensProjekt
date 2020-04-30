package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.UUID;

public class Projekt {
	private UUID id = UUID.randomUUID();
	private Medarbejder leder;
	private String navn;
	private Date startTid;
	
	public Projekt(String navn)
	{
		
	}
	
	public Projekt(String navn, Medarbejder leder)
	{
		
	}
	
	public void TilfoejAktivitet(Aktivitet aktivitet)
	{
		
	}
	
	public void TilfoejAktivitet(Date start, Date slut, Medarbejder medarbejder)
	
	public Rapport GenererRapport()
	{
		
	}
	
	public void SaetLeder(Medarbejder leder)
	{
		this.leder = leder;
	}
	
	public SaetStartTid(Date startTid)
	{
		this.startTid = startTid;
	}
	
	public UUID ID()
	{
		return this.id;
	}
	
	public Medarbejder Leder()
	{
		return this.leder;
	}
	
	public String Navn()
	{
		return this.navn;
	}

	public Date StartTid()
	{
		return this.startTid;
	}
}
