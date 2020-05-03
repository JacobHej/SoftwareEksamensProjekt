package Applikationslag.Domaeneklasser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;
import Applikationslag.Redskaber.Managers;

public class Rapport {
	private ArrayList<AktivitetsOpsummering> aktivitetsInformationer;
	
	private IAktivitetManager aktivitetManager = Managers.FaaAktivitetManager();
	private IBrugttidManager brugttidManager = Managers.FaaBrugttidManager();
	
	public void GenererRapport(Projekt projekt)
	{
		List<Entry<UUID, Aktivitet>> aktiviteter = aktivitetManager.AlleAktiviteterEfterProjekt(projekt);
		for(Entry<UUID, Aktivitet> e : aktiviteter)
		{
			int temp = 0;
			for(Entry<UUID, Brugttid> t : brugttidManager.AlleBrugttidEfterAktivitet(e.getValue()))
			{
				temp += t.getValue().Tid();
			}
			aktivitetsInformationer.add(new AktivitetsOpsummering(e.getValue(), temp));
		}
	}
	
	public ArrayList<AktivitetsOpsummering> AktivitetsInformationer()
	{
		return this.aktivitetsInformationer;
	}
}
