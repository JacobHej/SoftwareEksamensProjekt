package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Data.Datavedholdelsesklasser.BrugttidData;
import Applikationslag.Domaeneklasser.Brugttid;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;

public class BrugttidManager implements IBrugttidManager {

	//@Override
	public Boolean GemBrugttid(Brugttid brugttid) {
		BrugttidData.Bibliotek.put(brugttid.ID(), brugttid);
		return true;
	}

}
