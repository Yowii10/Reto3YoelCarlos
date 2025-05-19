package ClasesDAO;
import java.util.List;
import java.util.Scanner;
import ClasesDAO.*;
import Clases.*;

import Util.Funciones;

public class MainPrueba {
     static Scanner scanner = new Scanner(System.in);
   
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n MENÚ PRINCIPAL");
            System.out.println("1. Mantenimientos");
            System.out.println("2. Catálogo de productos");
            System.out.println("3. Pedidos");
            System.out.println("4. Informes");
            System.out.println("0. Salir");
            int opcion = Funciones.dimeEntero("Seleccione una opción:", scanner);

            switch (opcion) {
                case 1: mantenimientoMenu(); break;
                //case 2: catalogoMenu(); break;
                case 3: pedidosMenu(); break;
                case 4: informesMenu(); break;
                case 0: System.out.println(" Saliendo..."); return;
                default: System.out.println(" Opción inválida, intente de nuevo.");
            }
        }
    }

    private static void mantenimientoMenu() {
        System.out.println("\n MANTENIMIENTO");
        System.out.println("1.1 Gestión de categorías");
        System.out.println("1.2 Gestión de productos");
        System.out.println("1.3 Gestión de clientes");
        int opcion = Funciones.dimeEntero("Seleccione una opción:", scanner);

        if (opcion == 1) {
            String nombre = Funciones.dimeString("Ingrese el nombre de la nueva categoría:", scanner);
            CategoriasDAO.insertarCategoria(null);
        } else if (opcion == 2) {
            agregarProducto();
        } else if (opcion == 3) {
            gestionarClientes();
        }
    }

    private static void agregarProducto() {
        System.out.println("\n Agregar Producto");
        int idCategoria = Funciones.dimeEntero("Ingrese el ID de la categoría:", scanner);
        String nombre = Funciones.dimeString("Nombre del producto:", scanner);
        double precio = Funciones.dimeDouble("Precio del producto:", scanner);
        String descripcion = Funciones.dimeString("Descripción:", scanner);
        String color = Funciones.dimeString("Color:", scanner);
        String talla = Funciones.dimeString("Talla:", scanner);
        int stock = Funciones.dimeEntero("Stock disponible:", scanner);

        Productos producto = new Productos(0, idCategoria, nombre, precio, descripcion, color, talla, stock);
        ProductosDAO.insertarProducto(producto);
    }

    private static void gestionarClientes() {
        System.out.println("\n Gestión de Clientes");
        int opcion = Funciones.dimeEntero("1. Alta de nuevos clientes\n2. Buscar cliente\nSeleccione una opción:", scanner);

        if (opcion == 1) {
            String nombre = Funciones.dimeString("Nombre del cliente:", scanner);
            String direccion = Funciones.dimeString("Dirección:", scanner);
            int codigo = Funciones.dimeEntero("Código de cliente:", scanner);

            Clientes cliente = new Clientes(0, codigo, direccion, nombre);
            ClientesDAO.insertarCliente(cliente);
        } else if (opcion == 2) {
            int codigo = Funciones.dimeEntero("Ingrese el código del cliente:", scanner);
            Clientes cliente = ClientesDAO.buscarPorCodigo(codigo);
            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente.getNombre());
                System.out.println(", Dirección: " + cliente.getDireccion());		
            } else {
                System.out.println(" Cliente no encontrado.");
            }
        }
    }

    private static void pedidosMenu() {
        System.out.println("\n PEDIDOS");
        int opcion = Funciones.dimeEntero("1. Crear pedido\n2. Ver pedidos\nSeleccione una opción:", scanner);

        if (opcion == 1) {
            crearPedido();
        } else if (opcion == 2) {
            listarPedidos();
        }
    }

    private static void crearPedido() {
        System.out.println("\nCrear Pedido");
        int codigoCliente = Funciones.dimeEntero("Ingrese el código del cliente:", scanner);
        Cliente cliente = clienteDAO.buscarClientePorCodigo(codigoCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Cliente seleccionado: " + cliente.getNombre());
        String direccionEnvio = Funciones.dimeString("Ingrese la dirección de envío (o presione Enter para usar la del cliente):", scanner);
        if (direccionEnvio.isEmpty()) {
            direccionEnvio = cliente.getDireccion();
        }

        Pedido pedido = new Pedido(0, cliente.getIdCliente(), 0.0, direccionEnvio, new java.util.Date());
        pedidoDAO.crearPedido(pedido);

        double total = 0.0;
        while (true) {
            String nombreProducto = Funciones.dimeString("Ingrese el nombre del producto (o -1 para finalizar):", scanner);
            if (nombreProducto.equals("-1")) break;

            Producto producto = productoDAO.buscarProductoPorNombre(nombreProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            int unidades = Funciones.dimeEntero("Cantidad a comprar:", scanner);
            double precioTotal = producto.getPrecio() * unidades;
            total += precioTotal;

            PedidoProducto pedidoProducto = new PedidoProducto(0, pedido.getIdPedido(), producto.getIdProducto(), unidades, precioTotal);
            pedidoProductoDAO.agregarProductoAPedido(pedidoProducto);
        }

        pedidoDAO.actualizarPrecioTotal(pedido.getIdPedido(), total);
        System.out.println("Pedido guardado con total: " + total + "€");
    }

    private static void listarPedidos() {
        System.out.println("\nPedidos del mes:");
        List<Pedido> pedidos = pedidoDAO.listarPedidosDelMes();
        for (Pedido pedido : pedidos) {
            System.out.println("Pedido #" + pedido.getIdPedido() + " | Cliente ID: " + pedido.getIdCliente() + " | Total: " + pedido.getPrecioTotal() + "€");
        }
    }

    private static void informesMenu() {
        System.out.println("\n INFORMES");
        System.out.println("Funcionalidades aún por implementar...");
    }
}