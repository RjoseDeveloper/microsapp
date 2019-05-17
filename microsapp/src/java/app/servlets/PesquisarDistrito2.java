/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.DistritoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.model.Distrito;

/**
 *
 * @author Rofino Chunga Jr
 */
@WebServlet(name = "PesquisarDistrito2", urlPatterns = {"/PesquisarDistrito2"})
public class PesquisarDistrito2 extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");

            String targetId = request.getParameter("id");
            List<Distrito> distritoList;
            if (targetId.equals("")) {
                distritoList = new DistritoJpaController(emf).findDistritoEntities(); //System.out.println("1");
            } else {
                distritoList = new DistritoJpaController(emf).buscarDistrito(targetId);// System.out.println("2");
            }
            StringBuffer sb = new StringBuffer();

//        if (targetId != null) {
//            targetId = targetId.trim().toLowerCase();
//        } else {
//            context.getRequestDispatcher("/error.jsp").forward(request, response);
//        }
            boolean namesAdded = false;
            // if (action.equals("complete")) {

            // Verificar se encontrou alguma informacao
            for (int i = 0; i < distritoList.size(); i++) {
                sb.append("<distrito>");
                sb.append("<ide>" + distritoList.get(i).getIdidstrito() + "</ide>");
                sb.append("<nome>" + distritoList.get(i).getDescricao() + "</nome>");
                sb.append("<provincia>" + distritoList.get(i).getIdprovincia().getDescricao() + "</provincia>");
                sb.append("</distrito>");
                namesAdded = true;
                System.out.println("3");
            }
            if (namesAdded) {
                System.out.println("4");
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<distritos>" + sb.toString() + "</distritos>");
            } else {
                //nada para mostrar
                System.out.println("5");
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
