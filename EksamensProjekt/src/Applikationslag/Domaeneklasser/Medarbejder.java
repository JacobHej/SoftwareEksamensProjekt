package Applikationslag.Domaeneklasser;

import java.util.UUID;

import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Managers;

public class Medarbejder
{
	// klassevariable
    private UUID id = UUID.randomUUID();
    private String navn;
    
    private IMedarbejderManager medarbejderManager = Managers.FaaMedarbejderManager();
    
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
   
    public Boolean Gem()
    {
    	return medarbejderManager.GemMedarbejder(this);
    }
}