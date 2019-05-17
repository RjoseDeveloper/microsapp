/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.methods;

import app.controller.CreditoJpaController;
import app.controller.HistoricopagamentoJpaController;
import app.model.Credito;
import app.model.Historicopagamento;
import app.model.Tipocredito;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Raimundo Jose
 */
public class Functions {

    EntityManagerFactory emf = new ManagerConection().getCon();

    public double getSomaCredito(int tipo, boolean status) {
        double soma = 0;

        List<Historicopagamento> cs = new HistoricopagamentoJpaController(emf).findHistoricopagamentoEntities();

        for (Historicopagamento c : cs) {
          
            if (c.getIdcredito().getIdcredito().equals(tipo)  && c.getStatus().equals(status)) {
                soma += c.getValor();
                System.out.println("v .."+ c.getValor());
            }
        }
        //System.out.println(""+ soma);

        return soma;
    }

}
