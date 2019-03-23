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
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        try (PrintWriter out = response.getWriter()) {

            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
            String action = (String) request.getParameter("action");
            List<User> lstUser = new ArrayList<>();
          

            switch (action) {
                case "list":
                    try {
                        
                       lstUser = new UserJpaController(emf).findUserEntities();
                        JsonElement element = gson.toJsonTree(lstUser, new TypeToken<List<User>>() {
                        }.getType());
                        
                        JsonArray jsonArray = element.getAsJsonArray();
                        String listData = jsonArray.toString();

                        listData = "{\"Result\":\"OK\",\"Records\":" + listData + "}";
                        response.getWriter().print(listData);

                    } catch (Exception e) {
                        String error = "{\"Result\":\"ERROR\",\"Message\":" + e.getMessage() + "}";
                        response.getWriter().print(error);
                    }

//                    try {
//
//                        json.put("Result", "OK");
//                        //json.put("Records", lstUser);
//                        
////                         JSONArray array = new JSONArray();
////        
////        JSONObject user1 = new JSONObject();
////        user1.put("name", "TOM");
////        user1.put("age", "26");
////        JSONObject user2 = new JSONObject();
////        user2.put("name", "ASB");
////        user2.put("age", "26");
////        
////        array.put(user1);
////        array.put(user2);
//
//                        response.getWriter().write(array.toString());
//
//                    } catch (Exception ex) {
//
//                        response.getWriter().print("errpor");
//                    }
                    break;
                case "create":
                    break;
                case "update":
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
