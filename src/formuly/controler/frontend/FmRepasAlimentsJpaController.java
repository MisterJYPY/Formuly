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
import formuly.entities.FmRepas;
import formuly.entities.FmRepasAliments;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmRepasAlimentsJpaController implements Serializable {

    public FmRepasAlimentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmRepasAliments fmRepasAliments) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmAliments aliment = fmRepasAliments.getAliment();
            if (aliment != null) {
                aliment = em.getReference(aliment.getClass(), aliment.getId());
                fmRepasAliments.setAliment(aliment);
            }
            FmRepas repas = fmRepasAliments.getRepas();
            if (repas != null) {
                repas = em.getReference(repas.getClass(), repas.getId());
                fmRepasAliments.setRepas(repas);
            }
            em.persist(fmRepasAliments);
            if (aliment != null) {
                aliment.getFmRepasAlimentsCollection().add(fmRepasAliments);
                aliment = em.merge(aliment);
            }
            if (repas != null) {
                repas.getFmRepasAlimentsCollection().add(fmRepasAliments);
                repas = em.merge(repas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFmRepasAliments(fmRepasAliments.getId()) != null) {
                throw new PreexistingEntityException("FmRepasAliments " + fmRepasAliments + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmRepasAliments fmRepasAliments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmRepasAliments persistentFmRepasAliments = em.find(FmRepasAliments.class, fmRepasAliments.getId());
            FmAliments alimentOld = persistentFmRepasAliments.getAliment();
            FmAliments alimentNew = fmRepasAliments.getAliment();
            FmRepas repasOld = persistentFmRepasAliments.getRepas();
            FmRepas repasNew = fmRepasAliments.getRepas();
            if (alimentNew != null) {
                alimentNew = em.getReference(alimentNew.getClass(), alimentNew.getId());
                fmRepasAliments.setAliment(alimentNew);
            }
            if (repasNew != null) {
                repasNew = em.getReference(repasNew.getClass(), repasNew.getId());
                fmRepasAliments.setRepas(repasNew);
            }
            fmRepasAliments = em.merge(fmRepasAliments);
            if (alimentOld != null && !alimentOld.equals(alimentNew)) {
                alimentOld.getFmRepasAlimentsCollection().remove(fmRepasAliments);
                alimentOld = em.merge(alimentOld);
            }
            if (alimentNew != null && !alimentNew.equals(alimentOld)) {
                alimentNew.getFmRepasAlimentsCollection().add(fmRepasAliments);
                alimentNew = em.merge(alimentNew);
            }
            if (repasOld != null && !repasOld.equals(repasNew)) {
                repasOld.getFmRepasAlimentsCollection().remove(fmRepasAliments);
                repasOld = em.merge(repasOld);
            }
            if (repasNew != null && !repasNew.equals(repasOld)) {
                repasNew.getFmRepasAlimentsCollection().add(fmRepasAliments);
                repasNew = em.merge(repasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmRepasAliments.getId();
                if (findFmRepasAliments(id) == null) {
                    throw new NonexistentEntityException("The fmRepasAliments with id " + id + " no longer exists.");
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
            FmRepasAliments fmRepasAliments;
            try {
                fmRepasAliments = em.getReference(FmRepasAliments.class, id);
                fmRepasAliments.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmRepasAliments with id " + id + " no longer exists.", enfe);
            }
            FmAliments aliment = fmRepasAliments.getAliment();
            if (aliment != null) {
                aliment.getFmRepasAlimentsCollection().remove(fmRepasAliments);
                aliment = em.merge(aliment);
            }
            FmRepas repas = fmRepasAliments.getRepas();
            if (repas != null) {
                repas.getFmRepasAlimentsCollection().remove(fmRepasAliments);
                repas = em.merge(repas);
            }
            em.remove(fmRepasAliments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmRepasAliments> findFmRepasAlimentsEntities() {
        return findFmRepasAlimentsEntities(true, -1, -1);
    }

    public List<FmRepasAliments> findFmRepasAlimentsEntities(int maxResults, int firstResult) {
        return findFmRepasAlimentsEntities(false, maxResults, firstResult);
    }

    private List<FmRepasAliments> findFmRepasAlimentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmRepasAliments.class));
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

    public FmRepasAliments findFmRepasAliments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmRepasAliments.class, id);
        } finally {
            em.close();
        }
    }
    public List<FmRepasAliments> findFmRepasAlimentsByRepas(int idRepas) {
        EntityManager em = getEntityManager();
        List<FmRepasAliments>  list=null;
        try {
            String sql="SELECT f.id,f.aliment,f.quantite,f.date,f.repas FROM fm_repas_aliments f WHERE f.repas="+idRepas;
            Query qr=em.createNativeQuery(sql,FmRepasAliments.class);
            list=(  List<FmRepasAliments>) qr.getResultList();
        } finally {
            em.close();
        }
        return list;
    }

    public int getFmRepasAlimentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmRepasAliments> rt = cq.from(FmRepasAliments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
