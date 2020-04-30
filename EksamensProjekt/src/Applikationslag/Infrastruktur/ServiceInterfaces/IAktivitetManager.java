package Applikationslag.Infrastruktur.ServiceInterfaces;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.*;

public interface IAktivitetManager {
	public Boolean GemAktivitet(Aktivitet aktivitet);
	
	public Boolean eksisterer(Aktivitet aktivitet);
	
	public List<Entry<UUID, Aktivitet>> AlleAktiviteterEfterProjekt(Projekt projekt);
}
