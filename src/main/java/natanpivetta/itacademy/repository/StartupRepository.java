package natanpivetta.itacademy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.util.JPAUtil;

import java.util.List;

public class StartupRepository {

    private EntityManager em = JPAUtil.getEntityManager();

    public StartupRepository() {}


    public void save(Startup startup) {
        try {
            em.getTransaction().begin();
            em.persist(startup);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }


    public Startup findById(Long id) {
        return em.find(Startup.class, id);
    }


    public List<Startup> findAll() {
        return em.createQuery("SELECT s FROM Startup s", Startup.class).getResultList();
    }


    public void delete(Long id) {
        Startup startup = findById(id);
        if (startup != null) {
            em.getTransaction().begin();
            em.remove(startup);
            em.getTransaction().commit();
        }
    }


    public void update(Startup startup) {
        try {
            em.getTransaction().begin();
            em.merge(startup);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
