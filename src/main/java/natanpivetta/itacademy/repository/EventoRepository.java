package natanpivetta.itacademy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import natanpivetta.itacademy.model.Evento;
import natanpivetta.itacademy.util.JPAUtil;
import natanpivetta.itacademy.util.TipoEvento;

public class EventoRepository {
    private EntityManager em = JPAUtil.getEntityManager();

    public EventoRepository() {}

    public void registraEvento(Evento evento) {
        try {
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public Evento findById(int id) {
        return em.find(Evento.class, id);
    }

    public boolean existsBy(Long btID, Long stID, TipoEvento tipo) {
        String jpql = "SELECT COUNT(e) FROM Evento e " +
                "WHERE e.batalha.id = :batalhaId " +
                "AND e.startup.id = :startupId " +
                "AND e.tipo = :tipo";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("batalhaId", btID)
                .setParameter("startupId", stID)
                .setParameter("tipo", tipo)
                .getSingleResult();

        return count > 0;

    }
}
