/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.DistritoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.model.Distrito;
import app.model.Provincia;

/**
 *
 * @author Rofino Chunga Jr
 */
@WebServlet(name = "AdicionarDistrito", urlPatterns = {"/AdicionarDistrito"})
public class AdicionarDistrito extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String msg = "0";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            if (request.getParameter("ide") != null) {
                EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");

                int idDistrito = 0;
                Distrito distrito;
                if (!request.getParameter("ide").equals("")) {
                    idDistrito = Integer.parseInt(request.getParameter("ide"));
                    distrito = new DistritoJpaController(emf).findDistrito(idDistrito);
                } else {
                    distrito = new Distrito();
                }

                distrito.setDescricao(request.getParameter("distrito"));
                distrito.setIdprovincia(new Provincia(Integer.parseInt(request.getParameter("provincia"))));

                if (request.getParameter("ide").equals("")) {
                    try {
                        new DistritoJpaController(emf).create(distrito);
                        request.setAttribute("verificarAdd", 2);
                        request.getRequestDispatcher("listaDistrito.jsp").forward(request, response);
                    } catch (Exception ex) {

                    }
                } else {
                    try {
                        new DistritoJpaController(emf).edit(distrito);
                        request.setAttribute("verificarAdd", 2);
                        request.getRequestDispatcher("listaDistrito.jsp").forward(request, response);
                    } catch (Exception ex) {

                    }

                }
                msg = distrito.getDescricao() + "|" + distrito.getIdprovincia().getDescricao();
                // request.setAttribute("verificar", 1);
                request.getRequestDispatcher("listaUsuario.jsp").forward(request, response);
            } else {
                response.setContentType("text/event-stream");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("data: " + msg + "\n\n");
                response.getWriter().flush();
                response.getWriter().close();
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
            Logger.getLogger(AdicionarDistrito.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdicionarDistrito.class.getName()).log(Level.SEVERE, null, ex);
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
