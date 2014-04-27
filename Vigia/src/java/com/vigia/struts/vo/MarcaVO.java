/*
 * Clase MArca, copiado de la clase usuario
 */
package com.vigia.struts.vo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hsendoa
 */
// Clase Value Object que implementa la vista del producto.
public class MarcaVO extends BaseVO {

    // Datos de la marca.
    private String _smallImageURL;
    private String _marca;
    private String _descripcion;
    private int _id;

    

    // Constructor por defecto. 
    public MarcaVO() {
        super();
        
    }
// Constructor por defecto. 
    public MarcaVO(String marca) {
        super();
        this.setMarca(marca);
        boolean a= this.esMarca();
    }
    public MarcaVO(int id) {
        super();
        this._id = id;
        //validarId();
    }
    //constructor con datos
    public MarcaVO(int id, String name, String desc, String smallImageURL) {
        super(id, name, desc); //satisface la superclase
        //agrega nuevas caracteristicas 
        setSmallImageURL(smallImageURL);
    }
    /*Metodos Get and SET*/
    public String getMarca() {
        return _marca;
    }

    public void setMarca(String _marca) {
        this._marca = _marca;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }
    
    
    public String getSmallImageURL() {
        return _smallImageURL;
    }

    public void setSmallImageURL(String newSmallImageURL) {
        _smallImageURL = newSmallImageURL;
    }
/*sobreescripbe el toString de Object*/
    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("ID: ");
        buf.append(Integer.toString(getId()));
        buf.append(" Nombre: ");
        buf.append(getName());
        buf.append(" DescripciÛn: ");
        buf.append(getDescription());
        buf.append(" Imagen: ");
        buf.append(getSmallImageURL());
        return buf.toString();
    }
    public List<MarcaVO> selectMarcaInfo() throws SQLException {
        List<MarcaVO> al = new ArrayList<MarcaVO>();
        try {

// @formatter:off
            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.Marcas where eliminado=0";

// @formatter:on
            Conexion con = new Conexion();
            ResultSet resultSet = con.ejecutaSQL(query);

            while (resultSet.next()) {

                MarcaVO marca = new MarcaVO(resultSet.getString("marca"));
                marca.setDescripcion(resultSet.getString("descripcion"));
                marca.setId(resultSet.getInt("id_marca"));
                Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Marca:" + marca.getId() + marca.getMarca()+ marca.getDescripcion(), "");
                al.add(marca);
            }
            resultSet.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public boolean esMarca() {
        
        String nn = "";
        String pp = "";
        int ii = 0;

        Conexion con = new Conexion();
        ResultSet rs = null;
        String consulta = "select * from vigia.Marcas t where t.marca='" + getMarca()+ "' and eliminado=0";
            //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            rs = con.ejecutaSQL(consulta);

            //return true; 
            while (rs.next()) {
                pp = rs.getString("marca");
                nn = rs.getString("descripcion");
                ii = rs.getInt("id_marca");

                if (ii > 0) {
                    Logger.getLogger(UserVO.class.getName()).log(Level.INFO, ii+"-Ingresado:"+nn+"- BD:"+pp, "-");
                
                    this.setDescripcion(nn);
                    this.setMarca(pp);
                    this.setId(ii);
                    return true;
                }
                //break;//una sola ves consulta
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Error en USERVO.validar " + ex.toString(), "Error aca: " + ex);
        }
        return false;
    }
    public void crearMarca() {
        int ii = 0;
        //crea la conexion
        Conexion con = new Conexion();
        //carga la consulta
        String consulta = "insert into vigia.Marcas(marca, descripcion) values ('" + getMarca()+ "', '" + getDescripcion()+ "');";
        //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            //ejecuta la consulta
            ii = con.insertarSQL(consulta);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Error en USERVO.crear " + ex.toString(), "Error aca: " + ex);
        }

    }
    public void modificarMarca(int id) {
        
        int ii = 0;
        //crea la conexion
        Conexion con = new Conexion();
        //carga la consulta
        String consulta = "update vigia.Marcas set marca='" + getMarca() + "', descripcion='"+getDescripcion()+"' where id_marca='" + id+ "' and eliminado=0";
        //Logger.getLogger(UserVO.class.getName()).log(Level.INFO, consulta, "-");

        try {
            ii = con.insertarSQL(consulta);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Error en MarcaVO.crear " + ex.toString(), "Error aca: " + ex);
        }
    }
     public void eliminarMarca(int id) {
        int ii = 0;
        //CREA LA CONEXION
        Conexion con = new Conexion();
        
        String consulta = "update vigia.Marcas set eliminado=1 where id_marca=" + id + " and eliminado=0";
        Logger.getLogger(MarcaVO.class.getName()).log(Level.INFO, consulta, "- consulta");

        try {
            ii = con.insertarSQL(consulta);

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(MarcaVO.class.getName()).log(Level.SEVERE, "Error en MarcaVO.eliminar " + ex.toString(), "Error aca: " + ex);
        }
    }
}
