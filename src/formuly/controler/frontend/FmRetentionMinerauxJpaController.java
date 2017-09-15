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
import formuly.entities.FmRetentionMineraux;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmRetentionMinerauxJpaController implements Serializable {

    public FmRetentionMinerauxJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmRetentionMineraux fmRetentionMineraux) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmAliments aliment = fmRetentionMineraux.getAliment();
            if (aliment != null) {
                aliment = em.getReference(aliment.getClass(), aliment.getId());
                fmRetentionMineraux.setAliment(aliment);
            }
            em.persist(fmRetentionMineraux);
            if (aliment != null) {
                aliment.getFmRetentionMinerauxCollection().add(fmRetentionMineraux);
                aliment = em.merge(aliment);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmRetentionMineraux fmRetentionMineraux) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmRetentionMineraux persistentFmRetentionMineraux = em.find(FmRetentionMineraux.class, fmRetentionMineraux.getId());
            FmAliments alimentOld = persistentFmRetentionMineraux.getAliment();
            FmAliments alimentNew = fmRetentionMineraux.getAliment();
            if (alimentNew != null) {
                alimentNew = em.getReference(alimentNew.getClass(), alimentNew.getId());
                fmRetentionMineraux.setAliment(alimentNew);
            }
            fmRetentionMineraux = em.merge(fmRetentionMineraux);
            if (alimentOld != null && !alimentOld.equals(alimentNew)) {
                alimentOld.getFmRetentionMinerauxCollection().remove(fmRetentionMineraux);
                alimentOld = em.merge(alimentOld);
            }
            if (alimentNew != null && !alimentNew.equals(alimentOld)) {
                alimentNew.getFmRetentionMinerauxCollection().add(fmRetentionMineraux);
                alimentNew = em.merge(alimentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmRetentionMineraux.getId();
                if (findFmRetentionMineraux(id) == null) {
                    throw new NonexistentEntityException("The fmRetentionMineraux with id " + id + " no longer exists.");
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
            FmRetentionMineraux fmRetentionMineraux;
            try {
                fmRetentionMineraux = em.getReference(FmRetentionMineraux.class, id);
                fmRetentionMineraux.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmRetentionMineraux with id " + id + " no longer exists.", enfe);
            }
            FmAliments aliment = fmRetentionMineraux.getAliment();
            if (aliment != null) {
                aliment.getFmRetentionMinerauxCollection().remove(fmRetentionMineraux);
                aliment = em.merge(aliment);
            }
            em.remove(fmRetentionMineraux);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmRetentionMineraux> findFmRetentionMinerauxEntities() {
        return findFmRetentionMinerauxEntities(true, -1, -1);
    }

    public List<FmRetentionMineraux> findFmRetentionMinerauxEntities(int maxResults, int firstResult) {
        return findFmRetentionMinerauxEntities(false, maxResults, firstResult);
    }

    private List<FmRetentionMineraux> findFmRetentionMinerauxEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmRetentionMineraux.class));
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

    public FmRetentionMineraux findFmRetentionMineraux(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmRetentionMineraux.class, id);
        } finally {
            em.close();
        }
    }
    

    public int getFmRetentionMinerauxCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmRetentionMineraux> rt = cq.from(FmRetentionMineraux.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
