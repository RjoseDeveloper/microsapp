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
import app.model.Cliente;
import app.model.Funcionario;
import app.model.Instituicao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionario funcionario) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Cliente clienteOrphanCheck = funcionario.getCliente();
        if (clienteOrphanCheck != null) {
            Funcionario oldFuncionarioOfCliente = clienteOrphanCheck.getFuncionario();
            if (oldFuncionarioOfCliente != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Cliente " + clienteOrphanCheck + " already has an item of type Funcionario whose cliente column cannot be null. Please make another selection for the cliente field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = funcionario.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdcliente());
                funcionario.setCliente(cliente);
            }
            Instituicao idinstituicao = funcionario.getIdinstituicao();
            if (idinstituicao != null) {
                idinstituicao = em.getReference(idinstituicao.getClass(), idinstituicao.getIdinstituicao());
                funcionario.setIdinstituicao(idinstituicao);
            }
            em.persist(funcionario);
            if (cliente != null) {
                cliente.setFuncionario(funcionario);
                cliente = em.merge(cliente);
            }
            if (idinstituicao != null) {
                idinstituicao.getFuncionarioList().add(funcionario);
                idinstituicao = em.merge(idinstituicao);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario persistentFuncionario = em.find(Funcionario.class, funcionario.getIdfuncionario());
            Cliente clienteOld = persistentFuncionario.getCliente();
            Cliente clienteNew = funcionario.getCliente();
            Instituicao idinstituicaoOld = persistentFuncionario.getIdinstituicao();
            Instituicao idinstituicaoNew = funcionario.getIdinstituicao();
            List<String> illegalOrphanMessages = null;
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                Funcionario oldFuncionarioOfCliente = clienteNew.getFuncionario();
                if (oldFuncionarioOfCliente != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Cliente " + clienteNew + " already has an item of type Funcionario whose cliente column cannot be null. Please make another selection for the cliente field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdcliente());
                funcionario.setCliente(clienteNew);
            }
            if (idinstituicaoNew != null) {
                idinstituicaoNew = em.getReference(idinstituicaoNew.getClass(), idinstituicaoNew.getIdinstituicao());
                funcionario.setIdinstituicao(idinstituicaoNew);
            }
            funcionario = em.merge(funcionario);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.setFuncionario(null);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.setFuncionario(funcionario);
                clienteNew = em.merge(clienteNew);
            }
            if (idinstituicaoOld != null && !idinstituicaoOld.equals(idinstituicaoNew)) {
                idinstituicaoOld.getFuncionarioList().remove(funcionario);
                idinstituicaoOld = em.merge(idinstituicaoOld);
            }
            if (idinstituicaoNew != null && !idinstituicaoNew.equals(idinstituicaoOld)) {
                idinstituicaoNew.getFuncionarioList().add(funcionario);
                idinstituicaoNew = em.merge(idinstituicaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionario.getIdfuncionario();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
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
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getIdfuncionario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = funcionario.getCliente();
            if (cliente != null) {
                cliente.setFuncionario(null);
                cliente = em.merge(cliente);
            }
            Instituicao idinstituicao = funcionario.getIdinstituicao();
            if (idinstituicao != null) {
                idinstituicao.getFuncionarioList().remove(funcionario);
                idinstituicao = em.merge(idinstituicao);
            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
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

    public Funcionario findFuncionario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
