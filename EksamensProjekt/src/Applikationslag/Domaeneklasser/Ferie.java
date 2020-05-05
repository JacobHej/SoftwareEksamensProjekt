package Applikationslag.Domaeneklasser;

import java.util.UUID;

public class Ferie {
	private int startUge;
	private int slutUge;
	private int startaar;
	private int sluttaar;
	
	private UUID id = UUID.randomUUID();
	
	private Medarbejder medarbejder;
	
	public int StartUge()
	{
		return this.slutUge;
	}
	
	public int SlutUge()
	{
		return this.slutUge;
	}
	
	public int Startaar ()
	{
		return this.startaar;
	}
	
	public int Slutaar()
	{
		return this.sluttaar;
	}
	
	public Medarbejder Medarbejder()
	{
		return this.medarbejder;
	}

	public UUID ID() {
		return this.id;
	}
}
