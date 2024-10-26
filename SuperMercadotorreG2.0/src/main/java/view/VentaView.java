/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;




import Controller.ClienteController;
import Controller.ProductoController;
import Controller.VentaController;
import Utilidades.FacturaGenerator;
import model.DetalleVenta;
import model.Venta;
import model.Cliente;
import model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VentaView extends JFrame {

    private JTextField clienteIdField;
    private JTextField clienteNombreField;
    private JTextField productoCodigoField;
    private JTextField productoNombreField;
    private JTextField descripcionField;
    private JTextField cantidadField;
    private JTextField precioField;
    private JTextField totalField;
    private JComboBox<String> formaPagoComboBox;
    private JTextField recargoField;
    private JTextField impuestosField;
    private JTextField vendedorField;
    private JButton addButton;
    private JButton removeButton;
    private JButton createButton;
    private JButton backButton;
    private JButton buscarClienteButton;
    private JButton buscarProductoButton;
    private List<DetalleVenta> detalles;
    private JTable productosTable;
    private DefaultTableModel tableModel;

    public VentaView() {
        setTitle("Gestión de Ventas La Torre");
        setSize(700, 610);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        detalles = new ArrayList<>();

        JLabel clienteIdLabel = new JLabel("Cliente ID:");
        clienteIdLabel.setBounds(10, 20, 100, 25);
        panel.add(clienteIdLabel);

        clienteIdField = new JTextField(20);
        clienteIdField.setBounds(120, 20, 165, 25);
        panel.add(clienteIdField);

        buscarClienteButton = new JButton("Buscar Cliente");
        buscarClienteButton.setBounds(300, 20, 150, 25);
        panel.add(buscarClienteButton);

        JLabel clienteNombreLabel = new JLabel("Nombre:");
        clienteNombreLabel.setBounds(10, 50, 100, 25);
        panel.add(clienteNombreLabel);

        clienteNombreField = new JTextField(20);
        clienteNombreField.setBounds(120, 50, 330, 25);
        clienteNombreField.setEditable(false);
        panel.add(clienteNombreField);

        JLabel productoCodigoLabel = new JLabel("Producto Código:");
        productoCodigoLabel.setBounds(10, 80, 100, 25);
        panel.add(productoCodigoLabel);

        productoCodigoField = new JTextField(20);
        productoCodigoField.setBounds(120, 80, 165, 25);
        panel.add(productoCodigoField);

        buscarProductoButton = new JButton("Buscar Producto");
        buscarProductoButton.setBounds(300, 80, 150, 25);
        panel.add(buscarProductoButton);

        JLabel productoNombreLabel = new JLabel("Producto:");
        productoNombreLabel.setBounds(10, 110, 100, 25);
        panel.add(productoNombreLabel);

        productoNombreField = new JTextField(20);
        productoNombreField.setBounds(120, 110, 330, 25);
        productoNombreField.setEditable(false);
        panel.add(productoNombreField);
        
                JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setBounds(10, 140, 100, 25);
        panel.add(descripcionLabel);

        descripcionField = new JTextField(20);
        descripcionField.setBounds(120, 140, 330, 25);
        descripcionField.setEditable(false);
        panel.add(descripcionField);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setBounds(10, 170, 100, 25);
        panel.add(cantidadLabel);

        cantidadField = new JTextField(20);
        cantidadField.setBounds(120, 170, 165, 25);
        panel.add(cantidadField);

        JLabel precioLabel = new JLabel("Precio:");
        precioLabel.setBounds(10, 200, 100, 25);
        panel.add(precioLabel);

        precioField = new JTextField(20);
        precioField.setBounds(120, 200, 165, 25);
        panel.add(precioField);

        addButton = new JButton("Añadir Producto");
        addButton.setBounds(300, 200, 150, 25);
        panel.add(addButton);

        removeButton = new JButton("Quitar Producto");
        removeButton.setBounds(460, 200, 150, 25);
        panel.add(removeButton);

        // Tabla para mostrar los productos
        tableModel = new DefaultTableModel(new Object[]{"Código", "Nombre", "Descripción", "Cantidad", "Precio", "Total"}, 0);
        productosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productosTable);
        scrollPane.setBounds(10, 240, 660, 200);
        panel.add(scrollPane);

        JLabel formaPagoLabel = new JLabel("Forma de Pago:");
        formaPagoLabel.setBounds(10, 450, 100, 25);
        panel.add(formaPagoLabel);

        formaPagoComboBox = new JComboBox<>(new String[]{"efectivo", "tarjeta"});
        formaPagoComboBox.setBounds(120, 450, 165, 25);
        panel.add(formaPagoComboBox);

        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setBounds(10, 480, 100, 25);
        panel.add(totalLabel);

        totalField = new JTextField(20);
        totalField.setBounds(120, 480, 165, 25);
        totalField.setEditable(false); // El total se calculará automáticamente
        panel.add(totalField);

        JLabel recargoLabel = new JLabel("Recargo:");
        recargoLabel.setBounds(300, 480, 100, 25);
        panel.add(recargoLabel);

        recargoField = new JTextField(20);
        recargoField.setBounds(410, 480, 165, 25);
        recargoField.setEditable(false); // El recargo se calculará automáticamente
        panel.add(recargoField);

                JLabel impuestosLabel = new JLabel("Impuestos:");
        impuestosLabel.setBounds(10, 510, 100, 25);
        panel.add(impuestosLabel);

        impuestosField = new JTextField(20);
        impuestosField.setBounds(120, 510, 165, 25);
        impuestosField.setEditable(false); // Los impuestos se calcularán automáticamente
        panel.add(impuestosField);

        JLabel vendedorLabel = new JLabel("Vendedor:");
        vendedorLabel.setBounds(300, 510, 100, 25);
        panel.add(vendedorLabel);

        vendedorField = new JTextField(20);
        vendedorField.setBounds(410, 510, 165, 25);
        panel.add(vendedorField);

        createButton = new JButton("Crear Venta");
        createButton.setBounds(10, 540, 150, 25);
        panel.add(createButton);

        backButton = new JButton("Atrás");
        backButton.setBounds(170, 540, 100, 25);
        panel.add(backButton);

        this.add(panel);

        // Acción para el botón de buscar cliente
        buscarClienteButton.addActionListener((ActionEvent e) -> {
            buscarCliente();
        });

        // Acción para el botón de buscar producto
        buscarProductoButton.addActionListener((ActionEvent e) -> {
            buscarProducto();
        });

                // Acción para el comboBox de forma de pago
        formaPagoComboBox.addActionListener((ActionEvent e) -> {
            recalcularRecargo();
        });

        // Acción para el botón de añadir producto
        addButton.addActionListener((ActionEvent e) -> {
            añadirProducto();
        });

        // Acción para el botón de quitar producto
        removeButton.addActionListener((ActionEvent e) -> {
            quitarProducto();
        });

        // Acción para el botón de crear venta
        createButton.addActionListener((ActionEvent e) -> {
            crearVenta();
        });

        // Acción para el botón de volver atrás
        backButton.addActionListener((ActionEvent e) -> {
            VendedorView vendedorView = new VendedorView();
            vendedorView.setVisible(true);
            dispose();
        });
    }

    // Método para buscar cliente por ID y rellenar el campo de nombre
    private void buscarCliente() {
        int clienteId = Integer.parseInt(clienteIdField.getText());
        ClienteController clienteController = new ClienteController();
        Cliente cliente = clienteController.obtenerClientePorId(clienteId);
        if (cliente != null) {
            clienteNombreField.setText(cliente.getNombre());
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

        // Método para buscar producto por código y rellenar los campos de nombre, descripción, precio y total
    private void buscarProducto() {
        String codigo = productoCodigoField.getText();
        ProductoController productoController = new ProductoController();
        Producto producto = productoController.obtenerProductoPorCodigo(codigo);
        if (producto != null) {
            productoNombreField.setText(producto.getNombre());
            descripcionField.setText(producto.getDescripcion());
            precioField.setText(Double.toString(producto.getPrecioNormal())); // Usamos Double.toString() para convertir a String
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para añadir producto a la venta
    private void añadirProducto() {
        try {
            String codigo = productoCodigoField.getText();
            String nombre = productoNombreField.getText();
            String descripcion = descripcionField.getText();
            int cantidad = Integer.parseInt(cantidadField.getText());
            double precio = Double.parseDouble(precioField.getText());
            double total = cantidad * precio;
            DetalleVenta detalle = new DetalleVenta();
            detalle.setProductoCodigo(codigo);
            detalle.setNombreProducto(nombre); 
            detalle.setCantidad(cantidad);
            detalle.setPrecio(precio);
            detalles.add(detalle);

            // Añadir a la tabla
            tableModel.addRow(new Object[]{codigo, nombre, descripcion, cantidad, precio, total});

            // Actualizar el total
            calcularTotalRecargoImpuestos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce valores válidos para cantidad y precio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }

    // Método para quitar producto de la venta
    private void quitarProducto() {
        int selectedRow = productosTable.getSelectedRow();
        if (selectedRow != -1) {
            detalles.remove(selectedRow);
            tableModel.removeRow(selectedRow);

            // Actualizar el total
            calcularTotalRecargoImpuestos();
        }
    }

    // Método para recalcular el recargo
    private void recalcularRecargo() {
        double total = Double.parseDouble(totalField.getText());
        double recargo = formaPagoComboBox.getSelectedItem().equals("tarjeta") ? total * 0.05 : 0;
        recargoField.setText(String.valueOf(recargo));
    }

    // Método para calcular el total, recargo e impuestos
    private void calcularTotalRecargoImpuestos() {
        double total = detalles.stream().mapToDouble(d -> d.getCantidad() * d.getPrecio()).sum();
        totalField.setText(String.valueOf(total));
        double recargo = formaPagoComboBox.getSelectedItem().equals("tarjeta") ? total * 0.05 : 0;
        recargoField.setText(String.valueOf(recargo));
        double impuestos = total * 0.12;
        impuestosField.setText(String.valueOf(impuestos));
    }

    // Método para crear la venta
    private void crearVenta() {
        Venta venta = new Venta();
        venta.setClienteId(Integer.parseInt(clienteIdField.getText()));
        venta.setTotal(Double.parseDouble(totalField.getText()));
        venta.setFormaPago((String) formaPagoComboBox.getSelectedItem());
        venta.setRecargo(Double.parseDouble(recargoField.getText()));
        venta.setImpuestos(Double.parseDouble(impuestosField.getText()));
        venta.setVendedor(vendedorField.getText());
        venta.setFechaHora(new Timestamp(System.currentTimeMillis()));
        venta.setDetalles(detalles);

        VentaController controller = new VentaController();
        controller.crearVenta(venta);

        // Crear la carpeta si no existe
        String directorio = "C:\\Users\\arman\\Documents\\NetBeansProjects\\SupermercadoTorre\\src\\main\\java\\Facturas";
        File carpeta = new File(directorio);
        if (!carpeta.exists()) {
        carpeta.mkdirs();
    }

    // Generar factura en PDF
    String filePath = directorio + "\\factura_" + venta.getClienteId() + "_" + venta.getFechaHora().getTime() + ".pdf";
    FacturaGenerator.generarFactura(venta, filePath);
    
        // Limpiar campos y detalles después de crear la venta
        clienteIdField.setText("");
        clienteNombreField.setText("");
        productoCodigoField.setText("");
        productoNombreField.setText("");
        descripcionField.setText("");
        cantidadField.setText("");
        precioField.setText("");
        totalField.setText("");
        recargoField.setText("");
        impuestosField.setText("");
        vendedorField.setText("");
        detalles.clear();
        tableModel.setRowCount(0); // Limpiar la tabla

        JOptionPane.showMessageDialog(null, "Venta creada correctamente! Factura generada en: " + filePath);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentaView ventaView = new VentaView();
            ventaView.setVisible(true);
        });
    }
}




        
    /**
     * Creates new form VentaView
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
*/
