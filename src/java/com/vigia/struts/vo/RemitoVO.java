/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vigia.struts.vo;

import com.vigia.struts.remitos.RemitosActionFormBean;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matts
 */
public class RemitoVO extends BaseVO {

    private int _id;
    private int _remitoId;
    private int _eliminado;
    private int _receptor_id;
    private int _emisor_id;
    private Date _fecha;
    private String _destino;
    private List<DetalleRemitoVO> _detalles;
    private ProveedorVO _receptor;
    private EmpleadoVO _emisor;

    public RemitoVO(int receptor_id, int emisor_id, Date fecha, String destino, int eliminado) {
        super();
        this._eliminado = eliminado;
        this._receptor_id = receptor_id;
        this._emisor_id = emisor_id;
        this._fecha = fecha;
        this._destino = destino;
    }
    
    public RemitoVO(ProveedorVO receptor, EmpleadoVO emisor, Date fecha, String destino, List<DetalleRemitoVO> detalles){
        super();
        this._fecha = fecha;
        this._receptor = receptor;
        this._emisor = emisor;
        this._destino = destino;
        this._detalles = detalles;
    }

    public List<DetalleRemitoVO> getDetalles() {
        return _detalles;
    }

    public void setDetalles(List<DetalleRemitoVO> detalles) {
        this._detalles = detalles;
    }

    public ProveedorVO getReceptor() {
        return _receptor;
    }

    public void setReceptor(ProveedorVO _receptor) {
        this._receptor = _receptor;
    }

    public EmpleadoVO getEmisor() {
        return _emisor;
    }

    public void setEmisor(EmpleadoVO _emisor) {
        this._emisor = _emisor;
    }

    public int getRemitoId() {
        return _remitoId;
    }

    public void setRemitoId(int _remitoId) {
        this._remitoId = _remitoId;
    }

    public int getEliminado() {
        return _eliminado;
    }

    public void setEliminado(int _eliminado) {
        this._eliminado = _eliminado;
    }

    public int getReceptor_id() {
        return _receptor_id;
    }

    public void setReceptor_id(int _receptor_id) {
        this._receptor_id = _receptor_id;
    }

    public int getEmisor_id() {
        return _emisor_id;
    }

    public void setEmisor_id(int _emisor_id) {
        this._emisor_id = _emisor_id;
    }

    public Date getFecha() {
        return _fecha;
    }

    public void setFecha(Date _fecha) {
        this._fecha = _fecha;
    }

    public String getDestino() {
        return _destino;
    }

    public void setDestino(String _destino) {
        this._destino = _destino;
    }

    public String getNombreEmisor() {
        return "";
    }

    public String getNombreReceptor() {
        return "";
    }
    
    public static int getNextNumber(){
       String consulta = "SELECT id from vigia.remitos ORDER BY id DESC LIMIT 1"; 
       Conexion con = new Conexion();
       int number = 0;
        try {
            ResultSet resultset = con.ejecutaSQL(consulta);
            if(resultset.next()){
                number = resultset.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemitoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RemitoVO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RemitoVO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return number + 1;
    }
    public static RemitoVO create(RemitosActionFormBean rfb){
        RemitoVO remito = null;
        
        return remito;
    }
    public static RemitoVO findById(int id){
        Conexion con = new Conexion();
        String consulta = "SELECT * from vigia.remitos where eliminado = 0 AND id = " + id + "LIMIT 1";
        RemitoVO remito = null;
        ResultSet resultset = null;
        try {
            resultset = con.ejecutaSQL(consulta);
            if(resultset.next()){
                int empleadoId = resultset.getInt("emisor_id");
                int proveedorId = resultset.getInt("receptor_id");
                int remitoId = resultset.getInt("id");
                String destino = resultset.getString("destino");
                Date fecha = resultset.getDate("fecha");
                ProveedorVO proveedor = ProveedorVO.findByProveedorId(proveedorId);
                EmpleadoVO empleado = EmpleadoVO.findByEmpleadoId(empleadoId);
                List<DetalleRemitoVO> detalles = DetalleRemitoVO.findByRemitoId(remitoId);
                remito = new RemitoVO(proveedor, empleado, fecha, destino, detalles);
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(ClienteVO.class.getName()).log(Level.SEVERE, "Error en ClienteVO.crear " + ex.toString(), "Error aca: " + ex);
        }            
        return remito;
    }
    
    public static List<RemitoVO> all(){
        List<RemitoVO> remitos = new ArrayList<RemitoVO>();
                Conexion con = new Conexion();
        String consulta = "SELECT * from vigia.remitos where eliminado = 0";
        ResultSet resultset = null;
        try {
            resultset = con.ejecutaSQL(consulta);
            while(resultset.next()){
                int empleadoId = resultset.getInt("emisor_id");
                int proveedorId = resultset.getInt("receptor_id");
                int remitoId = resultset.getInt("id");
                String destino = resultset.getString("destino");
                Date fecha = resultset.getDate("fecha");
                ProveedorVO proveedor = ProveedorVO.findByProveedorId(proveedorId);
                EmpleadoVO empleado = EmpleadoVO.findByEmpleadoId(empleadoId);
                List<DetalleRemitoVO> detalles = DetalleRemitoVO.findByRemitoId(remitoId);
                RemitoVO remito = new RemitoVO(proveedor, empleado, fecha, destino, detalles);
                remitos.add(remito);
            }
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(ClienteVO.class.getName()).log(Level.SEVERE, "Error en ClienteVO.crear " + ex.toString(), "Error aca: " + ex);
        }       
        
        return remitos;
    }

}
