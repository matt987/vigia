/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author matts
 */
public class DetalleRemitoVO extends BaseVO{
    
    private int _id;
    private int _detalleRemitoId;
    private int _remitoId;
    private int _productoId;
    private int _cantidad;
    private int _eliminado;
    private RemitoVO _remito;
    private ProductoVO _producto;
    
    public DetalleRemitoVO(int id){
        super();
        this._id = id;
        this._detalleRemitoId = id;
        getFromDB();
        //validarId();        
    }
    public DetalleRemitoVO(){
        
    }
    
    public static List<DetalleRemitoVO> findByRemitoId(int remitoId){
        List<DetalleRemitoVO> detalles = new ArrayList<>();
        String query = "Select * from detalle_remitos where remito_id = " + remitoId;
        Conexion con = new Conexion();
        ResultSet resultset = null;
        try {
            resultset = con.ejecutaSQL(query);
            while(resultset.next()){
                DetalleRemitoVO detalleRemito = new DetalleRemitoVO();
                detalleRemito.setDetalleRemitoId(resultset.getInt("id"));
                detalleRemito.setId(resultset.getInt("id"));
                detalleRemito.setRemitoId(resultset.getInt("remito_id"));
                detalleRemito.setProductoId(resultset.getInt("producto_id"));
                detalleRemito.setCantidad(resultset.getInt("cantidad"));
                detalleRemito.setEliminado(resultset.getInt("eliminado"));
                detalleRemito.setRemito(RemitoVO.findById(detalleRemito.getRemitoId()));
                detalleRemito.setProducto(new ProductoVO(detalleRemito.getProductoId())); 
                detalles.add(detalleRemito);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleRemitoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleRemitoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetalleRemitoVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalles;
    }
    
    public final void getFromDB(){
        String consulta = "SELECT * FROM vigia.detalles_remito WHERE eliminado = 0 AND id = " + getDetalleRemitoId()+ " LIMIT 1";
        Conexion con = new Conexion();
        ResultSet resultset = null;
        try {
            resultset = con.ejecutaSQL(consulta);
            if (resultset.next()) {
                setDetalleRemitoId(resultset.getInt("id"));
                setId(resultset.getInt("id"));
                setRemitoId(resultset.getInt("remito_id"));
                setProductoId(resultset.getInt("producto_id"));
                setCantidad(resultset.getInt("cantidad"));
                setEliminado(resultset.getInt("eliminado"));
                setRemito(RemitoVO.findById(getRemitoId()));
                setProducto(new ProductoVO(getProductoId()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public RemitoVO getRemito() {
        return _remito;
    }

    public void setRemito(RemitoVO _remito) {
        this._remito = _remito;
    }

    public ProductoVO getProducto() {
        return _producto;
    }

    public void setProducto(ProductoVO _producto) {
        this._producto = _producto;
    }

    public int getDetalleRemitoId() {
        return _detalleRemitoId;
    }

    public void setDetalleRemitoId(int _detalleRemitoId) {
        this._detalleRemitoId = _detalleRemitoId;
    }

    public int getRemitoId() {
        return _remitoId;
    }

    public void setRemitoId(int _remitoId) {
        this._remitoId = _remitoId;
    }

    public int getProductoId() {
        return _productoId;
    }

    public void setProductoId(int _productoId) {
        this._productoId = _productoId;
    }

    public int getCantidad() {
        return _cantidad;
    }

    public void setCantidad(int _cantidad) {
        this._cantidad = _cantidad;
    }

    public int getEliminado() {
        return _eliminado;
    }

    public void setEliminado(int _eliminado) {
        this._eliminado = _eliminado;
    }
}
