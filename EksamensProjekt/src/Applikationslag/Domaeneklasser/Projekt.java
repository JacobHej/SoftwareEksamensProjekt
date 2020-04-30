package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

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
	
	public Boolean TilfoejAktivitet(Aktivitet aktivitet)
	{
		if (aktivitetManager.eksisterer(aktivitet))
		{
			aktivitetManager.GemAktivitet(aktivitet);
			return true;
		}
		return false;
	}
	
	public Boolean TilfoejAktivitet(Date start, Date slut, Medarbejder medarbejder)
	{
		if (aktivitetManager.eksisterer(new Aktivitet(start,slut,this,medarbejder)))
		{
			aktivitetManager.GemAktivitet(new Aktivitet(start,slut,this,medarbejder));
			return true;
		}
		return false;
		
	}

	public List<Entry<UUID, Aktivitet>> AlleAktiviteter()
	{
		return aktivitetManager.AlleAktiviteterEfterProjekt(this);
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
