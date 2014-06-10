package com.vigia.struts.vo;

import com.vigia.struts.clientes.ClientesActionFormBean;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Logger;


/**
 *
 * @author matts
 */
public class ClienteVO extends BaseVO {


    private int _id;
    private String _nombre;
    private String _apellido;
    private String _direccion;
    private String _ci;
    private String _ruc;
    private String _telefono;

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
    
    //constructor con datos
    public ClienteVO(int id, String nombre, String apellido, String direccion, String ci, String ruc, String telefono ) {
        super(); //satisface la superclase
        //agrega nuevas caracteristicas 
        setNombre(nombre);
        setApellido(apellido);
        setDireccion(direccion);
        setCi(ci);
        setRuc(ruc);
        setTelefono(telefono);
   }    

    public String getCi() {
        return _ci;
    }

    private void setCi(String _ci) {
        this._ci = _ci;
    }

    public String getRuc() {
        return _ruc;
    }

    private void setRuc(String _ruc) {
        this._ruc = _ruc;
    }

    public String getTelefono() {
        return _telefono;
    }

    private void setTelefono(String _telefono) {
        this._telefono = _telefono;
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
    
    public static ClienteVO editar(ClientesActionFormBean cfb) throws SQLException {
       ClienteVO cliente = ClienteVO.findByCi(cfb.getCi());
       String consulta = "UPDATE clientes set nombre='" + cfb.getNombre() 
                         + "', apellido='" + cfb.getApellido()
                         + "', direccion=' " + cfb.getDireccion()
                         + "', ci=' " + cfb.getCi()
                         + "', ruc= '" + cfb.getRuc()
                         + "', telefono=' " + cfb.getTelefono()
                         + "' where ci='" + cfb.getCi() + "' and eliminado=0";
        Conexion con = new Conexion();
        int ii = 0;
        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(ClienteVO.class.getName()).log(Level.SEVERE, "Error en ClienteVO.crear " + ex.toString(), "Error aca: " + ex);
        }        

       return cliente;
    }
    
    public static ClienteVO crear(ClientesActionFormBean cfb){
        ClienteVO cliente = new ClienteVO(cfb.getId(), cfb.getNombre(), cfb.getApellido(), cfb.getDireccion(), cfb.getCi(), cfb.getRuc(), cfb.getTelefono());
        String consulta = "INSERT INTO vigia.clientes (`nombre`, `apellido`, `direccion`, `ci`, `ruc`, `telefono`) VALUES ('" + cliente.getNombre() + "', '" + cliente.getApellido() + "', '" + cliente.getDireccion() + "', '" + cliente.getCi() + "' , '" + cliente.getRuc() + "', '" + cliente.getTelefono() + "')";
        Conexion con = new Conexion();
        int ii = 0;
        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(ClienteVO.class.getName()).log(Level.SEVERE, "Error en ClienteVO.crear " + ex.toString(), "Error aca: " + ex);
        }        
        return cliente;
    }
    
    public static boolean eliminar(String ci){
        int ii = 0;
        //CREA LA CONEXION
        Conexion con = new Conexion();
        
        String consulta = "UPDATE clientes SET eliminado=1 WHERE ci='" + ci + "' and eliminado=0";
        Logger.getLogger(ClienteVO.class.getName()).log(Level.INFO, consulta, "- consulta");

        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Error en ClienteVO.eliminar " + ex.toString(), "Error aca: " + ex);
            return false;
        }
        return true;
    }
    
    public List<ClienteVO> selectClienteInfo() throws SQLException {
        List<ClienteVO> al = new ArrayList<ClienteVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.clientes where eliminado=0";

// @formatter:on
            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, resultSet.toString(),"");
                while (resultSet.next()) {
                    
                    ClienteVO cliente = new ClienteVO(resultSet.getInt("id"),resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion"), resultSet.getString("ci"), resultSet.getString("ruc"), resultSet.getString("telefono") );
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
    
    public static ClienteVO findByCi(String ci) throws SQLException{
        String query
                = " SELECT * "
                + " FROM "
                + " vigia.clientes where ci =" + ci + " AND eliminado=0 limit 1";     
        ClienteVO cliente = null;
        Conexion con = new Conexion();
        try (ResultSet resultSet = con.ejecutaSQL(query)) {
            while (resultSet.next()) {

                cliente = new ClienteVO(resultSet.getInt("id"),resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion"), resultSet.getString("ci"), resultSet.getString("ruc"), resultSet.getString("telefono") );
                Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Cliente:" + cliente.getId() + cliente.getNombre()+ cliente.getDireccion(), "");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cliente;
    }
    
    public List<ClienteVO> selectClienteInfo(String search) throws SQLException {
        List<ClienteVO> al = new ArrayList<ClienteVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.clientes where eliminado=0"
                    + " and (nombre like '%" + search + "%'"
                    + "   OR apellido like '%" + search + "%'"
                    + "   OR direccion like '%" + search + "%')";

// @formatter:on
        Conexion con = new Conexion();
        try (ResultSet resultSet = con.ejecutaSQL(query)) {
            while (resultSet.next()) {

                ClienteVO cliente = new ClienteVO(resultSet.getInt("id"),resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion"), resultSet.getString("ci"), resultSet.getString("ruc"), resultSet.getString("telefono") );
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
