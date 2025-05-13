package Clases;

public class Productos {
	private int idProducto;
	private Categorias idCategoria;
	private String nombre;
	private double precio;
	private String descripcion;
	private String color;
	private String talla;
	private int stock;
	
	protected int getIdProducto() {
		return idProducto;
	}
	
	protected void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	protected Categorias getIdCategoria() {
		return idCategoria;
	}
	
	protected void setIdCategoria(Categorias idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	protected String getNombre() {
		return nombre;
	}
	
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	protected double getPrecio() {
		return precio;
	}
	
	protected void setPrecio(double precio) {
		this.precio = precio;
	}
	
	protected String getDescripcion() {
		return descripcion;
	}
	
	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	protected String getColor() {
		return color;
	}
	
	protected void setColor(String color) {
		this.color = color;
	}
	
	protected String getTalla() {
		return talla;
	}
	
	protected void setTalla(String talla) {
		this.talla = talla;
	}
	
	protected int getStock() {
		return stock;
	}
	
	protected void setStock(int stock) {
		this.stock = stock;
	}

	public Productos(int idProducto, Categorias idCategoria, String nombre, double precio, String descripcion,
			String color, String talla, int stock) {
		super();
		this.idProducto = idProducto;
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
	}
	
	
}
