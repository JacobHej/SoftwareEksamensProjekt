package Applikationslag.Domaeneklasser;

import java.util.UUID;

public class Brugttid {
	// klassevariable
	private Aktivitet aktivitet;
	private Medarbejder medarbejder;
	private int tid;
	private UUID id = UUID.randomUUID();
	
	//metoder
	public Brugttid(Aktivitet aktivitet, Medarbejder medarbejder, int tid)
	{
		this.aktivitet = aktivitet;
		this.medarbejder = medarbejder;
		this.tid = tid;
	}
	
	public void AendreTid(int nyTid)
	{
		
	}
		
	//Funtioner for returnering af klassevariable
	public Aktivitet Aktivitet()
	{
		return this.aktivitet;
	}
	
	public Medarbejder Medarbejder()
	{
		return this.medarbejder;
	}
	
	public int Tid()
	{
		return this.tid;
	}

	public UUID ID()
	{
		return this.id;
	}
}
