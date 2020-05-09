package Applikationslag.Redskaber;

import Applikationslag.Infrastruktur.ServiceImplementationer.*;
import Applikationslag.Infrastruktur.ServiceInterfaces.*;

public final class Managers {
	private Managers()
	{
		
	}
	
	public static IProjektManager FaaProjektManager()
	{
		return new ProjektManager();
	}
	
	public static IMedarbejderManager FaaMedarbejderManager()
	{
		return new MedarbejderManager();
	}
	
	public static IAktivitetManager FaaAktivitetManager()
	{
		return new AktivitetManager();
	}
	
	public static IBrugttidManager FaaBrugttidManager()
	{
		return new BrugttidManager();
	}
	
//	public static ISessionManager FaaSessionManager()
//	{
//		return new SessionManager();
//	}
//	
//	public static IAssistanceManager FaaAssistanceManager()
//	{
//		return new AssistanceManager();
//	}
	
	public static IFerieManager FaaFerieManager()
	{
		return new FerieManager();
	}
}
