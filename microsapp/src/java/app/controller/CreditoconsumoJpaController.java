/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.NonexistentEntityException;
import app.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.Credito;
import app.model.Creditoconsumo;
import app.model.CreditoconsumoPK;
import app.model.Instituicao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class CreditoconsumoJpaController implements Serializable {

    public CreditoconsumoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Creditoconsumo creditoconsumo) throws PreexistingEntityException, Exception {
        if (creditoconsumo.getCreditoconsumoPK() == null) {
            creditoconsumo.setCreditoconsumoPK(new CreditoconsumoPK());
        }
        creditoconsumo.getCreditoconsumoPK().setIdcredito(creditoconsumo.getCredito().getIdcredito().shortValue());
        creditoconsumo.getCreditoconsumoPK().setIdinstituicao(creditoconsumo.getInstituicao().getIdinstituicao().shortValue());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credito credito = creditoconsumo.getCredito();
            if (credito != null) {
                credito = em.getReference(credito.getClass(), credito.getIdcredito());
                creditoconsumo.setCredito(credito);
            }
            Instituicao instituicao = creditoconsumo.getInstituicao();
            if (instituicao != null) {
                instituicao = em.getReference(instituicao.getClass(), instituicao.getIdinstituicao());
                creditoconsumo.setInstituicao(instituicao);
            }
            em.persist(creditoconsumo);
            if (credito != null) {
                credito.getCreditoconsumoList().add(creditoconsumo);
                credito = em.merge(credito);
            }
            if (instituicao != null) {
                instituicao.getCreditoconsumoList().add(creditoconsumo);
                instituicao = em.merge(instituicao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCreditoconsumo(creditoconsumo.getCreditoconsumoPK()) != null) {
                throw new PreexistingEntityException("Creditoconsumo " + creditoconsumo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Creditoconsumo creditoconsumo) throws NonexistentEntityException, Exception {
        creditoconsumo.getCreditoconsumoPK().setIdcredito(creditoconsumo.getCredito().getIdcredito().shortValue());
        creditoconsumo.getCreditoconsumoPK().setIdinstituicao(creditoconsumo.getInstituicao().getIdinstituicao().shortValue());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditoconsumo persistentCreditoconsumo = em.find(Creditoconsumo.class, creditoconsumo.getCreditoconsumoPK());
            Credito creditoOld = persistentCreditoconsumo.getCredito();
            Credito creditoNew = creditoconsumo.getCredito();
            Instituicao instituicaoOld = persistentCreditoconsumo.getInstituicao();
            Instituicao instituicaoNew = creditoconsumo.getInstituicao();
            if (creditoNew != null) {
                creditoNew = em.getReference(creditoNew.getClass(), creditoNew.getIdcredito());
                creditoconsumo.setCredito(creditoNew);
            }
            if (instituicaoNew != null) {
                instituicaoNew = em.getReference(instituicaoNew.getClass(), instituicaoNew.getIdinstituicao());
                creditoconsumo.setInstituicao(instituicaoNew);
            }
            creditoconsumo = em.merge(creditoconsumo);
            if (creditoOld != null && !creditoOld.equals(creditoNew)) {
                creditoOld.getCreditoconsumoList().remove(creditoconsumo);
                creditoOld = em.merge(creditoOld);
            }
            if (creditoNew != null && !creditoNew.equals(creditoOld)) {
                creditoNew.getCreditoconsumoList().add(creditoconsumo);
                creditoNew = em.merge(creditoNew);
            }
            if (instituicaoOld != null && !instituicaoOld.equals(instituicaoNew)) {
                instituicaoOld.getCreditoconsumoList().remove(creditoconsumo);
                instituicaoOld = em.merge(instituicaoOld);
            }
            if (instituicaoNew != null && !instituicaoNew.equals(instituicaoOld)) {
                instituicaoNew.getCreditoconsumoList().add(creditoconsumo);
                instituicaoNew = em.merge(instituicaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CreditoconsumoPK id = creditoconsumo.getCreditoconsumoPK();
                if (findCreditoconsumo(id) == null) {
                    throw new NonexistentEntityException("The creditoconsumo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CreditoconsumoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditoconsumo creditoconsumo;
            try {
                creditoconsumo = em.getReference(Creditoconsumo.class, id);
                creditoconsumo.getCreditoconsumoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditoconsumo with id " + id + " no longer exists.", enfe);
            }
            Credito credito = creditoconsumo.getCredito();
            if (credito != null) {
                credito.getCreditoconsumoList().remove(creditoconsumo);
                credito = em.merge(credito);
            }
            Instituicao instituicao = creditoconsumo.getInstituicao();
            if (instituicao != null) {
                instituicao.getCreditoconsumoList().remove(creditoconsumo);
                instituicao = em.merge(instituicao);
            }
            em.remove(creditoconsumo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Creditoconsumo> findCreditoconsumoEntities() {
        return findCreditoconsumoEntities(true, -1, -1);
    }

    public List<Creditoconsumo> findCreditoconsumoEntities(int maxResults, int firstResult) {
        return findCreditoconsumoEntities(false, maxResults, firstResult);
    }

    private List<Creditoconsumo> findCreditoconsumoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Creditoconsumo.class));
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

    public Creditoconsumo findCreditoconsumo(CreditoconsumoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Creditoconsumo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditoconsumoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Creditoconsumo> rt = cq.from(Creditoconsumo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
