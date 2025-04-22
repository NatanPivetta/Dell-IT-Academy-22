package natanpivetta.itacademy.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("StartupRushPU");

    private static final EntityManager em = emf.createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }

    public static void close() {
        if (em.isOpen()) em.close();
        emf.close();
    }
}