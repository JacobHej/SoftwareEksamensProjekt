package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;

import Applikationslag.Data.Datavedholdelsesklasser.*;

public class AktivitetManager implements IAktivitetManager{

	//@Override
	public Boolean GemAktivitet(Aktivitet aktivitet) {
		AktivitetData.Bibliotek.put(aktivitet.ID(), aktivitet);
		return true;
	}

	//@Override
	public Boolean eksisterer(Aktivitet aktivitet) {
		return AktivitetData.Bibliotek.containsValue(aktivitet);
	}

	//@Override
	public List<Entry<UUID, Aktivitet>> AlleAktiviteterEfterProjekt(Projekt projekt) {
		return AktivitetData.Bibliotek.entrySet().stream()
			.filter(e -> e.getValue().Projekt().Navn() == projekt.Navn())
			.collect(Collectors.toList());
	}

}
