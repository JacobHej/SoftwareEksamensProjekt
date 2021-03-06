package Applikationslag.Infrastruktur.ServiceInterfaces;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.*;

public interface IProjektManager {
	public Boolean GemProjekt(Projekt projekt);
	
	public Projekt projektUdFraNavn(String navn);
	
	public Boolean eksisterer(Projekt projekt);
	
	public Boolean fjern(Projekt projekt);
	
	public List<Entry<UUID, Projekt>> hentAlleProjekter();
	
	
}
