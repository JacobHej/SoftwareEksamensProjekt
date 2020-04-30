package Applikationslag.Domaeneklasser;

import java.util.UUID;

public class Medarbejder
{
	// klassevariable
    private UUID id = UUID.randomUUID();
    private String name;
    
    //metoder
    
    //Funtioner for returnering af klassevariable
    public UUID ID()
    {
    	return this.id;
    }
    
    public String Name()
    {
    	return this.name;
    }
}