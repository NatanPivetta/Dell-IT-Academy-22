package natanpivetta.itacademy.repository;

import jakarta.persistence.EntityManager;
import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Torneio;
import natanpivetta.itacademy.util.JPAUtil;

import java.util.List;

public class TorneioRepository {

    private EntityManager em = JPAUtil.getEntityManager();

    public TorneioRepository() {}

    public void save(Torneio torneio) {
        try {
            em.getTransaction().begin();
            em.persist(torneio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Torneio> findAll() {
        return em.createQuery("SELECT t FROM Torneio t", Torneio.class).getResultList();
    }

}
