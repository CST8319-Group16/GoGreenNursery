package org.cst8319.gogreen;

import org.cst8319.gogreen.DTO.User;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;;
import java.util.Base64;

public class Utils {


    //return true if user is not logged in
    public static boolean checkLoginAndRedirect(HttpSession session, HttpServletResponse response) {
        if (getCurrentUser(session) == null) {
            try {
                response.sendRedirect("login.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    public static User getCurrentUser(HttpSession session) {
        if (session == null) {
            return null;
        }
        return (User) session.getAttribute("currentUser");
    }



    public static String hashPassword(final char[] password) {
        try {
            byte[] bytes = new String(password).getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(bytes);

            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(hash);
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException ex) {
            throw new RuntimeException("Could not hash password", ex);
        }
    }

}
