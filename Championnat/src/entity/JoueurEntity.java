package entity;

import java.util.List;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Championnat;
import model.Equipe;
import model.Joueur;

public class JoueurEntity extends DAO<Joueur>{

	private Session session;
	
	public JoueurEntity() {
		super();
		session = HibernateConfig.getSessionFactory().openSession();
	}
	@Override
	public boolean update(Joueur obj) {
		
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
	public boolean save(Joueur obj) {
		
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
	public boolean delete(Joueur obj) {
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
	public boolean create(Joueur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Joueur find(int i) {
		try {
			Query query = session.createQuery("from Joueur U WHERE U.id = :id");
			query.setParameter("id", i);
			List l = query.getResultList();
			return (Joueur)l.get(0);
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Joueur> findAll() {
		// TODO Auto-generated method stub
		try {
			Query query = session.createQuery("from Joueur U");
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	

	public List<Joueur> findJoueurChampionnatAll(Championnat c) {
		// TODO Auto-generated method stub
		try {
			Query query = session.createQuery("from Joueur U WHERE U.equipe.championnat = :championnat");
			query.setParameter("championnat", c);
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	public List<Joueur> findAllEquipe(Equipe equipe) {
		// TODO Auto-generated method stub
		try {
			Query query = session.createQuery("from Joueur U WHERE U.equipe = :equipe");
			query.setParameter("equipe", equipe);
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	

}
