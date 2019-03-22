/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.controller.exceptions.IllegalOrphanException;
import app.controller.exceptions.NonexistentEntityException;
import app.controller.exceptions.PreexistingEntityException;
import app.model.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.model.Distrito;
import app.model.Estadocivil;
import app.model.Sexo;
import app.model.User;
import app.model.Funcionario;
import app.model.Credito;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (cliente.getCreditoList() == null) {
            cliente.setCreditoList(new ArrayList<Credito>());
        }
        List<String> illegalOrphanMessages = null;
        User userOrphanCheck = cliente.getUser();
        if (userOrphanCheck != null) {
            Cliente oldClienteOfUser = userOrphanCheck.getCliente();
            if (oldClienteOfUser != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The User " + userOrphanCheck + " already has an item of type Cliente whose user column cannot be null. Please make another selection for the user field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distrito iddistrito = cliente.getIddistrito();
            if (iddistrito != null) {
                iddistrito = em.getReference(iddistrito.getClass(), iddistrito.getIdidstrito());
                cliente.setIddistrito(iddistrito);
            }
            Estadocivil idestadocivil = cliente.getIdestadocivil();
            if (idestadocivil != null) {
                idestadocivil = em.getReference(idestadocivil.getClass(), idestadocivil.getIdestadocivil());
                cliente.setIdestadocivil(idestadocivil);
            }
            Sexo idsexo = cliente.getIdsexo();
            if (idsexo != null) {
                idsexo = em.getReference(idsexo.getClass(), idsexo.getIdsexo());
                cliente.setIdsexo(idsexo);
            }
            User user = cliente.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getUserId());
                cliente.setUser(user);
            }
            Funcionario funcionario = cliente.getFuncionario();
            if (funcionario != null) {
                funcionario = em.getReference(funcionario.getClass(), funcionario.getIdfuncionario());
                cliente.setFuncionario(funcionario);
            }
            List<Credito> attachedCreditoList = new ArrayList<Credito>();
            for (Credito creditoListCreditoToAttach : cliente.getCreditoList()) {
                creditoListCreditoToAttach = em.getReference(creditoListCreditoToAttach.getClass(), creditoListCreditoToAttach.getIdcredito());
                attachedCreditoList.add(creditoListCreditoToAttach);
            }
            cliente.setCreditoList(attachedCreditoList);
            em.persist(cliente);
            if (iddistrito != null) {
                iddistrito.getClienteList().add(cliente);
                iddistrito = em.merge(iddistrito);
            }
            if (idestadocivil != null) {
                idestadocivil.getClienteList().add(cliente);
                idestadocivil = em.merge(idestadocivil);
            }
            if (idsexo != null) {
                idsexo.getClienteList().add(cliente);
                idsexo = em.merge(idsexo);
            }
            if (user != null) {
                user.setCliente(cliente);
                user = em.merge(user);
            }
            if (funcionario != null) {
                Cliente oldClienteOfFuncionario = funcionario.getCliente();
                if (oldClienteOfFuncionario != null) {
                    oldClienteOfFuncionario.setFuncionario(null);
                    oldClienteOfFuncionario = em.merge(oldClienteOfFuncionario);
                }
                funcionario.setCliente(cliente);
                funcionario = em.merge(funcionario);
            }
            for (Credito creditoListCredito : cliente.getCreditoList()) {
                Cliente oldIdclienteOfCreditoListCredito = creditoListCredito.getIdcliente();
                creditoListCredito.setIdcliente(cliente);
                creditoListCredito = em.merge(creditoListCredito);
                if (oldIdclienteOfCreditoListCredito != null) {
                    oldIdclienteOfCreditoListCredito.getCreditoList().remove(creditoListCredito);
                    oldIdclienteOfCreditoListCredito = em.merge(oldIdclienteOfCreditoListCredito);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getIdcliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            Distrito iddistritoOld = persistentCliente.getIddistrito();
            Distrito iddistritoNew = cliente.getIddistrito();
            Estadocivil idestadocivilOld = persistentCliente.getIdestadocivil();
            Estadocivil idestadocivilNew = cliente.getIdestadocivil();
            Sexo idsexoOld = persistentCliente.getIdsexo();
            Sexo idsexoNew = cliente.getIdsexo();
            User userOld = persistentCliente.getUser();
            User userNew = cliente.getUser();
            Funcionario funcionarioOld = persistentCliente.getFuncionario();
            Funcionario funcionarioNew = cliente.getFuncionario();
            List<Credito> creditoListOld = persistentCliente.getCreditoList();
            List<Credito> creditoListNew = cliente.getCreditoList();
            List<String> illegalOrphanMessages = null;
            if (userNew != null && !userNew.equals(userOld)) {
                Cliente oldClienteOfUser = userNew.getCliente();
                if (oldClienteOfUser != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The User " + userNew + " already has an item of type Cliente whose user column cannot be null. Please make another selection for the user field.");
                }
            }
            if (funcionarioOld != null && !funcionarioOld.equals(funcionarioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Funcionario " + funcionarioOld + " since its cliente field is not nullable.");
            }
            for (Credito creditoListOldCredito : creditoListOld) {
                if (!creditoListNew.contains(creditoListOldCredito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Credito " + creditoListOldCredito + " since its idcliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (iddistritoNew != null) {
                iddistritoNew = em.getReference(iddistritoNew.getClass(), iddistritoNew.getIdidstrito());
                cliente.setIddistrito(iddistritoNew);
            }
            if (idestadocivilNew != null) {
                idestadocivilNew = em.getReference(idestadocivilNew.getClass(), idestadocivilNew.getIdestadocivil());
                cliente.setIdestadocivil(idestadocivilNew);
            }
            if (idsexoNew != null) {
                idsexoNew = em.getReference(idsexoNew.getClass(), idsexoNew.getIdsexo());
                cliente.setIdsexo(idsexoNew);
            }
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getUserId());
                cliente.setUser(userNew);
            }
            if (funcionarioNew != null) {
                funcionarioNew = em.getReference(funcionarioNew.getClass(), funcionarioNew.getIdfuncionario());
                cliente.setFuncionario(funcionarioNew);
            }
            List<Credito> attachedCreditoListNew = new ArrayList<Credito>();
            for (Credito creditoListNewCreditoToAttach : creditoListNew) {
                creditoListNewCreditoToAttach = em.getReference(creditoListNewCreditoToAttach.getClass(), creditoListNewCreditoToAttach.getIdcredito());
                attachedCreditoListNew.add(creditoListNewCreditoToAttach);
            }
            creditoListNew = attachedCreditoListNew;
            cliente.setCreditoList(creditoListNew);
            cliente = em.merge(cliente);
            if (iddistritoOld != null && !iddistritoOld.equals(iddistritoNew)) {
                iddistritoOld.getClienteList().remove(cliente);
                iddistritoOld = em.merge(iddistritoOld);
            }
            if (iddistritoNew != null && !iddistritoNew.equals(iddistritoOld)) {
                iddistritoNew.getClienteList().add(cliente);
                iddistritoNew = em.merge(iddistritoNew);
            }
            if (idestadocivilOld != null && !idestadocivilOld.equals(idestadocivilNew)) {
                idestadocivilOld.getClienteList().remove(cliente);
                idestadocivilOld = em.merge(idestadocivilOld);
            }
            if (idestadocivilNew != null && !idestadocivilNew.equals(idestadocivilOld)) {
                idestadocivilNew.getClienteList().add(cliente);
                idestadocivilNew = em.merge(idestadocivilNew);
            }
            if (idsexoOld != null && !idsexoOld.equals(idsexoNew)) {
                idsexoOld.getClienteList().remove(cliente);
                idsexoOld = em.merge(idsexoOld);
            }
            if (idsexoNew != null && !idsexoNew.equals(idsexoOld)) {
                idsexoNew.getClienteList().add(cliente);
                idsexoNew = em.merge(idsexoNew);
            }
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.setCliente(null);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.setCliente(cliente);
                userNew = em.merge(userNew);
            }
            if (funcionarioNew != null && !funcionarioNew.equals(funcionarioOld)) {
                Cliente oldClienteOfFuncionario = funcionarioNew.getCliente();
                if (oldClienteOfFuncionario != null) {
                    oldClienteOfFuncionario.setFuncionario(null);
                    oldClienteOfFuncionario = em.merge(oldClienteOfFuncionario);
                }
                funcionarioNew.setCliente(cliente);
                funcionarioNew = em.merge(funcionarioNew);
            }
            for (Credito creditoListNewCredito : creditoListNew) {
                if (!creditoListOld.contains(creditoListNewCredito)) {
                    Cliente oldIdclienteOfCreditoListNewCredito = creditoListNewCredito.getIdcliente();
                    creditoListNewCredito.setIdcliente(cliente);
                    creditoListNewCredito = em.merge(creditoListNewCredito);
                    if (oldIdclienteOfCreditoListNewCredito != null && !oldIdclienteOfCreditoListNewCredito.equals(cliente)) {
                        oldIdclienteOfCreditoListNewCredito.getCreditoList().remove(creditoListNewCredito);
                        oldIdclienteOfCreditoListNewCredito = em.merge(oldIdclienteOfCreditoListNewCredito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Funcionario funcionarioOrphanCheck = cliente.getFuncionario();
            if (funcionarioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Funcionario " + funcionarioOrphanCheck + " in its funcionario field has a non-nullable cliente field.");
            }
            List<Credito> creditoListOrphanCheck = cliente.getCreditoList();
            for (Credito creditoListOrphanCheckCredito : creditoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Credito " + creditoListOrphanCheckCredito + " in its creditoList field has a non-nullable idcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Distrito iddistrito = cliente.getIddistrito();
            if (iddistrito != null) {
                iddistrito.getClienteList().remove(cliente);
                iddistrito = em.merge(iddistrito);
            }
            Estadocivil idestadocivil = cliente.getIdestadocivil();
            if (idestadocivil != null) {
                idestadocivil.getClienteList().remove(cliente);
                idestadocivil = em.merge(idestadocivil);
            }
            Sexo idsexo = cliente.getIdsexo();
            if (idsexo != null) {
                idsexo.getClienteList().remove(cliente);
                idsexo = em.merge(idsexo);
            }
            User user = cliente.getUser();
            if (user != null) {
                user.setCliente(null);
                user = em.merge(user);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
