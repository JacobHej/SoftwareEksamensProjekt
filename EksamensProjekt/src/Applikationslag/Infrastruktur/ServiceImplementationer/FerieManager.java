package Applikationslag.Infrastruktur.ServiceImplementationer;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.UUID;

import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Data.Datavedholdelsesklasser.FerieData;
import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Domaeneklasser.Ferie;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Infrastruktur.ServiceInterfaces.IFerieManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.GlobaleVariable;
import Applikationslag.Redskaber.Managers;


public class FerieManager implements IFerieManager {

	private IMedarbejderManager medarbejderManager = Managers.FaaMedarbejderManager();
	
	@Override
	public Boolean Gem(Ferie ferie) {
		// implementer tjek for ferie i medarbejder manager
		if (medarbejderManager.MedarbejderFri(ferie.StartUge(), ferie.SlutUge(), ferie.Startaar(), ferie.Slutaar(), ferie.Medarbejder()))
		{
			FerieData.Bibliotek.put(ferie.ID(), ferie);
			return true;
		}
		return false;
	}

	@Override
	public Boolean Slet(Ferie ferie) {
		FerieData.Bibliotek.remove(ferie.ID());
		return true;
	}

	@Override
	public List<Entry<UUID, Ferie>> hentAlle() {
		return ((HashMap<UUID, Ferie>)FerieData.Bibliotek.clone())
				.entrySet().stream().collect(Collectors.toList());
	}

	public Boolean FerieEfterPeriodeOgMedarbejder(int weekStart, int weekSlut, int yearStart, int yearSlut, Medarbejder medarbejder)
	{
		for(int i = yearStart; i <= yearSlut; i ++)
		{
			for(int j = (i == yearStart ? weekStart : 0); j <= (i == yearSlut ? weekSlut : 0); j++)
			{
				if(FerieIDenneUge(j, i, medarbejder) >= GlobaleVariable.MaksimaleVagter())
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public long FerieIDenneUge(int week, int year, Medarbejder medarbejder) {
		return FerieData.Bibliotek.entrySet().stream()
			.filter(e -> e.getValue().Medarbejder() != null)
			.filter(e -> e.getValue().Medarbejder().ID() == medarbejder.ID())
			.filter(e -> 
					((
							((e.getValue().Startaar() < year) 
								|| 
								(e.getValue().Startaar() == year 
									&&
								(e.getValue().StartUge() <= week)))
						&&
							((e.getValue().Slutaar() > year) 
								|| 
								(e.getValue().Slutaar() == year 
									&& 
								(e.getValue().SlutUge() >= week)))	
					))
					).count();
	}

}