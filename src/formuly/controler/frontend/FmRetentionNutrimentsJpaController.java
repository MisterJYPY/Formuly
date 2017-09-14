/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.controler.frontend.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formuly.entities.FmAliments;
import formuly.entities.FmRetentionNutriments;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmRetentionNutrimentsJpaController implements Serializable {

    public FmRetentionNutrimentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmRetentionNutriments fmRetentionNutriments) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmAliments aliment = fmRetentionNutriments.getAliment();
            if (aliment != null) {
                aliment = em.getReference(aliment.getClass(), aliment.getId());
                fmRetentionNutriments.setAliment(aliment);
            }
            em.persist(fmRetentionNutriments);
            if (aliment != null) {
                aliment.getFmRetentionNutrimentsCollection().add(fmRetentionNutriments);
                aliment = em.merge(aliment);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmRetentionNutriments fmRetentionNutriments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmRetentionNutriments persistentFmRetentionNutriments = em.find(FmRetentionNutriments.class, fmRetentionNutriments.getId());
            FmAliments alimentOld = persistentFmRetentionNutriments.getAliment();
            FmAliments alimentNew = fmRetentionNutriments.getAliment();
            if (alimentNew != null) {
                alimentNew = em.getReference(alimentNew.getClass(), alimentNew.getId());
                fmRetentionNutriments.setAliment(alimentNew);
            }
            fmRetentionNutriments = em.merge(fmRetentionNutriments);
            if (alimentOld != null && !alimentOld.equals(alimentNew)) {
                alimentOld.getFmRetentionNutrimentsCollection().remove(fmRetentionNutriments);
                alimentOld = em.merge(alimentOld);
            }
            if (alimentNew != null && !alimentNew.equals(alimentOld)) {
                alimentNew.getFmRetentionNutrimentsCollection().add(fmRetentionNutriments);
                alimentNew = em.merge(alimentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmRetentionNutriments.getId();
                if (findFmRetentionNutriments(id) == null) {
                    throw new NonexistentEntityException("The fmRetentionNutriments with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmRetentionNutriments fmRetentionNutriments;
            try {
                fmRetentionNutriments = em.getReference(FmRetentionNutriments.class, id);
                fmRetentionNutriments.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmRetentionNutriments with id " + id + " no longer exists.", enfe);
            }
            FmAliments aliment = fmRetentionNutriments.getAliment();
            if (aliment != null) {
                aliment.getFmRetentionNutrimentsCollection().remove(fmRetentionNutriments);
                aliment = em.merge(aliment);
            }
            em.remove(fmRetentionNutriments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmRetentionNutriments> findFmRetentionNutrimentsEntities() {
        return findFmRetentionNutrimentsEntities(true, -1, -1);
    }

    public List<FmRetentionNutriments> findFmRetentionNutrimentsEntities(int maxResults, int firstResult) {
        return findFmRetentionNutrimentsEntities(false, maxResults, firstResult);
    }

    private List<FmRetentionNutriments> findFmRetentionNutrimentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmRetentionNutriments.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FmRetentionNutriments findFmRetentionNutriments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmRetentionNutriments.class, id);
        } finally {
            em.close();
        }
    }

    public int getFmRetentionNutrimentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmRetentionNutriments> rt = cq.from(FmRetentionNutriments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
