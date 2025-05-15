package ClasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases.Categorias;
import Util.Conexion;

public class CategoriasDao {
	  public static List<Categorias> obtenerCategorias() {
	        List<Categorias> categorias = new ArrayList<>();
	        try (Connection con = Conexion.abreConexion()) {
	            PreparedStatement pst = con.prepareStatement("SELECT * FROM categorias");
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	                categorias.add(new Categorias(rs.getInt("idcategoria"), rs.getString("nombre")));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return categorias;
	    }

	    public static boolean insertarCategoria(Categorias categoria) {
	        try (Connection con = Conexion.abreConexion()) {
	        	
	            PreparedStatement ps = con.prepareStatement("INSERT INTO categorias (nombre) VALUES (?)");
	            ps.setString(1, categoria.getNombre());
	            return ps.executeUpdate() > 0;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

}
