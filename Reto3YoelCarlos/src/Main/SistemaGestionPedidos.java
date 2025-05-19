package Main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import Clases.*;
import ClasesDAO.*;
import Util.Funciones;

public class SistemaGestionPedidos {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE PEDIDOS ===");
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Crear Pedido");
            System.out.println("4. Ver Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Funciones.dimeEntero("", scanner);

            switch (opcion) {
                case 1:
                    gestionProductos();
                    break;
                case 2:
                    gestionClientes();
                    break;
                case 3:
                    crearPedido();
                    break;
                case 4:
                    verPedidos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void gestionProductos() {
        System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
        System.out.println("1. Listar Productos");
        System.out.println("2. Agregar Producto");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");

        int opcion = Funciones.dimeEntero("", scanner);

        switch (opcion) {
            case 1:
                listarProductos();
                break;
            case 2:
                agregarProducto();
                break;
            case 0:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void listarProductos() {
        System.out.println("\n=== LISTADO DE PRODUCTOS ===");
        List<Productos> productos = ProductosDAO.obtenerProductos();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Productos p : productos) {
            System.out.println("ID: " + p.getIdProducto());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Precio: " + p.getPrecio());
            System.out.println("Stock: " + p.getStock());
            System.out.println("-----------------------");
        }
    }

    private static void agregarProducto() {
        System.out.println("\n=== AGREGAR PRODUCTO ===");
        
        // Mostrar categorías disponibles
        List<Categorias> categorias = CategoriasDAO.obtenerCategorias();
        System.out.println("Categorías disponibles:");
        for (Categorias c : categorias) {
            System.out.println(c.getIdcategoria() + " - " + c.getNombre());
        }
        
        int idCategoria = Funciones.dimeEntero("Ingrese el ID de la categoría:", scanner);
        String nombre = Funciones.dimeString("Nombre del producto:", scanner);
        double precio = Funciones.dimeDouble("Precio del producto:", scanner);
        String descripcion = Funciones.dimeString("Descripción:", scanner);
        String color = Funciones.dimeString("Color:", scanner);
        String talla = Funciones.dimeString("Talla:", scanner);
        int stock = Funciones.dimeEntero("Stock inicial:", scanner);

        Productos nuevoProducto = new Productos(0, idCategoria, nombre, precio, 
                                              descripcion, color, talla, stock);
        
        if (ProductosDAO.insertarProducto(nuevoProducto)) {
            System.out.println("Producto agregado exitosamente!");
        } else {
            System.out.println("Error al agregar el producto.");
        }
    }

    private static void gestionClientes() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Agregar Cliente");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");

        int opcion = Funciones.dimeEntero("", scanner);

        switch (opcion) {
            case 1:
                listarClientes();
                break;
            case 2:
                agregarCliente();
                break;
            case 0:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void listarClientes() {
        System.out.println("\n=== LISTADO DE CLIENTES ===");
        List<Clientes> clientes = ClientesDAO.obtenerClientes();
        
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Clientes c : clientes) {
            System.out.println("ID: " + c.getIdCliente());
            System.out.println("Nombre: " + c.getNombre());
            System.out.println("Código: " + c.getCodCliente());
            System.out.println("Dirección: " + c.getDireccion());
            System.out.println("-----------------------");
        }
    }

    private static void agregarCliente() {
        System.out.println("\n=== AGREGAR CLIENTE ===");
        
        String nombre = Funciones.dimeString("Nombre del cliente:", scanner);
        String direccion = Funciones.dimeString("Dirección:", scanner);
        int codigo = Funciones.dimeEntero("Código de cliente:", scanner);

        Clientes nuevoCliente = new Clientes(0, codigo, direccion, nombre);
        
        if (ClientesDAO.insertarCliente(nuevoCliente)) {
            System.out.println("Cliente agregado exitosamente!");
        } else {
            System.out.println("Error al agregar el cliente. El código ya existe.");
        }
    }

    private static void crearPedido() {
        System.out.println("\n=== CREAR NUEVO PEDIDO ===");
        
        // Seleccionar cliente
        listarClientes();
        int codigoCliente = Funciones.dimeEntero("Ingrese el código del cliente:", scanner);
        Clientes cliente = ClientesDAO.buscarPorCodigo(codigoCliente);
        
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Crear el pedido
        Pedidos nuevoPedido = new Pedidos(0, cliente.getIdCliente(), 0.0, 
                                        cliente.getDireccion(), LocalDate.now());
        
        if (!PedidosDAO.insertarPedido(nuevoPedido)) {
            System.out.println("Error al crear el pedido.");
            return;
        }

        // Obtener el ID del pedido recién creado
        int idPedido = obtenerUltimoIdPedido();
        if (idPedido == 0) {
            System.out.println("Error al obtener el ID del pedido.");
            return;
        }

        // Agregar productos al pedido
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== AGREGAR PRODUCTOS AL PEDIDO ===");
            listarProductos();
            System.out.println("0. Finalizar pedido");
            
            int idProducto = Funciones.dimeEntero("Ingrese el ID del producto a agregar:", scanner);
            if (idProducto == 0) {
                continuar = false;
                continue;
            }
            
            Productos producto = ProductosDAO.buscarPorId(idProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }
            
            int cantidad = Funciones.dimeEntero("Ingrese la cantidad:", scanner);
            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que cero.");
                continue;
            }
            
            if (cantidad > producto.getStock()) {
                System.out.println("Stock insuficiente. Disponible: " + producto.getStock());
                continue;
            }
            
            // Crear relación pedido-producto
            PedidoProducto pedidoProducto = new PedidoProducto(0, idPedido, idProducto, 
                                                             cantidad, producto.getPrecio());
            
            if (PedidoProductoDAO.agregarProductoAPedido(pedidoProducto)) {
                // Actualizar stock
                ProductosDAO.actualizarStock(idProducto, -cantidad);
                System.out.println("Producto agregado al pedido.");
            } else {
                System.out.println("Error al agregar el producto al pedido.");
            }
        }
        
        // Calcular y actualizar el total del pedido
        double total = PedidoProductoDAO.calcularTotalPedido(idPedido);
        if (PedidosDAO.actualizarTotalPedido(idPedido, total)) {
            System.out.println("\nPedido completado exitosamente!");
            System.out.println("Número de pedido: " + idPedido);
            System.out.println("Total: " + total);
        } else {
            System.out.println("Error al actualizar el total del pedido.");
        }
    }

    private static int obtenerUltimoIdPedido() {
        List<Pedidos> pedidos = PedidosDAO.obtenerPedidosDelMes();
        return pedidos.isEmpty() ? 0 : pedidos.get(0).getIdPedido();
    }

    private static void verPedidos() {
        System.out.println("\n=== LISTADO DE PEDIDOS ===");
        List<Pedidos> pedidos = PedidosDAO.obtenerPedidosDelMes();
        
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados este mes.");
            return;
        }

        for (Pedidos p : pedidos) {
            System.out.println("\nPedido #" + p.getIdPedido());
            System.out.println("Fecha: " + p.getFecha());
            System.out.println("Cliente ID: " + p.getIdCliente());
            System.out.println("Total: " + p.getPrecioTotal());
            
            System.out.println("Productos:");
            List<PedidoProducto> productos = PedidoProductoDAO.obtenerProductosPorPedido(p.getIdPedido());
            for (PedidoProducto pp : productos) {
                Productos producto = ProductosDAO.buscarPorId(pp.getIdproducto());
                System.out.println("- " + producto.getNombre() + 
                                 " x" + pp.getUnidades() + 
                                 " a " + pp.getPrecio() + " c/u");
            }
            System.out.println("-----------------------");
        }
    }
    
}