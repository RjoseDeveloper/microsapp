/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.ClienteJpaController;
import app.controller.UserJpaController;
import app.controller.exceptions.PreexistingEntityException;
import app.model.Cliente;
import app.model.Distrito;
import app.model.Estadocivil;
import app.model.Instituicao;
import app.model.Provincia;
import app.model.Role;
import static app.model.Role_.role;
import app.model.Sexo;
import app.model.Tipocredito;
import app.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "UsersController", urlPatterns = {"/UsersController"})
public class UsersController extends HttpServlet {

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
            throws ServletException, IOException, PreexistingEntityException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
            HttpSession session = request.getSession();

            String nome = (String) request.getParameter("nome");
            String apelido = (String) request.getParameter("apelido");
            String nr_documento = (String) request.getParameter("nr_documento");
            String contacto1 = (String) request.getParameter("contacto1");
            String contacto2 = (String) request.getParameter("contacto2");
            String email = (String) request.getParameter("email");
            String sexo = (String) request.getParameter("sexo");
            String idestadocivil = (String) request.getParameter("estadocivil");
            String iddistrito = (String) request.getParameter("distrito");
            String linhaendereco1 = (String) request.getParameter("endereco1");
            String linhaendereco2 = null;
            String password = (String) request.getParameter("password");
            int role_id = 3;
            
            if (session.getAttribute("role_name").equals("ADMIN")){  
              role_id = Integer.parseInt(request.getParameter("role_id"));
            }
            User user = new User();

            user.setName(nome);
            user.setLastName(apelido);
            user.setEmail(email);
            user.setPassword(password);
            user.setActive(1);
           // SimpleDateFormat df = new Symp
            Date d = new Date();
            //LocalDateTime d = LocalDateTime;
            user.setDataAdded(new Date());
            user.setRoleId(new Role(role_id));
            new UserJpaController(emf).create(user);
            System.out.println("User Created: "+user.toString());

            Cliente cliente = new Cliente();
            
            cliente.setNrBi(nr_documento);
            cliente.setContacto1(contacto1);
            cliente.setContacto2(contacto2);
          
            cliente.setIdsexo(new Sexo(Long.parseLong(sexo)));
            cliente.setIdestadocivil(new Estadocivil(Integer.parseInt(idestadocivil)));
            cliente.setIddistrito(new Distrito(Integer.parseInt(iddistrito)));
            
            cliente.setLinhaendereco1(linhaendereco1);
            cliente.setLinhaendereco2(linhaendereco2);
            cliente.setIdcliente(user.getUserId().intValue());
            //cliente.setUser(user);

            new ClienteJpaController(emf).create(cliente);
            
            session.setAttribute("username", user.getEmail());
            session.setAttribute("password", user.getPassword());
            request.setAttribute("user", user);
            
            System.out.println("Cliente Created: "+cliente.toString());
            response.sendRedirect("/microsapp/tamplates/success.jsp");

//        if(request.getParameter("func").equals("")){}  
//        
//        }
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
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
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
