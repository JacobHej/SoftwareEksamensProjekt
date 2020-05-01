package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Data.Datavedholdelsesklasser.*;
import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.*;

public class ProjektManager implements IProjektManager{

	//@Override
	public Boolean GemProjekt(Projekt projekt) {
		if (!ProjektData.Bibliotek.containsValue(projekt))
			return (ProjektData.Bibliotek.put(projekt.ID(), projekt) == null);
		else 
			return false;
	}
	
	public Boolean eksisterer(Projekt Projekt)
	{
		return ProjektData.Bibliotek.containsValue(Projekt);
	}

}
