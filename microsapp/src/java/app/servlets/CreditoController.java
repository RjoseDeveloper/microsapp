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
import app.model.Cliente;
import app.model.Credito;
import app.model.Creditoconsumo;
import app.model.CreditoconsumoPK;
import app.model.Creditonegocio;
import app.model.Creditopenhor;
import app.model.Estado;
import app.model.Instituicao;
import app.model.Tipocredito;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            System.out.println("ID USR: "+session.getAttribute("iduser"));
            int userId = (int) session.getAttribute("iduser");
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            int PGTO = 2, STATUS = 1;
            String status = "1";
            int destino = Integer.parseInt(request.getParameter("destino"));

            try {

                Date date_emp = formatter.parse(request.getParameter("dataemp"));
                Date date_pag = formatter.parse(request.getParameter("datapag"));
                credito.setValor(Double.parseDouble(request.getParameter("montante")));

                credito.setIdtipocredito(new Tipocredito(destino));
                credito.setIdcliente(new Cliente(userId));
                credito.setNrMaxPag(PGTO);
                credito.setIdestado(new Estado(Long.parseLong(status)));
                credito.setDataEmprestimo(date_emp);
                credito.setDataPagamento(date_pag);

                new CreditoJpaController(emf).create(credito);
                 //response.sendRedirect("/microsapp/tamplates/success.jsp");

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (destino == 1) {

                System.out.println("Novo IdCreditos:" + credito.getIdcredito());

                Creditoconsumo creditoconsumo = new Creditoconsumo();
                Instituicao instituicao = new Instituicao();
                instituicao.setIdinstituicao(1);

                CreditoconsumoPK creditoconsumoPK = new CreditoconsumoPK();
                creditoconsumoPK.setIdcredito(credito.getIdcredito().shortValue());
                creditoconsumoPK.setIdinstituicao(instituicao.getIdinstituicao().shortValue());

                creditoconsumo.setCreditoconsumoPK(creditoconsumoPK);
                creditoconsumo.setFuncao(request.getParameter("funcao"));
                creditoconsumo.setContactogestor(request.getParameter("contactBoss"));
                creditoconsumo.setTitularconta(request.getParameter("titular"));
                creditoconsumo.setNrconta(request.getParameter("nr_conta"));
                creditoconsumo.setUrlbi(request.getParameter("bi"));
                creditoconsumo.setUrlextratobancario(request.getParameter("extrato"));
                creditoconsumo.setUrldeclaracaoservico(request.getParameter("declaracaoServico"));
                creditoconsumo.setUrloutro(request.getParameter("penhor"));
                creditoconsumo.setNomebanco(request.getParameter("banco"));

                new CreditoconsumoJpaController(emf).create(creditoconsumo);
                 response.sendRedirect("/microsapp/tamplates/success.jsp");

            }
            if (destino == 2) {

                Creditonegocio creditonegocio = new Creditonegocio();
                creditonegocio.setBem(request.getParameter("bempenhor"));
                creditonegocio.setIdcredito(credito.getIdcredito().shortValue());
                creditonegocio.setTestemunha1(request.getParameter("testemunha1"));
                creditonegocio.setTestemunha2(request.getParameter("testemunha2"));
                creditonegocio.setUrldeclaracao(request.getParameter("decbairro"));
                new CreditonegocioJpaController(emf).create(creditonegocio);
                 response.sendRedirect("/microsapp/tamplates/success.jsp");

            }
            if (destino == 3) {

                Creditopenhor penhor = new Creditopenhor();
                System.out.println("Creditos: [enhor: " + credito.getIdcredito());

                penhor.setIdcredito(credito.getIdcredito());
                penhor.setUrldecpenhor(request.getParameter("urldecimovel"));
                penhor.setUrlimovel(request.getParameter("urldecimovel"));

                new CreditopenhorJpaController(emf).create(penhor);
                 response.sendRedirect("/microsapp/tamplates/success.jsp");

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
