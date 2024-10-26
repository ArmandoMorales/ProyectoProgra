/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;




import model.Producto;
import Utilidades.ConexionDB;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoController {

   public void crearProducto(Producto producto) {
    String sql = "INSERT INTO Producto (codigo, nombre, descripcion, categoria, imagen, precioNormal, precioPromocion) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, producto.getCodigo());
        pstmt.setString(2, producto.getNombre());
        pstmt.setString(3, producto.getDescripcion());
        pstmt.setString(4, producto.getCategoria());
        pstmt.setBytes(5, producto.getImagen());
        pstmt.setBigDecimal(6, BigDecimal.valueOf(producto.getPrecioNormal()));
        pstmt.setBigDecimal(7, BigDecimal.valueOf(producto.getPrecioPromocion()));
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al crear el producto: " + e.getMessage());
    }
    }

    public void modificarProducto(Producto producto) {
        String sql = "UPDATE Producto SET nombre = ?, categoria = ?, imagen = ?, precioNormal = ?, precioPromocion = ? WHERE codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getCategoria());
            pstmt.setBytes(3, producto.getImagen());
            pstmt.setBigDecimal(4, BigDecimal.valueOf(producto.getPrecioNormal()));
            pstmt.setBigDecimal(5, BigDecimal.valueOf(producto.getPrecioPromocion()));
            pstmt.setString(6, producto.getCodigo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al modificar el producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(String codigo) {
        String sql = "DELETE FROM Producto WHERE codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
        public Producto obtenerProductoPorCodigo(String codigo) {
    String sql = "SELECT * FROM Producto WHERE codigo = ?";
    Producto producto = null;
    try (Connection conn = ConexionDB.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, codigo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            producto = new Producto();
            producto.setCodigo(rs.getString("codigo"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("categoria"));
            producto.setCategoria(rs.getString("categoria"));
            producto.setImagen(rs.getBytes("imagen"));
            producto.setPrecioNormal(rs.getBigDecimal("precioNormal").doubleValue());
            producto.setPrecioPromocion(rs.getBigDecimal("precioPromocion").doubleValue());
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el producto: " + e.getMessage());
    }
    return producto;
}


    public List<Producto> obtenerTodosLosProductos() {
        String sql = "SELECT * FROM Producto";
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setImagen(rs.getBytes("imagen"));
                producto.setPrecioNormal(rs.getBigDecimal("precioNormal").doubleValue());
                producto.setPrecioPromocion(rs.getBigDecimal("precioPromocion").doubleValue());
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }
        return productos;
    }
}

