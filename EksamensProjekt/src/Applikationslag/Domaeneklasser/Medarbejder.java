package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IFerieManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Managers;

public class Medarbejder
{
	// klassevariable
    private UUID id = UUID.randomUUID();
    private String navn = "TesT";
    
    private IBrugttidManager brugttidManager = Managers.FaaBrugttidManager();
    private IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
    
    private static IMedarbejderManager medarbejderManager = Managers.FaaMedarbejderManager();
    
    //metoder
    public Medarbejder(String navn)
    {
    	setNavn(navn);
    }
    //Funtioner for returnering af klassevariable
    public UUID ID()
    {
    	return this.id;
    }
    
    public String getNavn()
    {
    	return this.navn;
    }
    
    public boolean setNavn(String navn) {
    	if(navn.length()>4) {
    		return false;
    	}else {
    		this.navn=navn;
    		return true;
    	}
    }
   
    public Boolean Gem()
    {
    	return medarbejderManager.GemMedarbejder(this);
    }
    
    public long AktiviteterIDenneUge(int week, int year)
	{
		return medarbejderManager.AktiviteterIDenneUge(week, year, this);
	}
    
	public List<Entry<UUID, Aktivitet>> getAlleAktiviteter()
	{
		return aktivitetManager.AlleAktiviteterEfterMedarbejder(this);
	}
	
	public int tidBrugtIdag(Date dato) {
		int i = 0;
		List<Entry<UUID, Brugttid>> L = brugttidManager.AlleBrugttidEfterDagOgMedarbejder(dato, this);
		for(Entry<UUID, Brugttid> e : L) {
			i+=e.getValue().Tid();
		}
		return i;
	}
	
	public boolean ledig(int weekStart, int weekSlut, int yearStart, int yearSlut) {
		return medarbejderManager.MedarbejderLedig(weekStart, weekSlut, yearStart, yearSlut, this);
	}

	public boolean tagFerie(int startUge, int slutUge, int startaar, int slutaar) {
		Ferie f = new Ferie(slutaar, slutaar, slutaar, slutaar);
		return f.gem();
	}
	
	public boolean tagFerie(int startUge, int slutUge, int startaar, int slutaar, String forklaring) {
		Ferie f = new Ferie(slutaar, slutaar, slutaar, slutaar, forklaring);
		return f.gem();
	}

}