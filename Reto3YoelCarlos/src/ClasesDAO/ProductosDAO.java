package ClasesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Clases.Productos;
import Util.Conexion;

public class ProductosDAO {
    
    public static List<Productos> obtenerProductos() {
        List<Productos> productos = new ArrayList<>();
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT p.*, c.nombre as nombre_categoria FROM productos p " +
                "JOIN categorias c ON p.idcategoria = c.idcategoria");
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
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    public static boolean insertarProducto(Productos producto) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO productos (idcategoria, nombre, precio, descripcion, color, talla, stock) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, producto.getIdCategoria());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getDescripcion());
            ps.setString(5, producto.getColor());
            ps.setString(6, producto.getTalla());
            ps.setInt(7, producto.getStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    public static List<Productos> buscarProductos(String nombre, String color, String talla) {
        List<Productos> productos = new ArrayList<>();
        try (Connection con = Conexion.abreConexion()) {
            StringBuilder sql = new StringBuilder(
                "SELECT p.*, c.nombre as nombre_categoria FROM productos p " +
                "JOIN categorias c ON p.idcategoria = c.idcategoria WHERE 1=1");
            
            if (nombre != null && !nombre.isEmpty()) {
                sql.append(" AND p.nombre LIKE ?");
            }
            if (color != null && !color.isEmpty()) {
                sql.append(" AND p.color = ?");
            }
            if (talla != null && !talla.isEmpty()) {
                sql.append(" AND p.talla = ?");
            }

            PreparedStatement ps = con.prepareStatement(sql.toString());
            int paramIndex = 1;
            
            if (nombre != null && !nombre.isEmpty()) {
                ps.setString(paramIndex++, "%" + nombre + "%");
            }
            if (color != null && !color.isEmpty()) {
                ps.setString(paramIndex++, color);
            }
            if (talla != null && !talla.isEmpty()) {
                ps.setString(paramIndex++, talla);
            }

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
            System.err.println("Error al buscar productos: " + e.getMessage());
        }
        return productos;
    }

    public static boolean actualizarStock(int idProducto, int cantidad) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE productos SET stock = stock + ? WHERE idproducto = ?");
            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error al actualizar stock: " + e.getMessage());
            return false;
        }
    }

    public static Productos buscarPorId(int id) {
        Productos producto = null;
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT p.*, c.nombre as nombre_categoria FROM productos p " +
                "JOIN categorias c ON p.idcategoria = c.idcategoria " +
                "WHERE p.idproducto = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                producto = new Productos(
                    rs.getInt("idproducto"),
                    rs.getInt("idcategoria"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getString("descripcion"),
                    rs.getString("color"),
                    rs.getString("talla"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
        }
        return producto;
    }
}
