/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.ProfileJpaController;
import app.controller.TipocreditoJpaController;
import app.model.Profile;
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

/**
 *
 * @author Raimundo Jose
 */
@WebServlet(name = "ConfigController", urlPatterns = {"/ConfigController"})
public class ConfigController extends HttpServlet {

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
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
              EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
              
              SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                  switch(Integer.valueOf(request.getParameter("acao"))){
             case 1:
                 
                 double juro = Double.parseDouble(request.getParameter("juro"));
                 String tipocredito = request.getParameter("tipocredito");
                 boolean status = Boolean.parseBoolean(request.getParameter("status"));
                 Date d = df.parse(df.format(new Date()));
                 System.out.println("Data: "+d);
                 int qtdmes = Integer.parseInt(request.getParameter("mes"));
                 
                 new TipocreditoJpaController(emf).create(
                         new Tipocredito(tipocredito, qtdmes, juro, status, d));
                 response.sendRedirect(request.getContextPath()+"/tamplates/settings.jsp?msg=sucess");

                 
                break;
             case 2:
                 
                String nomeinstituicao = request.getParameter("nomeinstituicao");
                String nomefantasia = request.getParameter("nomefantasia");
                String gestor = request.getParameter("nomegestor");
                String contacto = request.getParameter("contacto");
                String email = request.getParameter("email");
                String endereco = request.getParameter("endereco");
                String ulrlogo = request.getParameter("logotipo");
                
                Profile profile = new Profile(1, nomeinstituicao, nomefantasia, 
                        gestor, contacto,ulrlogo,email,endereco);
          
                        if (new ProfileJpaController(emf).getProfileCount() == 1){
                            new ProfileJpaController(emf).edit(profile);
                        }else{
                             new ProfileJpaController(emf).create(profile);
                        }
                   response.sendRedirect(request.getContextPath()+"/tamplates/settings.jsp?msg1="+profile);
                
                 break;
             case 3:
                 break;
                 default: out.print("Parametro Invalido");
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
        } catch (ParseException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
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
