# QienApp

Hi Dave,
Ik heb even opgeschreven welke zaken er zijn bijgekomen/gewijzigd.

CHANGELOG:

//new files added

rest/UrendeclaratieEndpoints.java
--met de volgende endpoints:
	@PostMapping("/urendeclaratie/{maandnaam}/{maandnr}")   
  // door het opgeven van een maandnaam (String) en maandnr (int)
  //  wordt een nieuw urendeclaratie gegenereerd voor elk van de medewerkers in de database
	@GetMapping("/urendeclaraties")
  // get alle urendeclaraties
	@PutMapping("/gewerktedag/{dagId}")
  //moeten we even aan Maurice vragen/ moet ik in overleg beter uitwerken
  //dit wordt straks het "zo vul ik mn declaratieformulier in" endpoint

domein/urenform/Urendeclaratie.java
domein/urenform/GewerkteDag.java

controller/UrendeclaratieService.java
controller/UrendeclaratieRepository.java
controller/GewerkteDagRepository.java
controller/GewwerkteDagService.java

//lines added to:

domein/Medewerker.java:
	⁃	public void addUrendeclaratie (Urendeclaratie u) &c
	⁃	private List<Urendeclaratie> urendeclaraties &c
