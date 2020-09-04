package model;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.Table;


@Entity
@Table(name = "matchs")

public class Match {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)	
	private int id;
    
	@OneToOne
    @JoinColumn(name = "equipe_id1")
	private Equipe equipe1;
    
    @OneToOne
    @JoinColumn(name = "equipe_id2")
	private Equipe equipe2;
	
    @Column(name = "status", unique = false, nullable = false)	
	private boolean status; 
  
    @Column(name = "jourheurematch", unique = false, nullable = false, length = 100) 
    private Date jourheurematch;
    
    @OneToOne
    @JoinColumn(name = "championnat_id")
	private Championnat championnat;
    
    

    
    @ManyToMany
    private List<Joueur> MatchJoueurList;
    // standard constructors/getters/setters  
	
  
	public Championnat getChampionnat() {
		return championnat;
	}

	public List<Joueur> getMatchJoueurList() {
		return MatchJoueurList;
	}

	public void setMatchJoueurList(List<Joueur> matchJoueurList) {
		MatchJoueurList = matchJoueurList;
	}

	public void setChampionnat(Championnat championnat) {
		this.championnat = championnat;
	}
	public Date getJourheurematch() {
		return jourheurematch;
	}
	public void setJourheurematch(Date jourheurematch) {
		this.jourheurematch = jourheurematch;
	} 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Equipe getEquipe1() {
		return equipe1;
	}
	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}
	public Equipe getEquipe2() {
		return equipe2;
	}
	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
