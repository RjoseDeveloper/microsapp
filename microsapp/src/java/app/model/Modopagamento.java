/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "modopagamento", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modopagamento.findAll", query = "SELECT m FROM Modopagamento m"),
    @NamedQuery(name = "Modopagamento.findByIdmodo", query = "SELECT m FROM Modopagamento m WHERE m.idmodo = :idmodo"),
    @NamedQuery(name = "Modopagamento.findByDescricao", query = "SELECT m FROM Modopagamento m WHERE m.descricao = :descricao")})
public class Modopagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmodo", nullable = false)
    private Integer idmodo;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmodopagamento", fetch = FetchType.LAZY)
    private List<Historicopagamento> historicopagamentoList;

    public Modopagamento() {
    }

    public Modopagamento(Integer idmodo) {
        this.idmodo = idmodo;
    }

    public Modopagamento(Integer idmodo, String descricao) {
        this.idmodo = idmodo;
        this.descricao = descricao;
    }

    public Integer getIdmodo() {
        return idmodo;
    }

    public void setIdmodo(Integer idmodo) {
        this.idmodo = idmodo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Historicopagamento> getHistoricopagamentoList() {
        return historicopagamentoList;
    }

    public void setHistoricopagamentoList(List<Historicopagamento> historicopagamentoList) {
        this.historicopagamentoList = historicopagamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmodo != null ? idmodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modopagamento)) {
            return false;
        }
        Modopagamento other = (Modopagamento) object;
        if ((this.idmodo == null && other.idmodo != null) || (this.idmodo != null && !this.idmodo.equals(other.idmodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Modopagamento[ idmodo=" + idmodo + " ]";
    }
    
}
