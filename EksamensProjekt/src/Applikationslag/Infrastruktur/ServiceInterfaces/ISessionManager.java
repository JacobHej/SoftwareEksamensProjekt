package Applikationslag.Infrastruktur.ServiceInterfaces;

import Applikationslag.Domaeneklasser.Medarbejder;

public interface ISessionManager {
	public Medarbejder getNuvaerendeMedarbejder();
	public Boolean setNuvaerendeMedarbejder(Medarbejder medarbejder);
}
