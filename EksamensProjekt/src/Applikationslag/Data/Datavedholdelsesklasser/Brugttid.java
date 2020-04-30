package Applikationslag.Data.Datavedholdelsesklasser;

import Applikationslag.Domaeneklasser.*;
import java.util.HashMap;
import java.util.UUID;

final class BrugttidData
{
	private BrugttidData()
	{
		
	}
	
    public static HashMap<UUID, Brugttid> Bibliotek = new HashMap<>();
}