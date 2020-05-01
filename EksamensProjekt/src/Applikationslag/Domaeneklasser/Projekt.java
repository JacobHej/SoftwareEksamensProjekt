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
	private int startUge;
	
	private IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
	private IProjektManager projektManager = Managers.FaaProjektManager();
	
	public Projekt(String navn, int startUge)
	{
		this.navn = navn;
		this.startUge = startUge;
	}
	
	public Projekt(String navn)
	{
		this.navn = navn;
	}
	
	public Projekt(String navn, int startUge, Medarbejder leder)
	{
		this.navn = navn;
		this.startUge = startUge;
		this.leder = leder;
	}
	
	public Boolean tilfoejAktivitet(Aktivitet aktivitet)
	{
		aktivitet.setProjekt(this);
		return aktivitetManager.GemAktivitet(aktivitet);
	}
	
	public Boolean tilfoejAktivitet(String navn)
	{
		Aktivitet a = new Aktivitet(navn);
		a.setProjekt(this);
		aktivitetManager.GemAktivitet(a);
		return true;
	}
	
	public Boolean tilfoejAktivitet(int start, int slut, String navn)
	{
		Aktivitet a = new Aktivitet(start,slut,navn);
		a.setProjekt(this);
		return aktivitetManager.GemAktivitet(a);
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
		// eksisterer han
		this.leder = leder;
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
	
	public void setStartTid(Date startTid)
	{
		this.startUge = startUge;
	}

	public int getStartUge()
	{
		return this.startUge;
	}
}
