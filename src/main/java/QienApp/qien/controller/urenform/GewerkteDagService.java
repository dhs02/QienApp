package QienApp.qien.controller.urenform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QienApp.qien.domein.urenform.GewerkteDag;

@Service
public class GewerkteDagService {
	
@Autowired
private GewerkteDagRepository gewerkteDagRepository;

//methode genereert dagen als er een urenform(maand) wordt aangemaakt voor een medewerker
public GewerkteDag addDag(GewerkteDag dag) {
	System.out.println("DEBUG Dag aangemaakt voor deze maand in database");
	gewerkteDagRepository.save(dag);
	return dag;		
}

//methode wijzigt urensoortvelden als je de juiste dagID en objectnaanm ingeeft
public GewerkteDag updateDag(long dagId, GewerkteDag dagDetails) {
	System.out.println("Dag aangepast in database");
	GewerkteDag dag = gewerkteDagRepository.findById(dagId).get();
	
	// TODO Michiel denkt dat deze argumenten moeten worden toegespitst op binnenkomed JSON format
	dag.setAantalUrenOpdracht(dagDetails.getAantalUrenOpdracht());
	dag.setAantalUrenOverig(dagDetails.getAantalUrenOverig());
	dag.setAantalUrenOverwerk(dagDetails.getAantalUrenOverwerk());
	dag.setAantalUrenTraining(dagDetails.getAantalUrenTraining());
	dag.setAantalUrenVerlof(dagDetails.getAantalUrenVerlof());
	dag.setAantalUrenZiek(dagDetails.getAantalUrenZiek());
	dag.setVerklaringOverig(dagDetails.getVerklaringOverig());

	return gewerkteDagRepository.save(dag);
}


}
