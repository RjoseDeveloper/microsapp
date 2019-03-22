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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "funcionario", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByIdfuncionario", query = "SELECT f FROM Funcionario f WHERE f.idfuncionario = :idfuncionario"),
    @NamedQuery(name = "Funcionario.findByFuncao", query = "SELECT f FROM Funcionario f WHERE f.funcao = :funcao"),
    @NamedQuery(name = "Funcionario.findByProveniencia", query = "SELECT f FROM Funcionario f WHERE f.proveniencia = :proveniencia")})
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncionario", nullable = false)
    private Integer idfuncionario;
    @Basic(optional = false)
    @Column(name = "funcao", nullable = false, length = 255)
    private String funcao;
    @Column(name = "proveniencia", length = 255)
    private String proveniencia;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idcliente", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;
    @JoinColumn(name = "idinstituicao", referencedColumnName = "idinstituicao", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Instituicao idinstituicao;

    public Funcionario() {
    }

    public Funcionario(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Funcionario(Integer idfuncionario, String funcao) {
        this.idfuncionario = idfuncionario;
        this.funcao = funcao;
    }

    public Integer getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getProveniencia() {
        return proveniencia;
    }

    public void setProveniencia(String proveniencia) {
        this.proveniencia = proveniencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Instituicao getIdinstituicao() {
        return idinstituicao;
    }

    public void setIdinstituicao(Instituicao idinstituicao) {
        this.idinstituicao = idinstituicao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfuncionario != null ? idfuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.idfuncionario == null && other.idfuncionario != null) || (this.idfuncionario != null && !this.idfuncionario.equals(other.idfuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Funcionario[ idfuncionario=" + idfuncionario + " ]";
    }
    
}
