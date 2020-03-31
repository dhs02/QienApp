package QienApp.qien.domein.urenform;

//een ENUM class
public enum Status {

	BESCHIKBAAR, 
	TER_GOEDKEURING, 
	GOEDGEKEURD, 
	AFGEKEURD, 
	AFGEROND; 
} 


class Test { 
	// spiekbriefje
	public void watIsDeStatus(Status s) {
		System.out.println("Mogelijke statussen:");
		for(Status x: Status.values())
		{
		   System.out.println(x); 
		}
		switch (s) { 
		case BESCHIKBAAR: 
			System.out.println("Vul graag je uren in. (rood bolletje?)"); 
			break; 
		case TER_GOEDKEURING: 
			System.out.println("De baas gaat zeggen of het goed is. (oranje bolletje)"); 
			break; 
		case GOEDGEKEURD: 
			System.out.println("De baas vind het prima. (groen bolletje");
			break; 
		case AFGEKEURD: 
			System.out.println("Er gaat ergens iets fout... Admin, kijk even? (rood bolletje)"); 
			break; 
		case AFGEROND:
			System.out.println("Dikke prima de bima!."); 
			break; 
		} 
	} 
} 