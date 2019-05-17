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
import app.model.Provincia;
import app.model.Instituicao;
import java.util.ArrayList;
import java.util.List;
import app.model.Cliente;
import app.model.Distrito;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class DistritoJpaController implements Serializable {

    public DistritoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Distrito distrito) {
        if (distrito.getInstituicaoList() == null) {
            distrito.setInstituicaoList(new ArrayList<Instituicao>());
        }
        if (distrito.getClienteList() == null) {
            distrito.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia idprovincia = distrito.getIdprovincia();
            if (idprovincia != null) {
                idprovincia = em.getReference(idprovincia.getClass(), idprovincia.getIdprovincia());
                distrito.setIdprovincia(idprovincia);
            }
            List<Instituicao> attachedInstituicaoList = new ArrayList<Instituicao>();
            for (Instituicao instituicaoListInstituicaoToAttach : distrito.getInstituicaoList()) {
                instituicaoListInstituicaoToAttach = em.getReference(instituicaoListInstituicaoToAttach.getClass(), instituicaoListInstituicaoToAttach.getIdinstituicao());
                attachedInstituicaoList.add(instituicaoListInstituicaoToAttach);
            }
            distrito.setInstituicaoList(attachedInstituicaoList);
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : distrito.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            distrito.setClienteList(attachedClienteList);
            em.persist(distrito);
            if (idprovincia != null) {
                idprovincia.getDistritoList().add(distrito);
                idprovincia = em.merge(idprovincia);
            }
            for (Instituicao instituicaoListInstituicao : distrito.getInstituicaoList()) {
                Distrito oldIddistritoOfInstituicaoListInstituicao = instituicaoListInstituicao.getIddistrito();
                instituicaoListInstituicao.setIddistrito(distrito);
                instituicaoListInstituicao = em.merge(instituicaoListInstituicao);
                if (oldIddistritoOfInstituicaoListInstituicao != null) {
                    oldIddistritoOfInstituicaoListInstituicao.getInstituicaoList().remove(instituicaoListInstituicao);
                    oldIddistritoOfInstituicaoListInstituicao = em.merge(oldIddistritoOfInstituicaoListInstituicao);
                }
            }
            for (Cliente clienteListCliente : distrito.getClienteList()) {
                Distrito oldIddistritoOfClienteListCliente = clienteListCliente.getIddistrito();
                clienteListCliente.setIddistrito(distrito);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldIddistritoOfClienteListCliente != null) {
                    oldIddistritoOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldIddistritoOfClienteListCliente = em.merge(oldIddistritoOfClienteListCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Distrito distrito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distrito persistentDistrito = em.find(Distrito.class, distrito.getIdidstrito());
            Provincia idprovinciaOld = persistentDistrito.getIdprovincia();
            Provincia idprovinciaNew = distrito.getIdprovincia();
            List<Instituicao> instituicaoListOld = persistentDistrito.getInstituicaoList();
            List<Instituicao> instituicaoListNew = distrito.getInstituicaoList();
            List<Cliente> clienteListOld = persistentDistrito.getClienteList();
            List<Cliente> clienteListNew = distrito.getClienteList();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its iddistrito field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idprovinciaNew != null) {
                idprovinciaNew = em.getReference(idprovinciaNew.getClass(), idprovinciaNew.getIdprovincia());
                distrito.setIdprovincia(idprovinciaNew);
            }
            List<Instituicao> attachedInstituicaoListNew = new ArrayList<Instituicao>();
            for (Instituicao instituicaoListNewInstituicaoToAttach : instituicaoListNew) {
                instituicaoListNewInstituicaoToAttach = em.getReference(instituicaoListNewInstituicaoToAttach.getClass(), instituicaoListNewInstituicaoToAttach.getIdinstituicao());
                attachedInstituicaoListNew.add(instituicaoListNewInstituicaoToAttach);
            }
            instituicaoListNew = attachedInstituicaoListNew;
            distrito.setInstituicaoList(instituicaoListNew);
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            distrito.setClienteList(clienteListNew);
            distrito = em.merge(distrito);
            if (idprovinciaOld != null && !idprovinciaOld.equals(idprovinciaNew)) {
                idprovinciaOld.getDistritoList().remove(distrito);
                idprovinciaOld = em.merge(idprovinciaOld);
            }
            if (idprovinciaNew != null && !idprovinciaNew.equals(idprovinciaOld)) {
                idprovinciaNew.getDistritoList().add(distrito);
                idprovinciaNew = em.merge(idprovinciaNew);
            }
            for (Instituicao instituicaoListOldInstituicao : instituicaoListOld) {
                if (!instituicaoListNew.contains(instituicaoListOldInstituicao)) {
                    instituicaoListOldInstituicao.setIddistrito(null);
                    instituicaoListOldInstituicao = em.merge(instituicaoListOldInstituicao);
                }
            }
            for (Instituicao instituicaoListNewInstituicao : instituicaoListNew) {
                if (!instituicaoListOld.contains(instituicaoListNewInstituicao)) {
                    Distrito oldIddistritoOfInstituicaoListNewInstituicao = instituicaoListNewInstituicao.getIddistrito();
                    instituicaoListNewInstituicao.setIddistrito(distrito);
                    instituicaoListNewInstituicao = em.merge(instituicaoListNewInstituicao);
                    if (oldIddistritoOfInstituicaoListNewInstituicao != null && !oldIddistritoOfInstituicaoListNewInstituicao.equals(distrito)) {
                        oldIddistritoOfInstituicaoListNewInstituicao.getInstituicaoList().remove(instituicaoListNewInstituicao);
                        oldIddistritoOfInstituicaoListNewInstituicao = em.merge(oldIddistritoOfInstituicaoListNewInstituicao);
                    }
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Distrito oldIddistritoOfClienteListNewCliente = clienteListNewCliente.getIddistrito();
                    clienteListNewCliente.setIddistrito(distrito);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldIddistritoOfClienteListNewCliente != null && !oldIddistritoOfClienteListNewCliente.equals(distrito)) {
                        oldIddistritoOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldIddistritoOfClienteListNewCliente = em.merge(oldIddistritoOfClienteListNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = distrito.getIdidstrito();
                if (findDistrito(id) == null) {
                    throw new NonexistentEntityException("The distrito with id " + id + " no longer exists.");
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
            Distrito distrito;
            try {
                distrito = em.getReference(Distrito.class, id);
                distrito.getIdidstrito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The distrito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cliente> clienteListOrphanCheck = distrito.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Distrito (" + distrito + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable iddistrito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Provincia idprovincia = distrito.getIdprovincia();
            if (idprovincia != null) {
                idprovincia.getDistritoList().remove(distrito);
                idprovincia = em.merge(idprovincia);
            }
            List<Instituicao> instituicaoList = distrito.getInstituicaoList();
            for (Instituicao instituicaoListInstituicao : instituicaoList) {
                instituicaoListInstituicao.setIddistrito(null);
                instituicaoListInstituicao = em.merge(instituicaoListInstituicao);
            }
            em.remove(distrito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Distrito> findDistritoEntities() {
        return findDistritoEntities(true, -1, -1);
    }

    public List<Distrito> findDistritoEntities(int maxResults, int firstResult) {
        return findDistritoEntities(false, maxResults, firstResult);
    }

    private List<Distrito> findDistritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Distrito.class));
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

    public Distrito findDistrito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Distrito.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Distrito> rt = cq.from(Distrito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
     public List<Distrito> buscarDistrito(String nome) {
        EntityManager em = getEntityManager();
        String name = "%" + nome + "%";
        try {
            Query query = em.createQuery(
                    "from Distrito distrito where distrito.descricao like :name");
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
