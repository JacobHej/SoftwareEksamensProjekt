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
		this.navn = navn;
	}
	
	public Projekt(String navn, Medarbejder leder)
	{
		
	}
	
	public Boolean tilfoejAktivitet(Aktivitet aktivitet)
	{
		if (aktivitetManager.eksisterer(aktivitet))
		{
			aktivitetManager.GemAktivitet(aktivitet);
			return true;
		}
		return false;
	}
	
	public Boolean tilfoejAktivitet(Date start, Date slut, Medarbejder medarbejder)
	{
		if (aktivitetManager.eksisterer(new Aktivitet(start,slut,this,medarbejder)))
		{
			aktivitetManager.GemAktivitet(new Aktivitet(start,slut,this,medarbejder));
			return true;
		}
		return false;
		
	}

	public List<Entry<UUID, Aktivitet>> getAlleAktiviteter()
	{
		return aktivitetManager.AlleAktiviteterEfterProjekt(this);
	}
	
	public Rapport genererRapport()
	{
		return new Rapport();
	}
	
	public void setLeder(Medarbejder leder)
	{
		this.leder = leder;
	}
	
	public void setStartTid(Date startTid)
	{
		this.startTid = startTid;
	}
	
	
	
	public UUID ID()
	{
		return this.id;
	}
	
	public Medarbejder getLeder()
	{
		return this.leder;
	}
	
	public String getNavn()
	{
		return this.navn;
	}
	
	public void setNavn(String navn) {
		this.navn=navn;
	}

	public Date startTid()
	{
		return this.startTid;
	}
}
