package Applikationslag.Data.Datavedholdelsesklasser;

import java.util.HashMap;
import java.util.UUID;

import Applikationslag.Domaeneklasser.Assistance;
import Applikationslag.Domaeneklasser.Brugttid;

public final class AssistanceData {
	private AssistanceData()
	{
		
	}
	 public static HashMap<UUID, Assistance> Bibliotek = new HashMap<>();
}
