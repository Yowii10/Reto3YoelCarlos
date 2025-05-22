package ClasesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Clases.PedidoProducto;
import Clases.Pedidos;
import Util.Conexion;
import Util.Funciones;

public class PedidoProductoDAO {
    
    public static boolean agregarProductoAPedido(PedidoProducto pedidoProducto) {
        try (Connection con = Conexion.abreConexion()) {
            // Verificar stock disponible
            int stockDisponible = obtenerStockProducto(pedidoProducto.getIdpedido());
            if (stockDisponible < pedidoProducto.getUnidades()) {
                System.err.println("Stock insuficiente. Stock disponible: " + stockDisponible);
                return false;
            }

            // Insertar relación pedido-producto
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO pedidoproducto (idpedido, idproducto, unidades, precio) " +
                "VALUES (?, ?, ?, ?)");
            ps.setInt(1, pedidoProducto.getIdpedido());
            ps.setInt(2, pedidoProducto.getIdpedido());
            ps.setInt(3, pedidoProducto.getUnidades());
            ps.setDouble(4, pedidoProducto.getPrecio());
            
            if (ps.executeUpdate() > 0) {
                // Actualizar stock del producto
                return ProductosDAO.actualizarStock(
                    pedidoProducto.getIdpedido(), 
                    -pedidoProducto.getUnidades()
                );
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al agregar producto a pedido: " + e.getMessage());
            return false;
        }
    }

    private static int obtenerStockProducto(int idProducto) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT stock FROM productos WHERE idproducto = ?");
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("stock") : 0;
        }catch (Exception e) {
			System.out.println("Formato incorrecto");
		}
		return idProducto;
    }

    public static List<PedidoProducto> obtenerProductosPorPedido(int idPedido) {
        List<PedidoProducto> productos = new ArrayList<>();
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT pp.*, pr.nombre as nombre_producto, c.nombre as nombre_categoria " +
                "FROM pedidoproducto pp " +
                "JOIN productos pr ON pp.idproducto = pr.idproducto " +
                "JOIN categorias c ON pr.idcategoria = c.idcategoria " +
                "WHERE pp.idpedido = ?");
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productos.add(new PedidoProducto(
                    rs.getInt("idpedidoproducto"),
                    rs.getInt("idpedido"),
                    rs.getInt("idproducto"),
                    rs.getInt("unidades"),
                    rs.getDouble("precio")
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener productos del pedido: " + e.getMessage());
        }
        return productos;
    }

    public static double calcularTotalPedido(int idPedido) {
        double total = 0.0;
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT SUM(precio * unidades) as total FROM pedidoproducto WHERE idpedido = ?");
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular total del pedido: " + e.getMessage());
        }
        return total;
    }
    
    public static boolean guardarPrecioTotal(Pedidos pedido) {
            try (Connection con = Conexion.abreConexion()) {
                PreparedStatement ps = con.prepareStatement(
                		"UPDATE pedidos SET precioTotal = ? WHERE (idpedido = ?)");
                ps.setDouble(1, pedido.getPrecioTotal());
                ps.setInt(2, pedido.getIdPedido());
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                System.err.println("Error al actualizar el precio total: " + e.getMessage());
                return false;
            }
        }
    }
