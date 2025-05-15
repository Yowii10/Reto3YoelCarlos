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
	
	
	public int getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public Categorias getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(Categorias idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
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
