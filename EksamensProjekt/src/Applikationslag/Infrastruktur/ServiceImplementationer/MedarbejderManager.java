package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Domaeneklasser.Medarbejder;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;

public class MedarbejderManager implements IMedarbejderManager {

	@Override
	public Boolean GemMedarbejder(Medarbejder medarbejder) {
		if(!MedarbejderData.Bibliotek.containsValue(medarbejder))
			return (MedarbejderData.Bibliotek.put(medarbejder.ID(), medarbejder) == null);
		else
			return false;
	}
	
}
