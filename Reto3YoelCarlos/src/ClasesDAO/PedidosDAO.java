package ClasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases.Pedidos;
import Util.Conexion;

public class PedidosDAO {
	public static boolean insertarPedido(Pedidos pedido) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO pedidos (idcliente, direccion_envio, precio_total) VALUES (?, ?, ?)"
            );
            ps.setInt(1, pedido.getIdCliente());
            ps.setString(2, pedido.getDireccion());
            ps.setDouble(3, pedido.getPrecioTotal());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene los pedidos del mes actual.
     * @return Lista de pedidos.
     */
    public static List<Pedidos> obtenerPedidosDelMes() {
        List<Pedidos> pedidos = new ArrayList<>();
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM pedidos WHERE MONTH(fecha) = MONTH(CURDATE()) ORDER BY fecha DESC"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pedidos.add(new Pedidos(
                    rs.getInt("idpedido"),
                    rs.getInt("idcliente"),
                    rs.getString("direccion_envio"),
                    rs.getDouble("precio_total"),
                    rs.getDate("fecha")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedidos;
    }

 
}
