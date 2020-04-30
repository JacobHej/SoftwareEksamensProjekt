package Applikationslag.Data.Datavedholdelsesklasser;

import Applikationslag.Domaeneklasser.*;
import java.util.HashMap;
import java.util.UUID;

public final class ProjektData
{
	private ProjektData()
	{
		
	}
	
    public static HashMap<UUID, Projekt> Bibliotek = new HashMap<>();
}