package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Infrastruktur.ServiceImplementationer.*;
import Applikationslag.Infrastruktur.ServiceInterfaces.*;

public class Projekt {
	private UUID id = UUID.randomUUID();
	private Medarbejder leder;
	private String navn;
	private Date startTid;
	
	private IAktivitetManager aktivitetManager = new AktivitetManager();
	private IProjektManager projektManager = new ProjektManager();
	
	public Projekt(String navn, Date startTid)
	{
		this.navn = navn;
		this.startTid = startTid;
		
		projektManager.GemProjekt(this);
	}
	
	public Projekt(String navn, Date startTid, Medarbejder leder)
	{
		this.navn = navn;
		this.startTid = startTid;
		this.leder = leder;
		
		projektManager.GemProjekt(this);
	}
	
	public Boolean tilfoejAktivitet(Aktivitet aktivitet)
	{
		if (!aktivitetManager.eksisterer(aktivitet))
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
