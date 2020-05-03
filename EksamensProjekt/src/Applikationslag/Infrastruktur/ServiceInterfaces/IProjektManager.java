package Applikationslag.Infrastruktur.ServiceInterfaces;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.*;

public interface IProjektManager {
	public Boolean GemProjekt(Projekt projekt);
	
	public List<Entry<UUID, Projekt>> AlleProjekter();
	
	public Projekt projektUdFraNavn(String navn);
	
	public Boolean eksisterer(Projekt projekt);
}
