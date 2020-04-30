package Applikationslag.Data.Datavedholdelsesklasser;

import Applikationslag.Domaeneklasser.*;
import java.util.HashMap;
import java.util.UUID;

public final class MedarbejderData
{
	private MedarbejderData()
	{
		
	}
	
    public static HashMap<UUID, Medarbejder> Bibliotek = new HashMap<>();
}