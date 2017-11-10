package com.example.amandaspolti.todoapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cecilia on 12/1/16.
 */
public class UsuarioDAO extends AppCompatActivity {
    public static final String PREFS_NAME = "usersharedpreferences";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private String password;
    private String username;
    private SharedPreferences seetings;

    private static UsuarioDAO ourInstance = new UsuarioDAO();

    public static UsuarioDAO getInstance() {
        return ourInstance;
    }

    private UsuarioDAO() {   }

    public boolean tryLogin(String username, String password) {
        return (this.getUsername().equals(username) && this.getSenha().equals(password));
    }

    public boolean tryCadastrar(String username, String password) {
        if (this.getUsername() != null)
            return false;
        else {
            SharedPreferences.Editor ed = seetings.edit();
            ed.putString(USERNAME, username);
            ed.putString(PASSWORD, password);
            ed.commit();
            return true;
        }

    }

    public String getUsername() {
        if(username!=null)
            return username;
        else
            return seetings.getString(USERNAME, null);
    }

    public String getSenha(){
        if(password!= null)
            return password;
        else
            return seetings.getString(PASSWORD,null);
    }

    public void setSeetings(SharedPreferences seetings) {
        this.seetings = seetings;
        this.password = seetings.getString(PASSWORD, null);
        this.username = seetings.getString(USERNAME, null);
    }

    public boolean exists(){
        return this.getUsername() != null;
    }
}
