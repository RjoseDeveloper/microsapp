/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.IllegalOrphanException;
import app.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.Historicopagamento;
import app.model.Modopagamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class ModopagamentoJpaController implements Serializable {

    public ModopagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modopagamento modopagamento) {
        if (modopagamento.getHistoricopagamentoList() == null) {
            modopagamento.setHistoricopagamentoList(new ArrayList<Historicopagamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historicopagamento> attachedHistoricopagamentoList = new ArrayList<Historicopagamento>();
            for (Historicopagamento historicopagamentoListHistoricopagamentoToAttach : modopagamento.getHistoricopagamentoList()) {
                historicopagamentoListHistoricopagamentoToAttach = em.getReference(historicopagamentoListHistoricopagamentoToAttach.getClass(), historicopagamentoListHistoricopagamentoToAttach.getIdhistorico());
                attachedHistoricopagamentoList.add(historicopagamentoListHistoricopagamentoToAttach);
            }
            modopagamento.setHistoricopagamentoList(attachedHistoricopagamentoList);
            em.persist(modopagamento);
            for (Historicopagamento historicopagamentoListHistoricopagamento : modopagamento.getHistoricopagamentoList()) {
                Modopagamento oldIdmodopagamentoOfHistoricopagamentoListHistoricopagamento = historicopagamentoListHistoricopagamento.getIdmodopagamento();
                historicopagamentoListHistoricopagamento.setIdmodopagamento(modopagamento);
                historicopagamentoListHistoricopagamento = em.merge(historicopagamentoListHistoricopagamento);
                if (oldIdmodopagamentoOfHistoricopagamentoListHistoricopagamento != null) {
                    oldIdmodopagamentoOfHistoricopagamentoListHistoricopagamento.getHistoricopagamentoList().remove(historicopagamentoListHistoricopagamento);
                    oldIdmodopagamentoOfHistoricopagamentoListHistoricopagamento = em.merge(oldIdmodopagamentoOfHistoricopagamentoListHistoricopagamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modopagamento modopagamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modopagamento persistentModopagamento = em.find(Modopagamento.class, modopagamento.getIdmodo());
            List<Historicopagamento> historicopagamentoListOld = persistentModopagamento.getHistoricopagamentoList();
            List<Historicopagamento> historicopagamentoListNew = modopagamento.getHistoricopagamentoList();
            List<String> illegalOrphanMessages = null;
            for (Historicopagamento historicopagamentoListOldHistoricopagamento : historicopagamentoListOld) {
                if (!historicopagamentoListNew.contains(historicopagamentoListOldHistoricopagamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historicopagamento " + historicopagamentoListOldHistoricopagamento + " since its idmodopagamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historicopagamento> attachedHistoricopagamentoListNew = new ArrayList<Historicopagamento>();
            for (Historicopagamento historicopagamentoListNewHistoricopagamentoToAttach : historicopagamentoListNew) {
                historicopagamentoListNewHistoricopagamentoToAttach = em.getReference(historicopagamentoListNewHistoricopagamentoToAttach.getClass(), historicopagamentoListNewHistoricopagamentoToAttach.getIdhistorico());
                attachedHistoricopagamentoListNew.add(historicopagamentoListNewHistoricopagamentoToAttach);
            }
            historicopagamentoListNew = attachedHistoricopagamentoListNew;
            modopagamento.setHistoricopagamentoList(historicopagamentoListNew);
            modopagamento = em.merge(modopagamento);
            for (Historicopagamento historicopagamentoListNewHistoricopagamento : historicopagamentoListNew) {
                if (!historicopagamentoListOld.contains(historicopagamentoListNewHistoricopagamento)) {
                    Modopagamento oldIdmodopagamentoOfHistoricopagamentoListNewHistoricopagamento = historicopagamentoListNewHistoricopagamento.getIdmodopagamento();
                    historicopagamentoListNewHistoricopagamento.setIdmodopagamento(modopagamento);
                    historicopagamentoListNewHistoricopagamento = em.merge(historicopagamentoListNewHistoricopagamento);
                    if (oldIdmodopagamentoOfHistoricopagamentoListNewHistoricopagamento != null && !oldIdmodopagamentoOfHistoricopagamentoListNewHistoricopagamento.equals(modopagamento)) {
                        oldIdmodopagamentoOfHistoricopagamentoListNewHistoricopagamento.getHistoricopagamentoList().remove(historicopagamentoListNewHistoricopagamento);
                        oldIdmodopagamentoOfHistoricopagamentoListNewHistoricopagamento = em.merge(oldIdmodopagamentoOfHistoricopagamentoListNewHistoricopagamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = modopagamento.getIdmodo();
                if (findModopagamento(id) == null) {
                    throw new NonexistentEntityException("The modopagamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modopagamento modopagamento;
            try {
                modopagamento = em.getReference(Modopagamento.class, id);
                modopagamento.getIdmodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modopagamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historicopagamento> historicopagamentoListOrphanCheck = modopagamento.getHistoricopagamentoList();
            for (Historicopagamento historicopagamentoListOrphanCheckHistoricopagamento : historicopagamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Modopagamento (" + modopagamento + ") cannot be destroyed since the Historicopagamento " + historicopagamentoListOrphanCheckHistoricopagamento + " in its historicopagamentoList field has a non-nullable idmodopagamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(modopagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Modopagamento> findModopagamentoEntities() {
        return findModopagamentoEntities(true, -1, -1);
    }

    public List<Modopagamento> findModopagamentoEntities(int maxResults, int firstResult) {
        return findModopagamentoEntities(false, maxResults, firstResult);
    }

    private List<Modopagamento> findModopagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modopagamento.class));
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

    public Modopagamento findModopagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modopagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getModopagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modopagamento> rt = cq.from(Modopagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
