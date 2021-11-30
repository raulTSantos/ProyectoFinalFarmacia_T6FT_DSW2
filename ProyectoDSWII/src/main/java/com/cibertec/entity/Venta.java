package com.cibertec.entity;

import java.sql.Date;
import java.util.ArrayList;


public class Venta {

	private int idVenta;
	private Date fechaRegistro;
	private Pago  pago;
	private Cliente cliente;
	private Usuario usuario;
	private ArrayList<DetalleVenta> detalleVenta;
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ArrayList<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}
	public void setDetalleVenta(ArrayList<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
	
	
}
