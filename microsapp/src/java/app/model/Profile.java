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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "profile", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
    @NamedQuery(name = "Profile.findByIdprofile", query = "SELECT p FROM Profile p WHERE p.idprofile = :idprofile"),
    @NamedQuery(name = "Profile.findByInstituicao", query = "SELECT p FROM Profile p WHERE p.instituicao = :instituicao"),
    @NamedQuery(name = "Profile.findByNomefantasia", query = "SELECT p FROM Profile p WHERE p.nomefantasia = :nomefantasia"),
    @NamedQuery(name = "Profile.findByGestor", query = "SELECT p FROM Profile p WHERE p.gestor = :gestor"),
    @NamedQuery(name = "Profile.findByContacto", query = "SELECT p FROM Profile p WHERE p.contacto = :contacto"),
    @NamedQuery(name = "Profile.findByUlrimagem", query = "SELECT p FROM Profile p WHERE p.ulrimagem = :ulrimagem"),
    @NamedQuery(name = "Profile.findByEmail", query = "SELECT p FROM Profile p WHERE p.email = :email"),
    @NamedQuery(name = "Profile.findByEndereco", query = "SELECT p FROM Profile p WHERE p.endereco = :endereco")})
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofile", nullable = false)
    private Integer idprofile;
    @Column(name = "instituicao", length = 255)
    private String instituicao;
    @Column(name = "nomefantasia", length = 255)
    private String nomefantasia;
    @Column(name = "gestor", length = 255)
    private String gestor;
    @Column(name = "contacto", length = 255)
    private String contacto;
    @Column(name = "ulrimagem", length = 255)
    private String ulrimagem;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "endereco", length = 255)
    private String endereco;

    public Profile() {
    }

    public Profile(Integer idprofile) {
        this.idprofile = idprofile;
    }

    public Profile(Integer idprofile, String instituicao, String nomefantasia, String gestor, String contacto, String ulrimagem, String email, String endereco) {
        this.idprofile = idprofile;
        this.instituicao = instituicao;
        this.nomefantasia = nomefantasia;
        this.gestor = gestor;
        this.contacto = contacto;
        this.ulrimagem = ulrimagem;
        this.email = email;
        this.endereco = endereco;
    }

    public Integer getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(Integer idprofile) {
        this.idprofile = idprofile;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getUlrimagem() {
        return ulrimagem;
    }

    public void setUlrimagem(String ulrimagem) {
        this.ulrimagem = ulrimagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofile != null ? idprofile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.idprofile == null && other.idprofile != null) || (this.idprofile != null && !this.idprofile.equals(other.idprofile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Profile[ idprofile=" + idprofile + " ]";
    }
    
}
