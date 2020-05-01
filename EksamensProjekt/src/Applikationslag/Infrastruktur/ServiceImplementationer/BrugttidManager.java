package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Data.Datavedholdelsesklasser.BrugttidData;
import Applikationslag.Data.Datavedholdelsesklasser.MedarbejderData;
import Applikationslag.Domaeneklasser.Brugttid;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;

public class BrugttidManager implements IBrugttidManager {

	//@Override
	public Boolean GemBrugttid(Brugttid brugttid) {
		if(AktivitetData.Bibliotek.containsValue(brugttid.Aktivitet())
				&& MedarbejderData.Bibliotek.containsKey(brugttid.Medarbejder()))
			return (BrugttidData.Bibliotek.put(brugttid.ID(), brugttid) == null);
		else
			return false;
	}

}
