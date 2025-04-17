package natanpivetta.itacademy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import natanpivetta.itacademy.model.Startup;

import java.util.List;

public class StartupRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public StartupRepository() {
        this.emf = Persistence.createEntityManagerFactory("StartupRushPU");
        this.em = emf.createEntityManager();  // Make sure to use the class-level variables
    }

    // Save a Startup object into the database
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

    // Find a Startup by its ID
    public Startup findById(Long id) {
        return em.find(Startup.class, id);
    }

    // Get all the startups from the database
    public List<Startup> findAll() {
        return em.createQuery("SELECT s FROM Startup s", Startup.class).getResultList();
    }

    // Delete a Startup by ID
    public void delete(Long id) {
        Startup startup = findById(id);
        if (startup != null) {
            em.getTransaction().begin();
            em.remove(startup);
            em.getTransaction().commit();
        }
    }

    // Update a Startup
    public void update(Startup startup) {
        em.getTransaction().begin();
        em.merge(startup);
        em.getTransaction().commit();
    }

    // Close EntityManager and EntityManagerFactory when done
    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
