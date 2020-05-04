package Applikationslag.Domaeneklasser;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Managers;

public class Medarbejder
{
	// klassevariable
    private UUID id = UUID.randomUUID();
    private String navn;
    
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
}