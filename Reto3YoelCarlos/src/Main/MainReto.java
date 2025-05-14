package Main;

import java.util.Scanner;

import ClasesDAO.ClientesDAO;
import Util.Funciones;

public class MainReto {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int guardado = 0;
		do{ 
				System.out.println("1.Mantenimiento");
				
				System.out.println("2.Catalogo de productos");
			
				System.out.println("3.Pedidos");
				
				System.out.println("4.Informes");
				
				System.out.println("0.Salir");
				
				guardado = Funciones.dimeEntero("Eligue una opcion del menu",sc);
				
				switch (guardado) {
				case 1:
					menuManetenimientos(sc);
					break;
				case 2:
					menuCatalogo(sc);
					break;
				case 3:
					menuPedidos(sc);
					break;
				case 4:
					menuInformes(sc);
					break;
				case 0:
					break;
				}
		}while(guardado!=0);

	}
	
	public static void menuManetenimientos(Scanner sc) {
		int guardado = 0;
		do {
			System.out.println("1.Gestion de categorias");
			
			System.out.println("2.Gestion de productos");
			
			System.out.println("3.Gestion de clientes");
			
			System.out.println("0.Salir");
			
			guardado = Funciones.dimeEntero("Eligue una opcion del menu",sc);
			
			switch (guardado) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
			menuManetenimientos2(sc);
			case 0:
				break;
			}
		}while(guardado!=0);
	}
	

	public static void menuManetenimientos2(Scanner sc) {
		int guardado = 0;
		do {
			System.out.println("1.Alta de nuevos clientes");
			
			System.out.println("2.Busqueda por codigo");

			System.out.println("0.Salir");
			
			guardado = Funciones.dimeEntero("Eligue una opcion del menu",sc);
			
			switch (guardado) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 0:
				break;
			}
			
		}while(guardado!=0);
		}
	
	public static void menuCatalogo(Scanner sc) {
		int guardado = 0;
		do {
			System.out.println("1.Lista de productos por categoria");
			
			System.out.println("2.Buscar productos");
			
			System.out.println("0.Salir");
			
			guardado = Funciones.dimeEntero("Eligue una opcion del menu",sc);
			
			switch (guardado) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 0:
				break;
			}
		}while(guardado!=0);
	}
	
	public static void menuPedidos(Scanner sc) {
		int guardado = 0;
		do {
			System.out.println("1.Crear pedido");
			
			System.out.println("2.Ver pedidos");
			
			System.out.println("0.Salir");
			
			guardado = Funciones.dimeEntero("Eligue una opcion del menu",sc);
			
			switch (guardado) {
			case 1:
				ClientesDAO.selccionar(pedido(sc));
				break;
			case 2:
				
				break;
			
			case 0:
				break;
			}
			
		}while(guardado!=0);
	}
	
	public static void menuInformes(Scanner sc) {
		int guardado = 0;
		do {
			System.out.println("1.Bajo stock");
			
			System.out.println("2.Pedidos por cliente");
			
			System.out.println("3.Productos mas vendidos");
			
			System.out.println("0.Salir");
			
			guardado = Funciones.dimeEntero("Eligue una opcion del menu",sc);
			
			switch (guardado) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3: 
				
				break;
			case 0:
				break;
			}
			
		}while(guardado!=0);
	}
	
	public static int pedido(Scanner sc) {

		int guardadoCodCliente = Funciones.dimeEntero("Introduce el codigo de un cliente", sc);
		return  guardadoCodCliente;

	}
}
