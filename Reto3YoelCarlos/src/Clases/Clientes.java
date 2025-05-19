package Clases;

public class Clientes {
	private int idCliente;
	private int codCliente;
	private String direccion;
	private String nombre;
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Clientes(int idCliente, int codCliente, String direccion, String nombre) {
		super();
		this.idCliente = idCliente;
		this.codCliente = codCliente;
		this.direccion = direccion;
		this.nombre = nombre;
	}
	
	
}