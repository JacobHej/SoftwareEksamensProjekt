Feature: tilfoej aktivitet
  Description: Lav en aktivitet for et projekt
	Actor: Projekt-Leder

Scenario: Lederen laver en ny aktivitet
    Given der er et projekt "Projekt2" med lederen "JACO"
    When lederen laver en ny aktivitet med navnet "aktivitet3"
    Then projektet "Projekt2" har en ny aktivitet med navnet "aktivitet3"

Scenario: Lederen proever at lave en ny aktivitet som findes
    Given der er et projekt "Projekt3" med lederen "JAKO"
    And Projektet "Projekt3" har aktiviteten "aktivitet4"
    When lederen proever at lave en ny aktivitet med navnet "aktivitet4"
    Then Aktiviteten er ikke tilfoejet