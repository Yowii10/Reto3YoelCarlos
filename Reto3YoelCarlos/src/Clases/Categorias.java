package Clases;

public class Categorias {
	private int Idcategoria;
	private String Nombre;
	public int getIdcategoria() {
		return Idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		Idcategoria = idcategoria;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Categorias(int idcategoria, String nombre) {
		super();
		Idcategoria = idcategoria;
		Nombre = nombre;
	}
	public Categorias() {
		super();
	}
	@Override
	public String toString() {
		return "Categorias [Idcategoria=" + Idcategoria + ", Nombre=" + Nombre + "]";
	}
	
	

}
