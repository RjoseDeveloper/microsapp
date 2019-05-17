/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.CreditoJpaController;
import app.controller.UserJpaController;
import app.controller.exceptions.NonexistentEntityException;
import app.model.Credito;
import app.model.Estado;
import app.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Raimundo Jose
 */
public class CRUDController extends HttpServlet {

    private Object ex;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws app.controller.exceptions.NonexistentEntityException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NonexistentEntityException, Exception {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {

            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
            Gson gson = new GsonBuilder().create();
            
            String action = (String) request.getParameter("action");
            List<User> lstUser = new UserJpaController(emf).findUserEntities(4, 1);

            switch (action) {
                case "list":

                    try {

                        JSONObject object = new JSONObject();
                        for (User u : lstUser) {
                            object.accumulate("userid", u.getCliente().getNrBi());
                            object.accumulate("lastname", u.getLastName());
                            object.accumulate("firstname", u.getName());
                            object.accumulate("email", u.getEmail());
                        }
                      
                        out.print(object);

                    } catch (Exception ex) {
                        String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "rjose" + "}";
                        response.getWriter().print(error);
                        ex.printStackTrace();
                    }

                    break;
                case "create":

                    //Convert Java Object to Json				
                    String json = gson.toJson(toString());
                    //Return Json in the format required by jTable plugin
                    String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
                    response.getWriter().print(listData);
                    break;
                    
                case "update":

                    Credito c = new CreditoJpaController(emf).findCredito(Integer.parseInt(request.getParameter("idcredito")));
                    c.setIdestado(new Estado(4L)); // 4L estado autorizado;
                    new CreditoJpaController(emf).edit(c);

                    out.print("<div class=\"sufee-alert alert with-close alert-success alert-dismissible fade show\">\n"
                            + "                                <span class=\"badge badge-pill badge-success\">Autorizado com sucesso</span></div>");

                    break;

                case "delete":
                    String userid = (String) request.getParameter("userid");
                    
                    listData = "{\"Result\":\"OK\"}";
                    response.getWriter().print(listData);
                    break;
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
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
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
