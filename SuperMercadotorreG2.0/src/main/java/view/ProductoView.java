/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import Controller.InventarioController;
import Controller.ProductoController;
import model.Producto;
import model.Inventario;
import model.MovimientoInventario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

public class ProductoView extends JFrame {
    private JTextField productoCodigoField;
    private JTextField nombreField;
    private JTextField descripcionField;
    private JTextField categoriaField;
    private JTextField precioNormalField;
    private JTextField precioPromocionField;
    private JTextField cantidadField;
    private JLabel imagenLabel;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton cargarImagenButton;
    private JButton backButton;
    private File imagenFile;

    public ProductoView() {
        setTitle("Gestión de Productos");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel productoCodigoLabel = new JLabel("Código:");
        productoCodigoLabel.setBounds(10, 20, 100, 25);
        panel.add(productoCodigoLabel);

        productoCodigoField = new JTextField(20);
        productoCodigoField.setBounds(120, 20, 165, 25);
        panel.add(productoCodigoField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 50, 100, 25);
        panel.add(nombreLabel);

        nombreField = new JTextField(20);
        nombreField.setBounds(120, 50, 165, 25);
        panel.add(nombreField);

        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setBounds(10, 80, 100, 25);
        panel.add(descripcionLabel);

        descripcionField = new JTextField(20);
        descripcionField.setBounds(120, 80, 165, 25);
        panel.add(descripcionField);

        JLabel categoriaLabel = new JLabel("Categoría:");
        categoriaLabel.setBounds(10, 110, 100, 25);
        panel.add(categoriaLabel);

        categoriaField = new JTextField(20);
        categoriaField.setBounds(120, 110, 165, 25);
        panel.add(categoriaField);

        JLabel precioNormalLabel = new JLabel("Precio Normal:");
        precioNormalLabel.setBounds(10, 140, 100, 25);
        panel.add(precioNormalLabel);

        precioNormalField = new JTextField(20);
        precioNormalField.setBounds(120, 140, 165, 25);
        panel.add(precioNormalField);

        JLabel precioPromocionLabel = new JLabel("Precio Promoción:");
        precioPromocionLabel.setBounds(10, 170, 100, 25);
        panel.add(precioPromocionLabel);

        precioPromocionField = new JTextField(20);
        precioPromocionField.setBounds(120, 170, 165, 25);
        panel.add(precioPromocionField);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setBounds(10, 200, 100, 25);
        panel.add(cantidadLabel);

        cantidadField = new JTextField(20);
        cantidadField.setBounds(120, 200, 165, 25);
        panel.add(cantidadField);

        imagenLabel = new JLabel("Imagen:");
        imagenLabel.setBounds(10, 230, 100, 25);
        panel.add(imagenLabel);

        cargarImagenButton = new JButton("Cargar Imagen");
        cargarImagenButton.setBounds(120, 230, 165, 25);
        panel.add(cargarImagenButton);
        
                saveButton = new JButton("Guardar");
        saveButton.setBounds(10, 260, 100, 25);
        panel.add(saveButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.setBounds(120, 260, 100, 25);
        panel.add(deleteButton);

        backButton = new JButton("Atrás");
        backButton.setBounds(230, 260, 100, 25);
        panel.add(backButton);

        this.add(panel);

        ProductoController controller = new ProductoController();
        saveButton.addActionListener(e -> guardarProducto(controller));
        deleteButton.addActionListener(e -> eliminarProducto(controller));
        cargarImagenButton.addActionListener(e -> cargarImagen());
        backButton.addActionListener(e -> {
            SupervisorView supervisorView = new SupervisorView();
            supervisorView.setVisible(true);
            dispose();
        });

        this.add(panel);
    }
 private void cargarImagen() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        imagenFile = fileChooser.getSelectedFile();
        imagenLabel.setText(imagenFile.getName());
    }
}

private void guardarProducto(ProductoController controller) {
    Producto producto = new Producto();
    producto.setCodigo(productoCodigoField.getText());
    producto.setNombre(nombreField.getText());
    producto.setDescripcion(descripcionField.getText());
    producto.setCategoria(categoriaField.getText());
    producto.setPrecioNormal(Double.parseDouble(precioNormalField.getText()));
    producto.setPrecioPromocion(Double.parseDouble(precioPromocionField.getText()));
    producto.setCantidad(Integer.parseInt(cantidadField.getText()));

    if (imagenFile != null) {
        try (FileInputStream fis = new FileInputStream(imagenFile)) {
            byte[] imagenBytes = fis.readAllBytes();
            producto.setImagen(imagenBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    controller.crearProducto(producto);


        // Actualizar el inventario
        Inventario inventario = new Inventario();
        inventario.setProductoCodigo(producto.getCodigo());
        inventario.setCantidad(producto.getCantidad());
        InventarioController inventarioController = new InventarioController();
        inventarioController.crearInventario(inventario);

        // Registrar el movimiento del inventario
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProductoCodigo(producto.getCodigo());
        movimiento.setCantidad(producto.getCantidad());
        movimiento.setUsuario("usuario_admin"); // Ajusta el usuario según corresponda
        movimiento.setFechaHora(new Timestamp(System.currentTimeMillis()));
        movimiento.setMotivo("Ingreso inicial del producto");
        inventarioController.registrarMovimientoInventario(movimiento);
    }

        private void eliminarProducto(ProductoController controller) {
        String codigo = productoCodigoField.getText();
        controller.eliminarProducto(codigo);

        // También eliminar del inventario
        InventarioController inventarioController = new InventarioController();
        inventarioController.eliminarInventario(codigo);

        // Registrar el movimiento del inventario
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProductoCodigo(codigo);
        movimiento.setCantidad(0); // No se especifica cantidad al eliminar producto
        movimiento.setUsuario("usuario_admin"); // Ajusta el usuario según corresponda
        movimiento.setFechaHora(new Timestamp(System.currentTimeMillis()));
        movimiento.setMotivo("Eliminación del producto");
        inventarioController.registrarMovimientoInventario(movimiento);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductoView productoView = new ProductoView();
            productoView.setVisible(true);
        });
    }
}



     
    /**
     * Creates new form ProductoView
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("GESTIÓN DE PRODUCTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jLabel1)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
/*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
*/
