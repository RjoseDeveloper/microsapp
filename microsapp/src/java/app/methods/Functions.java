/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.methods;

import app.controller.CreditoJpaController;
import app.model.Credito;
import app.model.Tipocredito;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class Functions {

    EntityManagerFactory emf = new ManagerConection().getCon();

    public double getSomaCredito(int tipo, long status) {
        double soma = 0;

        List<Credito> cs = new CreditoJpaController(emf).findCreditoEntities();

        for (Credito c : cs) {
          
            if (c.getIdtipocredito().getIdcrecredito().equals(tipo)
                    && c.getIdestado().getIdestado().equals(status)) {
                soma += c.getValor();
            }
        }
        //System.out.println(""+ soma);

        return soma;
    }

}
