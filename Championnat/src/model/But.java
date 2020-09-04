package model;

import javax.persistence.*;


@Entity
@Table(name = "buts")

public class But {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)	
	private int id;
    
    @Column(name = "minute", unique = false, nullable = true)
    private int minute;
    
    @OneToOne
    @JoinColumn(name = "joueur_id")
	private Joueur joueur;
	
    @OneToOne
    @JoinColumn(name = "match_id")
	private Match match;
	
	
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	
	
	

}
