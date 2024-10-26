/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author nebra
 */


import model.Venta;
import model.DetalleVenta;
import model.Inventario;
import model.MovimientoInventario;
import Utilidades.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaController {

    public void crearVenta(Venta venta) {
        String sqlVenta = "INSERT INTO Venta (cliente_id, total, formaPago, recargo, impuestos, vendedor, fechaHora) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO DetalleVenta (venta_id, producto_codigo, cantidad, precio) VALUES (?, ?, ?, ?)";
        String sqlInventario = "UPDATE Inventario SET cantidad = cantidad - ? WHERE producto_codigo = ?";
        String sqlMovimiento = "INSERT INTO MovimientoInventario (producto_codigo, cantidad, usuario, fechaHora, motivo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection()) {
            conn.setAutoCommit(false); // Desactivar auto-commit para manejar la transacción manualmente

            try (PreparedStatement pstmtVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                pstmtVenta.setInt(1, venta.getClienteId());
                pstmtVenta.setDouble(2, venta.getTotal());
                pstmtVenta.setString(3, venta.getFormaPago());
                pstmtVenta.setDouble(4, venta.getRecargo());
                pstmtVenta.setDouble(5, venta.getImpuestos());
                pstmtVenta.setString(6, venta.getVendedor());
                pstmtVenta.setTimestamp(7, venta.getFechaHora());
                pstmtVenta.executeUpdate();

                try (ResultSet rs = pstmtVenta.getGeneratedKeys()) {
                    if (rs.next()) {
                        int ventaId = rs.getInt(1);

                        try (PreparedStatement pstmtDetalle = conn.prepareStatement(sqlDetalle);
                             PreparedStatement pstmtInventario = conn.prepareStatement(sqlInventario);
                             PreparedStatement pstmtMovimiento = conn.prepareStatement(sqlMovimiento)) {
                            for (DetalleVenta detalle : venta.getDetalles()) {
                                pstmtDetalle.setInt(1, ventaId);
                                pstmtDetalle.setString(2, detalle.getProductoCodigo());
                                pstmtDetalle.setInt(3, detalle.getCantidad());
                                pstmtDetalle.setDouble(4, detalle.getPrecio());
                                pstmtDetalle.addBatch();

                                pstmtInventario.setInt(1, detalle.getCantidad());
                                pstmtInventario.setString(2, detalle.getProductoCodigo());
                                pstmtInventario.addBatch();

                                pstmtMovimiento.setString(1, detalle.getProductoCodigo());
                                pstmtMovimiento.setInt(2, -detalle.getCantidad()); // Cantidad negativa para restar del inventario
                                pstmtMovimiento.setString(3, venta.getVendedor());
                                pstmtMovimiento.setTimestamp(4, venta.getFechaHora());
                                pstmtMovimiento.setString(5, "Venta");
                                pstmtMovimiento.addBatch();
                            }
                            pstmtDetalle.executeBatch();
                            pstmtInventario.executeBatch();
                            pstmtMovimiento.executeBatch();
                        }
                    }
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error al crear la venta: " + e.getMessage());
            e.printStackTrace();
        }
    }
        public Venta obtenerVentaPorId(int id) {
        String sql = "SELECT * FROM Venta WHERE id = ?";
        Venta venta = null;
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setClienteId(rs.getInt("cliente_id"));
                venta.setTotal(rs.getDouble("total"));
                venta.setFormaPago(rs.getString("formaPago"));
                venta.setRecargo(rs.getDouble("recargo"));
                venta.setImpuestos(rs.getDouble("impuestos"));
                venta.setVendedor(rs.getString("vendedor"));
                venta.setFechaHora(rs.getTimestamp("fechaHora"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la venta: " + e.getMessage());
            e.printStackTrace();
        }
        if (venta != null) {
            venta.setDetalles(obtenerDetallesPorVentaId(venta.getId()));
        }
        return venta;
    }

    private List<DetalleVenta> obtenerDetallesPorVentaId(int ventaId) {
        String sql = "SELECT * FROM DetalleVenta WHERE venta_id = ?";
        List<DetalleVenta> detalles = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ventaId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setId(rs.getInt("id"));
                detalle.setVentaId(rs.getInt("venta_id"));
                detalle.setProductoCodigo(rs.getString("producto_codigo"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio(rs.getDouble("precio"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los detalles de la venta: " + e.getMessage());
            e.printStackTrace();
        }
        return detalles;
    }
}



