package com.vigia.struts.vo;

import com.vigia.struts.empleados.EmpleadosActionFormBean;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matts
 */
public class EmpleadoVO extends BaseVO {

    private int _id;
    private String _nombre;
    private String _apellido;
    private String _direccion;
    private String _ci;
    private String _ruc;
    private String _telefono;
    private int _empleadoId;

    // Constructor por defecto. 
    public EmpleadoVO() {
        super();

    }

    public EmpleadoVO(int id) {
        super();
        this._id = id;
        this._empleadoId = id;
        getFromDB();
        //validarId();
    }

    //constructor con datos
    public EmpleadoVO(int empleadoId, String nombre, String apellido, String direccion) {
        super(); //satisface la superclase
        //agrega nuevas caracteristicas 
        setEmpleadoId(empleadoId);
        setNombre(nombre);
        setApellido(apellido);
        setDireccion(direccion);
    }

    //constructor con datos
    public EmpleadoVO(int id, String nombre, String apellido, String direccion, String ci, String ruc, String telefono) {
        super(); //satisface la superclase
        //agrega nuevas caracteristicas 

        setNombre(nombre);
        setApellido(apellido);
        setEmpleadoId(id);
        setDireccion(direccion);
        setCi(ci);
        setRuc(ruc);
        setTelefono(telefono);
    }

    public int getEmpleadoId() {
        return _empleadoId;
    }

    private void setEmpleadoId(int empleadoId) {
        this._empleadoId = empleadoId;
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

    private String setNombre(String nombre) {
        return this._nombre = nombre;
    }

    public String getNombre() {
        return this._nombre;
    }

    public String getDireccion() {
        return this._direccion;
    }

    private String setDireccion(String direccion) {
        return this._direccion = direccion;
    }

    public String getApellido() {
        return _apellido;
    }

    private void setApellido(String apellido) {
        this._apellido = apellido;
    }

    public String getNombreCompleto() {
        return this.getApellido() + " " + this.getNombre();
    }

    public static EmpleadoVO editar(EmpleadosActionFormBean efb) throws SQLException {
        EmpleadoVO empleado = EmpleadoVO.findByCi(efb.getCi());
        String consulta = "UPDATE empleados set nombre='" + efb.getNombre()
                + "', apellido='" + efb.getApellido()
                + "', direccion=' " + efb.getDireccion()
                + "', ci=' " + efb.getCi()
                + "', ruc= '" + efb.getRuc()
                + "', telefono=' " + efb.getTelefono()
                + "' where ci='" + efb.getCi() + "' and eliminado=0";
        Conexion con = new Conexion();
        int ii = 0;
        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "Error en EmpleadoVO.editar " + ex.toString(), "Error aca: " + ex);
        }

