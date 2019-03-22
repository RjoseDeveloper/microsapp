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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "grupoalvo", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupoalvo.findAll", query = "SELECT g FROM Grupoalvo g"),
    @NamedQuery(name = "Grupoalvo.findByIdgrupo", query = "SELECT g FROM Grupoalvo g WHERE g.idgrupo = :idgrupo"),
    @NamedQuery(name = "Grupoalvo.findByDescricao", query = "SELECT g FROM Grupoalvo g WHERE g.descricao = :descricao")})
public class Grupoalvo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgrupo", nullable = false)
    private Integer idgrupo;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    @JoinColumn(name = "idtipo_credito", referencedColumnName = "idcrecredito", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipocredito idtipoCredito;

    public Grupoalvo() {
    }

    public Grupoalvo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Grupoalvo(Integer idgrupo, String descricao) {
        this.idgrupo = idgrupo;
        this.descricao = descricao;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tipocredito getIdtipoCredito() {
        return idtipoCredito;
    }

    public void setIdtipoCredito(Tipocredito idtipoCredito) {
        this.idtipoCredito = idtipoCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupo != null ? idgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoalvo)) {
            return false;
        }
        Grupoalvo other = (Grupoalvo) object;
        if ((this.idgrupo == null && other.idgrupo != null) || (this.idgrupo != null && !this.idgrupo.equals(other.idgrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Grupoalvo[ idgrupo=" + idgrupo + " ]";
    }
    
}
