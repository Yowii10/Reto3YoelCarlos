package ClasesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Clases.Clientes;
import Util.Conexion;

public class ClientesDAO {
    
    public static List<Clientes> obtenerClientes() {
        List<Clientes> clientes = new ArrayList<>();
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientes.add(new Clientes(
                    rs.getInt("idcliente"),
                    rs.getInt("codigo"),
                    rs.getString("direccion"),
                    rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }

    public static boolean insertarCliente(Clientes cliente) {
        try (Connection con = Conexion.abreConexion()) {
            // Verificar si ya existe un cliente con ese código
            if (existeClienteConCodigo(cliente.getCodCliente())) {
                System.err.println("Ya existe un cliente con ese código");
                return false;
            }

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO clientes (nombre, direccion, codigo) VALUES (?, ?, ?)");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setInt(3, cliente.getCodCliente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    private static boolean existeClienteConCodigo(int codigo) throws SQLException {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT 1 FROM clientes WHERE codigo = ?");
            ps.setInt(1, codigo);
            return ps.executeQuery().next();
        }
    }

    public static Clientes buscarPorCodigo(int codigo) {
        Clientes cliente = null;
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM clientes WHERE codigo = ?");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Clientes(
                    rs.getInt("idcliente"),
                    rs.getInt("codigo"),
                    rs.getString("direccion"),
                    rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
        return cliente;
    }

    public static boolean actualizarCliente(Clientes cliente) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE clientes SET precioTotal = ?, direccion = ?, codigo = ? WHERE idcliente = ?");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setInt(3, cliente.getCodCliente());
            ps.setInt(4, cliente.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }
}
