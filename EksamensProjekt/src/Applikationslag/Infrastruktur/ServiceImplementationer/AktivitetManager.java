package Applikationslag.Infrastruktur.ServiceImplementationer;

import Applikationslag.Domaeneklasser.Aktivitet;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Data.Datavedholdelsesklasser.*;

public class AktivitetManager implements IAktivitetManager{

	//@Override
	public Boolean GemAktivitet(Aktivitet aktivitet) {
		AktivitetData.Bibliotek.put(aktivitet.ID(), aktivitet);
		return true;
	}

	//@Override
	public Boolean eksisterer(Aktivitet aktivitet) {
		return AktivitetData.Bibliotek.containsValue(aktivitet);
	}

}
