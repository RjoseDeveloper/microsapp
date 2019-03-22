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
@Table(name = "creditovip", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditovip.findAll", query = "SELECT c FROM Creditovip c"),
    @NamedQuery(name = "Creditovip.findByIdcredito", query = "SELECT c FROM Creditovip c WHERE c.idcredito = :idcredito"),
    @NamedQuery(name = "Creditovip.findByUrldeclaracaohonra", query = "SELECT c FROM Creditovip c WHERE c.urldeclaracaohonra = :urldeclaracaohonra"),
    @NamedQuery(name = "Creditovip.findByCredor", query = "SELECT c FROM Creditovip c WHERE c.credor = :credor")})
public class Creditovip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcredito", nullable = false)
    private Integer idcredito;
    @Column(name = "urldeclaracaohonra", length = 255)
    private String urldeclaracaohonra;
    @Column(name = "credor", length = 255)
    private String credor;
    @JoinColumn(name = "idcredito", referencedColumnName = "idcredito", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Credito credito;

    public Creditovip() {
    }

    public Creditovip(Integer idcredito) {
        this.idcredito = idcredito;
    }

    public Integer getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(Integer idcredito) {
        this.idcredito = idcredito;
    }

    public String getUrldeclaracaohonra() {
        return urldeclaracaohonra;
    }

    public void setUrldeclaracaohonra(String urldeclaracaohonra) {
        this.urldeclaracaohonra = urldeclaracaohonra;
    }

    public String getCredor() {
        return credor;
    }

    public void setCredor(String credor) {
        this.credor = credor;
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
        if (!(object instanceof Creditovip)) {
            return false;
        }
        Creditovip other = (Creditovip) object;
        if ((this.idcredito == null && other.idcredito != null) || (this.idcredito != null && !this.idcredito.equals(other.idcredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Creditovip[ idcredito=" + idcredito + " ]";
    }
    
}
