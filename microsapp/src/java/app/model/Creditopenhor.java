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
@Table(name = "creditopenhor", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditopenhor.findAll", query = "SELECT c FROM Creditopenhor c"),
    @NamedQuery(name = "Creditopenhor.findByIdcredito", query = "SELECT c FROM Creditopenhor c WHERE c.idcredito = :idcredito"),
    @NamedQuery(name = "Creditopenhor.findByUrlimovel", query = "SELECT c FROM Creditopenhor c WHERE c.urlimovel = :urlimovel"),
    @NamedQuery(name = "Creditopenhor.findByUrldecpenhor", query = "SELECT c FROM Creditopenhor c WHERE c.urldecpenhor = :urldecpenhor")})
public class Creditopenhor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcredito", nullable = false)
    private Integer idcredito;
    @Column(name = "urlimovel", length = 255)
    private String urlimovel;
    @Column(name = "urldecpenhor", length = 255)
    private String urldecpenhor;
    @JoinColumn(name = "idcredito", referencedColumnName = "idcredito", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Credito credito;

    public Creditopenhor() {
    }

    public Creditopenhor(Integer idcredito) {
        this.idcredito = idcredito;
    }

    public Integer getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(Integer idcredito) {
        this.idcredito = idcredito;
    }

    public String getUrlimovel() {
        return urlimovel;
    }

    public void setUrlimovel(String urlimovel) {
        this.urlimovel = urlimovel;
    }

    public String getUrldecpenhor() {
        return urldecpenhor;
    }

    public void setUrldecpenhor(String urldecpenhor) {
        this.urldecpenhor = urldecpenhor;
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
        if (!(object instanceof Creditopenhor)) {
            return false;
        }
        Creditopenhor other = (Creditopenhor) object;
        if ((this.idcredito == null && other.idcredito != null) || (this.idcredito != null && !this.idcredito.equals(other.idcredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Creditopenhor[ idcredito=" + idcredito + " ]";
    }
    
}
