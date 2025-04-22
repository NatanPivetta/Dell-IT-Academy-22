package natanpivetta.itacademy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Rodada;
import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.util.JPAUtil;

import java.util.List;

public class RodadaRepository {

    private EntityManager em = JPAUtil.getEntityManager();
    public RodadaRepository() {}

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

    public void update(Rodada rodada) {
        try {
            em.getTransaction().begin();
            em.merge(rodada);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Batalha> findAll(Rodada rodada) {
        return em.createQuery("SELECT b FROM Batalha b WHERE b.rodada = :rodada", Batalha.class)
                    .setParameter("rodada", rodada)
                    .getResultList();
        }

}

