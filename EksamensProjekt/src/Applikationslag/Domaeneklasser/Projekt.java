package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Infrastruktur.ServiceImplementationer.*;
import Applikationslag.Infrastruktur.ServiceInterfaces.*;
import Applikationslag.Redskaber.Managers;

public class Projekt {
	private UUID id = UUID.randomUUID();
	private Medarbejder leder;
	private String navn;
	private Date startTid;
	
	private IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
	private IProjektManager projektManager = Managers.FaaProjektManager();
	
	public Projekt(String navn, Date startTid)
	{
		this.navn = navn;
		this.startTid = startTid;
	}
	
	public Projekt(String navn, Date startTid, Medarbejder leder)
	{
		this.navn = navn;
		this.startTid = startTid;
		this.leder = leder;
	}
	
	public Boolean tilfoejAktivitet(Aktivitet aktivitet)
	{
		return aktivitetManager.GemAktivitet(aktivitet);
	}
	
	public Boolean tilfoejAktivitet(String navn)
	{
		aktivitetManager.GemAktivitet(new Aktivitet(navn,this));
		return true;
	}
	
	public Boolean tilfoejAktivitet(Date start, Date slut, String navn)
	{
		return aktivitetManager.GemAktivitet(new Aktivitet(start,slut,this,navn));
	}

	public Boolean Gem()
	{
		return projektManager.GemProjekt(this);
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

	public Date getStartTid()
	{
		return this.startTid;
	}
}
