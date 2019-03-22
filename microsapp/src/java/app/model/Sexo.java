/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "sexo", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s"),
    @NamedQuery(name = "Sexo.findByIdsexo", query = "SELECT s FROM Sexo s WHERE s.idsexo = :idsexo"),
    @NamedQuery(name = "Sexo.findByDescricao", query = "SELECT s FROM Sexo s WHERE s.descricao = :descricao")})
public class Sexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsexo", nullable = false)
    private Long idsexo;
    @Column(name = "descricao", length = 255)
    private String descricao;
    @OneToMany(mappedBy = "idsexo", fetch = FetchType.LAZY)
    private List<Cliente> clienteList;

    public Sexo() {
    }

    public Sexo(Long idsexo) {
        this.idsexo = idsexo;
    }

    public Long getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(Long idsexo) {
        this.idsexo = idsexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsexo != null ? idsexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.idsexo == null && other.idsexo != null) || (this.idsexo != null && !this.idsexo.equals(other.idsexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Sexo[ idsexo=" + idsexo + " ]";
    }
    
}
