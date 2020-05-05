package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Data.Datavedholdelsesklasser.SessionsData;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Infrastruktur.ServiceInterfaces.IProjektManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.ISessionManager;

public class SessionManager implements ISessionManager{

	@Override
	public Medarbejder getNuvaerendeMedarbejder() {
		return SessionsData.nuvaerendeMedarbejder;
	}

	@Override
	public Boolean setNuvaerendeMedarbejder(Medarbejder medarbejder) {
		SessionsData.nuvaerendeMedarbejder = medarbejder;
		return true;
	}

}
