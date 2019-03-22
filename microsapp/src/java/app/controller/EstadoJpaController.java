/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.Credito;
import app.model.Estado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class EstadoJpaController implements Serializable {

    public EstadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) {
        if (estado.getCreditoList() == null) {
            estado.setCreditoList(new ArrayList<Credito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Credito> attachedCreditoList = new ArrayList<Credito>();
            for (Credito creditoListCreditoToAttach : estado.getCreditoList()) {
                creditoListCreditoToAttach = em.getReference(creditoListCreditoToAttach.getClass(), creditoListCreditoToAttach.getIdcredito());
                attachedCreditoList.add(creditoListCreditoToAttach);
            }
            estado.setCreditoList(attachedCreditoList);
            em.persist(estado);
            for (Credito creditoListCredito : estado.getCreditoList()) {
                Estado oldIdestadoOfCreditoListCredito = creditoListCredito.getIdestado();
                creditoListCredito.setIdestado(estado);
                creditoListCredito = em.merge(creditoListCredito);
                if (oldIdestadoOfCreditoListCredito != null) {
                    oldIdestadoOfCreditoListCredito.getCreditoList().remove(creditoListCredito);
                    oldIdestadoOfCreditoListCredito = em.merge(oldIdestadoOfCreditoListCredito);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estado estado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado persistentEstado = em.find(Estado.class, estado.getIdestado());
            List<Credito> creditoListOld = persistentEstado.getCreditoList();
            List<Credito> creditoListNew = estado.getCreditoList();
            List<Credito> attachedCreditoListNew = new ArrayList<Credito>();
            for (Credito creditoListNewCreditoToAttach : creditoListNew) {
                creditoListNewCreditoToAttach = em.getReference(creditoListNewCreditoToAttach.getClass(), creditoListNewCreditoToAttach.getIdcredito());
                attachedCreditoListNew.add(creditoListNewCreditoToAttach);
            }
            creditoListNew = attachedCreditoListNew;
            estado.setCreditoList(creditoListNew);
            estado = em.merge(estado);
            for (Credito creditoListOldCredito : creditoListOld) {
                if (!creditoListNew.contains(creditoListOldCredito)) {
                    creditoListOldCredito.setIdestado(null);
                    creditoListOldCredito = em.merge(creditoListOldCredito);
                }
            }
            for (Credito creditoListNewCredito : creditoListNew) {
                if (!creditoListOld.contains(creditoListNewCredito)) {
                    Estado oldIdestadoOfCreditoListNewCredito = creditoListNewCredito.getIdestado();
                    creditoListNewCredito.setIdestado(estado);
                    creditoListNewCredito = em.merge(creditoListNewCredito);
                    if (oldIdestadoOfCreditoListNewCredito != null && !oldIdestadoOfCreditoListNewCredito.equals(estado)) {
                        oldIdestadoOfCreditoListNewCredito.getCreditoList().remove(creditoListNewCredito);
                        oldIdestadoOfCreditoListNewCredito = em.merge(oldIdestadoOfCreditoListNewCredito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estado.getIdestado();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            List<Credito> creditoList = estado.getCreditoList();
            for (Credito creditoListCredito : creditoList) {
                creditoListCredito.setIdestado(null);
                creditoListCredito = em.merge(creditoListCredito);
            }
            em.remove(estado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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

    public Estado findEstado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
