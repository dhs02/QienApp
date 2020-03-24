package QienApp.qien.domein.urenform;


import javax.persistence.Inheritance;

@Inheritance
abstract class Dag {
		
		private double opdracht_uren;
		private double overwerk_uren;
		private double verlof_uren;
		private double ziek_uren;
		private double training_uren;
		private double overig_uren;
		private double verklaring_overig_uren;
		
		public double getOpdracht_uren() {
			return opdracht_uren;
		}
		public void setOpdracht_uren(double opdracht_uren) {
			this.opdracht_uren = opdracht_uren;
		}
		public double getOverwerk_uren() {
			return overwerk_uren;
		}
		public void setOverwerk_uren(double overwerk_uren) {
			this.overwerk_uren = overwerk_uren;
		}
		public double getVerlof_uren() {
			return verlof_uren;
		}
		public void setVerlof_uren(double verlof_uren) {
			this.verlof_uren = verlof_uren;
		}
		public double getZiek_uren() {
			return ziek_uren;
		}
		public void setZiek_uren(double ziek_uren) {
			this.ziek_uren = ziek_uren;
		}
		public double getTraining_uren() {
			return training_uren;
		}
		public void setTraining_uren(double training_uren) {
			this.training_uren = training_uren;
		}
		public double getOverig_uren() {
			return overig_uren;
		}
		public void setOverig_uren(double overig_uren) {
			this.overig_uren = overig_uren;
		}
		public double getVerklaring_overig_uren() {
			return verklaring_overig_uren;
		}
		public void setVerklaring_overig_uren(double verklaring_overig_uren) {
			this.verklaring_overig_uren = verklaring_overig_uren;
		}
}
