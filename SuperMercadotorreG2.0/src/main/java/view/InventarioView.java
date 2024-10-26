/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import Controller.InventarioController;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


import model.Inventario;
import model.MovimientoInventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
/**
 *
 * @author nebra
 */
public class InventarioView extends javax.swing.JFrame {
   
    private JTextField productoCodigoField;
    private JTextField cantidadField;
    private JTextField usuarioField;
    private JTextField motivoField;
    private JButton addButton;
    private JButton removeButton;
    private JButton backButton;

    public InventarioView() {
        setTitle("Gestión de Inventarios");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel productoCodigoLabel = new JLabel("Producto Código:");
        productoCodigoLabel.setBounds(10, 20, 100, 25);
        panel.add(productoCodigoLabel);

        productoCodigoField = new JTextField(20);
        productoCodigoField.setBounds(120, 20, 165, 25);
        panel.add(productoCodigoField);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setBounds(10, 50, 100, 25);
        panel.add(cantidadLabel);

        cantidadField = new JTextField(20);
        cantidadField.setBounds(120, 50, 165, 25);
        panel.add(cantidadField);

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setBounds(10, 80, 100, 25);
        panel.add(usuarioLabel);

        usuarioField = new JTextField(20);
        usuarioField.setBounds(120, 80, 165, 25);
        panel.add(usuarioField);

        JLabel motivoLabel = new JLabel("Motivo:");
        motivoLabel.setBounds(10, 110, 100, 25);
        panel.add(motivoLabel);

        motivoField = new JTextField(20);
        motivoField.setBounds(120, 110, 165, 25);
        panel.add(motivoField);

        addButton = new JButton("Añadir");
        addButton.setBounds(10, 180, 100, 25);
        panel.add(addButton);

        removeButton = new JButton("Quitar");
        removeButton.setBounds(120, 180, 100, 25);
        panel.add(removeButton);

        backButton = new JButton("Atrás");
        backButton.setBounds(70, 230, 100, 25);
        panel.add(backButton);

        this.add(panel);

        InventarioController controller = new InventarioController();

        // Método para Añadir Existencias
        addButton.addActionListener((ActionEvent e) -> {
            añadirExistencias(controller);
        });

        // Método para Quitar Existencias
        removeButton.addActionListener((ActionEvent e) -> {
            quitarExistencias(controller);
        });

        // Acción para el botón de atrás
        backButton.addActionListener((ActionEvent e) -> {
            SupervisorView supervisorView = new SupervisorView();
            supervisorView.setVisible(true);
            dispose();
        });
    }

    // Método para Añadir Existencias
    private void añadirExistencias(InventarioController controller) {
        Inventario inventario = new Inventario();
        inventario.setProductoCodigo(productoCodigoField.getText());
        inventario.setCantidad(Integer.parseInt(cantidadField.getText()));

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProductoCodigo(productoCodigoField.getText());
        movimiento.setCantidad(Integer.parseInt(cantidadField.getText()));
        movimiento.setUsuario(usuarioField.getText());
        movimiento.setFechaHora(new Timestamp(System.currentTimeMillis()));
        movimiento.setMotivo(motivoField.getText());

        controller.añadirExistencias(inventario, movimiento);
    }

    // Método para Quitar Existencias
    private void quitarExistencias(InventarioController controller) {
        Inventario inventario = new Inventario();
        inventario.setProductoCodigo(productoCodigoField.getText());
        inventario.setCantidad(Integer.parseInt(cantidadField.getText()));

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProductoCodigo(productoCodigoField.getText());
        movimiento.setCantidad(Integer.parseInt(cantidadField.getText()));
        movimiento.setUsuario(usuarioField.getText());
        movimiento.setFechaHora(new Timestamp(System.currentTimeMillis()));
        movimiento.setMotivo(motivoField.getText());

        controller.quitarExistencias(inventario, movimiento);
    }

    public static void main(String[] args) {
        InventarioView inventarioView = new InventarioView();
        inventarioView.setVisible(true);
    }

}



    /**
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("aaaa");

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel1)
                .addGap(62, 62, 62)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(247, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
* */
