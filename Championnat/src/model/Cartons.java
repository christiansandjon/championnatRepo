package model;

import javax.persistence.*;


@Entity
@Table(name = "cartons")

public class Cartons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)	
	
	private int id;
    
    @OneToOne
    @JoinColumn(name = "joueur_id")
	private Joueur joueur;
	
    @OneToOne
    @JoinColumn(name = "match_id")
	private Match match;
    
    @Column(name = "nombrecarton", unique = false, nullable = false)	
	private int nombrecarton;
    
    @Column(name = "type", unique = false, nullable = false) 
	private boolean type ;
	
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public int getNombrecarton() {
		return nombrecarton;
	}
	public void setNombrecarton(int nombrecarton) {
		this.nombrecarton = nombrecarton;
	}
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	
	

}
