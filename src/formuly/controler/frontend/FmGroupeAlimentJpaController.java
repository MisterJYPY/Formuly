/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.exceptions.NonexistentEntityException;
import formuly.classe.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formuly.entities.FmAliments;
import formuly.entities.FmGroupeAliment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmGroupeAlimentJpaController implements Serializable {

    public FmGroupeAlimentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmGroupeAliment fmGroupeAliment) throws PreexistingEntityException, Exception {
        if (fmGroupeAliment.getFmAlimentsCollection() == null) {
            fmGroupeAliment.setFmAlimentsCollection(new ArrayList<FmAliments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<FmAliments> attachedFmAlimentsCollection = new ArrayList<FmAliments>();
            for (FmAliments fmAlimentsCollectionFmAlimentsToAttach : fmGroupeAliment.getFmAlimentsCollection()) {
                fmAlimentsCollectionFmAlimentsToAttach = em.getReference(fmAlimentsCollectionFmAlimentsToAttach.getClass(), fmAlimentsCollectionFmAlimentsToAttach.getId());
                attachedFmAlimentsCollection.add(fmAlimentsCollectionFmAlimentsToAttach);
            }
            fmGroupeAliment.setFmAlimentsCollection(attachedFmAlimentsCollection);
            em.persist(fmGroupeAliment);
            for (FmAliments fmAlimentsCollectionFmAliments : fmGroupeAliment.getFmAlimentsCollection()) {
                FmGroupeAliment oldGroupeOfFmAlimentsCollectionFmAliments = fmAlimentsCollectionFmAliments.getGroupe();
                fmAlimentsCollectionFmAliments.setGroupe(fmGroupeAliment);
                fmAlimentsCollectionFmAliments = em.merge(fmAlimentsCollectionFmAliments);
                if (oldGroupeOfFmAlimentsCollectionFmAliments != null) {
                    oldGroupeOfFmAlimentsCollectionFmAliments.getFmAlimentsCollection().remove(fmAlimentsCollectionFmAliments);
                    oldGroupeOfFmAlimentsCollectionFmAliments = em.merge(oldGroupeOfFmAlimentsCollectionFmAliments);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFmGroupeAliment(fmGroupeAliment.getId()) != null) {
                throw new PreexistingEntityException("FmGroupeAliment " + fmGroupeAliment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmGroupeAliment fmGroupeAliment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmGroupeAliment persistentFmGroupeAliment = em.find(FmGroupeAliment.class, fmGroupeAliment.getId());
            Collection<FmAliments> fmAlimentsCollectionOld = persistentFmGroupeAliment.getFmAlimentsCollection();
            Collection<FmAliments> fmAlimentsCollectionNew = fmGroupeAliment.getFmAlimentsCollection();
            Collection<FmAliments> attachedFmAlimentsCollectionNew = new ArrayList<FmAliments>();
            for (FmAliments fmAlimentsCollectionNewFmAlimentsToAttach : fmAlimentsCollectionNew) {
                fmAlimentsCollectionNewFmAlimentsToAttach = em.getReference(fmAlimentsCollectionNewFmAlimentsToAttach.getClass(), fmAlimentsCollectionNewFmAlimentsToAttach.getId());
                attachedFmAlimentsCollectionNew.add(fmAlimentsCollectionNewFmAlimentsToAttach);
            }
            fmAlimentsCollectionNew = attachedFmAlimentsCollectionNew;
            fmGroupeAliment.setFmAlimentsCollection(fmAlimentsCollectionNew);
            fmGroupeAliment = em.merge(fmGroupeAliment);
            for (FmAliments fmAlimentsCollectionOldFmAliments : fmAlimentsCollectionOld) {
                if (!fmAlimentsCollectionNew.contains(fmAlimentsCollectionOldFmAliments)) {
                    fmAlimentsCollectionOldFmAliments.setGroupe(null);
                    fmAlimentsCollectionOldFmAliments = em.merge(fmAlimentsCollectionOldFmAliments);
                }
            }
            for (FmAliments fmAlimentsCollectionNewFmAliments : fmAlimentsCollectionNew) {
                if (!fmAlimentsCollectionOld.contains(fmAlimentsCollectionNewFmAliments)) {
                    FmGroupeAliment oldGroupeOfFmAlimentsCollectionNewFmAliments = fmAlimentsCollectionNewFmAliments.getGroupe();
                    fmAlimentsCollectionNewFmAliments.setGroupe(fmGroupeAliment);
                    fmAlimentsCollectionNewFmAliments = em.merge(fmAlimentsCollectionNewFmAliments);
                    if (oldGroupeOfFmAlimentsCollectionNewFmAliments != null && !oldGroupeOfFmAlimentsCollectionNewFmAliments.equals(fmGroupeAliment)) {
                        oldGroupeOfFmAlimentsCollectionNewFmAliments.getFmAlimentsCollection().remove(fmAlimentsCollectionNewFmAliments);
                        oldGroupeOfFmAlimentsCollectionNewFmAliments = em.merge(oldGroupeOfFmAlimentsCollectionNewFmAliments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmGroupeAliment.getId();
                if (findFmGroupeAliment(id) == null) {
                    throw new NonexistentEntityException("The fmGroupeAliment with id " + id + " no longer exists.");
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
            FmGroupeAliment fmGroupeAliment;
            try {
                fmGroupeAliment = em.getReference(FmGroupeAliment.class, id);
                fmGroupeAliment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmGroupeAliment with id " + id + " no longer exists.", enfe);
            }
            Collection<FmAliments> fmAlimentsCollection = fmGroupeAliment.getFmAlimentsCollection();
            for (FmAliments fmAlimentsCollectionFmAliments : fmAlimentsCollection) {
                fmAlimentsCollectionFmAliments.setGroupe(null);
                fmAlimentsCollectionFmAliments = em.merge(fmAlimentsCollectionFmAliments);
            }
            em.remove(fmGroupeAliment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmGroupeAliment> findFmGroupeAlimentEntities() {
        return findFmGroupeAlimentEntities(true, -1, -1);
    }

    public List<FmGroupeAliment> findFmGroupeAlimentEntities(int maxResults, int firstResult) {
        return findFmGroupeAlimentEntities(false, maxResults, firstResult);
    }

    private List<FmGroupeAliment> findFmGroupeAlimentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmGroupeAliment.class));
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

    public FmGroupeAliment findFmGroupeAliment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmGroupeAliment.class, id);
        } finally {
            em.close();
        }
    }

    public int getFmGroupeAlimentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmGroupeAliment> rt = cq.from(FmGroupeAliment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
