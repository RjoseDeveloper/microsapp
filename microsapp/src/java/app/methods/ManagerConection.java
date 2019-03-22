/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.methods;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Raimundo Jose
 */
public class ManagerConection {
    
    EntityManagerFactory emf = null;
    public EntityManagerFactory getCon(){
      return (Persistence.createEntityManagerFactory("microsappPU"));
    }
}
