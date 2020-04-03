package QienApp.qien.controller.urenform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QienApp.qien.domein.urenform.GewerkteDag;

@Service
public class GewerkteDagService {
	@Autowired
	private GewerkteDagRepository gewerkteDagRepository;

	public void save(GewerkteDag gewerkteDag) {
		gewerkteDagRepository.save(gewerkteDag);
	}

	//methode genereert dagen als er een urenform(maand) wordt aangemaakt voor een medewerker
	public GewerkteDag addDagToRepository(GewerkteDag dag) {
		return gewerkteDagRepository.save(dag);
	}

	//methode wijzigt urensoortvelden als je de juiste dagID en objectnaanm ingeeft
	public GewerkteDag updateDag(long dagId, GewerkteDag dagDetails) {
		GewerkteDag dag = gewerkteDagRepository.findById(dagId).get();
		dag.setAantalUrenOpdracht(dagDetails.getAantalUrenOpdracht());
		dag.setAantalUrenOverwerk(dagDetails.getAantalUrenOverwerk());
		dag.setAantalUrenVerlof(dagDetails.getAantalUrenVerlof());
		dag.setAantalUrenZiek(dagDetails.getAantalUrenZiek());
		dag.setAantalUrenTraining(dagDetails.getAantalUrenTraining());
		dag.setAantalUrenOverig(dagDetails.getAantalUrenOverig());
		if (dagDetails.getVerklaringOverig() != null && dagDetails.getVerklaringOverig() != "") {
			dag.setVerklaringOverig(dagDetails.getVerklaringOverig());
		}
		System.out.println("GewerkteDag aangepast in database");
		return gewerkteDagRepository.save(dag);
	}
}