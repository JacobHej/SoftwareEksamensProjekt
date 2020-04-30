package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Data.Datavedholdelsesklasser.*;
import Applikationslag.Domaeneklasser.Projekt;
import Applikationslag.Infrastruktur.ServiceInterfaces.*;

public class ProjektManager implements IProjektManager{

	//@Override
	public Boolean GemProjekt(Projekt projekt) {
		ProjektData.Bibliotek.put(projekt.ID(), projekt);
		return true;
	}

}
