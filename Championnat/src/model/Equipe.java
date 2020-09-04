package model;

import javax.persistence.*;

@Entity
@Table(name = "equipe")

public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private int id;
    
    @Column(name = "nom", unique = false, nullable = false, length = 255) 
	private String nom;
    @Column(name = "description", unique = false, nullable = false, length = 255) 
	private String description;
    
	
    @OneToOne
    @JoinColumn(name = "championnat_id")
    private Championnat championnat;
	
	public Championnat getChampionnat() {
		return championnat;
	}
	public void setChampionnat(Championnat championnat) {
		this.championnat = championnat;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