        return empleado;
    }

    public static EmpleadoVO crear(EmpleadosActionFormBean efb) {
        EmpleadoVO empleado = new EmpleadoVO(efb.getId(), efb.getNombre(), efb.getApellido(), efb.getDireccion(), efb.getCi(), efb.getRuc(), efb.getTelefono());
        String consulta = "INSERT INTO vigia.empleados (`nombre`, `apellido`, `direccion`, `ci`, `ruc`, `telefono`, `tipo_empleado`) VALUES ('" + empleado.getNombre() + "', '" + empleado.getApellido() + "', '" + empleado.getDireccion() + "', '" + empleado.getCi() + "' , '" + empleado.getRuc() + "', '" + empleado.getTelefono() + "', 'vendedor')";
        Conexion con = new Conexion();
        int ii = 0;
        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "Error en ClienteVO.crear " + ex.toString(), "Error aca: " + ex);
        }
        return empleado;
    }

    public static boolean eliminar(String ci) {
        int ii = 0;
        //CREA LA CONEXION
        Conexion con = new Conexion();

        String consulta = "UPDATE empleados SET eliminado=1 WHERE ci='" + ci + "' and eliminado=0";
        Logger.getLogger(EmpleadoVO.class.getName()).log(Level.INFO, consulta, "- consulta");

        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "Error en EmpleadoVO.eliminar " + ex.toString(), "Error aca: " + ex);
            return false;
        }
        return true;
    }

    public List<EmpleadoVO> selectEmpleadoInfo() throws SQLException {
        List<EmpleadoVO> al = new ArrayList<EmpleadoVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.empleados where eliminado=0";

// @formatter:on
            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                while (resultSet.next()) {

                    EmpleadoVO empleado = new EmpleadoVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion"), resultSet.getString("ci"), resultSet.getString("ruc"), resultSet.getString("telefono"));
                    Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "Cliente:" + empleado.getId() + empleado.getNombre() + empleado.getDireccion(), "");
                    al.add(empleado);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public static EmpleadoVO findByCi(String ci) throws SQLException {
        String query
                = " SELECT * "
                + " FROM "
                + " vigia.empleados where ci =" + ci + " AND eliminado=0 limit 1";
        EmpleadoVO empleado = null;
        Conexion con = new Conexion();
        try (ResultSet resultSet = con.ejecutaSQL(query)) {
            while (resultSet.next()) {

                empleado = new EmpleadoVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion"), resultSet.getString("ci"), resultSet.getString("ruc"), resultSet.getString("telefono"));
                Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Cliente:" + empleado.getId() + empleado.getNombre() + empleado.getDireccion(), "");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empleado;
    }

    public static EmpleadoVO findByEmpleadoId(int id) {
        String query
                = " SELECT * "
                + " FROM "
                + " vigia.empleados where id =" + String.valueOf(id) + " AND eliminado=0 limit 1";
        EmpleadoVO empleado = new EmpleadoVO();
        Conexion con = new Conexion();
        try (ResultSet resultSet = con.ejecutaSQL(query)) {
            while (resultSet.next()) {

                empleado = new EmpleadoVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion"), resultSet.getString("ci"), resultSet.getString("ruc"), resultSet.getString("telefono"));
                //Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "GrissopnNNNNN:" + empleado.getId() + empleado.getNombre() + empleado.getDireccion(), "");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "ClassNotFoundException", ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "IOException", ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "SQLException", ex);
        }

        return empleado;
    }

    public List<EmpleadoVO> selectEmpleadoInfo(String search) throws SQLException {
        List<EmpleadoVO> al = new ArrayList<EmpleadoVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.empleados where eliminado=0"
                    + " and nombre like '%" + search + "%'"
                    + "   OR apellido like '%" + search + "%'"
                    + "   OR direccion like '%" + search + "%'";

// @formatter:on
            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                while (resultSet.next()) {

                    EmpleadoVO empleado = new EmpleadoVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion"), resultSet.getString("ci"), resultSet.getString("ruc"), resultSet.getString("telefono"));
                    Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Cliente:" + empleado.getId() + empleado.getNombre() + empleado.getDireccion(), "");
                    al.add(empleado);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public static List<EmpleadoVO> withoutUser() throws SQLException {
        List<EmpleadoVO> empleados = new ArrayList<EmpleadoVO>();
        String query = "SELECT * from vigia.empleados e "
                + " WHERE e.eliminado=0 and e.id not in("
                + " SELECT empleado_id"
                + " FROM  vigia.usuarios WHERE eliminado = 0 AND empleado_id is not null)";
        Conexion con = new Conexion();
        try (ResultSet resultSet = con.ejecutaSQL(query)) {
            while (resultSet.next()) {

                EmpleadoVO empleado = new EmpleadoVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("direccion"), resultSet.getString("ci"), resultSet.getString("ruc"), resultSet.getString("telefono"));
                empleados.add(empleado);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleados;
    }

    private void getFromDB() {
        String consulta = "SELECT * FROM vigia.empleados WHERE eliminado = 0 AND id = " + getEmpleadoId() + " LIMIT 1";
        Conexion con = new Conexion();
        ResultSet resultset = null;
        try {
            resultset = con.ejecutaSQL(consulta);
            if (resultset.next()) {
                setNombre(resultset.getString("nombre"));
                setApellido(resultset.getString("apellido"));
                setDireccion(resultset.getString("direccion"));
                setCi(resultset.getString("ci"));
                setRuc(resultset.getString("ruc"));
                setTelefono(resultset.getString("telefono"));
                setEmpleadoId(resultset.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
