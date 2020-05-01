package Applikationslag.Domaeneklasser;

import java.util.UUID;

public class Medarbejder
{
	// klassevariable
    private UUID id = UUID.randomUUID();
    private String navn;
    
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
   
}