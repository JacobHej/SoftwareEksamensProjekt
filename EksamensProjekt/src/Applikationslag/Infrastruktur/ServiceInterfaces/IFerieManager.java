package Applikationslag.Infrastruktur.ServiceInterfaces;

import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import Applikationslag.Domaeneklasser.Ferie;

public interface IFerieManager {
	public Boolean Gem(Ferie ferie);
	
	public Boolean Slet (Ferie ferie);
	
	public List<Entry<UUID, Ferie>> hentAlle();
}
