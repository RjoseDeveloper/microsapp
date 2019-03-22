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
import app.model.Creditoconsumo;
import java.util.ArrayList;
import java.util.List;
import app.model.Funcionario;
import app.model.Instituicao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class InstituicaoJpaController implements Serializable {

    public InstituicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Instituicao instituicao) {
        if (instituicao.getCreditoconsumoList() == null) {
            instituicao.setCreditoconsumoList(new ArrayList<Creditoconsumo>());
        }
        if (instituicao.getFuncionarioList() == null) {
            instituicao.setFuncionarioList(new ArrayList<Funcionario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distrito iddistrito = instituicao.getIddistrito();
            if (iddistrito != null) {
                iddistrito = em.getReference(iddistrito.getClass(), iddistrito.getIdidstrito());
                instituicao.setIddistrito(iddistrito);
            }
            List<Creditoconsumo> attachedCreditoconsumoList = new ArrayList<Creditoconsumo>();
            for (Creditoconsumo creditoconsumoListCreditoconsumoToAttach : instituicao.getCreditoconsumoList()) {
                creditoconsumoListCreditoconsumoToAttach = em.getReference(creditoconsumoListCreditoconsumoToAttach.getClass(), creditoconsumoListCreditoconsumoToAttach.getCreditoconsumoPK());
                attachedCreditoconsumoList.add(creditoconsumoListCreditoconsumoToAttach);
            }
            instituicao.setCreditoconsumoList(attachedCreditoconsumoList);
            List<Funcionario> attachedFuncionarioList = new ArrayList<Funcionario>();
            for (Funcionario funcionarioListFuncionarioToAttach : instituicao.getFuncionarioList()) {
                funcionarioListFuncionarioToAttach = em.getReference(funcionarioListFuncionarioToAttach.getClass(), funcionarioListFuncionarioToAttach.getIdfuncionario());
                attachedFuncionarioList.add(funcionarioListFuncionarioToAttach);
            }
            instituicao.setFuncionarioList(attachedFuncionarioList);
            em.persist(instituicao);
            if (iddistrito != null) {
                iddistrito.getInstituicaoList().add(instituicao);
                iddistrito = em.merge(iddistrito);
            }
            for (Creditoconsumo creditoconsumoListCreditoconsumo : instituicao.getCreditoconsumoList()) {
                Instituicao oldInstituicaoOfCreditoconsumoListCreditoconsumo = creditoconsumoListCreditoconsumo.getInstituicao();
                creditoconsumoListCreditoconsumo.setInstituicao(instituicao);
                creditoconsumoListCreditoconsumo = em.merge(creditoconsumoListCreditoconsumo);
                if (oldInstituicaoOfCreditoconsumoListCreditoconsumo != null) {
                    oldInstituicaoOfCreditoconsumoListCreditoconsumo.getCreditoconsumoList().remove(creditoconsumoListCreditoconsumo);
                    oldInstituicaoOfCreditoconsumoListCreditoconsumo = em.merge(oldInstituicaoOfCreditoconsumoListCreditoconsumo);
                }
            }
            for (Funcionario funcionarioListFuncionario : instituicao.getFuncionarioList()) {
                Instituicao oldIdinstituicaoOfFuncionarioListFuncionario = funcionarioListFuncionario.getIdinstituicao();
                funcionarioListFuncionario.setIdinstituicao(instituicao);
                funcionarioListFuncionario = em.merge(funcionarioListFuncionario);
                if (oldIdinstituicaoOfFuncionarioListFuncionario != null) {
                    oldIdinstituicaoOfFuncionarioListFuncionario.getFuncionarioList().remove(funcionarioListFuncionario);
                    oldIdinstituicaoOfFuncionarioListFuncionario = em.merge(oldIdinstituicaoOfFuncionarioListFuncionario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Instituicao instituicao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Instituicao persistentInstituicao = em.find(Instituicao.class, instituicao.getIdinstituicao());
            Distrito iddistritoOld = persistentInstituicao.getIddistrito();
            Distrito iddistritoNew = instituicao.getIddistrito();
            List<Creditoconsumo> creditoconsumoListOld = persistentInstituicao.getCreditoconsumoList();
            List<Creditoconsumo> creditoconsumoListNew = instituicao.getCreditoconsumoList();
            List<Funcionario> funcionarioListOld = persistentInstituicao.getFuncionarioList();
            List<Funcionario> funcionarioListNew = instituicao.getFuncionarioList();
            List<String> illegalOrphanMessages = null;
            for (Creditoconsumo creditoconsumoListOldCreditoconsumo : creditoconsumoListOld) {
                if (!creditoconsumoListNew.contains(creditoconsumoListOldCreditoconsumo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Creditoconsumo " + creditoconsumoListOldCreditoconsumo + " since its instituicao field is not nullable.");
                }
            }
            for (Funcionario funcionarioListOldFuncionario : funcionarioListOld) {
                if (!funcionarioListNew.contains(funcionarioListOldFuncionario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Funcionario " + funcionarioListOldFuncionario + " since its idinstituicao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (iddistritoNew != null) {
                iddistritoNew = em.getReference(iddistritoNew.getClass(), iddistritoNew.getIdidstrito());
                instituicao.setIddistrito(iddistritoNew);
            }
            List<Creditoconsumo> attachedCreditoconsumoListNew = new ArrayList<Creditoconsumo>();
            for (Creditoconsumo creditoconsumoListNewCreditoconsumoToAttach : creditoconsumoListNew) {
                creditoconsumoListNewCreditoconsumoToAttach = em.getReference(creditoconsumoListNewCreditoconsumoToAttach.getClass(), creditoconsumoListNewCreditoconsumoToAttach.getCreditoconsumoPK());
                attachedCreditoconsumoListNew.add(creditoconsumoListNewCreditoconsumoToAttach);
            }
            creditoconsumoListNew = attachedCreditoconsumoListNew;
            instituicao.setCreditoconsumoList(creditoconsumoListNew);
            List<Funcionario> attachedFuncionarioListNew = new ArrayList<Funcionario>();
            for (Funcionario funcionarioListNewFuncionarioToAttach : funcionarioListNew) {
                funcionarioListNewFuncionarioToAttach = em.getReference(funcionarioListNewFuncionarioToAttach.getClass(), funcionarioListNewFuncionarioToAttach.getIdfuncionario());
                attachedFuncionarioListNew.add(funcionarioListNewFuncionarioToAttach);
            }
            funcionarioListNew = attachedFuncionarioListNew;
            instituicao.setFuncionarioList(funcionarioListNew);
            instituicao = em.merge(instituicao);
            if (iddistritoOld != null && !iddistritoOld.equals(iddistritoNew)) {
                iddistritoOld.getInstituicaoList().remove(instituicao);
                iddistritoOld = em.merge(iddistritoOld);
            }
            if (iddistritoNew != null && !iddistritoNew.equals(iddistritoOld)) {
                iddistritoNew.getInstituicaoList().add(instituicao);
                iddistritoNew = em.merge(iddistritoNew);
            }
            for (Creditoconsumo creditoconsumoListNewCreditoconsumo : creditoconsumoListNew) {
                if (!creditoconsumoListOld.contains(creditoconsumoListNewCreditoconsumo)) {
                    Instituicao oldInstituicaoOfCreditoconsumoListNewCreditoconsumo = creditoconsumoListNewCreditoconsumo.getInstituicao();
                    creditoconsumoListNewCreditoconsumo.setInstituicao(instituicao);
                    creditoconsumoListNewCreditoconsumo = em.merge(creditoconsumoListNewCreditoconsumo);
                    if (oldInstituicaoOfCreditoconsumoListNewCreditoconsumo != null && !oldInstituicaoOfCreditoconsumoListNewCreditoconsumo.equals(instituicao)) {
                        oldInstituicaoOfCreditoconsumoListNewCreditoconsumo.getCreditoconsumoList().remove(creditoconsumoListNewCreditoconsumo);
                        oldInstituicaoOfCreditoconsumoListNewCreditoconsumo = em.merge(oldInstituicaoOfCreditoconsumoListNewCreditoconsumo);
                    }
                }
            }
            for (Funcionario funcionarioListNewFuncionario : funcionarioListNew) {
                if (!funcionarioListOld.contains(funcionarioListNewFuncionario)) {
                    Instituicao oldIdinstituicaoOfFuncionarioListNewFuncionario = funcionarioListNewFuncionario.getIdinstituicao();
                    funcionarioListNewFuncionario.setIdinstituicao(instituicao);
                    funcionarioListNewFuncionario = em.merge(funcionarioListNewFuncionario);
                    if (oldIdinstituicaoOfFuncionarioListNewFuncionario != null && !oldIdinstituicaoOfFuncionarioListNewFuncionario.equals(instituicao)) {
                        oldIdinstituicaoOfFuncionarioListNewFuncionario.getFuncionarioList().remove(funcionarioListNewFuncionario);
                        oldIdinstituicaoOfFuncionarioListNewFuncionario = em.merge(oldIdinstituicaoOfFuncionarioListNewFuncionario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = instituicao.getIdinstituicao();
                if (findInstituicao(id) == null) {
                    throw new NonexistentEntityException("The instituicao with id " + id + " no longer exists.");
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
            Instituicao instituicao;
            try {
                instituicao = em.getReference(Instituicao.class, id);
                instituicao.getIdinstituicao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The instituicao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Creditoconsumo> creditoconsumoListOrphanCheck = instituicao.getCreditoconsumoList();
            for (Creditoconsumo creditoconsumoListOrphanCheckCreditoconsumo : creditoconsumoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Instituicao (" + instituicao + ") cannot be destroyed since the Creditoconsumo " + creditoconsumoListOrphanCheckCreditoconsumo + " in its creditoconsumoList field has a non-nullable instituicao field.");
            }
            List<Funcionario> funcionarioListOrphanCheck = instituicao.getFuncionarioList();
            for (Funcionario funcionarioListOrphanCheckFuncionario : funcionarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Instituicao (" + instituicao + ") cannot be destroyed since the Funcionario " + funcionarioListOrphanCheckFuncionario + " in its funcionarioList field has a non-nullable idinstituicao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Distrito iddistrito = instituicao.getIddistrito();
            if (iddistrito != null) {
                iddistrito.getInstituicaoList().remove(instituicao);
                iddistrito = em.merge(iddistrito);
            }
            em.remove(instituicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Instituicao> findInstituicaoEntities() {
        return findInstituicaoEntities(true, -1, -1);
    }

    public List<Instituicao> findInstituicaoEntities(int maxResults, int firstResult) {
        return findInstituicaoEntities(false, maxResults, firstResult);
    }

    private List<Instituicao> findInstituicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Instituicao.class));
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

    public Instituicao findInstituicao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Instituicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstituicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Instituicao> rt = cq.from(Instituicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
