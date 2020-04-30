package Applikationslag.Data.Datavedholdelsesklasser;

import Applikationslag.Domaeneklasser.*;
import java.util.HashMap;
import java.util.UUID;

public final class BrugttidData
{
	private BrugttidData()
	{
		
	}
	
    public static HashMap<UUID, Brugttid> Bibliotek = new HashMap<>();
}