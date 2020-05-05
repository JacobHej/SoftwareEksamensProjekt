package Applikationslag.Data.Datavedholdelsesklasser;

import java.util.HashMap;
import java.util.UUID;

import Applikationslag.Domaeneklasser.Brugttid;
import Applikationslag.Domaeneklasser.Ferie;

public final class FerieData {
	private DerieData()
	{
		
	}
	
	public static HashMap<UUID, Ferie> Bibliotek = new HashMap<>();
}
