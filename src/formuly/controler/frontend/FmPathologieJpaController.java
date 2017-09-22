/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.controler.frontend.exceptions.NonexistentEntityException;
import formuly.controler.frontend.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmPathologie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmPathologieJpaController implements Serializable {

    public FmPathologieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmPathologie fmPathologie) throws PreexistingEntityException, Exception {
        if (fmPathologie.getFmAlimentsPathologieCollection() == null) {
            fmPathologie.setFmAlimentsPathologieCollection(new ArrayList<FmAlimentsPathologie>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<FmAlimentsPathologie> attachedFmAlimentsPathologieCollection = new ArrayList<FmAlimentsPathologie>();
            for (FmAlimentsPathologie fmAlimentsPathologieCollectionFmAlimentsPathologieToAttach : fmPathologie.getFmAlimentsPathologieCollection()) {
                fmAlimentsPathologieCollectionFmAlimentsPathologieToAttach = em.getReference(fmAlimentsPathologieCollectionFmAlimentsPathologieToAttach.getClass(), fmAlimentsPathologieCollectionFmAlimentsPathologieToAttach.getId());
                attachedFmAlimentsPathologieCollection.add(fmAlimentsPathologieCollectionFmAlimentsPathologieToAttach);
            }
            fmPathologie.setFmAlimentsPathologieCollection(attachedFmAlimentsPathologieCollection);
            em.persist(fmPathologie);
            for (FmAlimentsPathologie fmAlimentsPathologieCollectionFmAlimentsPathologie : fmPathologie.getFmAlimentsPathologieCollection()) {
                FmPathologie oldPathologieOfFmAlimentsPathologieCollectionFmAlimentsPathologie = fmAlimentsPathologieCollectionFmAlimentsPathologie.getPathologie();
                fmAlimentsPathologieCollectionFmAlimentsPathologie.setPathologie(fmPathologie);
                fmAlimentsPathologieCollectionFmAlimentsPathologie = em.merge(fmAlimentsPathologieCollectionFmAlimentsPathologie);
                if (oldPathologieOfFmAlimentsPathologieCollectionFmAlimentsPathologie != null) {
                    oldPathologieOfFmAlimentsPathologieCollectionFmAlimentsPathologie.getFmAlimentsPathologieCollection().remove(fmAlimentsPathologieCollectionFmAlimentsPathologie);
                    oldPathologieOfFmAlimentsPathologieCollectionFmAlimentsPathologie = em.merge(oldPathologieOfFmAlimentsPathologieCollectionFmAlimentsPathologie);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFmPathologie(fmPathologie.getId()) != null) {
                throw new PreexistingEntityException("FmPathologie " + fmPathologie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmPathologie fmPathologie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmPathologie persistentFmPathologie = em.find(FmPathologie.class, fmPathologie.getId());
            Collection<FmAlimentsPathologie> fmAlimentsPathologieCollectionOld = persistentFmPathologie.getFmAlimentsPathologieCollection();
            Collection<FmAlimentsPathologie> fmAlimentsPathologieCollectionNew = fmPathologie.getFmAlimentsPathologieCollection();
            Collection<FmAlimentsPathologie> attachedFmAlimentsPathologieCollectionNew = new ArrayList<FmAlimentsPathologie>();
            for (FmAlimentsPathologie fmAlimentsPathologieCollectionNewFmAlimentsPathologieToAttach : fmAlimentsPathologieCollectionNew) {
                fmAlimentsPathologieCollectionNewFmAlimentsPathologieToAttach = em.getReference(fmAlimentsPathologieCollectionNewFmAlimentsPathologieToAttach.getClass(), fmAlimentsPathologieCollectionNewFmAlimentsPathologieToAttach.getId());
                attachedFmAlimentsPathologieCollectionNew.add(fmAlimentsPathologieCollectionNewFmAlimentsPathologieToAttach);
            }
            fmAlimentsPathologieCollectionNew = attachedFmAlimentsPathologieCollectionNew;
            fmPathologie.setFmAlimentsPathologieCollection(fmAlimentsPathologieCollectionNew);
            fmPathologie = em.merge(fmPathologie);
            for (FmAlimentsPathologie fmAlimentsPathologieCollectionOldFmAlimentsPathologie : fmAlimentsPathologieCollectionOld) {
                if (!fmAlimentsPathologieCollectionNew.contains(fmAlimentsPathologieCollectionOldFmAlimentsPathologie)) {
                    fmAlimentsPathologieCollectionOldFmAlimentsPathologie.setPathologie(null);
                    fmAlimentsPathologieCollectionOldFmAlimentsPathologie = em.merge(fmAlimentsPathologieCollectionOldFmAlimentsPathologie);
                }
            }
            for (FmAlimentsPathologie fmAlimentsPathologieCollectionNewFmAlimentsPathologie : fmAlimentsPathologieCollectionNew) {
                if (!fmAlimentsPathologieCollectionOld.contains(fmAlimentsPathologieCollectionNewFmAlimentsPathologie)) {
                    FmPathologie oldPathologieOfFmAlimentsPathologieCollectionNewFmAlimentsPathologie = fmAlimentsPathologieCollectionNewFmAlimentsPathologie.getPathologie();
                    fmAlimentsPathologieCollectionNewFmAlimentsPathologie.setPathologie(fmPathologie);
                    fmAlimentsPathologieCollectionNewFmAlimentsPathologie = em.merge(fmAlimentsPathologieCollectionNewFmAlimentsPathologie);
                    if (oldPathologieOfFmAlimentsPathologieCollectionNewFmAlimentsPathologie != null && !oldPathologieOfFmAlimentsPathologieCollectionNewFmAlimentsPathologie.equals(fmPathologie)) {
                        oldPathologieOfFmAlimentsPathologieCollectionNewFmAlimentsPathologie.getFmAlimentsPathologieCollection().remove(fmAlimentsPathologieCollectionNewFmAlimentsPathologie);
                        oldPathologieOfFmAlimentsPathologieCollectionNewFmAlimentsPathologie = em.merge(oldPathologieOfFmAlimentsPathologieCollectionNewFmAlimentsPathologie);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmPathologie.getId();
                if (findFmPathologie(id) == null) {
                    throw new NonexistentEntityException("The fmPathologie with id " + id + " no longer exists.");
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
            FmPathologie fmPathologie;
            try {
                fmPathologie = em.getReference(FmPathologie.class, id);
                fmPathologie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmPathologie with id " + id + " no longer exists.", enfe);
            }
            Collection<FmAlimentsPathologie> fmAlimentsPathologieCollection = fmPathologie.getFmAlimentsPathologieCollection();
            for (FmAlimentsPathologie fmAlimentsPathologieCollectionFmAlimentsPathologie : fmAlimentsPathologieCollection) {
                fmAlimentsPathologieCollectionFmAlimentsPathologie.setPathologie(null);
                fmAlimentsPathologieCollectionFmAlimentsPathologie = em.merge(fmAlimentsPathologieCollectionFmAlimentsPathologie);
            }
            em.remove(fmPathologie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmPathologie> findFmPathologieEntities() {
        return findFmPathologieEntities(true, -1, -1);
    }

    public List<FmPathologie> findFmPathologieEntities(int maxResults, int firstResult) {
        return findFmPathologieEntities(false, maxResults, firstResult);
    }

    private List<FmPathologie> findFmPathologieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmPathologie.class));
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

    public FmPathologie findFmPathologie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmPathologie.class, id);
        } finally {
            em.close();
        }
    }

    public int getFmPathologieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmPathologie> rt = cq.from(FmPathologie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
