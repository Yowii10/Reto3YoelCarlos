package ClasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Util.Conexion;
import Clases.Clientes;

public class ClientesDAO {
	public static Clientes selccionar(int guardadoCodCliente) {
		Clientes cliente = null;
		try {
		Connection con = Conexion.abreConexion();
		PreparedStatement pst = con.prepareStatement("SELECT nombre FROM clientes\n"
				+ "WHERE codigo = ?;");
		pst.setInt(1, guardadoCodCliente); 
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			//creo objeto asignatura y lo añado a la lista
			cliente= new Clientes(rs.getString("nombre"));
		}
		rs.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		Conexion.cierraConexion();
	}
	return cliente;
}
	public static List<Clientes> obtenerClientes() {
        List<Clientes> clientes = new ArrayList<>();
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientes.add(new Clientes(
                	rs.getInt("codigo"),     
                 	rs.getInt("idcliente"),                
                    rs.getString("direccion"),
                    rs.getString("nombre")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public static Clientes buscarPorCodigo(int codigo) {
        Clientes cliente = null;
        try (Connection con = Conexion.abreConexion()) {  
            PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes WHERE codigo = ?");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Clientes(
                	rs.getInt("codigo"),  
                  	rs.getInt("idcliente"), 
                    rs.getString("direccion"),
                    rs.getString("nombre")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public static boolean insertarCliente(Clientes cliente) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO clientes (nombre, direccion, codigo) VALUES (?, ?, ?)");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setInt(3, cliente.getCodCliente());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
