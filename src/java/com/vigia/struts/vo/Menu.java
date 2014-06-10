/*
 * Clase Menu, se encarga de obner y armar el menu de la BD
 */
package com.vigia.struts.vo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author hsendoa
 */
public class Menu {

    private int _idUsuario = 0;
    private UserVO _user;

    public Menu(String usuario) {
        _user = new UserVO(usuario);
        _user.validar();
    }
    
    public UserVO getUser(){
        return this._user;
    }

    public String getMenu() {
        String menu = "";

        Conexion con = new Conexion();
        ResultSet rs = null;
        String menusIzquierda = "select * from vigia.sitemap t where t.perfil like '%admin%' and t.padre=0";
        String menusDerecha = "select * from vigia.sitemap t where t.perfil like '%admin%' and t.padre=-1";
        
        //Muestra los menus de la aplicacion que van del lado izquierdo
        menu += getHTMLMenu(con, rs, menusIzquierda, null);
        
        //Muerstra los menus de la aplicacion que van del lado derecho
        menu += "<div class='collapse navbar-collapse pull-right'>";
        menu += getHTMLMenu(con, rs, menusDerecha, this.getUser().getNombreCompleto());
        menu += "</div>";
        return menu;
    }
    
    public String getHTMLMenu(Conexion con, ResultSet rs, String sqlQuery, String staticEntry){
        if (null == staticEntry){
            staticEntry = "";
        }
        String menu = "";
        try {
            rs = con.ejecutaSQL(sqlQuery);
            menu += "<ul class='nav navbar-nav'>";
            menu += "<li><a><strong>" + staticEntry + "</strong></a></li>";
            while (rs.next()) {
                menu += "<li>";
                menu += "<a href='" + rs.getString("url") + "' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown'>" + rs.getString("nombre");
                if (rs.getInt("hijos") == 1) {
                    menu += "<b class='caret'></b></a>";
                    menu += "<ul class='dropdown-menu'>";
                    String subconsulta = "select * from vigia.sitemap t where t.perfil like '%admin%' and t.padre=" + rs.getInt("id");

                    ResultSet rsSub = con.ejecutaSQL(subconsulta);
                    while (rsSub.next()) {
                        menu += "<li>";
                        menu += "<a href='" + rsSub.getString("url") + "'>" + rsSub.getString("nombre") + "</a></li>";

                    }
                    menu += "</ul>";
                } else {
                    menu += "</a>";
                }

                menu += "</li>";

                //break;//una sola ves consulta
            }
            menu += "</ul>";
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menu;
    }

}
