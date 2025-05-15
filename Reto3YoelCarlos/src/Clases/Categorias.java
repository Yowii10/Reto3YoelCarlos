package Clases;

public class Categorias {
	private int Idcategoria;
	private String Nombre;
	
	public int getIdcategoria() {
		return idCategoria;
	}
	public void setIdcategoria(int idcategoria) {
		idCategoria = idcategoria;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Categorias(int idcategoria, String nombre) {
		super();
		idCategoria = idcategoria;
		Nombre = nombre;
	}
	public Categorias() {
		super();
	}
	
}
