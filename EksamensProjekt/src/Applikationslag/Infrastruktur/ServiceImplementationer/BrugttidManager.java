package Applikationslag.Infrastruktur.ServiceImplementationer;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.UUID;

import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Data.Datavedholdelsesklasser.BrugttidData;
import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Brugttid;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;

public class BrugttidManager implements IBrugttidManager {

	//@Override
	public Boolean GemBrugttid(Brugttid brugttid) {
		if(AktivitetData.Bibliotek.containsValue(brugttid.Aktivitet())
				&& MedarbejderData.Bibliotek.containsKey(brugttid.Medarbejder()))
			return (BrugttidData.Bibliotek.put(brugttid.ID(), brugttid) == null);
		else
			return false;
	}

	//@Override
	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterMedarbejder(Medarbejder medarbejder) {
		return BrugttidData.Bibliotek.entrySet().stream()
			.filter(e -> e.getValue().Medarbejder().Navn() == medarbejder.Navn())
			.collect(Collectors.toList());
	}

	//@Override
	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterAktivitet(Aktivitet aktivitet) {
		return BrugttidData.Bibliotek.entrySet().stream()
				.filter(e -> e.getValue().Aktivitet().ID() == aktivitet.ID())
				.collect(Collectors.toList());
	}

	//@Override
	public List<Entry<UUID, Brugttid>> AlleBrugttidEfterProjekt(Projekt projekt) {
		return BrugttidData.Bibliotek.entrySet().stream()
			.filter(e -> e.getValue().Aktivitet().Projekt().ID() == projekt.ID())
			.collect(Collectors.toList());
	}

}
