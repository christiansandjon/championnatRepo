package entity;

import config.HibernateConfig;

import java.util.List;

public abstract class DAO<T> extends HibernateConfig {
	
	public abstract boolean update(T obj);
	public abstract boolean save(T obj);
	public abstract boolean delete(T obj);
	public abstract boolean create(T obj);
	public abstract T find(int i);
	public abstract List<T> findAll();

}
