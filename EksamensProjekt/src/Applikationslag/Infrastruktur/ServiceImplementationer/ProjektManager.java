package Applikationslag.Infrastruktur.ServiceImplementationer;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import Applikationslag.Data.Datavedholdelsesklasser.*;
import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.*;

public class ProjektManager implements IProjektManager{

	//@Override
	public Boolean GemProjekt(Projekt projekt) {
		if (!ProjektData.Bibliotek.entrySet().stream().anyMatch(e -> e.getValue().getNavn() == projekt.getNavn()) )
			return (ProjektData.Bibliotek.put(projekt.ID(), projekt) == null);
		else 
			return false;
	}
	
	public List<Entry<UUID, Projekt>> AlleProjekter() {
		return ProjektData.Bibliotek.entrySet().stream()
			.collect(Collectors.toList());
	}
	
	public Projekt projektUdFraNavn(String navn) {
		for(Entry<UUID, Projekt> e : AlleProjekter()) {
			if (e.getValue().getNavn().equals(navn)) {
				return e.getValue();
			}
		}
		return null;
	}
	
	public void PrintAlleProjekter() {
		for(Entry<UUID, Projekt> p : AlleProjekter()) {
			System.out.println(p.getValue());
		}
	}
	
	public Boolean eksisterer(Projekt projekt)
	{
		return ProjektData.Bibliotek.containsValue(projekt);
	}

}
