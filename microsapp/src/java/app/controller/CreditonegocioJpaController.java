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
import app.model.Creditonegocio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class CreditonegocioJpaController implements Serializable {

    public CreditonegocioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Creditonegocio creditonegocio) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Credito creditoOrphanCheck = creditonegocio.getCredito();
        if (creditoOrphanCheck != null) {
            Creditonegocio oldCreditonegocioOfCredito = creditoOrphanCheck.getCreditonegocio();
            if (oldCreditonegocioOfCredito != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Credito " + creditoOrphanCheck + " already has an item of type Creditonegocio whose credito column cannot be null. Please make another selection for the credito field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credito credito = creditonegocio.getCredito();
            if (credito != null) {
                credito = em.getReference(credito.getClass(), credito.getIdcredito());
                creditonegocio.setCredito(credito);
            }
            em.persist(creditonegocio);
            if (credito != null) {
                credito.setCreditonegocio(creditonegocio);
                credito = em.merge(credito);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCreditonegocio(creditonegocio.getIdcredito()) != null) {
                throw new PreexistingEntityException("Creditonegocio " + creditonegocio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Creditonegocio creditonegocio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditonegocio persistentCreditonegocio = em.find(Creditonegocio.class, creditonegocio.getIdcredito());
            Credito creditoOld = persistentCreditonegocio.getCredito();
            Credito creditoNew = creditonegocio.getCredito();
            List<String> illegalOrphanMessages = null;
            if (creditoNew != null && !creditoNew.equals(creditoOld)) {
                Creditonegocio oldCreditonegocioOfCredito = creditoNew.getCreditonegocio();
                if (oldCreditonegocioOfCredito != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Credito " + creditoNew + " already has an item of type Creditonegocio whose credito column cannot be null. Please make another selection for the credito field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creditoNew != null) {
                creditoNew = em.getReference(creditoNew.getClass(), creditoNew.getIdcredito());
                creditonegocio.setCredito(creditoNew);
            }
            creditonegocio = em.merge(creditonegocio);
            if (creditoOld != null && !creditoOld.equals(creditoNew)) {
                creditoOld.setCreditonegocio(null);
                creditoOld = em.merge(creditoOld);
            }
            if (creditoNew != null && !creditoNew.equals(creditoOld)) {
                creditoNew.setCreditonegocio(creditonegocio);
                creditoNew = em.merge(creditoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = creditonegocio.getIdcredito();
                if (findCreditonegocio(id) == null) {
                    throw new NonexistentEntityException("The creditonegocio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditonegocio creditonegocio;
            try {
                creditonegocio = em.getReference(Creditonegocio.class, id);
                creditonegocio.getIdcredito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditonegocio with id " + id + " no longer exists.", enfe);
            }
            Credito credito = creditonegocio.getCredito();
            if (credito != null) {
                credito.setCreditonegocio(null);
                credito = em.merge(credito);
            }
            em.remove(creditonegocio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Creditonegocio> findCreditonegocioEntities() {
        return findCreditonegocioEntities(true, -1, -1);
    }

    public List<Creditonegocio> findCreditonegocioEntities(int maxResults, int firstResult) {
        return findCreditonegocioEntities(false, maxResults, firstResult);
    }

    private List<Creditonegocio> findCreditonegocioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Creditonegocio.class));
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

    public Creditonegocio findCreditonegocio(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Creditonegocio.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditonegocioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Creditonegocio> rt = cq.from(Creditonegocio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
