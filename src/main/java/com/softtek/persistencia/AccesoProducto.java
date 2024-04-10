package com.softtek.persistencia;

import com.softtek.modelo.Productos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccesoProducto extends Conexion {
    public List<Productos> obtenerTodos() throws ClassNotFoundException, SQLException {
        Statement sentencia;
        ResultSet resultado;
        String sql = "Select product_id, product_name, unit_price, units_in_stock from products;";
        List<Productos> productos = new ArrayList<>();
        abrirConexion();
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        while (resultado.next()) {
            productos.add(new Productos(resultado.getInt("product_id"),
                    resultado.getString("product_name"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("units_in_stock")
            ));
        }
        return productos;
    }

    public Productos obtenerUno(int id) throws ClassNotFoundException, SQLException {
        Statement sentencia;
        ResultSet resultado;
        abrirConexion();
        String sql = "Select product_id, product_name, unit_price, units_in_stock from products where product_id=" + id + ";";
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        resultado.next();
        Productos producto = new Productos(resultado.getInt("product_id"),
                resultado.getString("product_name"),
                resultado.getDouble("unit_price"),
                resultado.getInt("units_in_stock"));
        return producto;
    }

    public boolean insertarProducto(Productos productos) throws ClassNotFoundException, SQLException {
        int id = productos.getIdProducto();
        String nombre = productos.getNombreProducto();
        double precio = productos.getPrecioUnitario();
        int unidades = productos.getUnidadesStock();
        Statement sentencia;
        abrirConexion();
        String sql = "INSERT INTO products (product_id, product_name, unit_price, units_in_stock, discontinued) " +
                "VALUES (" + id + ", '" + nombre + "', " + precio + ", " + unidades + ", 0)";
        sentencia = miConexion.createStatement();
        int filasAfectadas = sentencia.executeUpdate(sql);
        return filasAfectadas > 0;
    }


    public boolean modificar(int id, Productos productos) throws ClassNotFoundException, SQLException {
        String nombre = productos.getNombreProducto();
        double precio = productos.getPrecioUnitario();
        int unidades = productos.getUnidadesStock();
        Statement sentencia;
        abrirConexion();
        String sql = "UPDATE products SET product_name = '" + nombre + "', unit_price = " + precio + ", units_in_stock = " + unidades + " WHERE product_id = " + id;
        sentencia = miConexion.createStatement();
        int filasAfectadas = sentencia.executeUpdate(sql);
        return filasAfectadas > 0;
    }

    public boolean delete(int id) throws ClassNotFoundException, SQLException {
        Statement sentencia;
        abrirConexion();
        String sql = "DELETE FROM products WHERE product_id = " + id;
        sentencia = miConexion.createStatement();
        int filasAfectadas = sentencia.executeUpdate(sql);
        return filasAfectadas > 0;
    }


}
