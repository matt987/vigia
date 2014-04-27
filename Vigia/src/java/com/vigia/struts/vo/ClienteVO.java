package com.vigia.struts.vo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author matts
 */
public class ClienteVO extends BaseVO {
    private int _id;
    private String _nombre;
    private String _apellido;
    private String _direccion;
    
    // Constructor por defecto. 
    public ClienteVO() {
        super();
        
    }
    
    public ClienteVO(int id) {
        super();
        this._id = id;
        //validarId();
    }
    //constructor con datos
    public ClienteVO(int id, String nombre, String apellido, String direccion) {
        super(); //satisface la superclase
        //agrega nuevas caracteristicas 
        setNombre(nombre);
        setApellido(apellido);
        setDireccion(direccion);
    }
    
    private String setNombre(String nombre){
        return this._nombre = nombre;
    }
    public String getNombre(){
        return this._nombre;
    }
    
    public String getDireccion(){
        return this._direccion;
    }
    
    private String setDireccion(String direccion){
        return this._direccion = direccion;
    }
    
        public String getApellido() {
        return _apellido;
    }

    private void setApellido(String apellido) {
        this._apellido = apellido;
    }
    
    public String getNombreCompleto(){
        return this.getApellido() + " " + this.getNombre();
    }
    
    

    
    public List<ClienteVO> selectClienteInfo() throws SQLException {
        List<ClienteVO> al = new ArrayList<ClienteVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.Clientes where eliminado=0";

// @formatter:on
            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                while (resultSet.next()) {
                    
                    ClienteVO cliente = new ClienteVO(resultSet.getInt("id"),resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion") );
                    Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Cliente:" + cliente.getId() + cliente.getNombre()+ cliente.getDireccion(), "");
                    al.add(cliente);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    public List<ClienteVO> selectClienteInfo(String search) throws SQLException {
        List<ClienteVO> al = new ArrayList<ClienteVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.Clientes where eliminado=0"
                    + " WHERE nombre like '%" + search + "%'"
                    + "   OR apellido like '%" + search + "%'"
                    + "   OR direccion like '%" + search + "%'"
                    + "   OR tipo_cliente like '%" + search + "%'";

// @formatter:on
            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                while (resultSet.next()) {
                    
                    ClienteVO cliente = new ClienteVO(resultSet.getInt("id"),resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion") );
                    Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Cliente:" + cliente.getId() + cliente.getNombre()+ cliente.getDireccion(), "");
                    al.add(cliente);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    
}
