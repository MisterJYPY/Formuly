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
import formuly.entities.FmAliments;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmPathologie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmAlimentsPathologieJpaController implements Serializable {

    public FmAlimentsPathologieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmAlimentsPathologie fmAlimentsPathologie) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmAliments aliment = fmAlimentsPathologie.getAliment();
            if (aliment != null) {
                aliment = em.getReference(aliment.getClass(), aliment.getId());
                fmAlimentsPathologie.setAliment(aliment);
            }
            FmPathologie pathologie = fmAlimentsPathologie.getPathologie();
            if (pathologie != null) {
                pathologie = em.getReference(pathologie.getClass(), pathologie.getId());
                fmAlimentsPathologie.setPathologie(pathologie);
            }
            em.persist(fmAlimentsPathologie);
            if (aliment != null) {
                aliment.getFmAlimentsPathologieCollection().add(fmAlimentsPathologie);
                aliment = em.merge(aliment);
            }
            if (pathologie != null) {
                pathologie.getFmAlimentsPathologieCollection().add(fmAlimentsPathologie);
                pathologie = em.merge(pathologie);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFmAlimentsPathologie(fmAlimentsPathologie.getId()) != null) {
                throw new PreexistingEntityException("FmAlimentsPathologie " + fmAlimentsPathologie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmAlimentsPathologie fmAlimentsPathologie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmAlimentsPathologie persistentFmAlimentsPathologie = em.find(FmAlimentsPathologie.class, fmAlimentsPathologie.getId());
            FmAliments alimentOld = persistentFmAlimentsPathologie.getAliment();
            FmAliments alimentNew = fmAlimentsPathologie.getAliment();
            FmPathologie pathologieOld = persistentFmAlimentsPathologie.getPathologie();
            FmPathologie pathologieNew = fmAlimentsPathologie.getPathologie();
            if (alimentNew != null) {
                alimentNew = em.getReference(alimentNew.getClass(), alimentNew.getId());
                fmAlimentsPathologie.setAliment(alimentNew);
            }
            if (pathologieNew != null) {
                pathologieNew = em.getReference(pathologieNew.getClass(), pathologieNew.getId());
                fmAlimentsPathologie.setPathologie(pathologieNew);
            }
            fmAlimentsPathologie = em.merge(fmAlimentsPathologie);
            if (alimentOld != null && !alimentOld.equals(alimentNew)) {
                alimentOld.getFmAlimentsPathologieCollection().remove(fmAlimentsPathologie);
                alimentOld = em.merge(alimentOld);
            }
            if (alimentNew != null && !alimentNew.equals(alimentOld)) {
                alimentNew.getFmAlimentsPathologieCollection().add(fmAlimentsPathologie);
                alimentNew = em.merge(alimentNew);
            }
            if (pathologieOld != null && !pathologieOld.equals(pathologieNew)) {
                pathologieOld.getFmAlimentsPathologieCollection().remove(fmAlimentsPathologie);
                pathologieOld = em.merge(pathologieOld);
            }
            if (pathologieNew != null && !pathologieNew.equals(pathologieOld)) {
                pathologieNew.getFmAlimentsPathologieCollection().add(fmAlimentsPathologie);
                pathologieNew = em.merge(pathologieNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmAlimentsPathologie.getId();
                if (findFmAlimentsPathologie(id) == null) {
                    throw new NonexistentEntityException("The fmAlimentsPathologie with id " + id + " no longer exists.");
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
            FmAlimentsPathologie fmAlimentsPathologie;
            try {
                fmAlimentsPathologie = em.getReference(FmAlimentsPathologie.class, id);
                fmAlimentsPathologie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmAlimentsPathologie with id " + id + " no longer exists.", enfe);
            }
            FmAliments aliment = fmAlimentsPathologie.getAliment();
            if (aliment != null) {
                aliment.getFmAlimentsPathologieCollection().remove(fmAlimentsPathologie);
                aliment = em.merge(aliment);
            }
            FmPathologie pathologie = fmAlimentsPathologie.getPathologie();
            if (pathologie != null) {
                pathologie.getFmAlimentsPathologieCollection().remove(fmAlimentsPathologie);
                pathologie = em.merge(pathologie);
            }
            em.remove(fmAlimentsPathologie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmAlimentsPathologie> findFmAlimentsPathologieEntities() {
        return findFmAlimentsPathologieEntities(true, -1, -1);
    }

    public List<FmAlimentsPathologie> findFmAlimentsPathologieEntities(int maxResults, int firstResult) {
        return findFmAlimentsPathologieEntities(false, maxResults, firstResult);
    }

    private List<FmAlimentsPathologie> findFmAlimentsPathologieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmAlimentsPathologie.class));
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

    public FmAlimentsPathologie findFmAlimentsPathologie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmAlimentsPathologie.class, id);
        } finally {
            em.close();
        }
    }

    public int getFmAlimentsPathologieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmAlimentsPathologie> rt = cq.from(FmAlimentsPathologie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
