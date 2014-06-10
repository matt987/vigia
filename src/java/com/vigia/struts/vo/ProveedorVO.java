package com.vigia.struts.vo;

import com.vigia.struts.proveedores.ProveedoresActionFormBean;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matts
 */
public class ProveedorVO extends BaseVO {

    private int _id;
    private String _nombre;
    private String _direccion;
    private String _ruc;
    private String _telefono;
    private String _paginaWeb;
    private String _email;

    // Constructor por defecto. 
    public ProveedorVO() {
        super();

    }

    public ProveedorVO(int id) {
        super();
        this._id = id;
        //validarId();
    }

    //constructor con datos
    public ProveedorVO(int id, String nombre, String direccion) {
        super(); //satisface la superclase
        //agrega nuevas caracteristicas 
        setNombre(nombre);
        setDireccion(direccion);
    }

    //constructor con datos
    public ProveedorVO(int id, String nombre, String direccion, String paginaWeb, String ruc, String telefono, String email) {
        super(); //satisface la superclase
        //agrega nuevas caracteristicas 
        setNombre(nombre);
        setDireccion(direccion);
        setPaginaWeb(paginaWeb);
        setRuc(ruc);
        setTelefono(telefono);
        setEmail(email);
    }

    public String getPaginaWeb() {
        return _paginaWeb;
    }

    private void setPaginaWeb(String _paginaWeb) {
        this._paginaWeb = _paginaWeb;
    }

    public String getEmail() {
        return _email;
    }

    private void setEmail(String _email) {
        this._email = _email;
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

    public static ProveedorVO editar(ProveedoresActionFormBean pfb) throws SQLException {
        ProveedorVO proveedor = ProveedorVO.findByRuc(pfb.getRuc());
        String consulta = "UPDATE proveedores set nombre='" + pfb.getNombre()
                + "', direccion=' " + pfb.getDireccion()
                + "', ruc= '" + pfb.getRuc()
                + "', email= '" + pfb.getEmail()
                + "', pagina_web= '" + pfb.getPaginaWeb()
                + "', telefono=' " + pfb.getTelefono()
                + "' where ruc='" + pfb.getRuc() + "' and eliminado=0";
        Conexion con = new Conexion();
        int ii = 0;
        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, "Error en ProveedorVO.editar " + ex.toString(), "Error aca: " + ex);
        }

        return proveedor;
    }

    public static ProveedorVO crear(ProveedoresActionFormBean pfb) {
        ProveedorVO proveedor = new ProveedorVO(pfb.getId(), pfb.getNombre(), pfb.getDireccion(), pfb.getPaginaWeb(), pfb.getRuc(), pfb.getTelefono(), pfb.getEmail());
        String consulta = "INSERT INTO vigia.proveedores "
                + " (`nombre`, `direccion`, `ruc`, `telefono`, `email`, `pagina_web`) "
                + "VALUES ('"
                + proveedor.getNombre() + "', '"
                + proveedor.getDireccion() + "', '"
                + proveedor.getRuc() + "', '"
                + proveedor.getTelefono() + "', '"
                + proveedor.getEmail() + "', '"
                + proveedor.getPaginaWeb() + "'"
                + ")";
        Conexion con = new Conexion();
        int ii = 0;
        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, "Error en ProveedorVO.crear " + ex.toString(), "Error aca: " + ex);
        }
        return proveedor;
    }

    public static boolean eliminar(String ruc) {
        int ii = 0;
        //CREA LA CONEXION
        Conexion con = new Conexion();

        String consulta = "UPDATE proveedores SET eliminado=1 WHERE ruc='" + ruc + "' and eliminado=0";
        Logger.getLogger(ProveedorVO.class.getName()).log(Level.INFO, consulta, "- consulta");

        try {
            ii = con.insertarSQL(consulta);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, "Error en ProveedorVO.eliminar " + ex.toString(), "Error aca: " + ex);
            return false;
        }
        return true;
    }

    public List<ProveedorVO> selectProveedorInfo() throws SQLException {
        List<ProveedorVO> al = new ArrayList<ProveedorVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.proveedores where eliminado=0";

// @formatter:on
            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                while (resultSet.next()) {
                    ProveedorVO proveedor = new ProveedorVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("direccion"), resultSet.getString("pagina_web"), resultSet.getString("ruc"), resultSet.getString("telefono"), resultSet.getString("email"));
                    Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, "Cliente:" + proveedor.getId() + proveedor.getNombre() + proveedor.getDireccion(), "");
                    al.add(proveedor);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public static String allJSON() throws SQLException {
        String jsonString = "{proveedores: [";
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.proveedores where eliminado=0";

// @formatter:on
            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                while (resultSet.next()) {
                    ProveedorVO proveedor = new ProveedorVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("direccion"), resultSet.getString("pagina_web"), resultSet.getString("ruc"), resultSet.getString("telefono"), resultSet.getString("email"));
                    jsonString += "{ruc: '" + proveedor.getRuc() 
                               + "', nombre: '" + proveedor.getNombre()
                               + "', telefono: '" + proveedor.getTelefono()
                               + "', direccion: '" + proveedor.getDireccion()
                               + "'},";
                    Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, "Cliente:" + proveedor.getId() + proveedor.getNombre() + proveedor.getDireccion(), "");
                }
                jsonString = jsonString.substring(0, jsonString.length() - 1);
                jsonString += "]}";
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonString;
    }
    
    
    public static ProveedorVO findByRuc(String ruc) throws SQLException {
        String query
                = " SELECT * "
                + " FROM "
                + " vigia.proveedores where ruc =" + ruc + " AND eliminado=0 limit 1";
        ProveedorVO proveedor = null;
        Conexion con = new Conexion();
        try (ResultSet resultSet = con.ejecutaSQL(query)) {
            while (resultSet.next()) {
                proveedor = new ProveedorVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("direccion"), resultSet.getString("pagina_web"), resultSet.getString("ruc"), resultSet.getString("telefono"), resultSet.getString("email"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return proveedor;
    }

    public List<ProveedorVO> selectProveedorInfo(String search) throws SQLException {
        List<ProveedorVO> al = new ArrayList<ProveedorVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.proveedores where eliminado=0"
                    + " and (nombre like '%" + search + "%'"
                    + "   OR ruc like '%" + search + "%'"
                    + "   OR direccion like '%" + search + "%')";

// @formatter:on
            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                while (resultSet.next()) {
                    ProveedorVO proveedor = new ProveedorVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("direccion"), resultSet.getString("pagina_web"), resultSet.getString("ruc"), resultSet.getString("telefono"), resultSet.getString("email"));
                    Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Cliente:" + proveedor.getId() + proveedor.getNombre() + proveedor.getDireccion(), "");
                    al.add(proveedor);
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public static ProveedorVO findByProveedorId(int id) {
        String query
                = " SELECT * "
                + " FROM "
                + " vigia.proveedores where id =" + String.valueOf(id) + " AND eliminado=0 limit 1";
        ProveedorVO proveedor = null;
        Conexion con = new Conexion();
        try (ResultSet resultSet = con.ejecutaSQL(query)) {
            if (resultSet.next()) {
                proveedor = new ProveedorVO(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("direccion"), resultSet.getString("pagina_web"), resultSet.getString("ruc"), resultSet.getString("telefono"), resultSet.getString("email"));
                Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "GrissopnNNNNN:" + proveedor.getId() + proveedor.getNombre() + proveedor.getDireccion(), "");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "ClassNotFoundException", ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "IOException", ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, "SQLException", ex);
        }

        return proveedor;
    }

}
