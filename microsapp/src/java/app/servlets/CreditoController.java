/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.CreditoJpaController;
import app.controller.CreditoconsumoJpaController;
import app.controller.CreditonegocioJpaController;
import app.controller.CreditopenhorJpaController;
import app.controller.CreditovipJpaController;
import app.controller.HistoricopagamentoJpaController;
import app.controller.TipocreditoJpaController;
import app.model.Cliente;
import app.model.Credito;
import app.model.Creditoconsumo;
import app.model.CreditoconsumoPK;
import app.model.Creditonegocio;
import app.model.Creditopenhor;
import app.model.Creditovip;
import app.model.Estado;
import app.model.Historicopagamento;
import app.model.Instituicao;
import app.model.Modopagamento;
import app.model.Tipocredito;
import static app.model.User_.userId;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Raimundo Jose
 */
@WebServlet(name = "CreditoController", urlPatterns = {"/CreditoController"})
public class CreditoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
            Credito credito = new Credito();
            
            HttpSession session = request.getSession();
            //System.out.println("ID USR: " + session.getAttribute("iduser"));
            String userid =  session.getAttribute("iduser").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             Calendar cal = Calendar.getInstance();
            
            String status = "5";  // Status 5 nao autorizado ;
            int destino = Integer.parseInt(request.getParameter("destino"));
            
            try {
                
                Tipocredito tipo = new TipocreditoJpaController(emf).findTipocredito(destino);
                int modopay = Integer.parseInt(request.getParameter("modopay"));
                double valor = Double.parseDouble(request.getParameter("montante"));
                
                int mes = tipo.getPgto();

                double pgto_r = valor /tipo.getPgto() ;
                double juro = valor * (tipo.getJuro() / 100);
                double tjuro = pgto_r + juro;
                double vf = tjuro *tipo.getPgto();
             
                int day_in_month = 30;
                
                Date date_emp = formatter.parse(request.getParameter("datai"));
                Date date_pag = formatter.parse(request.getParameter("dataf"));
                
                credito.setValor(Math.round(vf));
                credito.setIdtipocredito(new Tipocredito(destino));
                Cliente c = new Cliente();
                c.setIdcliente(Integer.parseInt(userid));
                credito.setIdcliente(c);
                credito.setNrMaxPag(tipo.getPgto());
                credito.setIdestado(new Estado(Long.parseLong(status)));
                credito.setDataEmprestimo(date_emp);
                credito.setDataPagamento(date_pag);
                
                new CreditoJpaController(emf).create(credito);
                 
                
                for (int i = 0; i < tipo.getPgto(); i++) {
                    
                    cal.add(Calendar.DATE, day_in_month);
                    String di = formatter.format(cal.getTime());
                    
                    Historicopagamento h = new Historicopagamento();
                    h.setIdcredito(credito);
                    h.setIdmodopagamento(new Modopagamento(modopay));
                    h.setOrdem(i+1);
                    h.setValor(Math.round(pgto_r + juro));
                    h.setData(formatter.parse(di));
                    new HistoricopagamentoJpaController(emf).create(h);
                }
                
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            if (destino == 1) {
                
                System.out.println("Novo IdCreditos:" + credito.getIdcredito());
                
                Creditoconsumo creditoconsumo = new Creditoconsumo();
                Instituicao instituicao = new Instituicao();
                instituicao.setIdinstituicao(Integer.parseInt(request.getParameter("instituicao")));
             
                creditoconsumo.setFuncao(request.getParameter("funcao"));
                creditoconsumo.setContactogestor(request.getParameter("contactBoss"));
                creditoconsumo.setTitularconta(request.getParameter("titular"));
                creditoconsumo.setNrconta(request.getParameter("nr_conta"));
                creditoconsumo.setUrlbi(request.getParameter("bi"));
                creditoconsumo.setUrlextratobancario(request.getParameter("extrato"));
                creditoconsumo.setUrldeclaracaoservico(request.getParameter("declaracaoServico"));
                creditoconsumo.setUrloutro(request.getParameter("penhor"));
                creditoconsumo.setNomebanco(request.getParameter("banco"));
                creditoconsumo.setCredito(credito);
                creditoconsumo.setInstituicao(instituicao);
                
                CreditoconsumoPK ck = new CreditoconsumoPK();
                ck.setIdcredito(creditoconsumo.getCredito().getIdcredito().shortValue());
                ck.setIdinstituicao(creditoconsumo.getInstituicao().getIdinstituicao().shortValue());
                creditoconsumo.setCreditoconsumoPK(ck);
                
                new CreditoconsumoJpaController(emf).create(creditoconsumo);
                response.sendRedirect("/microsapp/modal/success_cdr.jsp");
                
            }
            if (destino == 2) {
                
                Creditonegocio creditonegocio = new Creditonegocio();
                creditonegocio.setBem(request.getParameter("bempenhor"));
                creditonegocio.setIdcredito(credito.getIdcredito().shortValue());
                creditonegocio.setTestemunha1(request.getParameter("testemunha1"));
                creditonegocio.setTestemunha2(request.getParameter("testemunha2"));
                creditonegocio.setUrldeclaracao(request.getParameter("decbairro"));
                new CreditonegocioJpaController(emf).create(creditonegocio);
                response.sendRedirect("/microsapp/modal/success_cdr.jsp");
                
            }
            if (destino == 3) {
                
                Creditopenhor penhor = new Creditopenhor();
                System.out.println("Creditos: [enhor: " + credito.getIdcredito());
                
                penhor.setIdcredito(credito.getIdcredito());
                penhor.setUrldecpenhor(request.getParameter("urldecimovel"));
                penhor.setUrlimovel(request.getParameter("urldecimovel"));
                
                new CreditopenhorJpaController(emf).create(penhor);
                response.sendRedirect("/microsapp/modal/success_cdr.jsp");
                
            }
            if (destino == 4) {
                
                Creditovip vip = new Creditovip();
                System.out.println("Creditos: [enhor: " + credito.getIdcredito());
                
                vip.setIdcredito(credito.getIdcredito());
                vip.setCredor(request.getParameter("credor"));
                vip.setUrldeclaracaohonra(request.getParameter("urldechonra"));
                
                new CreditovipJpaController(emf).create(vip);
                response.sendRedirect("/microsapp/modal/success_cdr.jsp");
                
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CreditoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CreditoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
