/*
 * Clase base Usuario, muestra como el objeto UserVO representa a un usuario.
 */
package com.vigia.struts.vo;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hsendoa
 */
// Clase Value Object que implementa una vista del usuario.
public class UserVO extends BaseVO {

    private String _apellido;
    private String _nombre;
    private String _email;
    private String _password;
    private int _id;
    private int _empleadoId;
    private int _usuarioId;
    private EmpleadoVO _empleado;


    /*Constructores*/
    public UserVO() {
        super();
    }

    public UserVO(String nombre) {
        super();
        setNombre(nombre);
        boolean esU = this.esUsuario();
        if (esU == true) {
            getFromDB();
        }
    }

    public UserVO(String nombre, String password) {
        super();
        setNombre(nombre);
        setPassword(encriptar(password));
        validar();
    }

    public UserVO(String nombre, int empleadoId) {
        super();
        setNombre(nombre);
        setEmpleadoId(empleadoId);
    }

    public UserVO(String nombre, String password, int id) {
        super();
        setNombre(nombre);
        setPassword(encriptar(password));
        setId(id);
        validar();
    }

    public UserVO(String nombre, String password, int id, int empleadoId) {
        super();
        setNombre(nombre);
        setPassword(encriptar(password));
        setEmpleadoId(empleadoId);
        setId(id);
        validar();
    }

