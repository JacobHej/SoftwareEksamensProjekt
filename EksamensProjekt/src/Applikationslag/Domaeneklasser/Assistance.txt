package Applikationslag.Domaeneklasser;

import java.util.UUID;

import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Managers;

public class Assistance {
	private Aktivitet aktivitet;
	private UUID id = UUID.randomUUID();
	
	IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
	
	public Assistance (Aktivitet aktivitet)
	{
		if(aktivitetManager.eksisterer(aktivitet))
			this.aktivitet = aktivitet;
	}
	
	public Aktivitet Aktivitet()
	{
		return this.aktivitet;
	}
	
	public UUID ID()
	{
		return this.id;
	}
}
