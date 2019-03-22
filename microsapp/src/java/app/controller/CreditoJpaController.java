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
import app.model.Creditonegocio;
import app.model.Creditopenhor;
import app.model.Creditovip;
import app.model.Cliente;
import app.model.Credito;
import app.model.Estado;
import app.model.Tipocredito;
import app.model.Historicopagamento;
import java.util.ArrayList;
import java.util.List;
import app.model.Creditoconsumo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class CreditoJpaController implements Serializable {

    public CreditoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Credito credito) throws IllegalOrphanException {
        if (credito.getHistoricopagamentoList() == null) {
            credito.setHistoricopagamentoList(new ArrayList<Historicopagamento>());
        }
        if (credito.getCreditoconsumoList() == null) {
            credito.setCreditoconsumoList(new ArrayList<Creditoconsumo>());
        }
        List<String> illegalOrphanMessages = null;
        Credito credito1OrphanCheck = credito.getCredito1();
        if (credito1OrphanCheck != null) {
            Credito oldCreditoOfCredito1 = credito1OrphanCheck.getCredito();
            if (oldCreditoOfCredito1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Credito " + credito1OrphanCheck + " already has an item of type Credito whose credito1 column cannot be null. Please make another selection for the credito1 field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditonegocio creditonegocio = credito.getCreditonegocio();
            if (creditonegocio != null) {
                creditonegocio = em.getReference(creditonegocio.getClass(), creditonegocio.getIdcredito());
                credito.setCreditonegocio(creditonegocio);
            }
            Creditopenhor creditopenhor = credito.getCreditopenhor();
            if (creditopenhor != null) {
                creditopenhor = em.getReference(creditopenhor.getClass(), creditopenhor.getIdcredito());
                credito.setCreditopenhor(creditopenhor);
            }
            Creditovip creditovip = credito.getCreditovip();
            if (creditovip != null) {
                creditovip = em.getReference(creditovip.getClass(), creditovip.getIdcredito());
                credito.setCreditovip(creditovip);
            }
            Cliente idcliente = credito.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                credito.setIdcliente(idcliente);
            }
            Credito creditoRel = credito.getCredito();
            if (creditoRel != null) {
                creditoRel = em.getReference(creditoRel.getClass(), creditoRel.getIdcredito());
                credito.setCredito(creditoRel);
            }
            Credito credito1 = credito.getCredito1();
            if (credito1 != null) {
                credito1 = em.getReference(credito1.getClass(), credito1.getIdcredito());
                credito.setCredito1(credito1);
            }
            Estado idestado = credito.getIdestado();
            if (idestado != null) {
                idestado = em.getReference(idestado.getClass(), idestado.getIdestado());
                credito.setIdestado(idestado);
            }
            Tipocredito idtipocredito = credito.getIdtipocredito();
            if (idtipocredito != null) {
                idtipocredito = em.getReference(idtipocredito.getClass(), idtipocredito.getIdcrecredito());
                credito.setIdtipocredito(idtipocredito);
            }
            List<Historicopagamento> attachedHistoricopagamentoList = new ArrayList<Historicopagamento>();
            for (Historicopagamento historicopagamentoListHistoricopagamentoToAttach : credito.getHistoricopagamentoList()) {
                historicopagamentoListHistoricopagamentoToAttach = em.getReference(historicopagamentoListHistoricopagamentoToAttach.getClass(), historicopagamentoListHistoricopagamentoToAttach.getIdhistorico());
                attachedHistoricopagamentoList.add(historicopagamentoListHistoricopagamentoToAttach);
            }
            credito.setHistoricopagamentoList(attachedHistoricopagamentoList);
            List<Creditoconsumo> attachedCreditoconsumoList = new ArrayList<Creditoconsumo>();
            for (Creditoconsumo creditoconsumoListCreditoconsumoToAttach : credito.getCreditoconsumoList()) {
                creditoconsumoListCreditoconsumoToAttach = em.getReference(creditoconsumoListCreditoconsumoToAttach.getClass(), creditoconsumoListCreditoconsumoToAttach.getCreditoconsumoPK());
                attachedCreditoconsumoList.add(creditoconsumoListCreditoconsumoToAttach);
            }
            credito.setCreditoconsumoList(attachedCreditoconsumoList);
            em.persist(credito);
            if (creditonegocio != null) {
                Credito oldCreditoOfCreditonegocio = creditonegocio.getCredito();
                if (oldCreditoOfCreditonegocio != null) {
                    oldCreditoOfCreditonegocio.setCreditonegocio(null);
                    oldCreditoOfCreditonegocio = em.merge(oldCreditoOfCreditonegocio);
                }
                creditonegocio.setCredito(credito);
                creditonegocio = em.merge(creditonegocio);
            }
            if (creditopenhor != null) {
                Credito oldCreditoOfCreditopenhor = creditopenhor.getCredito();
                if (oldCreditoOfCreditopenhor != null) {
                    oldCreditoOfCreditopenhor.setCreditopenhor(null);
                    oldCreditoOfCreditopenhor = em.merge(oldCreditoOfCreditopenhor);
                }
                creditopenhor.setCredito(credito);
                creditopenhor = em.merge(creditopenhor);
            }
            if (creditovip != null) {
                Credito oldCreditoOfCreditovip = creditovip.getCredito();
                if (oldCreditoOfCreditovip != null) {
                    oldCreditoOfCreditovip.setCreditovip(null);
                    oldCreditoOfCreditovip = em.merge(oldCreditoOfCreditovip);
                }
                creditovip.setCredito(credito);
                creditovip = em.merge(creditovip);
            }
            if (idcliente != null) {
                idcliente.getCreditoList().add(credito);
                idcliente = em.merge(idcliente);
            }
            if (creditoRel != null) {
                Credito oldCredito1OfCreditoRel = creditoRel.getCredito1();
                if (oldCredito1OfCreditoRel != null) {
                    oldCredito1OfCreditoRel.setCredito(null);
                    oldCredito1OfCreditoRel = em.merge(oldCredito1OfCreditoRel);
                }
                creditoRel.setCredito1(credito);
                creditoRel = em.merge(creditoRel);
            }
            if (credito1 != null) {
                credito1.setCredito(credito);
                credito1 = em.merge(credito1);
            }
            if (idestado != null) {
                idestado.getCreditoList().add(credito);
                idestado = em.merge(idestado);
            }
            if (idtipocredito != null) {
                idtipocredito.getCreditoList().add(credito);
                idtipocredito = em.merge(idtipocredito);
            }
            for (Historicopagamento historicopagamentoListHistoricopagamento : credito.getHistoricopagamentoList()) {
                Credito oldIdcreditoOfHistoricopagamentoListHistoricopagamento = historicopagamentoListHistoricopagamento.getIdcredito();
                historicopagamentoListHistoricopagamento.setIdcredito(credito);
                historicopagamentoListHistoricopagamento = em.merge(historicopagamentoListHistoricopagamento);
                if (oldIdcreditoOfHistoricopagamentoListHistoricopagamento != null) {
                    oldIdcreditoOfHistoricopagamentoListHistoricopagamento.getHistoricopagamentoList().remove(historicopagamentoListHistoricopagamento);
                    oldIdcreditoOfHistoricopagamentoListHistoricopagamento = em.merge(oldIdcreditoOfHistoricopagamentoListHistoricopagamento);
                }
            }
            for (Creditoconsumo creditoconsumoListCreditoconsumo : credito.getCreditoconsumoList()) {
                Credito oldCreditoOfCreditoconsumoListCreditoconsumo = creditoconsumoListCreditoconsumo.getCredito();
                creditoconsumoListCreditoconsumo.setCredito(credito);
                creditoconsumoListCreditoconsumo = em.merge(creditoconsumoListCreditoconsumo);
                if (oldCreditoOfCreditoconsumoListCreditoconsumo != null) {
                    oldCreditoOfCreditoconsumoListCreditoconsumo.getCreditoconsumoList().remove(creditoconsumoListCreditoconsumo);
                    oldCreditoOfCreditoconsumoListCreditoconsumo = em.merge(oldCreditoOfCreditoconsumoListCreditoconsumo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Credito credito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credito persistentCredito = em.find(Credito.class, credito.getIdcredito());
            Creditonegocio creditonegocioOld = persistentCredito.getCreditonegocio();
            Creditonegocio creditonegocioNew = credito.getCreditonegocio();
            Creditopenhor creditopenhorOld = persistentCredito.getCreditopenhor();
            Creditopenhor creditopenhorNew = credito.getCreditopenhor();
            Creditovip creditovipOld = persistentCredito.getCreditovip();
            Creditovip creditovipNew = credito.getCreditovip();
            Cliente idclienteOld = persistentCredito.getIdcliente();
            Cliente idclienteNew = credito.getIdcliente();
            Credito creditoRelOld = persistentCredito.getCredito();
            Credito creditoRelNew = credito.getCredito();
            Credito credito1Old = persistentCredito.getCredito1();
            Credito credito1New = credito.getCredito1();
            Estado idestadoOld = persistentCredito.getIdestado();
            Estado idestadoNew = credito.getIdestado();
            Tipocredito idtipocreditoOld = persistentCredito.getIdtipocredito();
            Tipocredito idtipocreditoNew = credito.getIdtipocredito();
            List<Historicopagamento> historicopagamentoListOld = persistentCredito.getHistoricopagamentoList();
            List<Historicopagamento> historicopagamentoListNew = credito.getHistoricopagamentoList();
            List<Creditoconsumo> creditoconsumoListOld = persistentCredito.getCreditoconsumoList();
            List<Creditoconsumo> creditoconsumoListNew = credito.getCreditoconsumoList();
            List<String> illegalOrphanMessages = null;
            if (creditonegocioOld != null && !creditonegocioOld.equals(creditonegocioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Creditonegocio " + creditonegocioOld + " since its credito field is not nullable.");
            }
            if (creditopenhorOld != null && !creditopenhorOld.equals(creditopenhorNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Creditopenhor " + creditopenhorOld + " since its credito field is not nullable.");
            }
            if (creditovipOld != null && !creditovipOld.equals(creditovipNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Creditovip " + creditovipOld + " since its credito field is not nullable.");
            }
            if (creditoRelOld != null && !creditoRelOld.equals(creditoRelNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Credito " + creditoRelOld + " since its credito1 field is not nullable.");
            }
            if (credito1New != null && !credito1New.equals(credito1Old)) {
                Credito oldCreditoOfCredito1 = credito1New.getCredito();
                if (oldCreditoOfCredito1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Credito " + credito1New + " already has an item of type Credito whose credito1 column cannot be null. Please make another selection for the credito1 field.");
                }
            }
            for (Historicopagamento historicopagamentoListOldHistoricopagamento : historicopagamentoListOld) {
                if (!historicopagamentoListNew.contains(historicopagamentoListOldHistoricopagamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historicopagamento " + historicopagamentoListOldHistoricopagamento + " since its idcredito field is not nullable.");
                }
            }
            for (Creditoconsumo creditoconsumoListOldCreditoconsumo : creditoconsumoListOld) {
                if (!creditoconsumoListNew.contains(creditoconsumoListOldCreditoconsumo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Creditoconsumo " + creditoconsumoListOldCreditoconsumo + " since its credito field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creditonegocioNew != null) {
                creditonegocioNew = em.getReference(creditonegocioNew.getClass(), creditonegocioNew.getIdcredito());
                credito.setCreditonegocio(creditonegocioNew);
            }
            if (creditopenhorNew != null) {
                creditopenhorNew = em.getReference(creditopenhorNew.getClass(), creditopenhorNew.getIdcredito());
                credito.setCreditopenhor(creditopenhorNew);
            }
            if (creditovipNew != null) {
                creditovipNew = em.getReference(creditovipNew.getClass(), creditovipNew.getIdcredito());
                credito.setCreditovip(creditovipNew);
            }
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                credito.setIdcliente(idclienteNew);
            }
            if (creditoRelNew != null) {
                creditoRelNew = em.getReference(creditoRelNew.getClass(), creditoRelNew.getIdcredito());
                credito.setCredito(creditoRelNew);
            }
            if (credito1New != null) {
                credito1New = em.getReference(credito1New.getClass(), credito1New.getIdcredito());
                credito.setCredito1(credito1New);
            }
            if (idestadoNew != null) {
                idestadoNew = em.getReference(idestadoNew.getClass(), idestadoNew.getIdestado());
                credito.setIdestado(idestadoNew);
            }
            if (idtipocreditoNew != null) {
                idtipocreditoNew = em.getReference(idtipocreditoNew.getClass(), idtipocreditoNew.getIdcrecredito());
                credito.setIdtipocredito(idtipocreditoNew);
            }
            List<Historicopagamento> attachedHistoricopagamentoListNew = new ArrayList<Historicopagamento>();
            for (Historicopagamento historicopagamentoListNewHistoricopagamentoToAttach : historicopagamentoListNew) {
                historicopagamentoListNewHistoricopagamentoToAttach = em.getReference(historicopagamentoListNewHistoricopagamentoToAttach.getClass(), historicopagamentoListNewHistoricopagamentoToAttach.getIdhistorico());
                attachedHistoricopagamentoListNew.add(historicopagamentoListNewHistoricopagamentoToAttach);
            }
            historicopagamentoListNew = attachedHistoricopagamentoListNew;
            credito.setHistoricopagamentoList(historicopagamentoListNew);
            List<Creditoconsumo> attachedCreditoconsumoListNew = new ArrayList<Creditoconsumo>();
            for (Creditoconsumo creditoconsumoListNewCreditoconsumoToAttach : creditoconsumoListNew) {
                creditoconsumoListNewCreditoconsumoToAttach = em.getReference(creditoconsumoListNewCreditoconsumoToAttach.getClass(), creditoconsumoListNewCreditoconsumoToAttach.getCreditoconsumoPK());
                attachedCreditoconsumoListNew.add(creditoconsumoListNewCreditoconsumoToAttach);
            }
            creditoconsumoListNew = attachedCreditoconsumoListNew;
            credito.setCreditoconsumoList(creditoconsumoListNew);
            credito = em.merge(credito);
            if (creditonegocioNew != null && !creditonegocioNew.equals(creditonegocioOld)) {
                Credito oldCreditoOfCreditonegocio = creditonegocioNew.getCredito();
                if (oldCreditoOfCreditonegocio != null) {
                    oldCreditoOfCreditonegocio.setCreditonegocio(null);
                    oldCreditoOfCreditonegocio = em.merge(oldCreditoOfCreditonegocio);
                }
                creditonegocioNew.setCredito(credito);
                creditonegocioNew = em.merge(creditonegocioNew);
            }
            if (creditopenhorNew != null && !creditopenhorNew.equals(creditopenhorOld)) {
                Credito oldCreditoOfCreditopenhor = creditopenhorNew.getCredito();
                if (oldCreditoOfCreditopenhor != null) {
                    oldCreditoOfCreditopenhor.setCreditopenhor(null);
                    oldCreditoOfCreditopenhor = em.merge(oldCreditoOfCreditopenhor);
                }
                creditopenhorNew.setCredito(credito);
                creditopenhorNew = em.merge(creditopenhorNew);
            }
            if (creditovipNew != null && !creditovipNew.equals(creditovipOld)) {
                Credito oldCreditoOfCreditovip = creditovipNew.getCredito();
                if (oldCreditoOfCreditovip != null) {
                    oldCreditoOfCreditovip.setCreditovip(null);
                    oldCreditoOfCreditovip = em.merge(oldCreditoOfCreditovip);
                }
                creditovipNew.setCredito(credito);
                creditovipNew = em.merge(creditovipNew);
            }
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getCreditoList().remove(credito);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getCreditoList().add(credito);
                idclienteNew = em.merge(idclienteNew);
            }
            if (creditoRelNew != null && !creditoRelNew.equals(creditoRelOld)) {
                Credito oldCredito1OfCreditoRel = creditoRelNew.getCredito1();
                if (oldCredito1OfCreditoRel != null) {
                    oldCredito1OfCreditoRel.setCredito(null);
                    oldCredito1OfCreditoRel = em.merge(oldCredito1OfCreditoRel);
                }
                creditoRelNew.setCredito1(credito);
                creditoRelNew = em.merge(creditoRelNew);
            }
            if (credito1Old != null && !credito1Old.equals(credito1New)) {
                credito1Old.setCredito(null);
                credito1Old = em.merge(credito1Old);
            }
            if (credito1New != null && !credito1New.equals(credito1Old)) {
                credito1New.setCredito(credito);
                credito1New = em.merge(credito1New);
            }
            if (idestadoOld != null && !idestadoOld.equals(idestadoNew)) {
                idestadoOld.getCreditoList().remove(credito);
                idestadoOld = em.merge(idestadoOld);
            }
            if (idestadoNew != null && !idestadoNew.equals(idestadoOld)) {
                idestadoNew.getCreditoList().add(credito);
                idestadoNew = em.merge(idestadoNew);
            }
            if (idtipocreditoOld != null && !idtipocreditoOld.equals(idtipocreditoNew)) {
                idtipocreditoOld.getCreditoList().remove(credito);
                idtipocreditoOld = em.merge(idtipocreditoOld);
            }
            if (idtipocreditoNew != null && !idtipocreditoNew.equals(idtipocreditoOld)) {
                idtipocreditoNew.getCreditoList().add(credito);
                idtipocreditoNew = em.merge(idtipocreditoNew);
            }
            for (Historicopagamento historicopagamentoListNewHistoricopagamento : historicopagamentoListNew) {
                if (!historicopagamentoListOld.contains(historicopagamentoListNewHistoricopagamento)) {
                    Credito oldIdcreditoOfHistoricopagamentoListNewHistoricopagamento = historicopagamentoListNewHistoricopagamento.getIdcredito();
                    historicopagamentoListNewHistoricopagamento.setIdcredito(credito);
                    historicopagamentoListNewHistoricopagamento = em.merge(historicopagamentoListNewHistoricopagamento);
                    if (oldIdcreditoOfHistoricopagamentoListNewHistoricopagamento != null && !oldIdcreditoOfHistoricopagamentoListNewHistoricopagamento.equals(credito)) {
                        oldIdcreditoOfHistoricopagamentoListNewHistoricopagamento.getHistoricopagamentoList().remove(historicopagamentoListNewHistoricopagamento);
                        oldIdcreditoOfHistoricopagamentoListNewHistoricopagamento = em.merge(oldIdcreditoOfHistoricopagamentoListNewHistoricopagamento);
                    }
                }
            }
            for (Creditoconsumo creditoconsumoListNewCreditoconsumo : creditoconsumoListNew) {
                if (!creditoconsumoListOld.contains(creditoconsumoListNewCreditoconsumo)) {
                    Credito oldCreditoOfCreditoconsumoListNewCreditoconsumo = creditoconsumoListNewCreditoconsumo.getCredito();
                    creditoconsumoListNewCreditoconsumo.setCredito(credito);
                    creditoconsumoListNewCreditoconsumo = em.merge(creditoconsumoListNewCreditoconsumo);
                    if (oldCreditoOfCreditoconsumoListNewCreditoconsumo != null && !oldCreditoOfCreditoconsumoListNewCreditoconsumo.equals(credito)) {
                        oldCreditoOfCreditoconsumoListNewCreditoconsumo.getCreditoconsumoList().remove(creditoconsumoListNewCreditoconsumo);
                        oldCreditoOfCreditoconsumoListNewCreditoconsumo = em.merge(oldCreditoOfCreditoconsumoListNewCreditoconsumo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = credito.getIdcredito();
                if (findCredito(id) == null) {
                    throw new NonexistentEntityException("The credito with id " + id + " no longer exists.");
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
            Credito credito;
            try {
                credito = em.getReference(Credito.class, id);
                credito.getIdcredito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The credito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Creditonegocio creditonegocioOrphanCheck = credito.getCreditonegocio();
            if (creditonegocioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Credito (" + credito + ") cannot be destroyed since the Creditonegocio " + creditonegocioOrphanCheck + " in its creditonegocio field has a non-nullable credito field.");
            }
            Creditopenhor creditopenhorOrphanCheck = credito.getCreditopenhor();
            if (creditopenhorOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Credito (" + credito + ") cannot be destroyed since the Creditopenhor " + creditopenhorOrphanCheck + " in its creditopenhor field has a non-nullable credito field.");
            }
            Creditovip creditovipOrphanCheck = credito.getCreditovip();
            if (creditovipOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Credito (" + credito + ") cannot be destroyed since the Creditovip " + creditovipOrphanCheck + " in its creditovip field has a non-nullable credito field.");
            }
            Credito creditoOrphanCheck = credito.getCredito();
            if (creditoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Credito (" + credito + ") cannot be destroyed since the Credito " + creditoOrphanCheck + " in its credito field has a non-nullable credito1 field.");
            }
            List<Historicopagamento> historicopagamentoListOrphanCheck = credito.getHistoricopagamentoList();
            for (Historicopagamento historicopagamentoListOrphanCheckHistoricopagamento : historicopagamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Credito (" + credito + ") cannot be destroyed since the Historicopagamento " + historicopagamentoListOrphanCheckHistoricopagamento + " in its historicopagamentoList field has a non-nullable idcredito field.");
            }
            List<Creditoconsumo> creditoconsumoListOrphanCheck = credito.getCreditoconsumoList();
            for (Creditoconsumo creditoconsumoListOrphanCheckCreditoconsumo : creditoconsumoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Credito (" + credito + ") cannot be destroyed since the Creditoconsumo " + creditoconsumoListOrphanCheckCreditoconsumo + " in its creditoconsumoList field has a non-nullable credito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idcliente = credito.getIdcliente();
            if (idcliente != null) {
                idcliente.getCreditoList().remove(credito);
                idcliente = em.merge(idcliente);
            }
            Credito credito1 = credito.getCredito1();
            if (credito1 != null) {
                credito1.setCredito(null);
                credito1 = em.merge(credito1);
            }
            Estado idestado = credito.getIdestado();
            if (idestado != null) {
                idestado.getCreditoList().remove(credito);
                idestado = em.merge(idestado);
            }
            Tipocredito idtipocredito = credito.getIdtipocredito();
            if (idtipocredito != null) {
                idtipocredito.getCreditoList().remove(credito);
                idtipocredito = em.merge(idtipocredito);
            }
            em.remove(credito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Credito> findCreditoEntities() {
        return findCreditoEntities(true, -1, -1);
    }

    public List<Credito> findCreditoEntities(int maxResults, int firstResult) {
        return findCreditoEntities(false, maxResults, firstResult);
    }

    private List<Credito> findCreditoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Credito.class));
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

    public Credito findCredito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Credito.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Credito> rt = cq.from(Credito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
