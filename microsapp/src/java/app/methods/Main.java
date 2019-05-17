/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.methods;

import app.controller.TipocreditoJpaController;
import app.model.Tipocredito;
import app.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.SimpleFormatter;
import javax.persistence.EntityManagerFactory;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Raimundo Jose
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException {
        // TODO code application logic here

        //System.out.println(""+new Functions().getSomaCredito(1, 3));
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        EntityManagerFactory emf = new ManagerConection().getCon();

//        Calendar cal = Calendar.getInstance();
//        int days = 30;
//        int mes = 3;
//        cal.add(Calendar.DATE, days);
//        System.out.println("/DateINI: "+sf.format(cal.getTime()));
//        
//        cal.add(Calendar.MONTH, mes-1);
//        System.out.println("/DateF: "+sf.format(cal.getTime()));
        //System.out.println("Data: "+new Functions().getSomaCredito(3, true));
        
        List<Tipocredito> list = new TipocreditoJpaController(emf).findTipocreditoEntities();

        JSONObject object = new JSONObject();
        JsonArray array = new JsonArray();
        Gson gson = new Gson();

        for (Tipocredito tpc : list) {
            object.accumulate("nome", tpc.getDescricao());
            object.accumulate("juro", tpc.getJuro());
        }

    }

}
