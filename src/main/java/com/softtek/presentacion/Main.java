package com.softtek.presentacion;

import com.softtek.modelo.Productos;
import com.softtek.persistencia.AccesoProducto;
import com.softtek.persistencia.Conexion;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Conexion c1 = new Conexion();
        try {
            c1.abrirConexion();
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        AccesoProducto aP1 = new AccesoProducto();
        try {
            System.out.println(aP1.obtenerUno(2));
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        AccesoProducto aP2 = new AccesoProducto();
        Productos pp = new Productos(999, "Peras", 3.5, 4);
        try {
            System.out.println(aP2.insertarProducto(pp));
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println(aP2.modificar(4, pp)); // Aquí se corrige la llamada al método modificar
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        try {
            // Eliminar el producto con ID 3
            int idProductoAEliminar = 999;
            if (aP1.delete(idProductoAEliminar)) {
                System.out.println("Producto eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el producto");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
    }
}
