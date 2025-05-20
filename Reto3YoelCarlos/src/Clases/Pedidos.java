package Clases;

import java.time.LocalDate;
import java.util.Date;

public class Pedidos {
	private int idPedido;
	private int idCliente;
	private double precioTotal;
	private String direccion;
	private LocalDate fecha;
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}
	
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Pedidos(int idPedido, int idCliente, double precioTotal, String direccion, LocalDate fecha) {
		super();
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.precioTotal = precioTotal;
		this.direccion = direccion;
		this.fecha = fecha;
	}
	
	
	
}
