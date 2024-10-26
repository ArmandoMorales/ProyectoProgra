/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.DetalleVenta;
import model.Venta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class FacturaGenerator {

    public static void generarFactura(Venta venta, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Añadir contenido a la factura
            document.add(new Paragraph("Factura de Venta"));
            document.add(new Paragraph("ID de Cliente: " + venta.getClienteId()));
            document.add(new Paragraph("Vendedor: " + venta.getVendedor()));
            document.add(new Paragraph("Fecha y Hora: " + venta.getFechaHora().toString()));
            document.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(5);
            addTableHeader(table);
            addRows(table, venta.getDetalles());

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total: " + venta.getTotal()));
            document.add(new Paragraph("Recargo: " + venta.getRecargo()));
            document.add(new Paragraph("Impuestos: " + venta.getImpuestos()));
            document.add(new Paragraph("Forma de Pago: " + venta.getFormaPago()));

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Código", "Nombre", "Cantidad", "Precio", "Total")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, List<DetalleVenta> detalles) {
    for (DetalleVenta detalle : detalles) {
        table.addCell(detalle.getProductoCodigo());
        table.addCell(detalle.getNombreProducto()); 
        table.addCell(String.valueOf(detalle.getCantidad()));
        table.addCell(String.valueOf(detalle.getPrecio()));
        table.addCell(String.valueOf(detalle.getCantidad() * detalle.getPrecio()));
    }
}

}

