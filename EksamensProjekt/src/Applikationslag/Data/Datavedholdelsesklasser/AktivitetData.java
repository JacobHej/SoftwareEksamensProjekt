package Applikationslag.Data.Datavedholdelsesklasser;

import Applikationslag.Domaeneklasser.*;
import java.util.HashMap;
import java.util.UUID;

public final class AktivitetData
{
	private AktivitetData()
	{
		
	}
	
    public static HashMap<UUID, Aktivitet> Bibliotek = new HashMap<>();
    
}