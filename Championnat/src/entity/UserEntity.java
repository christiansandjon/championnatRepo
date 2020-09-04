package entity;

import java.util.List;

import config.HibernateConfig;
import entity.DAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.User;

public class UserEntity extends DAO<User> {

	private Session session;

	public UserEntity() {
		super();
		session = HibernateConfig.getSessionFactory().openSession();
	}
	@Override
	public boolean update(User obj) {
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
	public boolean save(User obj) {
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
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User find(int i) {
		try {
			Query query = session.createQuery("from User U WHERE U.id = :id");
			query.setParameter("id", i);
			List l = query.getResultList();
			return (User)l.get(0);
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		try {
			Query query = session.createQuery("from User U");
		}catch (Exception e) {

		}

		return null;
	}


	public List<User> findAll(User user) {
		// TODO Auto-generated method stub
		try {
			Query query = session.createQuery("from User U WHERE U.login != :login");
			query.setParameter("login", user.getLogin());
			List l = query.getResultList();
			return l;
		}catch (Exception e) {
			return null;
		}

	}

	public User findByUsername(String username,String password) {

		try {
			Query query = session.createQuery("from User L WHERE L.login= :login AND L.motpasse= :password");
			query.setParameter("login", username);
			query.setParameter("password", password);
			List l = query.getResultList();

			return (User)l.get(0);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}


		//return session.get(User.class, username);

	}
}
