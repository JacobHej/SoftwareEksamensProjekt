package Applikationslag.Infrastruktur.ServiceImplementationer;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.UUID;

import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Dates;
import Applikationslag.Redskaber.GlobaleVariable;

public class MedarbejderManager implements IMedarbejderManager {

	@Override
	public Boolean GemMedarbejder(Medarbejder medarbejder) {
<<<<<<< HEAD
		if(!MedarbejderData.Bibliotek.entrySet().stream()
				.anyMatch(e -> e.getValue().Navn() == medarbejder.Navn()))
=======
		if(!MedarbejderData.Bibliotek.entrySet().stream().anyMatch(e -> e.getValue().getNavn() == medarbejder.getNavn()))
>>>>>>> 4e516c0e6977b31fd454bc8507784d05fc8cc4d7
			return (MedarbejderData.Bibliotek.put(medarbejder.ID(), medarbejder) == null);
		else
			return false;
	}

	@Override
	public List<Entry<UUID, Medarbejder>> AlleLedigeMedarbejdere() {
		return MedarbejderData.Bibliotek.entrySet().stream()
			.filter(e -> AktiviteterIDenneUge(Dates.getCurrentWeek(), e.getValue()) 
					< GlobaleVariable.MaksimaleVagter())
			.collect(Collectors.toList());
	}

	@Override
	public long AktiviteterIDenneUge(int week, Medarbejder medarbejder) {
		return AktivitetData.Bibliotek.entrySet().stream()
			.filter(e -> e.getValue().Medarbejder() != null)
			.filter(e -> e.getValue().Medarbejder().ID() == medarbejder.ID())
			.filter(e -> e.getValue().getStartUge() <= week
					&& e.getValue().getSlutUge() >= week)
			.count();
	}
	
	@Override
	public List<Entry<UUID, Medarbejder>> hentAlleMedarbejdere() {
		return MedarbejderData.Bibliotek.entrySet().stream().collect(Collectors.toList());
	}
	
}
