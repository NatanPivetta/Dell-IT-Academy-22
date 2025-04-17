package natanpivetta.itacademy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Rodada;

import java.util.List;

public class RodadaRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public RodadaRepository() {
        this.emf = Persistence.createEntityManagerFactory("StartupRushPU");
        this.em = emf.createEntityManager();
    }

    public void save(Rodada rodada) {
        try {
            em.getTransaction().begin();
            em.persist(rodada);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

