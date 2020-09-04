package entity;

import java.util.List;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Equipe;
import model.Match;

public class MatchEntity extends DAO<Match> {

	private Session session;
	
	public MatchEntity() {
		super();
		session = HibernateConfig.getSessionFactory().openSession();
	}
	
	@Override
	public boolean update(Match obj) {
	     session.beginTransaction(); 
	     session.update(obj);
	     try {
	    	 session.getTransaction().commit();	 
	    	 return true;
	     }catch(Exception e) {
	    	 System.out.println(e.getMessage());
	    	 return false;
	     }	
	}

	@Override
	public boolean save(Match obj) {
	     session.beginTransaction(); 
	     session.save(obj);
	     try {
	    	 session.getTransaction().commit();	 
	    	 return true;
	     }catch(Exception e) {
	    	 
	    	 e.getStackTrace();
	    	 return false;
	     }
	}

	@Override
	public boolean delete(Match obj) {
	     session.beginTransaction(); 
	     session.delete(obj);;
	     try {
	    	 session.getTransaction().commit();	 
	    	 return true;
	     }catch(Exception e) {
	    	 System.out.println(e.getMessage());
	    	 return false;
	     }
	}

	@Override
	public boolean create(Match obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Match> findMatch(int idchampionnat,boolean status){
		try {
			Query query = session.createQuery("from Match U WHERE U.championnat.id = :id AND U.status = :status");
			query.setParameter("id", idchampionnat);
			query.setParameter("status", status);
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			return null;
		}
		
	}
	
	
	public List<Match> findMatchEquipe(Equipe equipe,boolean status){
		try {
			Query query = session.createQuery("from Match U WHERE (U.equipe1 = :equipe OR U.equipe2 = :equipe) AND U.status = :status");
			query.setParameter("equipe", equipe);
			query.setParameter("status", status);
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Match find(int i) {
		try {
			Query query = session.createQuery("from Match U WHERE U.id = :id");
			query.setParameter("id", i);
			List l = query.getResultList();
			return (Match)l.get(0);
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Match> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
