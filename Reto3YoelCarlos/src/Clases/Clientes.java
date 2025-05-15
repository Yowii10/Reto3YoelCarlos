package Clases;

public class Clientes {
	private int codCliente;
	private int idCliente;
	private String nombre;
	private String direccion;
	
	public int getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	
	public int getIdCliente() {
		return idCliente;	
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
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
