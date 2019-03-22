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
import app.model.Credito;
import java.util.ArrayList;
import java.util.List;
import app.model.Grupoalvo;
import app.model.Tipocredito;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class TipocreditoJpaController implements Serializable {

    public TipocreditoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipocredito tipocredito) {
        if (tipocredito.getCreditoList() == null) {
            tipocredito.setCreditoList(new ArrayList<Credito>());
        }
        if (tipocredito.getGrupoalvoList() == null) {
            tipocredito.setGrupoalvoList(new ArrayList<Grupoalvo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Credito> attachedCreditoList = new ArrayList<Credito>();
            for (Credito creditoListCreditoToAttach : tipocredito.getCreditoList()) {
                creditoListCreditoToAttach = em.getReference(creditoListCreditoToAttach.getClass(), creditoListCreditoToAttach.getIdcredito());
                attachedCreditoList.add(creditoListCreditoToAttach);
            }
            tipocredito.setCreditoList(attachedCreditoList);
            List<Grupoalvo> attachedGrupoalvoList = new ArrayList<Grupoalvo>();
            for (Grupoalvo grupoalvoListGrupoalvoToAttach : tipocredito.getGrupoalvoList()) {
                grupoalvoListGrupoalvoToAttach = em.getReference(grupoalvoListGrupoalvoToAttach.getClass(), grupoalvoListGrupoalvoToAttach.getIdgrupo());
                attachedGrupoalvoList.add(grupoalvoListGrupoalvoToAttach);
            }
            tipocredito.setGrupoalvoList(attachedGrupoalvoList);
            em.persist(tipocredito);
            for (Credito creditoListCredito : tipocredito.getCreditoList()) {
                Tipocredito oldIdtipocreditoOfCreditoListCredito = creditoListCredito.getIdtipocredito();
                creditoListCredito.setIdtipocredito(tipocredito);
                creditoListCredito = em.merge(creditoListCredito);
                if (oldIdtipocreditoOfCreditoListCredito != null) {
                    oldIdtipocreditoOfCreditoListCredito.getCreditoList().remove(creditoListCredito);
                    oldIdtipocreditoOfCreditoListCredito = em.merge(oldIdtipocreditoOfCreditoListCredito);
                }
            }
            for (Grupoalvo grupoalvoListGrupoalvo : tipocredito.getGrupoalvoList()) {
                Tipocredito oldIdtipoCreditoOfGrupoalvoListGrupoalvo = grupoalvoListGrupoalvo.getIdtipoCredito();
                grupoalvoListGrupoalvo.setIdtipoCredito(tipocredito);
                grupoalvoListGrupoalvo = em.merge(grupoalvoListGrupoalvo);
                if (oldIdtipoCreditoOfGrupoalvoListGrupoalvo != null) {
                    oldIdtipoCreditoOfGrupoalvoListGrupoalvo.getGrupoalvoList().remove(grupoalvoListGrupoalvo);
                    oldIdtipoCreditoOfGrupoalvoListGrupoalvo = em.merge(oldIdtipoCreditoOfGrupoalvoListGrupoalvo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipocredito tipocredito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocredito persistentTipocredito = em.find(Tipocredito.class, tipocredito.getIdcrecredito());
            List<Credito> creditoListOld = persistentTipocredito.getCreditoList();
            List<Credito> creditoListNew = tipocredito.getCreditoList();
            List<Grupoalvo> grupoalvoListOld = persistentTipocredito.getGrupoalvoList();
            List<Grupoalvo> grupoalvoListNew = tipocredito.getGrupoalvoList();
            List<String> illegalOrphanMessages = null;
            for (Grupoalvo grupoalvoListOldGrupoalvo : grupoalvoListOld) {
                if (!grupoalvoListNew.contains(grupoalvoListOldGrupoalvo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Grupoalvo " + grupoalvoListOldGrupoalvo + " since its idtipoCredito field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Credito> attachedCreditoListNew = new ArrayList<Credito>();
            for (Credito creditoListNewCreditoToAttach : creditoListNew) {
                creditoListNewCreditoToAttach = em.getReference(creditoListNewCreditoToAttach.getClass(), creditoListNewCreditoToAttach.getIdcredito());
                attachedCreditoListNew.add(creditoListNewCreditoToAttach);
            }
            creditoListNew = attachedCreditoListNew;
            tipocredito.setCreditoList(creditoListNew);
            List<Grupoalvo> attachedGrupoalvoListNew = new ArrayList<Grupoalvo>();
            for (Grupoalvo grupoalvoListNewGrupoalvoToAttach : grupoalvoListNew) {
                grupoalvoListNewGrupoalvoToAttach = em.getReference(grupoalvoListNewGrupoalvoToAttach.getClass(), grupoalvoListNewGrupoalvoToAttach.getIdgrupo());
                attachedGrupoalvoListNew.add(grupoalvoListNewGrupoalvoToAttach);
            }
            grupoalvoListNew = attachedGrupoalvoListNew;
            tipocredito.setGrupoalvoList(grupoalvoListNew);
            tipocredito = em.merge(tipocredito);
            for (Credito creditoListOldCredito : creditoListOld) {
                if (!creditoListNew.contains(creditoListOldCredito)) {
                    creditoListOldCredito.setIdtipocredito(null);
                    creditoListOldCredito = em.merge(creditoListOldCredito);
                }
            }
            for (Credito creditoListNewCredito : creditoListNew) {
                if (!creditoListOld.contains(creditoListNewCredito)) {
                    Tipocredito oldIdtipocreditoOfCreditoListNewCredito = creditoListNewCredito.getIdtipocredito();
                    creditoListNewCredito.setIdtipocredito(tipocredito);
                    creditoListNewCredito = em.merge(creditoListNewCredito);
                    if (oldIdtipocreditoOfCreditoListNewCredito != null && !oldIdtipocreditoOfCreditoListNewCredito.equals(tipocredito)) {
                        oldIdtipocreditoOfCreditoListNewCredito.getCreditoList().remove(creditoListNewCredito);
                        oldIdtipocreditoOfCreditoListNewCredito = em.merge(oldIdtipocreditoOfCreditoListNewCredito);
                    }
                }
            }
            for (Grupoalvo grupoalvoListNewGrupoalvo : grupoalvoListNew) {
                if (!grupoalvoListOld.contains(grupoalvoListNewGrupoalvo)) {
                    Tipocredito oldIdtipoCreditoOfGrupoalvoListNewGrupoalvo = grupoalvoListNewGrupoalvo.getIdtipoCredito();
                    grupoalvoListNewGrupoalvo.setIdtipoCredito(tipocredito);
                    grupoalvoListNewGrupoalvo = em.merge(grupoalvoListNewGrupoalvo);
                    if (oldIdtipoCreditoOfGrupoalvoListNewGrupoalvo != null && !oldIdtipoCreditoOfGrupoalvoListNewGrupoalvo.equals(tipocredito)) {
                        oldIdtipoCreditoOfGrupoalvoListNewGrupoalvo.getGrupoalvoList().remove(grupoalvoListNewGrupoalvo);
                        oldIdtipoCreditoOfGrupoalvoListNewGrupoalvo = em.merge(oldIdtipoCreditoOfGrupoalvoListNewGrupoalvo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipocredito.getIdcrecredito();
                if (findTipocredito(id) == null) {
                    throw new NonexistentEntityException("The tipocredito with id " + id + " no longer exists.");
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
            Tipocredito tipocredito;
            try {
                tipocredito = em.getReference(Tipocredito.class, id);
                tipocredito.getIdcrecredito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipocredito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Grupoalvo> grupoalvoListOrphanCheck = tipocredito.getGrupoalvoList();
            for (Grupoalvo grupoalvoListOrphanCheckGrupoalvo : grupoalvoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipocredito (" + tipocredito + ") cannot be destroyed since the Grupoalvo " + grupoalvoListOrphanCheckGrupoalvo + " in its grupoalvoList field has a non-nullable idtipoCredito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Credito> creditoList = tipocredito.getCreditoList();
            for (Credito creditoListCredito : creditoList) {
                creditoListCredito.setIdtipocredito(null);
                creditoListCredito = em.merge(creditoListCredito);
            }
            em.remove(tipocredito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipocredito> findTipocreditoEntities() {
        return findTipocreditoEntities(true, -1, -1);
    }

    public List<Tipocredito> findTipocreditoEntities(int maxResults, int firstResult) {
        return findTipocreditoEntities(false, maxResults, firstResult);
    }

    private List<Tipocredito> findTipocreditoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipocredito.class));
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

    public Tipocredito findTipocredito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipocredito.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipocreditoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipocredito> rt = cq.from(Tipocredito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
