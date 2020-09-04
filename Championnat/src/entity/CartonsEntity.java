package entity;

import java.util.List;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Cartons;
import model.Joueur;
import model.Match;

public class CartonsEntity extends DAO<Cartons>{
	
	private Session session;
	
	
	public CartonsEntity() {
		super();
		session = HibernateConfig.getSessionFactory().openSession();
	}
	
	@Override
	public boolean update(Cartons obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Cartons obj) {
	     session.beginTransaction(); 
	     session.save(obj);
	     try {
	    	 session.getTransaction().commit();	 
	    	 return true;
	     }catch(Exception e) {
	    	 System.out.println(e.getMessage());
	    	 return false;
	     }
	}

	@Override
	public boolean delete(Cartons obj) {
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
	public boolean create(Cartons obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cartons find(int i) {
		try {
			Query query = session.createQuery("from Cartons U WHERE U.id = :id");
			query.setParameter("id", i);
			List l = query.getResultList();
			return (Cartons)l.get(0);
		}catch (Exception e) {
			return null;
		}
	}
	
	public List<Cartons> findCartonsMatch(Match match){
		try {
			Query query = session.createQuery("from Cartons U WHERE U.match = :match");
			query.setParameter("match", match);
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<Cartons> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Cartons> findCartonsJoueur(Joueur joueur,boolean type){
		try {
			Query query = session.createQuery("from Cartons U WHERE U.joueur = :joueur AND type = :type");
			query.setParameter("joueur", joueur);
			query.setParameter("type", type);
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			return null;
		}
		
	}

}
