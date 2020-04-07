/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyw
 */
public class Seguranca {
    private static MessageDigest md;
    public  static String criptografe(String texto) {
        StringBuilder builder = new StringBuilder();
        try {
            md =  MessageDigest.getInstance("MD5");
            byte message[] = md.digest(texto.getBytes("UTF-8"));
                        for (byte b : message) {
                builder.append(String.format("%02x", 0xFF & b));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return builder.toString();
    }
}
