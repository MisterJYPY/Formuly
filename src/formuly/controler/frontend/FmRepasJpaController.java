/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.controler.frontend.exceptions.NonexistentEntityException;
import formuly.controler.frontend.exceptions.PreexistingEntityException;
import formuly.entities.FmRepas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formuly.entities.FmRepasAliments;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmRepasJpaController implements Serializable {

    public FmRepasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmRepas fmRepas) throws PreexistingEntityException, Exception {
        if (fmRepas.getFmRepasAlimentsCollection() == null) {
            fmRepas.setFmRepasAlimentsCollection(new ArrayList<FmRepasAliments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<FmRepasAliments> attachedFmRepasAlimentsCollection = new ArrayList<FmRepasAliments>();
            for (FmRepasAliments fmRepasAlimentsCollectionFmRepasAlimentsToAttach : fmRepas.getFmRepasAlimentsCollection()) {
                fmRepasAlimentsCollectionFmRepasAlimentsToAttach = em.getReference(fmRepasAlimentsCollectionFmRepasAlimentsToAttach.getClass(), fmRepasAlimentsCollectionFmRepasAlimentsToAttach.getId());
                attachedFmRepasAlimentsCollection.add(fmRepasAlimentsCollectionFmRepasAlimentsToAttach);
            }
            fmRepas.setFmRepasAlimentsCollection(attachedFmRepasAlimentsCollection);
            em.persist(fmRepas);
            for (FmRepasAliments fmRepasAlimentsCollectionFmRepasAliments : fmRepas.getFmRepasAlimentsCollection()) {
                FmRepas oldRepasOfFmRepasAlimentsCollectionFmRepasAliments = fmRepasAlimentsCollectionFmRepasAliments.getRepas();
                fmRepasAlimentsCollectionFmRepasAliments.setRepas(fmRepas);
                fmRepasAlimentsCollectionFmRepasAliments = em.merge(fmRepasAlimentsCollectionFmRepasAliments);
                if (oldRepasOfFmRepasAlimentsCollectionFmRepasAliments != null) {
                    oldRepasOfFmRepasAlimentsCollectionFmRepasAliments.getFmRepasAlimentsCollection().remove(fmRepasAlimentsCollectionFmRepasAliments);
                    oldRepasOfFmRepasAlimentsCollectionFmRepasAliments = em.merge(oldRepasOfFmRepasAlimentsCollectionFmRepasAliments);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFmRepas(fmRepas.getId()) != null) {
                throw new PreexistingEntityException("FmRepas " + fmRepas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmRepas fmRepas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmRepas persistentFmRepas = em.find(FmRepas.class, fmRepas.getId());
            Collection<FmRepasAliments> fmRepasAlimentsCollectionOld = persistentFmRepas.getFmRepasAlimentsCollection();
            Collection<FmRepasAliments> fmRepasAlimentsCollectionNew = fmRepas.getFmRepasAlimentsCollection();
            Collection<FmRepasAliments> attachedFmRepasAlimentsCollectionNew = new ArrayList<FmRepasAliments>();
            for (FmRepasAliments fmRepasAlimentsCollectionNewFmRepasAlimentsToAttach : fmRepasAlimentsCollectionNew) {
                fmRepasAlimentsCollectionNewFmRepasAlimentsToAttach = em.getReference(fmRepasAlimentsCollectionNewFmRepasAlimentsToAttach.getClass(), fmRepasAlimentsCollectionNewFmRepasAlimentsToAttach.getId());
                attachedFmRepasAlimentsCollectionNew.add(fmRepasAlimentsCollectionNewFmRepasAlimentsToAttach);
            }
            fmRepasAlimentsCollectionNew = attachedFmRepasAlimentsCollectionNew;
            fmRepas.setFmRepasAlimentsCollection(fmRepasAlimentsCollectionNew);
            fmRepas = em.merge(fmRepas);
            for (FmRepasAliments fmRepasAlimentsCollectionOldFmRepasAliments : fmRepasAlimentsCollectionOld) {
                if (!fmRepasAlimentsCollectionNew.contains(fmRepasAlimentsCollectionOldFmRepasAliments)) {
                    fmRepasAlimentsCollectionOldFmRepasAliments.setRepas(null);
                    fmRepasAlimentsCollectionOldFmRepasAliments = em.merge(fmRepasAlimentsCollectionOldFmRepasAliments);
                }
            }
            for (FmRepasAliments fmRepasAlimentsCollectionNewFmRepasAliments : fmRepasAlimentsCollectionNew) {
                if (!fmRepasAlimentsCollectionOld.contains(fmRepasAlimentsCollectionNewFmRepasAliments)) {
                    FmRepas oldRepasOfFmRepasAlimentsCollectionNewFmRepasAliments = fmRepasAlimentsCollectionNewFmRepasAliments.getRepas();
                    fmRepasAlimentsCollectionNewFmRepasAliments.setRepas(fmRepas);
                    fmRepasAlimentsCollectionNewFmRepasAliments = em.merge(fmRepasAlimentsCollectionNewFmRepasAliments);
                    if (oldRepasOfFmRepasAlimentsCollectionNewFmRepasAliments != null && !oldRepasOfFmRepasAlimentsCollectionNewFmRepasAliments.equals(fmRepas)) {
                        oldRepasOfFmRepasAlimentsCollectionNewFmRepasAliments.getFmRepasAlimentsCollection().remove(fmRepasAlimentsCollectionNewFmRepasAliments);
                        oldRepasOfFmRepasAlimentsCollectionNewFmRepasAliments = em.merge(oldRepasOfFmRepasAlimentsCollectionNewFmRepasAliments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmRepas.getId();
                if (findFmRepas(id) == null) {
                    throw new NonexistentEntityException("The fmRepas with id " + id + " no longer exists.");
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
            FmRepas fmRepas;
            try {
                fmRepas = em.getReference(FmRepas.class, id);
                fmRepas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmRepas with id " + id + " no longer exists.", enfe);
            }
            Collection<FmRepasAliments> fmRepasAlimentsCollection = fmRepas.getFmRepasAlimentsCollection();
            for (FmRepasAliments fmRepasAlimentsCollectionFmRepasAliments : fmRepasAlimentsCollection) {
                fmRepasAlimentsCollectionFmRepasAliments.setRepas(null);
                fmRepasAlimentsCollectionFmRepasAliments = em.merge(fmRepasAlimentsCollectionFmRepasAliments);
            }
            em.remove(fmRepas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmRepas> findFmRepasEntities() {
        return findFmRepasEntities(true, -1, -1);
    }

    public List<FmRepas> findFmRepasEntities(int maxResults, int firstResult) {
        return findFmRepasEntities(false, maxResults, firstResult);
    }

    private List<FmRepas> findFmRepasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmRepas.class));
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

    public FmRepas findFmRepas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmRepas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFmRepasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmRepas> rt = cq.from(FmRepas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
