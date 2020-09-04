package entity;

import java.util.List;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Equipe;

public class EquipeEntity extends DAO<Equipe>{

	private Session session;
	
	public EquipeEntity() {
		super();
		session = HibernateConfig.getSessionFactory().openSession();
	}
	
	@Override
	public boolean update(Equipe obj) {
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
	public boolean save(Equipe obj) {
	
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
	public boolean delete(Equipe obj) {
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
	public boolean create(Equipe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Equipe find(int i) {
		try {
			Query query = session.createQuery("from Equipe U WHERE U.id = :id");
			query.setParameter("id", i);
			List l = query.getResultList();
			return (Equipe)l.get(0);
		}catch (Exception e) {
			return null;
		}
	}


	@Override
	public List<Equipe> findAll() {
		// TODO Auto-generated method stub
		try {
			Query query = session.createQuery("from Equipe U");
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}

}
