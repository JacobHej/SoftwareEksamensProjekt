package Applikationslag.Infrastruktur.ServiceInterfaces;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;

public interface IMedarbejderManager {
	public Boolean GemMedarbejder(Medarbejder medarbejder);
	public List<Entry<UUID, Medarbejder>> AlleLedigeMedarbejdere();
	public long AktiviteterIDenneUge(int week, Medarbejder medarbejder);
	public List<Entry<UUID, Medarbejder>> hentAlleMedarbejdere();
}
