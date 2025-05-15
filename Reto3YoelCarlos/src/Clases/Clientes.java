package Clases;

public class Clientes {
	private int codCliente;
	private int idCliente;
	private String nombre;
	private String direccion;
	
	protected int getCodCliente() {
		return codCliente;
	}
	
	protected void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	
	protected int getIdCliente() {
		return idCliente;	
	}
	
	protected void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	protected String getNombre() {
		return nombre;
	}
	
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	protected String getDireccion() {
		return direccion;
	}
	
	protected void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Clientes(int codCliente, int idCliente, String nombre, String direccion) {
		super();
		this.codCliente = codCliente;
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public Clientes(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Clientes [codCliente=" + codCliente + ", idCliente=" + idCliente + ", nombre=" + nombre + ", direccion="
				+ direccion ;
	}


	
}
