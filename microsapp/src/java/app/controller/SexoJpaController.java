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
import app.model.Cliente;
import app.model.Sexo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class SexoJpaController implements Serializable {

    public SexoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sexo sexo) {
        if (sexo.getClienteList() == null) {
            sexo.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : sexo.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            sexo.setClienteList(attachedClienteList);
            em.persist(sexo);
            for (Cliente clienteListCliente : sexo.getClienteList()) {
                Sexo oldIdsexoOfClienteListCliente = clienteListCliente.getIdsexo();
                clienteListCliente.setIdsexo(sexo);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldIdsexoOfClienteListCliente != null) {
                    oldIdsexoOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldIdsexoOfClienteListCliente = em.merge(oldIdsexoOfClienteListCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sexo sexo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sexo persistentSexo = em.find(Sexo.class, sexo.getIdsexo());
            List<Cliente> clienteListOld = persistentSexo.getClienteList();
            List<Cliente> clienteListNew = sexo.getClienteList();
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            sexo.setClienteList(clienteListNew);
            sexo = em.merge(sexo);
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setIdsexo(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Sexo oldIdsexoOfClienteListNewCliente = clienteListNewCliente.getIdsexo();
                    clienteListNewCliente.setIdsexo(sexo);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldIdsexoOfClienteListNewCliente != null && !oldIdsexoOfClienteListNewCliente.equals(sexo)) {
                        oldIdsexoOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldIdsexoOfClienteListNewCliente = em.merge(oldIdsexoOfClienteListNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sexo.getIdsexo();
                if (findSexo(id) == null) {
                    throw new NonexistentEntityException("The sexo with id " + id + " no longer exists.");
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
            Sexo sexo;
            try {
                sexo = em.getReference(Sexo.class, id);
                sexo.getIdsexo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sexo with id " + id + " no longer exists.", enfe);
            }
            List<Cliente> clienteList = sexo.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setIdsexo(null);
                clienteListCliente = em.merge(clienteListCliente);
            }
            em.remove(sexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sexo> findSexoEntities() {
        return findSexoEntities(true, -1, -1);
    }

    public List<Sexo> findSexoEntities(int maxResults, int firstResult) {
        return findSexoEntities(false, maxResults, firstResult);
    }

    private List<Sexo> findSexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sexo.class));
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

    public Sexo findSexo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sexo> rt = cq.from(Sexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
