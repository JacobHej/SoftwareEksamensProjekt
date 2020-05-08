package Applikationslag.Domaeneklasser;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Infrastruktur.ServiceInterfaces.*;
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
	private int startaar;
	
	private IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
	private IProjektManager projektManager = Managers.FaaProjektManager();
	
	public Projekt(String navn, int startUge)
	{
		this.navn = navn;
		this.startUge = startUge;
		genererProjektnummer();

	}
	
	public String toString() {
		String s = (projektnummer + ": " + navn);
		return s;
	}
	
	public Projekt(String navn)
	{
		this.navn = navn;
		genererProjektnummer();
	}
	
	public Boolean tilfoejAktivitet(Aktivitet aktivitet)
	{
		if(!aktivitet.setProjekt(this)) {
//			System.out.println("Aktivitet kunne ikke sætte projekt");
			return false;
		}
		boolean done2 = aktivitetManager.GemAktivitet(aktivitet);
//		System.out.println("Her er done2:"+done2);
		return done2;
	}
	
	public Boolean tilfoejAktivitet(String navn)
	{
		Aktivitet a = new Aktivitet(navn);
		return tilfoejAktivitet(a);
	}
	
	private boolean genererProjektnummer() {
		try {
			String formattedDate = ""+((Dates.getYear())%100);
			String id = String.format("%04d", LoebeNummer.genererloebeNummer());
			
			projektnummer = Integer.parseInt(formattedDate+id);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}

	public Boolean Gem()
	{
		return projektManager.GemProjekt(this);
	}
	
	public Boolean fjernFraData() {
		return projektManager.fjern(this);
    }
	
	public List<Entry<UUID, Aktivitet>> getAlleAktiviteter()
	{
		return aktivitetManager.AlleAktiviteterEfterProjekt(this);
	}
	
	public UUID ID()
	{
		return this.id;
	}
	
	public void setLeder(Medarbejder leder)
	{
		// eksisterer han
		this.leder = leder;
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
	
	public void setStartaar(int startaar)
	{
		this.startaar = startaar;
	}

	public int getStartaar()
	{
		return this.startaar;
	}
}
