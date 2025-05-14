package ClasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Util.Conexion;
import Util.Funciones;
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
}
