/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gleywson
 */
public class HibernateUtil {

    public static EntityManagerFactory factory = null;
    
    static {
        init();
    }

    private static void init() {
        try {
            if(factory == null) {
                factory = Persistence.createEntityManagerFactory("EmpatiaPU");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