    public UserVO(String nombre, int id, int empleadoId) {
        super();
        setNombre(nombre);
        setEmpleadoId(empleadoId);
        setUsuarioId(id);
        Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "CHACHACHA" + String.valueOf(this.getUsuarioId()), "-");
        validar();
    }

    public UserVO(int id) {
        super();
        this._id = id;
        boolean a = validarId();
    }

    private void getFromDB() {
        ResultSet resultset = null;
        Conexion con = new Conexion();
//        String consulta = "SELECT * FROM vigia.usuarios where eliminado = 0 AND login ='" + getNombre() + "' LIMIT 1";
        String consulta = "select * from vigia.usuarios t where t.login='" + getNombre() + "' AND eliminado=0 LIMIT 1";
        try {
            resultset = con.ejecutaSQL(consulta);
            if (resultset.next()) {
                setUsuarioId(resultset.getInt("id"));
                setEmpleadoId(resultset.getInt("empleado_id"));
                setEmpleado(new EmpleadoVO(getEmpleadoId()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EmpleadoVO getEmpleado() {
        return _empleado;
    }

    public void setEmpleado(EmpleadoVO _empleado) {
        this._empleado = _empleado;
    }

    public int getUsuarioId() {
        return _usuarioId;
    }

    public void setUsuarioId(int _usuarioId) {
        this._usuarioId = _usuarioId;
    }

    public int getEmpleadoId() {
        return _empleadoId;
    }

    private void setEmpleadoId(int _empleadoId) {
        this._empleadoId = _empleadoId;
    }

    public String getNombreEmpleado() {
        EmpleadoVO empleado = getEmpleado();
        if (empleado == null) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "AAAAAAAAAAAAAAA" + this.getEmpleadoId() + "// " + this.getNombre(), "");
            empleado = EmpleadoVO.findByEmpleadoId(this.getEmpleadoId());
        }
        return empleado.getNombreCompleto();
    }

    public String getDireccionEmpleado() {
        EmpleadoVO empleado = getEmpleado();
        if (empleado == null) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "AAAAAAAAAAAAAAA" + this.getEmpleadoId() + "// " + this.getNombre(), "");
            empleado = EmpleadoVO.findByEmpleadoId(this.getEmpleadoId());
        }
        return empleado.getDireccion();
    }
    /*metodos get and set*/

    public int getId() {
        return this._id;
    }
    /*private void setId(int id){
     this._id=id;
     }*/

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getNombre() {
        return _nombre;
    }

    private void setNombre(String nombre) {
        this._nombre = nombre;
    }

    private void setApellido(String apellido) {
        this._apellido = apellido;
    }

    public String getApellido() {
        return _apellido;
    }

    public String getEmail() {
        return _email;
    }

    private void setEmail(String email) {
        this._email = email;
    }

    public String getNombreCompleto() {
        String nombre = this.getNombre() == null ? "" : this.getNombre();
        String apellido = this.getApellido() == null ? "" : this.getApellido();
        return apellido + " " + nombre;
    }

    public boolean validar() {

        String nn = "";
        String pp = "";
        int ii = 0;

        Conexion con = new Conexion();
        ResultSet rs = null;
        String consulta = "select * from vigia.usuarios t where t.login='" + getNombre() + "' and password='" + getPassword() + "' and eliminado=0";
        //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            rs = con.ejecutaSQL(consulta);

            //return true; 
            while (rs.next()) {
                pp = rs.getString("password");
                nn = rs.getString("login");
                ii = rs.getInt("id");

                //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, ii+"-Ingresado:"+this.getPassword()+"- BD:"+pp, "-");
                if (ii > 0) {
                    this.setNombre(nn);
                    this.setPassword(pp);
                    this.setId(ii);
                    return true;
                }
                //break;//una sola ves consulta
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.validar " + ex.toString(), "Error aca: " + ex);
        }
        return false;
    }

    public boolean validarId() {

        String nn = "";
        String pp = "";
        int ii = 0;

        Conexion con = new Conexion();
        ResultSet rs = null;
        String consulta = "select * from vigia.usuarios t where t.id=" + this._id + " and eliminado=0";
        //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            rs = con.ejecutaSQL(consulta);

            //return true; 
            while (rs.next()) {
                pp = rs.getString("password");
                nn = rs.getString("login");
                ii = rs.getInt("id");

                //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, ii+"-Ingresado:"+this.getPassword()+"- BD:"+pp, "-");
                if (ii > 0) {
                    this.setNombre(nn);
                    this.setPassword(pp);
                    this.setId(ii);
                    return true;
                }
                //break;//una sola ves consulta
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.validarId " + ex.toString(), "Error aca: " + ex);
        }
        return false;
    }

    public boolean esUsuario() {

        String nn = "";
        String pp = "";
        int ii = 0;

        Conexion con = new Conexion();
        ResultSet rs = null;
        String consulta = "select * from vigia.usuarios t where t.login='" + getNombre() + "' and eliminado=0";
        //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            rs = con.ejecutaSQL(consulta);

            //return true; 
            while (rs.next()) {
                pp = rs.getString("password");
                nn = rs.getString("login");
                ii = rs.getInt("id");

                //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, ii+"-Ingresado:"+this.getPassword()+"- BD:"+pp, "-");
                if (ii > 0) {
                    this.setNombre(nn);
                    this.setPassword(pp);
                    this._id = ii;
                    return true;
                }
                //break;//una sola ves consulta
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.validar " + ex.toString(), "Error aca: " + ex);
        }
        return false;
    }

    private String encriptar(String usuario) {
        String encriptado = "";
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(usuario.getBytes());
            byte[] digest = sha256.digest();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02x", digest[i]));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        encriptado = sb.toString();
        return encriptado;
    }

    public void crearUsuario() {
        int ii = 0;
        //crea la conexion
        Conexion con = new Conexion();
        //carga la consulta
        String consulta = "insert into vigia.usuarios(`login`, `password`, `empleado_id`) values ('" + getNombre() + "', '" + this.encriptar(getPassword()) + "', " + getEmpleadoId() + ");";
        //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            //ejecuta la consulta
            ii = con.insertarSQL(consulta);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.crear " + ex.toString(), "Error aca: " + ex);
        }

    }

    public void modificarUsuario() {

        int ii = 0;
        //crea la conexion
        Conexion con = new Conexion();
        //carga la consulta
        String consulta = "update vigia.usuarios set password='" + this.encriptar(getPassword()) + "' where login='" + getNombre() + "' and eliminado=0";
        //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            ii = con.insertarSQL(consulta);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.crear " + ex.toString(), "Error aca: " + ex);
        }
    }

    public void modificarUsuario(int id) {
        int ii = 0;
        //crea la conexion
        Conexion con = new Conexion();
        //carga la consulta
        String consulta = "update vigia.usuarios set login='" + this.getNombre() + "', password='" + this.encriptar(getPassword()) + "' where id='" + id + "' and eliminado=0";
        //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            ii = con.insertarSQL(consulta);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.modificar " + ex.toString(), "Error aca: " + ex);
        }
    }

    public void eliminarUsuario(int id) {
        int ii = 0;
        //CREA LA CONEXION
        Conexion con = new Conexion();
        Logger.getLogger(UserVO.class.getName()).log(Level.INFO, "holaa", "- consulta");

        String consulta = "update vigia.usuarios set eliminado=1, empleado_id=1 where id=" + id;
        Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "- consulta");

        try {
            ii = con.insertarSQL(consulta);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.eliminar " + ex.toString(), "Error aca: " + ex);
        }
    }

    public UserVO getUsuario(String id) throws SQLException {
        int ii = 0;
        UserVO usuario = null;
        Conexion con = new Conexion();
        String consulta = "SELECT * FROM vigia.usuarios WHERE eliminado=0 AND id=" + id;
        try {
            ResultSet resultSet = con.ejecutaSQL(consulta);
            usuario = new UserVO(resultSet.getString("login"));

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.eliminar " + ex.toString(), "Error aca: " + ex);
        }
        return usuario;
    }

    public List<UserVO> selectUsuariosInfo() throws SQLException {
        List<UserVO> al = new ArrayList<UserVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.usuarios where eliminado=0"
                    + " AND empleado_id not in (select id from empleados where eliminado=1)"
                    + " order by id desc ";

// @formatter:on
            Conexion con = new Conexion();
            ResultSet resultSet = con.ejecutaSQL(query);

            while (resultSet.next()) {

                UserVO usuario = new UserVO(resultSet.getString("login"));
                Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Usuario es:" + "$" + usuario.getUsuarioId() + "$" + usuario.getNombre() + "$" + resultSet.getInt("id"), "");
                al.add(usuario);
            }
            resultSet.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public List<UserVO> selectUsuariosInfo(String search) throws SQLException {
        List<UserVO> al = new ArrayList<UserVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.usuarios WHERE eliminado=0"
                    + " AND login LIKE '%" + search + "%'"
                    + " order by id desc ";

// @formatter:on
            Conexion con = new Conexion();
            ResultSet resultSet = con.ejecutaSQL(query);

            while (resultSet.next()) {

                UserVO usuario = new UserVO(resultSet.getString("login"));
                //Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Usuario es:" + usuario.getId() + usuario.getNombre(), "");
                al.add(usuario);
            }
            resultSet.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

}
