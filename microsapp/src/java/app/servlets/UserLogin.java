/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.RoleJpaController;
import app.controller.UserJpaController;
import app.model.User;
import app.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "UserLogin", urlPatterns = {"/UserLogin"})
public class UserLogin extends HttpServlet {

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
        User u = null;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            boolean status = false;
            HttpSession session = request.getSession();
            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

            String username = request.getParameter("email");
            String password = request.getParameter("password");

            UserJpaController user = new UserJpaController(emf);
            RoleJpaController role = new RoleJpaController(emf);
            List<User> users = user.findUserEntities();

            for (User user1 : users) {
                
                if ((username.equals(user1.getEmail())) && (password.equals(user1.getPassword()))) {
             
                    session.setAttribute("username", user1.getEmail());
                    session.setAttribute("apelido", user1.getLastName());
                    session.setAttribute("nome", user1.getName());
                    session.setAttribute("iduser", user1.getUserId().intValue());
                    session.setAttribute("role_name", user1.getRoleId().getRole());
                    session.setAttribute("role_id", user1.getRoleId().getRoleId());
                    session.setAttribute("status", true);
                    session.setAttribute("user", user1);
                    status = true;
                    
                     if (user1.getRoleId().getRole().equals("ADMIN")) {
                        response.sendRedirect("/microsapp/tamplates/admin.jsp");
                       
                    } else {
                    }
                    if (user1.getRoleId().getRole().equals("STANDARD")) {
                       response.sendRedirect("/microsapp/tamplates/utente.jsp");
                    }
                    if (user1.getRoleId().getRole().equals("CLIENT")) {
                        response.sendRedirect("/microsapp/tamplates/client.jsp");
                    }
                }
            }

            if (status != true) {
                
                response.sendRedirect(request.getContentType()+ "/login.jsp");
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
