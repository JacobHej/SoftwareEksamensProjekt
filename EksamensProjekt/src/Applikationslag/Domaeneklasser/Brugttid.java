package Applikationslag.Domaeneklasser;

import java.util.Date;
import java.util.UUID;

public class Brugttid {
	// klassevariable
	private Aktivitet aktivitet;
	private Medarbejder medarbejder;
	private Date dato;
	private int tid;
	private UUID id = UUID.randomUUID();
	
	//metoder
	public Brugttid(Aktivitet aktivitet, Medarbejder medarbejder, int tid)
	{
		this.aktivitet = aktivitet;
		this.medarbejder = medarbejder;
		this.tid = tid;
		this.dato = new Date();
	}
	
	public Brugttid(Aktivitet aktivitet, Medarbejder medarbejder, int tid, Date dato)
	{
		this.aktivitet = aktivitet;
		this.medarbejder = medarbejder;
		this.tid = tid;
		this.dato = dato;
	}
	
	public void AendreTid(int nyTid)
	{
		this.tid = nyTid;
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

	public Date Dato ()
	{
		return this.dato;
	}
}
