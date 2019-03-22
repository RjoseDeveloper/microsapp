/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.IllegalOrphanException;
import app.controller.exceptions.NonexistentEntityException;
import app.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.Credito;
import app.model.Creditovip;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class CreditovipJpaController implements Serializable {

    public CreditovipJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Creditovip creditovip) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Credito creditoOrphanCheck = creditovip.getCredito();
        if (creditoOrphanCheck != null) {
            Creditovip oldCreditovipOfCredito = creditoOrphanCheck.getCreditovip();
            if (oldCreditovipOfCredito != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Credito " + creditoOrphanCheck + " already has an item of type Creditovip whose credito column cannot be null. Please make another selection for the credito field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credito credito = creditovip.getCredito();
            if (credito != null) {
                credito = em.getReference(credito.getClass(), credito.getIdcredito());
                creditovip.setCredito(credito);
            }
            em.persist(creditovip);
            if (credito != null) {
                credito.setCreditovip(creditovip);
                credito = em.merge(credito);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCreditovip(creditovip.getIdcredito()) != null) {
                throw new PreexistingEntityException("Creditovip " + creditovip + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Creditovip creditovip) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditovip persistentCreditovip = em.find(Creditovip.class, creditovip.getIdcredito());
            Credito creditoOld = persistentCreditovip.getCredito();
            Credito creditoNew = creditovip.getCredito();
            List<String> illegalOrphanMessages = null;
            if (creditoNew != null && !creditoNew.equals(creditoOld)) {
                Creditovip oldCreditovipOfCredito = creditoNew.getCreditovip();
                if (oldCreditovipOfCredito != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Credito " + creditoNew + " already has an item of type Creditovip whose credito column cannot be null. Please make another selection for the credito field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creditoNew != null) {
                creditoNew = em.getReference(creditoNew.getClass(), creditoNew.getIdcredito());
                creditovip.setCredito(creditoNew);
            }
            creditovip = em.merge(creditovip);
            if (creditoOld != null && !creditoOld.equals(creditoNew)) {
                creditoOld.setCreditovip(null);
                creditoOld = em.merge(creditoOld);
            }
            if (creditoNew != null && !creditoNew.equals(creditoOld)) {
                creditoNew.setCreditovip(creditovip);
                creditoNew = em.merge(creditoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditovip.getIdcredito();
                if (findCreditovip(id) == null) {
                    throw new NonexistentEntityException("The creditovip with id " + id + " no longer exists.");
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
            Creditovip creditovip;
            try {
                creditovip = em.getReference(Creditovip.class, id);
                creditovip.getIdcredito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditovip with id " + id + " no longer exists.", enfe);
            }
            Credito credito = creditovip.getCredito();
            if (credito != null) {
                credito.setCreditovip(null);
                credito = em.merge(credito);
            }
            em.remove(creditovip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Creditovip> findCreditovipEntities() {
        return findCreditovipEntities(true, -1, -1);
    }

    public List<Creditovip> findCreditovipEntities(int maxResults, int firstResult) {
        return findCreditovipEntities(false, maxResults, firstResult);
    }

    private List<Creditovip> findCreditovipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Creditovip.class));
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

    public Creditovip findCreditovip(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Creditovip.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditovipCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Creditovip> rt = cq.from(Creditovip.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
