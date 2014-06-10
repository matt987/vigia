/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vigia.struts.vo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matts
 */
public class ProductoVO extends BaseVO {

    private int _id;
    private int _productoId;
    private int _eliminado;
    private String _nombre;
    private String _descripcion;
    private String _codigo;
    private float _precioCosto;
    private float _precioVenta1;
    private float _precioVenta2;
    private float _precioVenta3;
    private int _stockMinimo;
    private String _imagenUrl;
    private float _descuento;
    private int _categoriaId;

    public ProductoVO(ResultSet resultset) {
        try {
            this._id = resultset.getInt("id");
            this._productoId = resultset.getInt("id");
            this._eliminado = resultset.getInt("eliminado");
            this._nombre = resultset.getString("nombre");
            this._descripcion = resultset.getString("descripcion");
            this._codigo = resultset.getString("codigo");
            this._precioCosto = resultset.getFloat("precio_costo");
            this._precioVenta1 = resultset.getFloat("precio_venta_1");
            this._precioVenta2 = resultset.getFloat("precio_venta_2");
            this._precioVenta3 = resultset.getFloat("precio_venta_3");
            this._stockMinimo = resultset.getInt("stock_minimo");
            this._imagenUrl = resultset.getString("imagen_url");
            this._descuento = resultset.getFloat("descuento");
            this._categoriaId = resultset.getInt("categoria_id");
        } catch (SQLException ex) {
            Logger.getLogger(ProductoVO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ProductoVO(String codigo) {
        super();
        _codigo = codigo;
        getFromDB();
    }

    public ProductoVO(int id) {
        super();
        _productoId = id;
        getFromDB();
    }

    public void getFromDB() {
        String query = "";
        if (this.getCodigo() != null) {
            query = "SELECT * FROM vigia.productos where codigo = '" + this.getCodigo() + "' LIMIT 1";
        } else if (this.getProductoId() != 0) {
            query = "SELECT * FROM vigia.productos where id = '" + this.getProductoId() + "' LIMIT 1";
        }

        Conexion con = new Conexion();
        try {
            ResultSet resultset = con.ejecutaSQL(query);
            if (resultset.next()) {
                this.setId(resultset.getInt("id"));
                this.setProductoId(resultset.getInt("id"));
                this.setEliminado(resultset.getInt("eliminado"));
                this.setNombre(resultset.getString("nombre"));
                this.setDescripcion(resultset.getString("descripcion"));
                this.setCodigo(resultset.getString("codigo"));
                this.setPrecioCosto(resultset.getFloat("precio_costo"));
                this.setPrecioVenta1(resultset.getFloat("precio_venta_1"));
                this.setPrecioVenta2(resultset.getFloat("precio_venta_2"));
                this.setPrecioVenta3(resultset.getFloat("precio_venta_3"));
                this.setStockMinimo(resultset.getInt("stock_minimo"));
                this.setImagenUrl(resultset.getString("imagen_url"));
                this.setDescuento(resultset.getFloat("descuento"));
                this.setCategoriaId(resultset.getInt("categoria_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductoVO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String allJSON() throws SQLException {
        int cantidad = 0;
        String jsonString = "{productos: [";
        try {

            String query
                    = " SELECT * "
                    + " FROM "
                    + " vigia.productos where eliminado=0";

            Conexion con = new Conexion();
            try (ResultSet resultSet = con.ejecutaSQL(query)) {
                while (resultSet.next()) {
                    cantidad++;
                    ProductoVO producto = new ProductoVO(resultSet);
                    jsonString += "{codigo: '" + producto.getCodigo()
                            + "', nombre: '" + producto.getNombre()
                            + "', descripcion: '" + producto.getDescripcion()
                            + "'},";
                }
                if(cantidad > 0){
                    jsonString = jsonString.substring(0, jsonString.length() - 1);
                }
                jsonString += "]}";
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonString;
    }

    public int getProductoId() {
        return _productoId;
    }

    public void setProductoId(int _productoId) {
        this._productoId = _productoId;
    }

    public int getEliminado() {
        return _eliminado;
    }

    public void setEliminado(int _eliminado) {
        this._eliminado = _eliminado;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String getCodigo() {
        return _codigo;
    }

    public void setCodigo(String _codigo) {
        this._codigo = _codigo;
    }

    public float getPrecioCosto() {
        return _precioCosto;
    }

    public void setPrecioCosto(float _precioCosto) {
        this._precioCosto = _precioCosto;
    }

    public float getPrecioVenta1() {
        return _precioVenta1;
    }

    public void setPrecioVenta1(float _precioVenta1) {
        this._precioVenta1 = _precioVenta1;
    }

    public float getPrecioVenta2() {
        return _precioVenta2;
    }

    public void setPrecioVenta2(float _precioVenta2) {
        this._precioVenta2 = _precioVenta2;
    }

    public float getPrecioVenta3() {
        return _precioVenta3;
    }

    public void setPrecioVenta3(float _precioVenta3) {
        this._precioVenta3 = _precioVenta3;
    }

    public int getStockMinimo() {
        return _stockMinimo;
    }

    public void setStockMinimo(int _stockMinimo) {
        this._stockMinimo = _stockMinimo;
    }

    public String getImagenUrl() {
        return _imagenUrl;
    }

    public void setImagenUrl(String _imagenUrl) {
        this._imagenUrl = _imagenUrl;
    }

    public float getDescuento() {
        return _descuento;
    }

    public void setDescuento(float _descuento) {
        this._descuento = _descuento;
    }

    public int getCategoriaId() {
        return _categoriaId;
    }

    public void setCategoriaId(int categoria_id) {
        this._categoriaId = categoria_id;
    }

}
