/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.servlets;

import app.controller.TipocreditoJpaController;
import app.model.Tipocredito;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jdk.nashorn.internal.objects.NativeArray.map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Raimundo Jose
 */
public class GetJson extends HttpServlet {

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
            throws ServletException, IOException, JSONException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

            switch (Integer.parseInt(request.getParameter("acao"))) {
                case 1:

                    SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar cal = Calendar.getInstance();
                  
                    int id = Integer.parseInt(request.getParameter("idtipocredito"));
                    Tipocredito tipo = new TipocreditoJpaController(emf).findTipocredito(id);
          
                    int days = 30;
                    
                    JSONObject json = new JSONObject();
                    json.accumulate("juro", tipo.getJuro());
                    json.accumulate("pgto", tipo.getPgto());
                    json.accumulate("status", tipo.getStatus());
                    
                    cal.add(Calendar.DATE, days);
                    String di = sf.format(cal.getTime());
                    
                    json.accumulate("di", di);
                    cal.add(Calendar.MONTH, tipo.getPgto() - 1);
                    
                    String df = sf.format(cal.getTime());
                    json.accumulate("df", df);

                    response.getWriter().write(json.toString());

                    break;
                case 2:
                    break;
            }

//            List<Tipocredito> list = new ArrayList<Tipocredito>();
//            list.add("Sunday");
//            list.add("Monday");
//            list.add("Tuesday");
//            json.accumulate("weekdays", list);
//            System.out.println(json.toString());
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
        } catch (JSONException ex) {
            Logger.getLogger(GetJson.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (JSONException ex) {
            Logger.getLogger(GetJson.class.getName()).log(Level.SEVERE, null, ex);
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
