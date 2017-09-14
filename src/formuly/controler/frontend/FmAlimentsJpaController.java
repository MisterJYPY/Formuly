/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.exceptions.NonexistentEntityException;
import formuly.entities.FmAliments;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import formuly.entities.FmGroupeAliment;
import formuly.entities.FmRetentionMineraux;
import java.util.ArrayList;
import java.util.Collection;
import formuly.entities.FmRetentionVitamines;
import formuly.entities.FmRetentionNutriments;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mr_JYPY
 */
public class FmAlimentsJpaController implements Serializable {

    public FmAlimentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FmAliments fmAliments) {
        if (fmAliments.getFmRetentionMinerauxCollection() == null) {
            fmAliments.setFmRetentionMinerauxCollection(new ArrayList<FmRetentionMineraux>());
        }
        if (fmAliments.getFmRetentionVitaminesCollection() == null) {
            fmAliments.setFmRetentionVitaminesCollection(new ArrayList<FmRetentionVitamines>());
        }
        if (fmAliments.getFmRetentionNutrimentsCollection() == null) {
            fmAliments.setFmRetentionNutrimentsCollection(new ArrayList<FmRetentionNutriments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmGroupeAliment groupe = fmAliments.getGroupe();
            if (groupe != null) {
                groupe = em.getReference(groupe.getClass(), groupe.getId());
                fmAliments.setGroupe(groupe);
            }
            Collection<FmRetentionMineraux> attachedFmRetentionMinerauxCollection = new ArrayList<FmRetentionMineraux>();
            for (FmRetentionMineraux fmRetentionMinerauxCollectionFmRetentionMinerauxToAttach : fmAliments.getFmRetentionMinerauxCollection()) {
                fmRetentionMinerauxCollectionFmRetentionMinerauxToAttach = em.getReference(fmRetentionMinerauxCollectionFmRetentionMinerauxToAttach.getClass(), fmRetentionMinerauxCollectionFmRetentionMinerauxToAttach.getId());
                attachedFmRetentionMinerauxCollection.add(fmRetentionMinerauxCollectionFmRetentionMinerauxToAttach);
            }
            fmAliments.setFmRetentionMinerauxCollection(attachedFmRetentionMinerauxCollection);
            Collection<FmRetentionVitamines> attachedFmRetentionVitaminesCollection = new ArrayList<FmRetentionVitamines>();
            for (FmRetentionVitamines fmRetentionVitaminesCollectionFmRetentionVitaminesToAttach : fmAliments.getFmRetentionVitaminesCollection()) {
                fmRetentionVitaminesCollectionFmRetentionVitaminesToAttach = em.getReference(fmRetentionVitaminesCollectionFmRetentionVitaminesToAttach.getClass(), fmRetentionVitaminesCollectionFmRetentionVitaminesToAttach.getId());
                attachedFmRetentionVitaminesCollection.add(fmRetentionVitaminesCollectionFmRetentionVitaminesToAttach);
            }
            fmAliments.setFmRetentionVitaminesCollection(attachedFmRetentionVitaminesCollection);
            Collection<FmRetentionNutriments> attachedFmRetentionNutrimentsCollection = new ArrayList<FmRetentionNutriments>();
            for (FmRetentionNutriments fmRetentionNutrimentsCollectionFmRetentionNutrimentsToAttach : fmAliments.getFmRetentionNutrimentsCollection()) {
                fmRetentionNutrimentsCollectionFmRetentionNutrimentsToAttach = em.getReference(fmRetentionNutrimentsCollectionFmRetentionNutrimentsToAttach.getClass(), fmRetentionNutrimentsCollectionFmRetentionNutrimentsToAttach.getId());
                attachedFmRetentionNutrimentsCollection.add(fmRetentionNutrimentsCollectionFmRetentionNutrimentsToAttach);
            }
            fmAliments.setFmRetentionNutrimentsCollection(attachedFmRetentionNutrimentsCollection);
            em.persist(fmAliments);
            if (groupe != null) {
                groupe.getFmAlimentsCollection().add(fmAliments);
                groupe = em.merge(groupe);
            }
            for (FmRetentionMineraux fmRetentionMinerauxCollectionFmRetentionMineraux : fmAliments.getFmRetentionMinerauxCollection()) {
                FmAliments oldAlimentOfFmRetentionMinerauxCollectionFmRetentionMineraux = fmRetentionMinerauxCollectionFmRetentionMineraux.getAliment();
                fmRetentionMinerauxCollectionFmRetentionMineraux.setAliment(fmAliments);
                fmRetentionMinerauxCollectionFmRetentionMineraux = em.merge(fmRetentionMinerauxCollectionFmRetentionMineraux);
                if (oldAlimentOfFmRetentionMinerauxCollectionFmRetentionMineraux != null) {
                    oldAlimentOfFmRetentionMinerauxCollectionFmRetentionMineraux.getFmRetentionMinerauxCollection().remove(fmRetentionMinerauxCollectionFmRetentionMineraux);
                    oldAlimentOfFmRetentionMinerauxCollectionFmRetentionMineraux = em.merge(oldAlimentOfFmRetentionMinerauxCollectionFmRetentionMineraux);
                }
            }
            for (FmRetentionVitamines fmRetentionVitaminesCollectionFmRetentionVitamines : fmAliments.getFmRetentionVitaminesCollection()) {
                FmAliments oldAlimentOfFmRetentionVitaminesCollectionFmRetentionVitamines = fmRetentionVitaminesCollectionFmRetentionVitamines.getAliment();
                fmRetentionVitaminesCollectionFmRetentionVitamines.setAliment(fmAliments);
                fmRetentionVitaminesCollectionFmRetentionVitamines = em.merge(fmRetentionVitaminesCollectionFmRetentionVitamines);
                if (oldAlimentOfFmRetentionVitaminesCollectionFmRetentionVitamines != null) {
                    oldAlimentOfFmRetentionVitaminesCollectionFmRetentionVitamines.getFmRetentionVitaminesCollection().remove(fmRetentionVitaminesCollectionFmRetentionVitamines);
                    oldAlimentOfFmRetentionVitaminesCollectionFmRetentionVitamines = em.merge(oldAlimentOfFmRetentionVitaminesCollectionFmRetentionVitamines);
                }
            }
            for (FmRetentionNutriments fmRetentionNutrimentsCollectionFmRetentionNutriments : fmAliments.getFmRetentionNutrimentsCollection()) {
                FmAliments oldAlimentOfFmRetentionNutrimentsCollectionFmRetentionNutriments = fmRetentionNutrimentsCollectionFmRetentionNutriments.getAliment();
                fmRetentionNutrimentsCollectionFmRetentionNutriments.setAliment(fmAliments);
                fmRetentionNutrimentsCollectionFmRetentionNutriments = em.merge(fmRetentionNutrimentsCollectionFmRetentionNutriments);
                if (oldAlimentOfFmRetentionNutrimentsCollectionFmRetentionNutriments != null) {
                    oldAlimentOfFmRetentionNutrimentsCollectionFmRetentionNutriments.getFmRetentionNutrimentsCollection().remove(fmRetentionNutrimentsCollectionFmRetentionNutriments);
                    oldAlimentOfFmRetentionNutrimentsCollectionFmRetentionNutriments = em.merge(oldAlimentOfFmRetentionNutrimentsCollectionFmRetentionNutriments);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FmAliments fmAliments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FmAliments persistentFmAliments = em.find(FmAliments.class, fmAliments.getId());
            FmGroupeAliment groupeOld = persistentFmAliments.getGroupe();
            FmGroupeAliment groupeNew = fmAliments.getGroupe();
            Collection<FmRetentionMineraux> fmRetentionMinerauxCollectionOld = persistentFmAliments.getFmRetentionMinerauxCollection();
            Collection<FmRetentionMineraux> fmRetentionMinerauxCollectionNew = fmAliments.getFmRetentionMinerauxCollection();
            Collection<FmRetentionVitamines> fmRetentionVitaminesCollectionOld = persistentFmAliments.getFmRetentionVitaminesCollection();
            Collection<FmRetentionVitamines> fmRetentionVitaminesCollectionNew = fmAliments.getFmRetentionVitaminesCollection();
            Collection<FmRetentionNutriments> fmRetentionNutrimentsCollectionOld = persistentFmAliments.getFmRetentionNutrimentsCollection();
            Collection<FmRetentionNutriments> fmRetentionNutrimentsCollectionNew = fmAliments.getFmRetentionNutrimentsCollection();
            if (groupeNew != null) {
                groupeNew = em.getReference(groupeNew.getClass(), groupeNew.getId());
                fmAliments.setGroupe(groupeNew);
            }
            Collection<FmRetentionMineraux> attachedFmRetentionMinerauxCollectionNew = new ArrayList<FmRetentionMineraux>();
            for (FmRetentionMineraux fmRetentionMinerauxCollectionNewFmRetentionMinerauxToAttach : fmRetentionMinerauxCollectionNew) {
                fmRetentionMinerauxCollectionNewFmRetentionMinerauxToAttach = em.getReference(fmRetentionMinerauxCollectionNewFmRetentionMinerauxToAttach.getClass(), fmRetentionMinerauxCollectionNewFmRetentionMinerauxToAttach.getId());
                attachedFmRetentionMinerauxCollectionNew.add(fmRetentionMinerauxCollectionNewFmRetentionMinerauxToAttach);
            }
            fmRetentionMinerauxCollectionNew = attachedFmRetentionMinerauxCollectionNew;
            fmAliments.setFmRetentionMinerauxCollection(fmRetentionMinerauxCollectionNew);
            Collection<FmRetentionVitamines> attachedFmRetentionVitaminesCollectionNew = new ArrayList<FmRetentionVitamines>();
            for (FmRetentionVitamines fmRetentionVitaminesCollectionNewFmRetentionVitaminesToAttach : fmRetentionVitaminesCollectionNew) {
                fmRetentionVitaminesCollectionNewFmRetentionVitaminesToAttach = em.getReference(fmRetentionVitaminesCollectionNewFmRetentionVitaminesToAttach.getClass(), fmRetentionVitaminesCollectionNewFmRetentionVitaminesToAttach.getId());
                attachedFmRetentionVitaminesCollectionNew.add(fmRetentionVitaminesCollectionNewFmRetentionVitaminesToAttach);
            }
            fmRetentionVitaminesCollectionNew = attachedFmRetentionVitaminesCollectionNew;
            fmAliments.setFmRetentionVitaminesCollection(fmRetentionVitaminesCollectionNew);
            Collection<FmRetentionNutriments> attachedFmRetentionNutrimentsCollectionNew = new ArrayList<FmRetentionNutriments>();
            for (FmRetentionNutriments fmRetentionNutrimentsCollectionNewFmRetentionNutrimentsToAttach : fmRetentionNutrimentsCollectionNew) {
                fmRetentionNutrimentsCollectionNewFmRetentionNutrimentsToAttach = em.getReference(fmRetentionNutrimentsCollectionNewFmRetentionNutrimentsToAttach.getClass(), fmRetentionNutrimentsCollectionNewFmRetentionNutrimentsToAttach.getId());
                attachedFmRetentionNutrimentsCollectionNew.add(fmRetentionNutrimentsCollectionNewFmRetentionNutrimentsToAttach);
            }
            fmRetentionNutrimentsCollectionNew = attachedFmRetentionNutrimentsCollectionNew;
            fmAliments.setFmRetentionNutrimentsCollection(fmRetentionNutrimentsCollectionNew);
            fmAliments = em.merge(fmAliments);
            if (groupeOld != null && !groupeOld.equals(groupeNew)) {
                groupeOld.getFmAlimentsCollection().remove(fmAliments);
                groupeOld = em.merge(groupeOld);
            }
            if (groupeNew != null && !groupeNew.equals(groupeOld)) {
                groupeNew.getFmAlimentsCollection().add(fmAliments);
                groupeNew = em.merge(groupeNew);
            }
            for (FmRetentionMineraux fmRetentionMinerauxCollectionOldFmRetentionMineraux : fmRetentionMinerauxCollectionOld) {
                if (!fmRetentionMinerauxCollectionNew.contains(fmRetentionMinerauxCollectionOldFmRetentionMineraux)) {
                    fmRetentionMinerauxCollectionOldFmRetentionMineraux.setAliment(null);
                    fmRetentionMinerauxCollectionOldFmRetentionMineraux = em.merge(fmRetentionMinerauxCollectionOldFmRetentionMineraux);
                }
            }
            for (FmRetentionMineraux fmRetentionMinerauxCollectionNewFmRetentionMineraux : fmRetentionMinerauxCollectionNew) {
                if (!fmRetentionMinerauxCollectionOld.contains(fmRetentionMinerauxCollectionNewFmRetentionMineraux)) {
                    FmAliments oldAlimentOfFmRetentionMinerauxCollectionNewFmRetentionMineraux = fmRetentionMinerauxCollectionNewFmRetentionMineraux.getAliment();
                    fmRetentionMinerauxCollectionNewFmRetentionMineraux.setAliment(fmAliments);
                    fmRetentionMinerauxCollectionNewFmRetentionMineraux = em.merge(fmRetentionMinerauxCollectionNewFmRetentionMineraux);
                    if (oldAlimentOfFmRetentionMinerauxCollectionNewFmRetentionMineraux != null && !oldAlimentOfFmRetentionMinerauxCollectionNewFmRetentionMineraux.equals(fmAliments)) {
                        oldAlimentOfFmRetentionMinerauxCollectionNewFmRetentionMineraux.getFmRetentionMinerauxCollection().remove(fmRetentionMinerauxCollectionNewFmRetentionMineraux);
                        oldAlimentOfFmRetentionMinerauxCollectionNewFmRetentionMineraux = em.merge(oldAlimentOfFmRetentionMinerauxCollectionNewFmRetentionMineraux);
                    }
                }
            }
            for (FmRetentionVitamines fmRetentionVitaminesCollectionOldFmRetentionVitamines : fmRetentionVitaminesCollectionOld) {
                if (!fmRetentionVitaminesCollectionNew.contains(fmRetentionVitaminesCollectionOldFmRetentionVitamines)) {
                    fmRetentionVitaminesCollectionOldFmRetentionVitamines.setAliment(null);
                    fmRetentionVitaminesCollectionOldFmRetentionVitamines = em.merge(fmRetentionVitaminesCollectionOldFmRetentionVitamines);
                }
            }
            for (FmRetentionVitamines fmRetentionVitaminesCollectionNewFmRetentionVitamines : fmRetentionVitaminesCollectionNew) {
                if (!fmRetentionVitaminesCollectionOld.contains(fmRetentionVitaminesCollectionNewFmRetentionVitamines)) {
                    FmAliments oldAlimentOfFmRetentionVitaminesCollectionNewFmRetentionVitamines = fmRetentionVitaminesCollectionNewFmRetentionVitamines.getAliment();
                    fmRetentionVitaminesCollectionNewFmRetentionVitamines.setAliment(fmAliments);
                    fmRetentionVitaminesCollectionNewFmRetentionVitamines = em.merge(fmRetentionVitaminesCollectionNewFmRetentionVitamines);
                    if (oldAlimentOfFmRetentionVitaminesCollectionNewFmRetentionVitamines != null && !oldAlimentOfFmRetentionVitaminesCollectionNewFmRetentionVitamines.equals(fmAliments)) {
                        oldAlimentOfFmRetentionVitaminesCollectionNewFmRetentionVitamines.getFmRetentionVitaminesCollection().remove(fmRetentionVitaminesCollectionNewFmRetentionVitamines);
                        oldAlimentOfFmRetentionVitaminesCollectionNewFmRetentionVitamines = em.merge(oldAlimentOfFmRetentionVitaminesCollectionNewFmRetentionVitamines);
                    }
                }
            }
            for (FmRetentionNutriments fmRetentionNutrimentsCollectionOldFmRetentionNutriments : fmRetentionNutrimentsCollectionOld) {
                if (!fmRetentionNutrimentsCollectionNew.contains(fmRetentionNutrimentsCollectionOldFmRetentionNutriments)) {
                    fmRetentionNutrimentsCollectionOldFmRetentionNutriments.setAliment(null);
                    fmRetentionNutrimentsCollectionOldFmRetentionNutriments = em.merge(fmRetentionNutrimentsCollectionOldFmRetentionNutriments);
                }
            }
            for (FmRetentionNutriments fmRetentionNutrimentsCollectionNewFmRetentionNutriments : fmRetentionNutrimentsCollectionNew) {
                if (!fmRetentionNutrimentsCollectionOld.contains(fmRetentionNutrimentsCollectionNewFmRetentionNutriments)) {
                    FmAliments oldAlimentOfFmRetentionNutrimentsCollectionNewFmRetentionNutriments = fmRetentionNutrimentsCollectionNewFmRetentionNutriments.getAliment();
                    fmRetentionNutrimentsCollectionNewFmRetentionNutriments.setAliment(fmAliments);
                    fmRetentionNutrimentsCollectionNewFmRetentionNutriments = em.merge(fmRetentionNutrimentsCollectionNewFmRetentionNutriments);
                    if (oldAlimentOfFmRetentionNutrimentsCollectionNewFmRetentionNutriments != null && !oldAlimentOfFmRetentionNutrimentsCollectionNewFmRetentionNutriments.equals(fmAliments)) {
                        oldAlimentOfFmRetentionNutrimentsCollectionNewFmRetentionNutriments.getFmRetentionNutrimentsCollection().remove(fmRetentionNutrimentsCollectionNewFmRetentionNutriments);
                        oldAlimentOfFmRetentionNutrimentsCollectionNewFmRetentionNutriments = em.merge(oldAlimentOfFmRetentionNutrimentsCollectionNewFmRetentionNutriments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fmAliments.getId();
                if (findFmAliments(id) == null) {
                    throw new NonexistentEntityException("The fmAliments with id " + id + " no longer exists.");
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
            FmAliments fmAliments;
            try {
                fmAliments = em.getReference(FmAliments.class, id);
                fmAliments.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fmAliments with id " + id + " no longer exists.", enfe);
            }
            FmGroupeAliment groupe = fmAliments.getGroupe();
            if (groupe != null) {
                groupe.getFmAlimentsCollection().remove(fmAliments);
                groupe = em.merge(groupe);
            }
            Collection<FmRetentionMineraux> fmRetentionMinerauxCollection = fmAliments.getFmRetentionMinerauxCollection();
            for (FmRetentionMineraux fmRetentionMinerauxCollectionFmRetentionMineraux : fmRetentionMinerauxCollection) {
                fmRetentionMinerauxCollectionFmRetentionMineraux.setAliment(null);
                fmRetentionMinerauxCollectionFmRetentionMineraux = em.merge(fmRetentionMinerauxCollectionFmRetentionMineraux);
            }
            Collection<FmRetentionVitamines> fmRetentionVitaminesCollection = fmAliments.getFmRetentionVitaminesCollection();
            for (FmRetentionVitamines fmRetentionVitaminesCollectionFmRetentionVitamines : fmRetentionVitaminesCollection) {
                fmRetentionVitaminesCollectionFmRetentionVitamines.setAliment(null);
                fmRetentionVitaminesCollectionFmRetentionVitamines = em.merge(fmRetentionVitaminesCollectionFmRetentionVitamines);
            }
            Collection<FmRetentionNutriments> fmRetentionNutrimentsCollection = fmAliments.getFmRetentionNutrimentsCollection();
            for (FmRetentionNutriments fmRetentionNutrimentsCollectionFmRetentionNutriments : fmRetentionNutrimentsCollection) {
                fmRetentionNutrimentsCollectionFmRetentionNutriments.setAliment(null);
                fmRetentionNutrimentsCollectionFmRetentionNutriments = em.merge(fmRetentionNutrimentsCollectionFmRetentionNutriments);
            }
            em.remove(fmAliments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FmAliments> findFmAlimentsEntities() {
        return findFmAlimentsEntities(true, -1, -1);
    }

    public List<FmAliments> findFmAlimentsEntities(int maxResults, int firstResult) {
        return findFmAlimentsEntities(false, maxResults, firstResult);
    }

    private List<FmAliments> findFmAlimentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FmAliments.class));
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

    public FmAliments findFmAliments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FmAliments.class, id);
        } finally {
            em.close();
        }
    }

    public int getFmAlimentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FmAliments> rt = cq.from(FmAliments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
