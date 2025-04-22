package natanpivetta.itacademy.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.util.JPAUtil;
import java.util.List;

public class BatalhaRepository {


    private EntityManager em = JPAUtil.getEntityManager();

    public BatalhaRepository() {
    }

    public void save(Batalha batalha) {
        try {
            em.getTransaction().begin();
            em.persist(batalha);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Batalha> findAll() {
        return em.createQuery("SELECT b FROM Batalha b", Batalha.class).getResultList();
    }


    public void update(Batalha batalha) {
        try {
            em.getTransaction().begin();
            em.merge(batalha);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

