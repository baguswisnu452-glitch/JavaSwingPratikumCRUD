/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author idaba
 */

//  menyimpan memori login pengguna selama aplikasi berjalan
public class UserSession {
    private static String username;
    private static String role; // Isi nilai bisa: "Admin", "Lecturer", atau "Student"

    public static void setSession(String user, String userRole) {
        username = user;
        role = userRole;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static void clearSession() {
        username = null;
        role = null;
    } 
}
