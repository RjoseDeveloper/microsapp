/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.UserJpaController;
import app.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raimundo Jose
 */
public class TestJson extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
             EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

             
            if (request.getParameter("action") != null) {
                List<User> lstUser = new ArrayList<User>();
                String action = (String) request.getParameter("action");
                Gson gson = new Gson();
                

                if (action.equals("list")) {
                    try {
                        //Fetch Data from User Table
                        lstUser = new UserJpaController(emf).findUserEntities();
                        //Convert Java Object to Json				
                        JsonElement element = gson.toJsonTree(lstUser, new TypeToken<List<User>>() {
                        }.getType());
                        JsonArray jsonArray = element.getAsJsonArray();
                        String listData = jsonArray.toString();
                        //Return Json in the format required by jTable plugin
                        
                        out.print(listData);
                        listData = "{\"Result\":\"OK\",\"Records\":" + listData + "}";
                        //response.getWriter().print(listData);
                    } catch (Exception ex) {
                        String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
                        response.getWriter().print(error);
                        ex.printStackTrace();
                    }
                } else if (action.equals("create") || action.equals("update")) {
                    User user = new User();
                    if (request.getParameter("userid") != null) {
                        int userid = Integer.parseInt(request.getParameter("userid"));
                        //user.setUserid(userid);
                    }
                    if (request.getParameter("firstName") != null) {
                        String firstname = (String) request.getParameter("firstName");
                        //user.setFirstName(firstname);
                    }
                    if (request.getParameter("lastName") != null) {
                        String lastname = (String) request.getParameter("lastName");
                        user.setLastName(lastname);
                    }
                    if (request.getParameter("email") != null) {
                        String email = (String) request.getParameter("email");
                        user.setEmail(email);
                    }
                    try {
                        if (action.equals("create")) {//Create new record
                            //dao.addUser(user);
                            lstUser.add(user);
                            //Convert Java Object to Json				
                            String json = gson.toJson(user);
                            //Return Json in the format required by jTable plugin
                            String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
                            response.getWriter().print(listData);
                        } else if (action.equals("update")) {//Update existing record
                            //dao.updateUser(user);
                            String listData = "{\"Result\":\"OK\"}";
                            response.getWriter().print(listData);
                        }
                    } catch (Exception ex) {
                        String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
                        response.getWriter().print(error);
                    }
                } else if (action.equals("delete")) {//Delete record
                    try {
                        if (request.getParameter("userid") != null) {
                            String userid = (String) request.getParameter("userid");
                            //dao.deleteUser(Integer.parseInt(userid));
                            String listData = "{\"Result\":\"OK\"}";
                            response.getWriter().print(listData);
                        }
                    } catch (Exception ex) {
                        String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
                        response.getWriter().print(error);
                    }
                }
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
