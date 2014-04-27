/*
 * Clase base Usuario, muestra como el objeto UserVO representa a un usuario.
 */
package com.vigia.struts.vo;

import com.vigia.struts.vo.BaseVO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    private int _id = 0;

   /*Constructores*/

    public UserVO()
     {
     super();
     }
    public UserVO(String nombre) {
        super();
        setNombre(nombre);
        boolean esU = this.esUsuario();
    }

    public UserVO(String nombre, String password) {
        super();
        setNombre(nombre);
        setPassword(encriptar(password));
        validar();
    }

    public UserVO(String nombre, String password, int id) {
        super();
        setNombre(nombre);
        setPassword(encriptar(password));
        setId(id);
        validar();
    }

    public UserVO(int id) {
        super();
        this._id = id;
        boolean a= validarId();
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
    
    public String getNombreCompleto(){
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
        String consulta = "select * from vigia.Usuarios t where t.usuario='" + getNombre() + "' and password='" + getPassword() + "' and eliminado=0";
            //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            rs = con.ejecutaSQL(consulta);

            //return true; 
            while (rs.next()) {
                pp = rs.getString("password");
                nn = rs.getString("usuario");
                ii = rs.getInt("id_usuario");

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
        String consulta = "select * from vigia.Usuarios t where t.id_usuario=" + this._id + " and eliminado=0";
            //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            rs = con.ejecutaSQL(consulta);

            //return true; 
            while (rs.next()) {
                pp = rs.getString("password");
                nn = rs.getString("usuario");
                ii = rs.getInt("id_usuario");

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
        String consulta = "select * from vigia.Usuarios t where t.usuario='" + getNombre() + "' and eliminado=0";
            //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            rs = con.ejecutaSQL(consulta);

            //return true; 
            while (rs.next()) {
                pp = rs.getString("password");
                nn = rs.getString("usuario");
                ii = rs.getInt("id_usuario");

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
        String consulta = "insert into vigia.Usuarios(usuario, password) values ('" + getNombre() + "', '" + getPassword() + "');";
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
        String consulta = "update vigia.Usuarios set password='" + this.encriptar(getPassword()) + "' where usuario='" + getNombre() + "' and eliminado=0";
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
        String consulta = "update vigia.Usuarios set usuario='" + this.getNombre() + "', password='" + this.encriptar(getPassword()) + "' where id_usuario='" + id + "' and eliminado=0";
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

        String consulta = "update vigia.Usuarios set eliminado=1 where id_usuario=" + id ;
        Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "- consulta");

        try {
            ii = con.insertarSQL(consulta);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, "Error en USERVO.eliminar " + ex.toString(), "Error aca: " + ex);
        }
    }

    public List<UserVO> selectUsuariosInfo() throws SQLException {
        List<UserVO> al = new ArrayList<UserVO>();
        try {

// @formatter:off
            String query
                    = " SELECT id_usuario, usuario "
                    + " FROM "
                    + " vigia.Usuarios where eliminado=0"
                    + " order by id_usuario desc ";

// @formatter:on
            Conexion con = new Conexion();
            ResultSet resultSet = con.ejecutaSQL(query);

            while (resultSet.next()) {

                UserVO usuario = new UserVO(resultSet.getString("usuario"));
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
