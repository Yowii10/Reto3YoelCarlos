package ClasesDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Clases.Clientes;
import Clases.Pedidos;
import Util.Conexion;

public class PedidosDAO {
    
    public static boolean insertarPedido(Pedidos pedido) {
        Connection con = null;
        try {
            con = Conexion.abreConexion();
            con.setAutoCommit(false); // Iniciar transacción

            // Insertar el pedido
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO pedidos (idcliente, precioTotal, direccionEnvio, fecha) " +
                "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pedido.getIdCliente());
            ps.setDouble(2, pedido.getPrecioTotal());
            ps.setString(3, pedido.getDireccion());
            ps.setDate(4, java.sql.Date.valueOf(pedido.getFecha()));
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el pedido");
            }

            // Obtener el ID generado
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pedido.setIdPedido(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se obtuvo el ID del pedido");
                }
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            System.err.println("Error al insertar pedido: " + e.getMessage());
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    public static List<Pedidos> obtenerPedidosDelMes() {
        List<Pedidos> pedidos = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());

        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT p.*, c.nombre as nombre_cliente FROM pedidos p " +
                "JOIN clientes c ON p.idcliente = c.idcliente " +
                "WHERE p.fecha BETWEEN ? AND ? " +
                "ORDER BY p.fecha DESC");
            ps.setDate(1, java.sql.Date.valueOf(startOfMonth));
            ps.setDate(2, java.sql.Date.valueOf(endOfMonth));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pedidos.add(new Pedidos(
                    rs.getInt("idpedido"),
                    rs.getInt("idcliente"),
                    rs.getDouble("precioTotal"),
                    rs.getString("direccionEnvio"),
                    rs.getDate("fecha").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    public static List<Pedidos> obtenerPedidosPorCliente(int idCliente) {
        List<Pedidos> pedidos = new ArrayList<>();
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM pedidos WHERE idcliente = ? ORDER BY fecha DESC");
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pedidos.add(new Pedidos(
                    rs.getInt("idpedido"),
                    rs.getInt("idcliente"),
                    rs.getDouble("precioTotal"),
                    rs.getString("direccionEnvio"),
                    rs.getDate("fecha").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos por cliente: " + e.getMessage());
        }
        return pedidos;
    }

    public static Pedidos buscarPorId(int idPedido) {
        Pedidos pedido = null;
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT p.*, c.nombre as nombre_cliente FROM pedidos p " +
                "JOIN clientes c ON p.idcliente = c.idcliente " +
                "WHERE p.idpedido = ?");
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pedido = new Pedidos(
                    rs.getInt("idpedido"),
                    rs.getInt("idcliente"),
                    rs.getDouble("precioTotal"),
                    rs.getString("direccionEnvio"),
                    rs.getDate("fecha").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar pedido: " + e.getMessage());
        }
        return pedido;
    }
    public static boolean actualizarPedidos(Pedidos pedido) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE pedidos SET nombre = ?, direccion = ?, idPedido = ? WHERE idcliente = ?");
            ps.setInt(1, pedido.getIdPedido());
            ps.setString(2, pedido.getDireccion());
            ps.setDate(3, pedido.getFecha());
            ps.setInt(4, pedido.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }
}
