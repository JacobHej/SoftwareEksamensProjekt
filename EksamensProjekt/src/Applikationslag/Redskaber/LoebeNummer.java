package Applikationslag.Redskaber;

import java.util.Calendar;

public class LoebeNummer {
	
	static int loebeNummer;
	
	public static int genererloebeNummer() {
		forstoerLoebenummer();
		return loebeNummer;
		
	}
	
	private static void forstoerLoebenummer() {
		loebeNummer++;
		if(loebeNummer>9999) {
			loebeNummer = loebeNummer%10000;
		}
	}
}
