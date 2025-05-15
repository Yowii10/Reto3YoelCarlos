package ClasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases.Productos;
import Util.Conexion;

public class ProductosDAO {
	  public static List<Productos> obtenerProductos() {
	        List<Productos> productos = new ArrayList<>();
	        try (Connection con = Conexion.abreConexion()) {
	          
	            PreparedStatement ps = con.prepareStatement("SELECT * FROM productos");
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                productos.add(new Productos(
	                    rs.getInt("idproducto"),
	                    rs.getInt("idcategoria"),
	                    rs.getString("nombre"),
	                    rs.getDouble("precio"),
	                    rs.getString("descripcion"),
	                    rs.getString("color"),
	                    rs.getString("talla"),
	                    rs.getInt("stock")
	                ));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return productos;
	    }

	    public static boolean insertarProducto(Productos producto) {
	        try (Connection con = Conexion.abreConexion()) {
	           
	            PreparedStatement ps = con.prepareStatement("INSERT INTO productos (idcategoria, nombre, precio, descripcion, color, talla, stock) VALUES (?, ?, ?, ?, ?, ?, ?)");
	            ps.setInt(1, producto.getIdCategoria());
	            ps.setString(2, producto.getNombre());
	            ps.setDouble(3, producto.getPrecio());
	            ps.setString(4, producto.getDescripcion());
	            ps.setString(5, producto.getColor());
	            ps.setString(6, producto.getTalla());
	            ps.setInt(7, producto.getStock());
	            return ps.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
}
