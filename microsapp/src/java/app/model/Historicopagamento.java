/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raimundo Jose
 */
@Entity
@Table(name = "historicopagamento", catalog = "jmicrocreditos", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicopagamento.findAll", query = "SELECT h FROM Historicopagamento h"),
    @NamedQuery(name = "Historicopagamento.findByData", query = "SELECT h FROM Historicopagamento h WHERE h.data = :data"),
    @NamedQuery(name = "Historicopagamento.findByValor", query = "SELECT h FROM Historicopagamento h WHERE h.valor = :valor"),
    @NamedQuery(name = "Historicopagamento.findByOrdem", query = "SELECT h FROM Historicopagamento h WHERE h.ordem = :ordem"),
    @NamedQuery(name = "Historicopagamento.findByIdhistorico", query = "SELECT h FROM Historicopagamento h WHERE h.idhistorico = :idhistorico")})
public class Historicopagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private double valor;
    @Basic(optional = false)
    @Column(name = "ordem", nullable = false)
    private int ordem;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistorico", nullable = false)
    private Integer idhistorico;
    @JoinColumn(name = "idcredito", referencedColumnName = "idcredito", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Credito idcredito;
    @JoinColumn(name = "idmodopagamento", referencedColumnName = "idmodo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modopagamento idmodopagamento;

    public Historicopagamento() {
    }

    public Historicopagamento(Integer idhistorico) {
        this.idhistorico = idhistorico;
    }

    public Historicopagamento(Integer idhistorico, Date data, double valor, int ordem) {
        this.idhistorico = idhistorico;
        this.data = data;
        this.valor = valor;
        this.ordem = ordem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public Integer getIdhistorico() {
        return idhistorico;
    }

    public void setIdhistorico(Integer idhistorico) {
        this.idhistorico = idhistorico;
    }

    public Credito getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(Credito idcredito) {
        this.idcredito = idcredito;
    }

    public Modopagamento getIdmodopagamento() {
        return idmodopagamento;
    }

    public void setIdmodopagamento(Modopagamento idmodopagamento) {
        this.idmodopagamento = idmodopagamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistorico != null ? idhistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicopagamento)) {
            return false;
        }
        Historicopagamento other = (Historicopagamento) object;
        if ((this.idhistorico == null && other.idhistorico != null) || (this.idhistorico != null && !this.idhistorico.equals(other.idhistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.Historicopagamento[ idhistorico=" + idhistorico + " ]";
    }
    
}
