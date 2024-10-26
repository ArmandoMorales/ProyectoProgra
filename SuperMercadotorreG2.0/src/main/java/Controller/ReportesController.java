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
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;

public class ReportesController {

    // Método para obtener las ventas del día
    public ResultSet obtenerVentasDelDia() {
        String sql = "SELECT * FROM Venta WHERE DATE(fechaHora) = CURDATE()";
        ResultSet rs = null;

        try {
            Connection conn = ConexionDB.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas del día: " + e.getMessage());
        }

        return rs;
    }

    // Método para exportar las ventas a Excel
    public void exportarAExcel(String filePath) {
        ResultSet rs = obtenerVentasDelDia();

        if (rs != null) {
            try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(filePath)) {
                Sheet sheet = workbook.createSheet("Ventas del Día");

                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Cliente ID");
                headerRow.createCell(2).setCellValue("Total");
                headerRow.createCell(3).setCellValue("Forma de Pago");
                headerRow.createCell(4).setCellValue("Recargo");
                headerRow.createCell(5).setCellValue("Impuestos");
                headerRow.createCell(6).setCellValue("Vendedor");
                headerRow.createCell(7).setCellValue("Fecha y Hora");

                int rowNum = 1;

                while (rs.next()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(rs.getInt("id"));
                    row.createCell(1).setCellValue(rs.getInt("cliente_id"));
                    row.createCell(2).setCellValue(rs.getDouble("total"));
                    row.createCell(3).setCellValue(rs.getString("formaPago"));
                    row.createCell(4).setCellValue(rs.getDouble("recargo"));
                    row.createCell(5).setCellValue(rs.getDouble("impuestos"));
                    row.createCell(6).setCellValue(rs.getString("vendedor"));
                    row.createCell(7).setCellValue(rs.getTimestamp("fechaHora").toString());
                }

                workbook.write(fileOut);
            } catch (SQLException | IOException e) {
                System.out.println("Error al exportar a Excel: " + e.getMessage());
            }
        }
    }
    // Método para exportar las ventas a PDF
    public void exportarAPDF(String filePath) {
        ResultSet rs = obtenerVentasDelDia();

        if (rs != null) {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();
                PdfPTable table = new PdfPTable(8);
                table.addCell("ID");
                table.addCell("Cliente ID");
                table.addCell("Total");
                table.addCell("Forma de Pago");
                table.addCell("Recargo");
                table.addCell("Impuestos");
                table.addCell("Vendedor");
                table.addCell("Fecha y Hora");

                while (rs.next()) {
                    table.addCell(String.valueOf(rs.getInt("id")));
                    table.addCell(String.valueOf(rs.getInt("cliente_id")));
                    table.addCell(String.valueOf(rs.getDouble("total")));
                    table.addCell(rs.getString("formaPago"));
                    table.addCell(String.valueOf(rs.getDouble("recargo")));
                    table.addCell(String.valueOf(rs.getDouble("impuestos")));
                    table.addCell(rs.getString("vendedor"));
                    table.addCell(rs.getTimestamp("fechaHora").toString());
                }

                document.add(table);
            } catch (SQLException | DocumentException | IOException e) {
                System.out.println("Error al exportar a PDF: " + e.getMessage());
            } finally {
                document.close();
            }
        }
    }
}

