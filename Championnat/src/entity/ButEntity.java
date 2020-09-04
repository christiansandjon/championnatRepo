package entity;

import config.HibernateConfig;
import model.But;
import model.Equipe;
import model.Joueur;
import model.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ButEntity extends DAO<But> {

    private Session session;

    public ButEntity() {
        super();
        session = HibernateConfig.getSessionFactory().openSession();
    }

    @Override
    public boolean update(But obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean save(But obj) {
        session.beginTransaction();
        session.save(obj);
        try {
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(But obj) {
        session.beginTransaction();
        session.delete(obj);
        ;
        try {
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(But obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public But find(int i) {
        try {
            Query query = session.createQuery("from But U WHERE U.id = :id");
            query.setParameter("id", i);
            List l = query.getResultList();
            return (But) l.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<But> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<But> findButMatch(Match match) {
        try {
            Query query = session.createQuery("from But U WHERE U.match = :match");
            query.setParameter("match", match);
            List l = query.getResultList();
            return l;
        } catch (Exception e) {
            return null;
        }

    }


    public List<But> findButMatchEquipe(Match match, Equipe equipe) {
        try {
            Query query = session.createQuery("from But U WHERE U.match = :match AND U.joueur.equipe = :equipe");
            query.setParameter("match", match);
            query.setParameter("equipe", equipe);
            List l = query.getResultList();
            return l;
        } catch (Exception e) {
            return null;
        }

    }

    public List<But> findButJoueur(Joueur joueur) {
        try {
            Query query = session.createQuery("from But U WHERE U.joueur = :joueur");
            query.setParameter("joueur", joueur);
            List l = query.getResultList();
            return l;
        } catch (Exception e) {
            return null;
        }

    }

}
