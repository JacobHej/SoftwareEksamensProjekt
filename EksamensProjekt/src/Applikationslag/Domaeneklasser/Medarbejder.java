package Applikationslag.Domaeneklasser;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Managers;

public class Medarbejder
{
	// klassevariable
    private UUID id = UUID.randomUUID();
    private String navn;
    
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
    		if(medarbejderManager.MedarbejderUdFraNavn(navn)==null) {
    			this.navn=navn;
    			return true;
    		}
    		return false;
    	}
    }
   
    public Boolean Gem()
    {
    	return medarbejderManager.GemMedarbejder(this);
    }
    
	public List<Entry<UUID, Aktivitet>> getAlleAktiviteter()
	{
		return aktivitetManager.AlleAktiviteterEfterMedarbejder(this);
	}
	
	public long AktiviteterIDenneUge(int week, int year)
	{
		return medarbejderManager.AktiviteterIDenneUge(week, year, this);
	}
	
	public boolean ledig(int weekStart, int weekSlut, int yearStart, int yearSlut) {
		return medarbejderManager.MedarbejderLedig(weekStart, weekSlut, yearStart, yearSlut, this);
	}
}