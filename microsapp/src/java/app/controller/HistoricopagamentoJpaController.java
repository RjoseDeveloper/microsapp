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
import app.model.Historicopagamento;
import app.model.Modopagamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class HistoricopagamentoJpaController implements Serializable {

    public HistoricopagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historicopagamento historicopagamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credito idcredito = historicopagamento.getIdcredito();
            if (idcredito != null) {
                idcredito = em.getReference(idcredito.getClass(), idcredito.getIdcredito());
                historicopagamento.setIdcredito(idcredito);
            }
            Modopagamento idmodopagamento = historicopagamento.getIdmodopagamento();
            if (idmodopagamento != null) {
                idmodopagamento = em.getReference(idmodopagamento.getClass(), idmodopagamento.getIdmodo());
                historicopagamento.setIdmodopagamento(idmodopagamento);
            }
            em.persist(historicopagamento);
            if (idcredito != null) {
                idcredito.getHistoricopagamentoList().add(historicopagamento);
                idcredito = em.merge(idcredito);
            }
            if (idmodopagamento != null) {
                idmodopagamento.getHistoricopagamentoList().add(historicopagamento);
                idmodopagamento = em.merge(idmodopagamento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historicopagamento historicopagamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historicopagamento persistentHistoricopagamento = em.find(Historicopagamento.class, historicopagamento.getIdhistorico());
            Credito idcreditoOld = persistentHistoricopagamento.getIdcredito();
            Credito idcreditoNew = historicopagamento.getIdcredito();
            Modopagamento idmodopagamentoOld = persistentHistoricopagamento.getIdmodopagamento();
            Modopagamento idmodopagamentoNew = historicopagamento.getIdmodopagamento();
            if (idcreditoNew != null) {
                idcreditoNew = em.getReference(idcreditoNew.getClass(), idcreditoNew.getIdcredito());
                historicopagamento.setIdcredito(idcreditoNew);
            }
            if (idmodopagamentoNew != null) {
                idmodopagamentoNew = em.getReference(idmodopagamentoNew.getClass(), idmodopagamentoNew.getIdmodo());
                historicopagamento.setIdmodopagamento(idmodopagamentoNew);
            }
            historicopagamento = em.merge(historicopagamento);
            if (idcreditoOld != null && !idcreditoOld.equals(idcreditoNew)) {
                idcreditoOld.getHistoricopagamentoList().remove(historicopagamento);
                idcreditoOld = em.merge(idcreditoOld);
            }
            if (idcreditoNew != null && !idcreditoNew.equals(idcreditoOld)) {
                idcreditoNew.getHistoricopagamentoList().add(historicopagamento);
                idcreditoNew = em.merge(idcreditoNew);
            }
            if (idmodopagamentoOld != null && !idmodopagamentoOld.equals(idmodopagamentoNew)) {
                idmodopagamentoOld.getHistoricopagamentoList().remove(historicopagamento);
                idmodopagamentoOld = em.merge(idmodopagamentoOld);
            }
            if (idmodopagamentoNew != null && !idmodopagamentoNew.equals(idmodopagamentoOld)) {
                idmodopagamentoNew.getHistoricopagamentoList().add(historicopagamento);
                idmodopagamentoNew = em.merge(idmodopagamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historicopagamento.getIdhistorico();
                if (findHistoricopagamento(id) == null) {
                    throw new NonexistentEntityException("The historicopagamento with id " + id + " no longer exists.");
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
            Historicopagamento historicopagamento;
            try {
                historicopagamento = em.getReference(Historicopagamento.class, id);
                historicopagamento.getIdhistorico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicopagamento with id " + id + " no longer exists.", enfe);
            }
            Credito idcredito = historicopagamento.getIdcredito();
            if (idcredito != null) {
                idcredito.getHistoricopagamentoList().remove(historicopagamento);
                idcredito = em.merge(idcredito);
            }
            Modopagamento idmodopagamento = historicopagamento.getIdmodopagamento();
            if (idmodopagamento != null) {
                idmodopagamento.getHistoricopagamentoList().remove(historicopagamento);
                idmodopagamento = em.merge(idmodopagamento);
            }
            em.remove(historicopagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historicopagamento> findHistoricopagamentoEntities() {
        return findHistoricopagamentoEntities(true, -1, -1);
    }

    public List<Historicopagamento> findHistoricopagamentoEntities(int maxResults, int firstResult) {
        return findHistoricopagamentoEntities(false, maxResults, firstResult);
    }

    private List<Historicopagamento> findHistoricopagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historicopagamento.class));
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

    public Historicopagamento findHistoricopagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historicopagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricopagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historicopagamento> rt = cq.from(Historicopagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
