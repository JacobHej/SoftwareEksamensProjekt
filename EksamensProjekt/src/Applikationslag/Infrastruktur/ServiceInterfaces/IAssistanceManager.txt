package Applikationslag.Infrastruktur.ServiceInterfaces;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import Applikationslag.Domaeneklasser.Assistance;

public interface IAssistanceManager {
	public Boolean Gem(Assistance assistance);
	
	public List<Entry<UUID, Assistance>> hentAlleAssistancer();
	
	public Boolean Slet(Assistance assistance);
}
