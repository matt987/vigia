/*
 * CLASE CONEXION crea una conexion de modo a ser utilizada, provee dos metodos para metodos dml
ejecuta select
inserta insert, delete, update
 */
package com.vigia.struts.vo;

import com.vigia.struts.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;

/**
 *
 * @author hsendoa
 */
public class Conexion {

    public static String bdServer = "";
    public static String bdUser = "";
    public static String bdPass = "";

    //public static Connection con = null;
    private Connection getConexion() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vigia", "root", "");

            //}catch(IOException | ClassNotFoundException | SQLException e){
        } catch (SQLException e) {
            //System.err.printf("Error MYSQL-0001: Error Al obtener la conexion en Conexion.getConexion()"+ e.toString());
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error MYSQL-0001: Error Al obtener la conexion en Conexion.getConexion()", "Error aca: " + e);
        }
        return con;
    }

    public ResultSet ejecutaSQL(String sentenciaSQL) throws SQLException, ClassNotFoundException, IOException {

        Connection con = this.getConexion();
        if (con != null && !sentenciaSQL.isEmpty()) {
            PreparedStatement pst = null;
            pst = con.prepareStatement(sentenciaSQL);
            ResultSet rs = null;
            rs = pst.executeQuery();
            //con.close();
            return rs;
        } else {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error MYSQL-0002: Error Al ejecutar la consulta en Conexion.ejecutarSQL(" + sentenciaSQL + ")", "Error aca: ");
        }
        return null;
    }

    public int insertarSQL(String sentenciaSQL) throws SQLException, ClassNotFoundException, IOException {

        Connection con = this.getConexion();
        if (con != null && !sentenciaSQL.isEmpty()) {
            PreparedStatement pst = null;
            pst = con.prepareStatement(sentenciaSQL);
            int resutado = pst.executeUpdate();
            //con.close();
            return resutado;
        } else {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error MYSQL-0002: Error Al ejecutar la consulta en Conexion.ejecutarSQL(" + sentenciaSQL + ")", "Error aca: ");
        }
        return 0;
    }
    
}
