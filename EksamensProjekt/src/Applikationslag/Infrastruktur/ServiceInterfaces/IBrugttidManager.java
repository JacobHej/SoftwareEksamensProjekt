package Applikationslag.Infrastruktur.ServiceInterfaces;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Brugttid;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;

public interface IBrugttidManager {
	public Boolean GemBrugttid(Brugttid brugttid);
	
	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterMedarbejder(Medarbejder medarbejder);
	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterAktivitet(Aktivitet aktivitet);
//	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterProjekt(Projekt projekt);
	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterAktivitetOgMedarbejder(Aktivitet aktivitet, Medarbejder medarbejder);
//	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterDag(Date dato);
	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterDagOgMedarbejder(Date dato, Medarbejder medarbejder);
	public boolean Eksisterer(Brugttid b);
	public Boolean fjern(Brugttid brugttid);
}
