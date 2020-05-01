package Applikationslag.Infrastruktur.ServiceInterfaces;

import Applikationslag.Domaeneklasser.*;

public interface IProjektManager {
	public Boolean GemProjekt(Projekt projekt);
	
	public Boolean eksisterer(Projekt projekt);
}
