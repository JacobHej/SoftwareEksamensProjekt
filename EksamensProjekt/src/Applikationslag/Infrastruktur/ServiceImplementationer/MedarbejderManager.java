package Applikationslag.Infrastruktur.ServiceImplementationer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.UUID;

import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;

public class MedarbejderManager implements IMedarbejderManager {

	@Override
	public Boolean GemMedarbejder(Medarbejder medarbejder) {
		if(!MedarbejderData.Bibliotek.containsValue(medarbejder))
			return (MedarbejderData.Bibliotek.put(medarbejder.ID(), medarbejder) == null);
		else
			return false;
	}

	//@Override
	public List<Entry<UUID, Medarbejder>> AlleLedigeMedarbejdere() {
		
	}

	@Override
	public long AktiviteterIDenneUge(int week, Medarbejder medarbejder) {
		return AktivitetData.Bibliotek.entrySet().stream()
			.filter(e -> e.getValue().Medarbejder().ID() == medarbejder.ID())
			.filter(e -> e.getValue().Uge() == week)
			.count();
	}
	
}
