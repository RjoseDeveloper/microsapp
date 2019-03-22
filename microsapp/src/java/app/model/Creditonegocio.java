/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "creditonegocio", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditonegocio.findAll", query = "SELECT c FROM Creditonegocio c"),
    @NamedQuery(name = "Creditonegocio.findByIdcredito", query = "SELECT c FROM Creditonegocio c WHERE c.idcredito = :idcredito"),
    @NamedQuery(name = "Creditonegocio.findByTestemunha1", query = "SELECT c FROM Creditonegocio c WHERE c.testemunha1 = :testemunha1"),
    @NamedQuery(name = "Creditonegocio.findByTestemunha2", query = "SELECT c FROM Creditonegocio c WHERE c.testemunha2 = :testemunha2"),
    @NamedQuery(name = "Creditonegocio.findByBem", query = "SELECT c FROM Creditonegocio c WHERE c.bem = :bem"),
    @NamedQuery(name = "Creditonegocio.findByUrldeclaracao", query = "SELECT c FROM Creditonegocio c WHERE c.urldeclaracao = :urldeclaracao")})
public class Creditonegocio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcredito", nullable = false)
    private Short idcredito;
    @Column(name = "testemunha1", length = 255)
    private String testemunha1;
    @Column(name = "testemunha2", length = 255)
    private String testemunha2;
    @Column(name = "bem", length = 2147483647)
    private String bem;
    @Column(name = "urldeclaracao", length = 255)
    private String urldeclaracao;
    @JoinColumn(name = "idcredito", referencedColumnName = "idcredito", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Credito credito;

    public Creditonegocio() {
    }

    public Creditonegocio(Short idcredito) {
        this.idcredito = idcredito;
    }

    public Short getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(Short idcredito) {
        this.idcredito = idcredito;
    }

    public String getTestemunha1() {
        return testemunha1;
    }

    public void setTestemunha1(String testemunha1) {
        this.testemunha1 = testemunha1;
    }

    public String getTestemunha2() {
        return testemunha2;
    }

    public void setTestemunha2(String testemunha2) {
        this.testemunha2 = testemunha2;
    }

    public String getBem() {
        return bem;
    }

    public void setBem(String bem) {
        this.bem = bem;
    }

    public String getUrldeclaracao() {
        return urldeclaracao;
    }

    public void setUrldeclaracao(String urldeclaracao) {
        this.urldeclaracao = urldeclaracao;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcredito != null ? idcredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creditonegocio)) {
            return false;
        }
        Creditonegocio other = (Creditonegocio) object;
        if ((this.idcredito == null && other.idcredito != null) || (this.idcredito != null && !this.idcredito.equals(other.idcredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Creditonegocio[ idcredito=" + idcredito + " ]";
    }
    
}
