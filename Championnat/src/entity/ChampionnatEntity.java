package entity;

import java.util.List;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Championnat;
import model.Equipe;

public class ChampionnatEntity extends DAO<Championnat>{

	private Session session;
	
	public ChampionnatEntity() {
		super();
		session = HibernateConfig.getSessionFactory().openSession();
	}
	@Override
	public boolean update(Championnat obj) {
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
	public boolean save(Championnat obj) {
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
	public boolean delete(Championnat obj) {
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
	public boolean create(Championnat obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Championnat find(int i) {
		try {
			Query query = session.createQuery("from Championnat U WHERE U.id = :id");
			query.setParameter("id", i);
			List l = query.getResultList();
			return (Championnat)l.get(0);
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Championnat> findAll() {
		// TODO Auto-generated method stub
		try {
			Query query = session.createQuery("from Championnat U");
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			return null;
		}
		
		
	}
	
	
	public List<Equipe> findAllEquipe(int id) {
		// TODO Auto-generated method stub
		try {
			Query query = session.createQuery("from Equipe U WHERE U.championnat.id = :id");
			query.setParameter("id", id);
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			return null;
		}
		
		
	}
	

	public List<Championnat> findAll(Championnat c) {
		// TODO Auto-generated method stub
	/*	try {
			Query query = session.createQuery("from User U WHERE U.login != :login");
			query.setParameter("login", user.getLogin());
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			return null;
		}
		
		*/
		return null;
		
	}

	public Championnat findByUsername(String username,String password) {
		
		return null;
		//return session.get(User.class, username);
		
	}
}
