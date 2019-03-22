/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.NonexistentEntityException;
import app.model.Grupoalvo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.Tipocredito;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class GrupoalvoJpaController implements Serializable {

    public GrupoalvoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupoalvo grupoalvo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocredito idtipoCredito = grupoalvo.getIdtipoCredito();
            if (idtipoCredito != null) {
                idtipoCredito = em.getReference(idtipoCredito.getClass(), idtipoCredito.getIdcrecredito());
                grupoalvo.setIdtipoCredito(idtipoCredito);
            }
            em.persist(grupoalvo);
            if (idtipoCredito != null) {
                idtipoCredito.getGrupoalvoList().add(grupoalvo);
                idtipoCredito = em.merge(idtipoCredito);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupoalvo grupoalvo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupoalvo persistentGrupoalvo = em.find(Grupoalvo.class, grupoalvo.getIdgrupo());
            Tipocredito idtipoCreditoOld = persistentGrupoalvo.getIdtipoCredito();
            Tipocredito idtipoCreditoNew = grupoalvo.getIdtipoCredito();
            if (idtipoCreditoNew != null) {
                idtipoCreditoNew = em.getReference(idtipoCreditoNew.getClass(), idtipoCreditoNew.getIdcrecredito());
                grupoalvo.setIdtipoCredito(idtipoCreditoNew);
            }
            grupoalvo = em.merge(grupoalvo);
            if (idtipoCreditoOld != null && !idtipoCreditoOld.equals(idtipoCreditoNew)) {
                idtipoCreditoOld.getGrupoalvoList().remove(grupoalvo);
                idtipoCreditoOld = em.merge(idtipoCreditoOld);
            }
            if (idtipoCreditoNew != null && !idtipoCreditoNew.equals(idtipoCreditoOld)) {
                idtipoCreditoNew.getGrupoalvoList().add(grupoalvo);
                idtipoCreditoNew = em.merge(idtipoCreditoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupoalvo.getIdgrupo();
                if (findGrupoalvo(id) == null) {
                    throw new NonexistentEntityException("The grupoalvo with id " + id + " no longer exists.");
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
            Grupoalvo grupoalvo;
            try {
                grupoalvo = em.getReference(Grupoalvo.class, id);
                grupoalvo.getIdgrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoalvo with id " + id + " no longer exists.", enfe);
            }
            Tipocredito idtipoCredito = grupoalvo.getIdtipoCredito();
            if (idtipoCredito != null) {
                idtipoCredito.getGrupoalvoList().remove(grupoalvo);
                idtipoCredito = em.merge(idtipoCredito);
            }
            em.remove(grupoalvo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupoalvo> findGrupoalvoEntities() {
        return findGrupoalvoEntities(true, -1, -1);
    }

    public List<Grupoalvo> findGrupoalvoEntities(int maxResults, int firstResult) {
        return findGrupoalvoEntities(false, maxResults, firstResult);
    }

    private List<Grupoalvo> findGrupoalvoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupoalvo.class));
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

    public Grupoalvo findGrupoalvo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupoalvo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoalvoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupoalvo> rt = cq.from(Grupoalvo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
