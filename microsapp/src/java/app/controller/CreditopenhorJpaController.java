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
import app.model.Creditopenhor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class CreditopenhorJpaController implements Serializable {

    public CreditopenhorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Creditopenhor creditopenhor) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Credito creditoOrphanCheck = creditopenhor.getCredito();
        if (creditoOrphanCheck != null) {
            Creditopenhor oldCreditopenhorOfCredito = creditoOrphanCheck.getCreditopenhor();
            if (oldCreditopenhorOfCredito != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Credito " + creditoOrphanCheck + " already has an item of type Creditopenhor whose credito column cannot be null. Please make another selection for the credito field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credito credito = creditopenhor.getCredito();
            if (credito != null) {
                credito = em.getReference(credito.getClass(), credito.getIdcredito());
                creditopenhor.setCredito(credito);
            }
            em.persist(creditopenhor);
            if (credito != null) {
                credito.setCreditopenhor(creditopenhor);
                credito = em.merge(credito);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCreditopenhor(creditopenhor.getIdcredito()) != null) {
                throw new PreexistingEntityException("Creditopenhor " + creditopenhor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Creditopenhor creditopenhor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditopenhor persistentCreditopenhor = em.find(Creditopenhor.class, creditopenhor.getIdcredito());
            Credito creditoOld = persistentCreditopenhor.getCredito();
            Credito creditoNew = creditopenhor.getCredito();
            List<String> illegalOrphanMessages = null;
            if (creditoNew != null && !creditoNew.equals(creditoOld)) {
                Creditopenhor oldCreditopenhorOfCredito = creditoNew.getCreditopenhor();
                if (oldCreditopenhorOfCredito != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Credito " + creditoNew + " already has an item of type Creditopenhor whose credito column cannot be null. Please make another selection for the credito field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creditoNew != null) {
                creditoNew = em.getReference(creditoNew.getClass(), creditoNew.getIdcredito());
                creditopenhor.setCredito(creditoNew);
            }
            creditopenhor = em.merge(creditopenhor);
            if (creditoOld != null && !creditoOld.equals(creditoNew)) {
                creditoOld.setCreditopenhor(null);
                creditoOld = em.merge(creditoOld);
            }
            if (creditoNew != null && !creditoNew.equals(creditoOld)) {
                creditoNew.setCreditopenhor(creditopenhor);
                creditoNew = em.merge(creditoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditopenhor.getIdcredito();
                if (findCreditopenhor(id) == null) {
                    throw new NonexistentEntityException("The creditopenhor with id " + id + " no longer exists.");
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
            Creditopenhor creditopenhor;
            try {
                creditopenhor = em.getReference(Creditopenhor.class, id);
                creditopenhor.getIdcredito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditopenhor with id " + id + " no longer exists.", enfe);
            }
            Credito credito = creditopenhor.getCredito();
            if (credito != null) {
                credito.setCreditopenhor(null);
                credito = em.merge(credito);
            }
            em.remove(creditopenhor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Creditopenhor> findCreditopenhorEntities() {
        return findCreditopenhorEntities(true, -1, -1);
    }

    public List<Creditopenhor> findCreditopenhorEntities(int maxResults, int firstResult) {
        return findCreditopenhorEntities(false, maxResults, firstResult);
    }

    private List<Creditopenhor> findCreditopenhorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Creditopenhor.class));
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

    public Creditopenhor findCreditopenhor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Creditopenhor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditopenhorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Creditopenhor> rt = cq.from(Creditopenhor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
