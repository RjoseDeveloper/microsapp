/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Raimundo Jose
 */
@Embeddable
public class CreditoconsumoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idcredito", nullable = false)
    private short idcredito;
    @Basic(optional = false)
    @Column(name = "idinstituicao", nullable = false)
    private short idinstituicao;

    public CreditoconsumoPK() {
    }

    public CreditoconsumoPK(short idcredito, short idinstituicao) {
        this.idcredito = idcredito;
        this.idinstituicao = idinstituicao;
    }

    public short getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(short idcredito) {
        this.idcredito = idcredito;
    }

    public short getIdinstituicao() {
        return idinstituicao;
    }

    public void setIdinstituicao(short idinstituicao) {
        this.idinstituicao = idinstituicao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcredito;
        hash += (int) idinstituicao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditoconsumoPK)) {
            return false;
        }
        CreditoconsumoPK other = (CreditoconsumoPK) object;
        if (this.idcredito != other.idcredito) {
            return false;
        }
        if (this.idinstituicao != other.idinstituicao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.model.CreditoconsumoPK[ idcredito=" + idcredito + ", idinstituicao=" + idinstituicao + " ]";
    }
    
}
