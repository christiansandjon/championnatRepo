package model;

public class JoueurBut {

	private Joueur joueur;
	private int nombrebut;
	private int nombrecartonjaune;
	private int nombrecartonrouge;
	
	
	
	public int getNombrecartonjaune() {
		return nombrecartonjaune;
	}
	public void setNombrecartonjaune(int nombrecartonjaune) {
		this.nombrecartonjaune = nombrecartonjaune;
	}
	public int getNombrecartonrouge() {
		return nombrecartonrouge;
	}
	public void setNombrecartonrouge(int nombrecartonrouge) {
		this.nombrecartonrouge = nombrecartonrouge;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public int getNombrebut() {
		return nombrebut;
	}
	public void setNombrebut(int nombrebut) {
		this.nombrebut = nombrebut;
	}
	
	
}
