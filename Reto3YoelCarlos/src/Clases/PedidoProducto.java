package Clases;

public class PedidoProducto {
	private int idpedidoproducto, idpedido, idproducto, unidades; 
    private Double precio;
    
	public int getIdpedidoproducto() {
		return idpedidoproducto;
	}
	public void setIdpedidoproducto(int idpedidoproducto) {
		this.idpedidoproducto = idpedidoproducto;
	}
	public int getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	/**
	 * 
	 * @param idpedidoproducto int
	 * @param idpedido int 
	 * @param idproducto int 
	 * @param unidades int 
	 * @param precio Double
	 */
	public PedidoProducto(int idpedidoproducto, int idpedido, int idproducto, int unidades, Double precio) {
		super();
		this.idpedidoproducto = idpedidoproducto;
		this.idpedido = idpedido;
		this.idproducto = idproducto;
		this.unidades = unidades;
		this.precio = precio;
	}
	public PedidoProducto() {
		super();
	}
	@Override
	public String toString() {
		return "PedidoProducto [idpedidoproducto=" + idpedidoproducto + ", idpedido=" + idpedido + ", idproducto="
				+ idproducto + ", unidades=" + unidades + ", precio=" + precio + "]";
	}
     
    

}
