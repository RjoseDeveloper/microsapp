/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "creditoconsumo", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditoconsumo.findAll", query = "SELECT c FROM Creditoconsumo c"),
    @NamedQuery(name = "Creditoconsumo.findByIdcredito", query = "SELECT c FROM Creditoconsumo c WHERE c.creditoconsumoPK.idcredito = :idcredito"),
    @NamedQuery(name = "Creditoconsumo.findByIdinstituicao", query = "SELECT c FROM Creditoconsumo c WHERE c.creditoconsumoPK.idinstituicao = :idinstituicao"),
    @NamedQuery(name = "Creditoconsumo.findByFuncao", query = "SELECT c FROM Creditoconsumo c WHERE c.funcao = :funcao"),
    @NamedQuery(name = "Creditoconsumo.findByContactogestor", query = "SELECT c FROM Creditoconsumo c WHERE c.contactogestor = :contactogestor"),
    @NamedQuery(name = "Creditoconsumo.findByTitularconta", query = "SELECT c FROM Creditoconsumo c WHERE c.titularconta = :titularconta"),
    @NamedQuery(name = "Creditoconsumo.findByNrconta", query = "SELECT c FROM Creditoconsumo c WHERE c.nrconta = :nrconta"),
    @NamedQuery(name = "Creditoconsumo.findByNomebanco", query = "SELECT c FROM Creditoconsumo c WHERE c.nomebanco = :nomebanco"),
    @NamedQuery(name = "Creditoconsumo.findByUrldeclaracaoservico", query = "SELECT c FROM Creditoconsumo c WHERE c.urldeclaracaoservico = :urldeclaracaoservico"),
    @NamedQuery(name = "Creditoconsumo.findByUrlbi", query = "SELECT c FROM Creditoconsumo c WHERE c.urlbi = :urlbi"),
    @NamedQuery(name = "Creditoconsumo.findByUrlextratobancario", query = "SELECT c FROM Creditoconsumo c WHERE c.urlextratobancario = :urlextratobancario"),
    @NamedQuery(name = "Creditoconsumo.findByUrloutro", query = "SELECT c FROM Creditoconsumo c WHERE c.urloutro = :urloutro")})
public class Creditoconsumo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CreditoconsumoPK creditoconsumoPK;
    @Column(name = "funcao", length = 255)
    private String funcao;
    @Column(name = "contactogestor", length = 255)
    private String contactogestor;
    @Column(name = "titularconta", length = 255)
    private String titularconta;
    @Column(name = "nrconta", length = 255)
    private String nrconta;
    @Column(name = "nomebanco", length = 255)
    private String nomebanco;
    @Column(name = "urldeclaracaoservico", length = 255)
    private String urldeclaracaoservico;
    @Column(name = "urlbi", length = 255)
    private String urlbi;
    @Column(name = "urlextratobancario", length = 255)
    private String urlextratobancario;
    @Column(name = "urloutro", length = 255)
    private String urloutro;
    @JoinColumn(name = "idcredito", referencedColumnName = "idcredito", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Credito credito;
    @JoinColumn(name = "idinstituicao", referencedColumnName = "idinstituicao", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Instituicao instituicao;

    public Creditoconsumo() {
    }

    public Creditoconsumo(CreditoconsumoPK creditoconsumoPK) {
        this.creditoconsumoPK = creditoconsumoPK;
    }

    public Creditoconsumo(short idcredito, short idinstituicao) {
        this.creditoconsumoPK = new CreditoconsumoPK(idcredito, idinstituicao);
    }

    public CreditoconsumoPK getCreditoconsumoPK() {
        return creditoconsumoPK;
    }

    public void setCreditoconsumoPK(CreditoconsumoPK creditoconsumoPK) {
        this.creditoconsumoPK = creditoconsumoPK;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getContactogestor() {
        return contactogestor;
    }

    public void setContactogestor(String contactogestor) {
        this.contactogestor = contactogestor;
    }

    public String getTitularconta() {
        return titularconta;
    }

    public void setTitularconta(String titularconta) {
        this.titularconta = titularconta;
    }

    public String getNrconta() {
        return nrconta;
    }

    public void setNrconta(String nrconta) {
        this.nrconta = nrconta;
    }

    public String getNomebanco() {
        return nomebanco;
    }

    public void setNomebanco(String nomebanco) {
        this.nomebanco = nomebanco;
    }

    public String getUrldeclaracaoservico() {
        return urldeclaracaoservico;
    }

    public void setUrldeclaracaoservico(String urldeclaracaoservico) {
        this.urldeclaracaoservico = urldeclaracaoservico;
    }

    public String getUrlbi() {
        return urlbi;
    }

    public void setUrlbi(String urlbi) {
        this.urlbi = urlbi;
    }

    public String getUrlextratobancario() {
        return urlextratobancario;
    }

    public void setUrlextratobancario(String urlextratobancario) {
        this.urlextratobancario = urlextratobancario;
    }

    public String getUrloutro() {
        return urloutro;
    }

    public void setUrloutro(String urloutro) {
        this.urloutro = urloutro;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditoconsumoPK != null ? creditoconsumoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creditoconsumo)) {
            return false;
        }
        Creditoconsumo other = (Creditoconsumo) object;
        if ((this.creditoconsumoPK == null && other.creditoconsumoPK != null) || (this.creditoconsumoPK != null && !this.creditoconsumoPK.equals(other.creditoconsumoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Creditoconsumo[ creditoconsumoPK=" + creditoconsumoPK + " ]";
    }
    
}
