package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;

import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;

import Applikationslag.Data.Datavedholdelsesklasser.*;

public class AktivitetManager implements IAktivitetManager{

	//@Override
	public Boolean GemAktivitet(Aktivitet aktivitet) {
		if(aktivitet.Projekt() == null)
			return false;
		if (!AktivitetData.Bibliotek.containsValue(aktivitet) 
				&& ProjektData.Bibliotek.containsValue(aktivitet.Projekt()))
			return (AktivitetData.Bibliotek.put(aktivitet.ID(), aktivitet) == null);
		else
			return false;
	}

	//@Override
	public List<Entry<UUID, Aktivitet>> AlleAktiviteterEfterProjekt(Projekt projekt) {
		return AktivitetData.Bibliotek.entrySet().stream()
			.filter(e -> e.getValue().Projekt().getNavn() == projekt.getNavn())
			.collect(Collectors.toList());
	}

}
