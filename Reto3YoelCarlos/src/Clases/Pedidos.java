package Clases;

import java.util.Date;

public class Pedidos {
	private int idPedido;
	private Clientes idCliente;
	private double precioTotal;
	private String direccion;
	private Date fecha;
	
	protected int getIdPedido() {
		return idPedido;
	}
	
	protected void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
	protected Clientes getIdCliente() {
		return idCliente;
	}
	
	protected void setIdCliente(Clientes idCliente) {
		this.idCliente = idCliente;
	}
	
	protected double getPrecioTotal() {
		return precioTotal;
	}
	
	protected void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	protected String getDireccion() {
		return direccion;
	}
	
	protected void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	protected Date getFecha() {
		return fecha;
	}
	
	protected void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Pedidos(int idPedido, Clientes idCliente, double precioTotal, String direccion, Date fecha) {
		super();
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.precioTotal = precioTotal;
		this.direccion = direccion;
		this.fecha = fecha;
	}
	
	
}
