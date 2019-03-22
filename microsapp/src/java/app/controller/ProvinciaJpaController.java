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
import app.model.Distrito;
import app.model.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class ProvinciaJpaController implements Serializable {

    public ProvinciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provincia provincia) {
        if (provincia.getDistritoList() == null) {
            provincia.setDistritoList(new ArrayList<Distrito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Distrito> attachedDistritoList = new ArrayList<Distrito>();
            for (Distrito distritoListDistritoToAttach : provincia.getDistritoList()) {
                distritoListDistritoToAttach = em.getReference(distritoListDistritoToAttach.getClass(), distritoListDistritoToAttach.getIdidstrito());
                attachedDistritoList.add(distritoListDistritoToAttach);
            }
            provincia.setDistritoList(attachedDistritoList);
            em.persist(provincia);
            for (Distrito distritoListDistrito : provincia.getDistritoList()) {
                Provincia oldIdprovinciaOfDistritoListDistrito = distritoListDistrito.getIdprovincia();
                distritoListDistrito.setIdprovincia(provincia);
                distritoListDistrito = em.merge(distritoListDistrito);
                if (oldIdprovinciaOfDistritoListDistrito != null) {
                    oldIdprovinciaOfDistritoListDistrito.getDistritoList().remove(distritoListDistrito);
                    oldIdprovinciaOfDistritoListDistrito = em.merge(oldIdprovinciaOfDistritoListDistrito);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provincia provincia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find(Provincia.class, provincia.getIdprovincia());
            List<Distrito> distritoListOld = persistentProvincia.getDistritoList();
            List<Distrito> distritoListNew = provincia.getDistritoList();
            List<String> illegalOrphanMessages = null;
            for (Distrito distritoListOldDistrito : distritoListOld) {
                if (!distritoListNew.contains(distritoListOldDistrito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Distrito " + distritoListOldDistrito + " since its idprovincia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Distrito> attachedDistritoListNew = new ArrayList<Distrito>();
            for (Distrito distritoListNewDistritoToAttach : distritoListNew) {
                distritoListNewDistritoToAttach = em.getReference(distritoListNewDistritoToAttach.getClass(), distritoListNewDistritoToAttach.getIdidstrito());
                attachedDistritoListNew.add(distritoListNewDistritoToAttach);
            }
            distritoListNew = attachedDistritoListNew;
            provincia.setDistritoList(distritoListNew);
            provincia = em.merge(provincia);
            for (Distrito distritoListNewDistrito : distritoListNew) {
                if (!distritoListOld.contains(distritoListNewDistrito)) {
                    Provincia oldIdprovinciaOfDistritoListNewDistrito = distritoListNewDistrito.getIdprovincia();
                    distritoListNewDistrito.setIdprovincia(provincia);
                    distritoListNewDistrito = em.merge(distritoListNewDistrito);
                    if (oldIdprovinciaOfDistritoListNewDistrito != null && !oldIdprovinciaOfDistritoListNewDistrito.equals(provincia)) {
                        oldIdprovinciaOfDistritoListNewDistrito.getDistritoList().remove(distritoListNewDistrito);
                        oldIdprovinciaOfDistritoListNewDistrito = em.merge(oldIdprovinciaOfDistritoListNewDistrito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provincia.getIdprovincia();
                if (findProvincia(id) == null) {
                    throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.");
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
            Provincia provincia;
            try {
                provincia = em.getReference(Provincia.class, id);
                provincia.getIdprovincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Distrito> distritoListOrphanCheck = provincia.getDistritoList();
            for (Distrito distritoListOrphanCheckDistrito : distritoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provincia (" + provincia + ") cannot be destroyed since the Distrito " + distritoListOrphanCheckDistrito + " in its distritoList field has a non-nullable idprovincia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(provincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provincia> findProvinciaEntities() {
        return findProvinciaEntities(true, -1, -1);
    }

    public List<Provincia> findProvinciaEntities(int maxResults, int firstResult) {
        return findProvinciaEntities(false, maxResults, firstResult);
    }

    private List<Provincia> findProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provincia.class));
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

    public Provincia findProvincia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from(Provincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
