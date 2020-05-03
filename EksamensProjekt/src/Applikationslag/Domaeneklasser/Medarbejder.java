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
    	this.navn = navn;
    }
    //Funtioner for returnering af klassevariable
    public UUID ID()
    {
    	return this.id;
    }
    
    public String Navn()
    {
    	return this.navn;
    }
    
    public String getInitialer() {
    	String[] navnOrd = navn.split(" ");
    	String initialer = "";
    	for(int i = 0; i<Math.min(4,navnOrd.length);i++) {
    		initialer += navnOrd[i].charAt(0);
    	}
    	return initialer;
    	
    }
   
    public Boolean Gem()
    {
    	return medarbejderManager.GemMedarbejder(this);
    }
    
    public List<Entry<UUID, Medarbejder>> hentAlle() {
    	return medarbejderManager.hentAlleMedarbejdere();
    }
}