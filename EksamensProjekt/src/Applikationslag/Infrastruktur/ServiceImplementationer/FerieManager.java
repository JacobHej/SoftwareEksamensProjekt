package Applikationslag.Infrastruktur.ServiceImplementationer;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.UUID;

import Applikationslag.Data.Datavedholdelsesklasser.FerieData;
import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Domaeneklasser.Ferie;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Infrastruktur.ServiceInterfaces.IFerieManager;

public class FerieManager implements IFerieManager {

	@Override
	public Boolean Gem(Ferie ferie) {
		// tjek om nul aktiviteter i hele perioden
		// implementer tjek for ferie i medarbejder manager
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

	

}
