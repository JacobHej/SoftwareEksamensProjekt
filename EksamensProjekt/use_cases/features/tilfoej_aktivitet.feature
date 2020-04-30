Feature: tilfoej aktivitet
  Description: Lav en aktivitet for et projekt
	Actor: Projekt-Leder

  Scenario: Lederen laver en ny aktivitet
    Given der er et projekt med lederen leder med idet id "id"
    When lederen laver en ny aktivitet med navnet "aktivitet name"
    Then projektet har en ny aktivitet med navnet "aktivitet name"