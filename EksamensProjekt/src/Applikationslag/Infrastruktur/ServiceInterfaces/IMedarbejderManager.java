package Applikationslag.Infrastruktur.ServiceInterfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;

public interface IMedarbejderManager {
	public Boolean GemMedarbejder(Medarbejder medarbejder);
	public List<Entry<UUID, Medarbejder>> AlleLedigeMedarbejdere(int weekStart, int weekSlut, int yearStart, int yearSlut);
	public List<Entry<UUID, Medarbejder>> hentAlleMedarbejdere();
	public long AktiviteterIDenneUge(int week, int year, Medarbejder medarbejder); 
	public Boolean MedarbejderLedig(int weekStart, int weekSlut, int yearStart, int yearSlut, Medarbejder medarbejder);
	public Medarbejder MedarbejderUdFraNavn(String medarbejdernavn);
}
