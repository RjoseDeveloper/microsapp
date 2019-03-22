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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "cliente", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByNrBi", query = "SELECT c FROM Cliente c WHERE c.nrBi = :nrBi"),
    @NamedQuery(name = "Cliente.findByLinhaendereco1", query = "SELECT c FROM Cliente c WHERE c.linhaendereco1 = :linhaendereco1"),
    @NamedQuery(name = "Cliente.findByLinhaendereco2", query = "SELECT c FROM Cliente c WHERE c.linhaendereco2 = :linhaendereco2"),
    @NamedQuery(name = "Cliente.findByContacto1", query = "SELECT c FROM Cliente c WHERE c.contacto1 = :contacto1"),
    @NamedQuery(name = "Cliente.findByContacto2", query = "SELECT c FROM Cliente c WHERE c.contacto2 = :contacto2")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcliente", nullable = false)
    private Integer idcliente;
    @Basic(optional = false)
    @Column(name = "nr_bi", nullable = false, length = 32)
    private String nrBi;
    @Column(name = "linhaendereco1", length = 255)
    private String linhaendereco1;
    @Column(name = "linhaendereco2", length = 255)
    private String linhaendereco2;
    @Basic(optional = false)
    @Column(name = "contacto1", nullable = false, length = 255)
    private String contacto1;
    @Column(name = "contacto2", length = 255)
    private String contacto2;
    @JoinColumn(name = "iddistrito", referencedColumnName = "ididstrito", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Distrito iddistrito;
    @JoinColumn(name = "idestadocivil", referencedColumnName = "idestadocivil", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estadocivil idestadocivil;
    @JoinColumn(name = "idsexo", referencedColumnName = "idsexo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sexo idsexo;
    @JoinColumn(name = "idcliente", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente", fetch = FetchType.LAZY)
    private List<Credito> creditoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Funcionario funcionario;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(Integer idcliente, String nrBi, String contacto1) {
        this.idcliente = idcliente;
        this.nrBi = nrBi;
        this.contacto1 = contacto1;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNrBi() {
        return nrBi;
    }

    public void setNrBi(String nrBi) {
        this.nrBi = nrBi;
    }

    public String getLinhaendereco1() {
        return linhaendereco1;
    }

    public void setLinhaendereco1(String linhaendereco1) {
        this.linhaendereco1 = linhaendereco1;
    }

    public String getLinhaendereco2() {
        return linhaendereco2;
    }

    public void setLinhaendereco2(String linhaendereco2) {
        this.linhaendereco2 = linhaendereco2;
    }

    public String getContacto1() {
        return contacto1;
    }

    public void setContacto1(String contacto1) {
        this.contacto1 = contacto1;
    }

    public String getContacto2() {
        return contacto2;
    }

    public void setContacto2(String contacto2) {
        this.contacto2 = contacto2;
    }

    public Distrito getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(Distrito iddistrito) {
        this.iddistrito = iddistrito;
    }

    public Estadocivil getIdestadocivil() {
        return idestadocivil;
    }

    public void setIdestadocivil(Estadocivil idestadocivil) {
        this.idestadocivil = idestadocivil;
    }

    public Sexo getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(Sexo idsexo) {
        this.idsexo = idsexo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public List<Credito> getCreditoList() {
        return creditoList;
    }

    public void setCreditoList(List<Credito> creditoList) {
        this.creditoList = creditoList;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Cliente[ idcliente=" + idcliente + " ]";
    }
    
}
