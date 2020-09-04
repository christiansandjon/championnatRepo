package model;

public class Classement {
	
	private Equipe equipe;
	private int matchjouer;
	private int matchgagner;
	private int matchperdu;
	private int matchnull;
	
	
	
	public int getMatchnull() {
		return matchnull;
	}
	public void setMatchnull(int matchnull) {
		this.matchnull = matchnull;
	}
	private int point;
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public int getMatchjouer() {
		return matchjouer;
	}
	public void setMatchjouer(int matchjouer) {
		this.matchjouer = matchjouer;
	}
	public int getMatchgagner() {
		return matchgagner;
	}
	public void setMatchgagner(int matchgagner) {
		this.matchgagner = matchgagner;
	}
	public int getMatchperdu() {
		return matchperdu;
	}
	public void setMatchperdu(int matchperdu) {
		this.matchperdu = matchperdu;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	
	
}
