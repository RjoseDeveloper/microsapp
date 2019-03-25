/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "tipocredito", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocredito.findAll", query = "SELECT t FROM Tipocredito t"),
    @NamedQuery(name = "Tipocredito.findByIdcrecredito", query = "SELECT t FROM Tipocredito t WHERE t.idcrecredito = :idcrecredito"),
    @NamedQuery(name = "Tipocredito.findByDescricao", query = "SELECT t FROM Tipocredito t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "Tipocredito.findByPgto", query = "SELECT t FROM Tipocredito t WHERE t.pgto = :pgto"),
    @NamedQuery(name = "Tipocredito.findByJuro", query = "SELECT t FROM Tipocredito t WHERE t.juro = :juro"),
    @NamedQuery(name = "Tipocredito.findByStatus", query = "SELECT t FROM Tipocredito t WHERE t.status = :status"),
    @NamedQuery(name = "Tipocredito.findByData", query = "SELECT t FROM Tipocredito t WHERE t.data = :data")})
public class Tipocredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcrecredito", nullable = false)
    private Integer idcrecredito;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    @Column(name = "pgto")
    private Integer pgto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "juro", precision = 17, scale = 17)
    private Double juro;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @OneToMany(mappedBy = "idtipocredito", fetch = FetchType.LAZY)
    private List<Credito> creditoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoCredito", fetch = FetchType.LAZY)
    private List<Grupoalvo> grupoalvoList;

    public Tipocredito() {
    }

    public Tipocredito(String descricao, Integer pgto, Double juro, Boolean status, Date data) {
        this.descricao = descricao;
        this.pgto = pgto;
        this.juro = juro;
        this.status = status;
        this.data = data;
    }

    public Tipocredito(Integer idcrecredito) {
        this.idcrecredito = idcrecredito;
    }

    public Tipocredito(Integer idcrecredito, String descricao) {
        this.idcrecredito = idcrecredito;
        this.descricao = descricao;
    }

    public Integer getIdcrecredito() {
        return idcrecredito;
    }

    public void setIdcrecredito(Integer idcrecredito) {
        this.idcrecredito = idcrecredito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPgto() {
        return pgto;
    }

    public void setPgto(Integer pgto) {
        this.pgto = pgto;
    }

    public Double getJuro() {
        return juro;
    }

    public void setJuro(Double juro) {
        this.juro = juro;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @XmlTransient
    public List<Credito> getCreditoList() {
        return creditoList;
    }

    public void setCreditoList(List<Credito> creditoList) {
        this.creditoList = creditoList;
    }

    @XmlTransient
    public List<Grupoalvo> getGrupoalvoList() {
        return grupoalvoList;
    }

    public void setGrupoalvoList(List<Grupoalvo> grupoalvoList) {
        this.grupoalvoList = grupoalvoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcrecredito != null ? idcrecredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocredito)) {
            return false;
        }
        Tipocredito other = (Tipocredito) object;
        if ((this.idcrecredito == null && other.idcrecredito != null) || (this.idcrecredito != null && !this.idcrecredito.equals(other.idcrecredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Tipocredito[ idcrecredito=" + idcrecredito + " ]";
    }
    
}
