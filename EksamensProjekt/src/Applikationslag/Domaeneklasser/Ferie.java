package Applikationslag.Domaeneklasser;

import java.util.UUID;

import Applikationslag.Infrastruktur.ServiceInterfaces.IFerieManager;
import Applikationslag.Redskaber.Managers;

public class Ferie {
	
	private IFerieManager ferieManager = Managers.FaaFerieManager();
	
	private int startUge;
	private int slutUge;
	private int startaar;
	private int slutaar;
	private String forklaring;
	
	private UUID id = UUID.randomUUID();
	
	private Medarbejder medarbejder;
	
	public Ferie(Medarbejder medarbejder, int startUge, int slutUge, int startaar, int slutaar) {
		this.startUge=startUge;
		this.slutUge=slutUge;
		this.startaar = startaar;
		this.slutaar = slutaar;
		this.medarbejder = medarbejder;
	}
	
	public Ferie(Medarbejder medarbejder, int startUge, int slutUge, int startaar, int slutaar,String forklaring) {
		this.startUge=startUge;
		this.slutUge=slutUge;
		this.startaar = startaar;
		this.slutaar = slutaar;
		this.forklaring = forklaring;
		this.medarbejder = medarbejder;
	}
	
	public Boolean fjernFraData() {
		return ferieManager.fjern(this);
    }
	
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
		return this.slutaar;
	}
	
	public Medarbejder Medarbejder()
	{
		return this.medarbejder;
	}

	public UUID ID() {
		return this.id;
	}
	
	public String getForklaring() {
		return forklaring;
	}
	
//	public void setForklaring(String forklaring) {
//		this.forklaring=forklaring;
//	}
	
	public boolean gem() {
		return ferieManager.Gem(this);
	}
	
	public String getFlotStart() {
		return "U:"+startUge+" Y:"+startaar;
	}
	
//	public String getFlotSlut() {
//		return "U:"+slutUge+" Y:"+slutaar;
//	}
}
