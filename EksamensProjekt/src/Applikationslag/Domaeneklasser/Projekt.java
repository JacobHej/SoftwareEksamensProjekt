package Applikationslag.Domaeneklasser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.Random;

import Applikationslag.Infrastruktur.ServiceImplementationer.*;
import Applikationslag.Infrastruktur.ServiceInterfaces.*;
import Applikationslag.Redskaber.*;
import Applikationslag.Redskaber.LoebeNummer;
import Applikationslag.Redskaber.Dates;
import Applikationslag.Redskaber.Managers;

public class Projekt {
	static private int loebenummerCounter = 0;
	private UUID id = UUID.randomUUID();
	private Medarbejder leder;
	private String navn;
	private int projektnummer;
	private int startUge;
	private int start≈r;
	
	private IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
	private IProjektManager projektManager = Managers.FaaProjektManager();
	
	public Projekt(String navn, int startUge)
	{
		this.navn = navn;
		this.startUge = startUge;
		genererProjektnummer();

	}
	
	public Projekt(String navn)
	{
		this.navn = navn;
		genererProjektnummer();
	}
	
	public Projekt(String navn, int startUge, Medarbejder leder)
	{
		this.navn = navn;
		this.startUge = startUge;
		this.leder = leder;
		genererProjektnummer();
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
	
	private boolean genererProjektnummer() {
		try {
			String formattedDate = ""+((Dates.getYear())%100);
			String id = String.format("%04d", LoebeNummer.genererloebeNummer());
			
			projektnummer = Integer.parseInt(formattedDate+id);
			System.out.println(projektnummer+"");
			return true;
		}catch(Exception e) {
			return false;
		}
		
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
	
	public void setProjektnummer(int projektnummer) {
		this.projektnummer=projektnummer;
	}
	
	public int getProjektnummer()
	{
		return this.projektnummer;
	}
	
	public void setNavn(String navn) {
		this.navn=navn;
	}
	
	public void setStartUge(int startUge)
	{
		this.startUge = startUge;
	}

	public int getStartUge()
	{
		return this.startUge;
	}
	
	public void setStart≈r(int start≈r)
	{
		this.start≈r = start≈r;
	}

	public int getStart≈r()
	{
		return this.start≈r;
	}
}
