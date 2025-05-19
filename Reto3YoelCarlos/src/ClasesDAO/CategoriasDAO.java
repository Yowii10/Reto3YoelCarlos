package ClasesDAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Clases.Categorias;
import Util.Conexion;

public class CategoriasDAO {
    
    public static List<Categorias> obtenerCategorias() {
        List<Categorias> categorias = new ArrayList<>();
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categorias");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categorias.add(new Categorias(
                    rs.getInt("idcategoria"), 
                    rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener categorías: " + e.getMessage());
        }
        return categorias;
    }

    public static boolean insertarCategoria(Categorias categoria) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO categorias (nombre) VALUES (?)");
            ps.setString(1, categoria.getNombre());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar categoría: " + e.getMessage());
            return false;
        }
    }

    public static Categorias buscarPorId(int id) {
        Categorias categoria = null;
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM categorias WHERE idcategoria = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new Categorias(
                    rs.getInt("idcategoria"),
                    rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar categoría: " + e.getMessage());
        }
        return categoria;
    }
}