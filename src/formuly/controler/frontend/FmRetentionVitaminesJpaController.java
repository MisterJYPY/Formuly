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
import formuly.entities.FmRetentionVitamines;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmRetentionVitaminesJpaController implements Serializable {

    public FmRetentionVitaminesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmRetentionVitamines fmRetentionVitamines) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmAliments aliment = fmRetentionVitamines.getAliment();
            if (aliment != null) {
                aliment = em.getReference(aliment.getClass(), aliment.getId());
                fmRetentionVitamines.setAliment(aliment);
            }
            em.persist(fmRetentionVitamines);
            if (aliment != null) {
                aliment.getFmRetentionVitaminesCollection().add(fmRetentionVitamines);
                aliment = em.merge(aliment);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmRetentionVitamines fmRetentionVitamines) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmRetentionVitamines persistentFmRetentionVitamines = em.find(FmRetentionVitamines.class, fmRetentionVitamines.getId());
            FmAliments alimentOld = persistentFmRetentionVitamines.getAliment();
            FmAliments alimentNew = fmRetentionVitamines.getAliment();
            if (alimentNew != null) {
                alimentNew = em.getReference(alimentNew.getClass(), alimentNew.getId());
                fmRetentionVitamines.setAliment(alimentNew);
            }
            fmRetentionVitamines = em.merge(fmRetentionVitamines);
            if (alimentOld != null && !alimentOld.equals(alimentNew)) {
                alimentOld.getFmRetentionVitaminesCollection().remove(fmRetentionVitamines);
                alimentOld = em.merge(alimentOld);
            }
            if (alimentNew != null && !alimentNew.equals(alimentOld)) {
                alimentNew.getFmRetentionVitaminesCollection().add(fmRetentionVitamines);
                alimentNew = em.merge(alimentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmRetentionVitamines.getId();
                if (findFmRetentionVitamines(id) == null) {
                    throw new NonexistentEntityException("The fmRetentionVitamines with id " + id + " no longer exists.");
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
            FmRetentionVitamines fmRetentionVitamines;
            try {
                fmRetentionVitamines = em.getReference(FmRetentionVitamines.class, id);
                fmRetentionVitamines.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmRetentionVitamines with id " + id + " no longer exists.", enfe);
            }
            FmAliments aliment = fmRetentionVitamines.getAliment();
            if (aliment != null) {
                aliment.getFmRetentionVitaminesCollection().remove(fmRetentionVitamines);
                aliment = em.merge(aliment);
            }
            em.remove(fmRetentionVitamines);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmRetentionVitamines> findFmRetentionVitaminesEntities() {
        return findFmRetentionVitaminesEntities(true, -1, -1);
    }

    public List<FmRetentionVitamines> findFmRetentionVitaminesEntities(int maxResults, int firstResult) {
        return findFmRetentionVitaminesEntities(false, maxResults, firstResult);
    }

    private List<FmRetentionVitamines> findFmRetentionVitaminesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmRetentionVitamines.class));
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

    public FmRetentionVitamines findFmRetentionVitamines(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmRetentionVitamines.class, id);
        } finally {
            em.close();
        }
    }

    public int getFmRetentionVitaminesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmRetentionVitamines> rt = cq.from(FmRetentionVitamines.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
