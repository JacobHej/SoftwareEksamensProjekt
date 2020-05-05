package Applikationslag.Infrastruktur.ServiceImplementationer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.UUID;

import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Data.Datavedholdelsesklasser.ProjektData;
import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.IFerieManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Dates;
import Applikationslag.Redskaber.GlobaleVariable;
import Applikationslag.Redskaber.Managers;

public class MedarbejderManager implements IMedarbejderManager {

	private IFerieManager ferieManager = Managers.FaaFerieManager();
	@Override
	public Boolean GemMedarbejder(Medarbejder medarbejder) {
		if(!MedarbejderData.Bibliotek.entrySet().stream()
				.anyMatch(e -> e.getValue().getNavn().equals(medarbejder.getNavn())))
			return (MedarbejderData.Bibliotek.put(medarbejder.ID(), medarbejder) == null);
		else
			return false;
	}

	@Override
	public long AktiviteterIDenneUge(int week, int year, Medarbejder medarbejder) {
		return AktivitetData.Bibliotek.entrySet().stream()
			.filter(e -> e.getValue().Medarbejder() != null)
			.filter(e -> e.getValue().Medarbejder().ID() == medarbejder.ID())
			.filter(e -> 
					((
							((e.getValue().getStartaar() < year) 
								|| 
								(e.getValue().getStartaar() == year 
									&&
								(e.getValue().getStartUge() <= week)))
						&&
							((e.getValue().getSlutaar() > year) 
								|| 
								(e.getValue().getSlutaar() == year 
									&& 
								(e.getValue().getSlutUge() >= week)))	
					))
					).count();
	}
	
	@Override
	public List<Entry<UUID, Medarbejder>> hentAlleMedarbejdere() {
		return ((HashMap<UUID, Medarbejder>)MedarbejderData.Bibliotek.clone())
				.entrySet().stream().collect(Collectors.toList());
	}
	
	public List<Entry<UUID, Medarbejder>> AlleLedigeMedarbejdere(int weekStart, int weekSlut, int yearStart, int yearSlut) {
		List<Entry<UUID, Medarbejder>> result = MedarbejderData.Bibliotek.entrySet().stream().collect(Collectors.toList());
		
		for(int i = yearStart; i <= yearSlut; i ++)
		{
			for(int j = (i == yearStart ? weekStart : 0); j <= (i == yearSlut ? weekSlut : 53); j++)
			{
				Iterator<Entry<UUID, Medarbejder>> it = result.iterator();
				while(it.hasNext()) {
					Entry<UUID, Medarbejder> m = it.next();
					if(AktiviteterIDenneUge(j, i, m.getValue()) >= GlobaleVariable.MaksimaleVagter()
							|| ferieManager.FerieEfterPeriodeOgMedarbejder(weekStart, weekSlut, yearStart, yearSlut, m.getValue()))
					{
						it.remove();
					}
				}
			}
		}
		return result;
	}

	@Override
	public Boolean MedarbejderLedig(int weekStart, int weekSlut, int yearStart, int yearSlut, Medarbejder medarbejder) {
		for(int i = yearStart; i <= yearSlut; i ++)
		{
			for(int j = (i == yearStart ? weekStart : 0); j <= (i == yearSlut ? weekSlut : 0); j++)
			{
				if(AktiviteterIDenneUge(j, i, medarbejder) >= GlobaleVariable.MaksimaleVagter()
						|| ferieManager.FerieEfterPeriodeOgMedarbejder(weekStart, weekSlut, yearStart, yearSlut, medarbejder))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean eksistererMedNavn(String navn) {
		for(Entry<UUID, Medarbejder> e : MedarbejderData.Bibliotek.entrySet()) {
			if (e.getValue().getNavn().equals(navn)) {
				return true;
			}
		}
		return false;
	}
	
	
	public Boolean MedarbejderFri(int weekStart, int weekSlut, int yearStart, int yearSlut, Medarbejder medarbejder) {
		for(int i = yearStart; i <= yearSlut; i ++)
		{
			for(int j = (i == yearStart ? weekStart : 0); j <= (i == yearSlut ? weekSlut : 0); j++)
			{
				if(AktiviteterIDenneUge(j, i, medarbejder) > 0)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public Medarbejder MedarbejderUdFraNavn(String medarbejdernavn){
		return (MedarbejderData.Bibliotek.entrySet().stream()
					.filter(e -> e.getValue().getNavn().equals(medarbejdernavn))
					.collect(Collectors.toList()).get(0).getValue());
		
				
	}

}
