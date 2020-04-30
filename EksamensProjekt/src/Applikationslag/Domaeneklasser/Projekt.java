package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.UUID;

import Applikationslag.Infrastruktur.ServiceImplementationer.AktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;

public class Projekt {
	private UUID id = UUID.randomUUID();
	private Medarbejder leder;
	private String navn;
	private Date startTid;
	
	private IAktivitetManager aktivitetManager = new AktivitetManager();
	
	
	public Projekt(String navn)
	{
		
	}
	
	public Projekt(String navn, Medarbejder leder)
	{
		
	}
	
	public void TilfoejAktivitet(Aktivitet aktivitet)
	{
		aktivitetManager.GemAktivitet(aktivitet);
	}
	
	public void TilfoejAktivitet(Date start, Date slut, Medarbejder medarbejder)
	{
		aktivitetManager.GemAktivitet(new Aktivitet(start,slut,this,medarbejder));
	}

	public Rapport GenererRapport()
	{
		return new Rapport();
	}
	
	public void SaetLeder(Medarbejder leder)
	{
		this.leder = leder;
	}
	
	public void SaetStartTid(Date startTid)
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
