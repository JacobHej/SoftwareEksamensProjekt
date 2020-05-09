package Applikationslag.Infrastruktur.ServiceImplementationer;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Applikationslag.Data.Datavedholdelsesklasser.AssistanceData;
import Applikationslag.Domaeneklasser.Assistance;
import Applikationslag.Domaeneklasser.Brugttid;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAssistanceManager;

import java.util.UUID;
import java.util.stream.Collectors;

public class AssistanceManager implements IAssistanceManager {

	@Override
	public Boolean Gem(Assistance assistance) {
		AssistanceData.Bibliotek.put(assistance.ID(), assistance);
		return true;
	}

	@Override
	public List<Entry<UUID, Assistance>> hentAlleAssistancer() {
		return ((HashMap<UUID, Assistance>)AssistanceData.Bibliotek.clone())
				.entrySet().stream().collect(Collectors.toList());
	}

	@Override
	public Boolean Slet(Assistance assistance) {
		AssistanceData.Bibliotek.remove(assistance.ID());
		return true;
	}

}
