/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import model.Inventario;
import model.MovimientoInventario;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


/**
 *
 * @author nebra
 */


public class InventarioController {

    // Método para crear un nuevo registro de inventario
    public void crearInventario(Inventario inventario) {
        String sql = "INSERT INTO Inventario (producto_codigo, cantidad) VALUES (?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, inventario.getProductoCodigo());
            pstmt.setInt(2, inventario.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear el inventario: " + e.getMessage());
        }
    }

    // Método para obtener un inventario por el código del producto
    public Inventario obtenerInventarioPorCodigo(String codigo) {
        String sql = "SELECT * FROM Inventario WHERE producto_codigo = ?";
        Inventario inventario = null;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                inventario = new Inventario();
                inventario.setId(rs.getInt("id"));
                inventario.setProductoCodigo(rs.getString("producto_codigo"));
                inventario.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el inventario: " + e.getMessage());
        }
        return inventario;
    }


    // Método para modificar un inventario existente
    public void modificarInventario(Inventario inventario) {
        String sql = "UPDATE Inventario SET cantidad = ? WHERE producto_codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inventario.getCantidad());
            pstmt.setString(2, inventario.getProductoCodigo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al modificar el inventario: " + e.getMessage());
        }
    }

    // Método para registrar un movimiento de inventario
    public void registrarMovimientoInventario(MovimientoInventario movimiento) {
        String sql = "INSERT INTO MovimientoInventario (producto_codigo, cantidad, usuario, fechaHora, motivo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movimiento.getProductoCodigo());
            pstmt.setInt(2, movimiento.getCantidad());
            pstmt.setString(3, movimiento.getUsuario());
            pstmt.setTimestamp(4, movimiento.getFechaHora());
            pstmt.setString(5, movimiento.getMotivo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar el movimiento del inventario: " + e.getMessage());
        }
    }

   // Método para añadir existencias a un inventario existente
    public void añadirExistencias(Inventario inventario, MovimientoInventario movimiento) {
        String sqlInventario = "UPDATE Inventario SET cantidad = cantidad + ? WHERE producto_codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmtInventario = conn.prepareStatement(sqlInventario)) {
            pstmtInventario.setInt(1, inventario.getCantidad());
            pstmtInventario.setString(2, inventario.getProductoCodigo());
            pstmtInventario.executeUpdate();

            // Registrar el movimiento de inventario
            registrarMovimientoInventario(movimiento);
        } catch (SQLException e) {
            System.out.println("Error al añadir existencias: " + e.getMessage());
        }
    }


    // Método para quitar existencias de un inventario existente
    public void quitarExistencias(Inventario inventario, MovimientoInventario movimiento) {
        String sqlInventario = "UPDATE Inventario SET cantidad = cantidad - ? WHERE producto_codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmtInventario = conn.prepareStatement(sqlInventario)) {
            pstmtInventario.setInt(1, inventario.getCantidad());
            pstmtInventario.setString(2, inventario.getProductoCodigo());
            pstmtInventario.executeUpdate();

            // Registrar el movimiento de inventario
            movimiento.setCantidad(-movimiento.getCantidad()); // Negativo para salida
            registrarMovimientoInventario(movimiento);
        } catch (SQLException e) {
            System.out.println("Error al quitar existencias: " + e.getMessage());
        }
    }

    // Método para eliminar un inventario existente
    public void eliminarInventario(String productoCodigo) {
        String sql = "DELETE FROM Inventario WHERE producto_codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productoCodigo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el inventario: " + e.getMessage());
        }
    }
}




