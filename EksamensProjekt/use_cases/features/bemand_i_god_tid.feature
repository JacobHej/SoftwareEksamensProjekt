Feature: Opret projekt med aktiviteter og bemand i god tid
  Description: Opret et projekt som foerst starter om lang tid, og se, 
  				at personer som skal arbjede paa aktiviteterne i optaget i fjern fremtid
	Actor: Medarbejder
	
Scenario: Et projekt med aktiviteter oprettes og bemandes laenge foer arbejdet paa det skal startes
	Given der er en medarbejder "ABCD"
	And Der er et projekt "Fremtidigt Projekt" som har starttid uge 45 aar 2025
	And projektet har en aktivitet "Fremtidig Aktivitet"
	When "ABCD" er tildelt aktiviteten
	Then "ABCD" kan se, at han/hun har et projekt i uge 45 aar 2025