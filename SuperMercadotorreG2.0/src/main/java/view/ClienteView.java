/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import Controller.ClienteController;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


import model.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
/**
 *
 * @author nebra
 */
public class ClienteView extends javax.swing.JFrame {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField contactoField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton volverAtrasButton;

    public ClienteView() {
        setTitle("Gestión de Clientes");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 20, 80, 25);
        panel.add(idLabel);

        idField = new JTextField(20);
        idField.setBounds(100, 20, 165, 25);
        panel.add(idField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 50, 80, 25);
        panel.add(nombreLabel);

        nombreField = new JTextField(20);
        nombreField.setBounds(100, 50, 165, 25);
        panel.add(nombreField);

        JLabel contactoLabel = new JLabel("Contacto:");
        contactoLabel.setBounds(10, 80, 80, 25);
        panel.add(contactoLabel);

        contactoField = new JTextField(20);
        contactoField.setBounds(100, 80, 165, 25);
        panel.add(contactoField);

        saveButton = new JButton("Guardar");
        saveButton.setBounds(10, 220, 100, 25);
        panel.add(saveButton);

        updateButton = new JButton("Modificar");
        updateButton.setBounds(120, 220, 100, 25);
        panel.add(updateButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.setBounds(230, 220, 100, 25);
        panel.add(deleteButton);

        volverAtrasButton = new JButton("Atrás");
        volverAtrasButton.setBounds(10, 260, 150, 25);
        panel.add(volverAtrasButton);

        this.add(panel);

        ClienteController controller = new ClienteController();
        saveButton.addActionListener(e -> guardarCliente(controller));
        updateButton.addActionListener(e -> modificarCliente(controller));
        deleteButton.addActionListener(e -> eliminarCliente(controller));
        volverAtrasButton.addActionListener(e -> {
            VendedorView vendedorView = new VendedorView();
            vendedorView.setVisible(true);
            dispose();
        });
    }

    private void guardarCliente(ClienteController controller) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombreField.getText());
        cliente.setContacto(contactoField.getText());
        controller.crearCliente(cliente);
    }

    private void modificarCliente(ClienteController controller) {
        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(idField.getText()));
        cliente.setNombre(nombreField.getText());
        cliente.setContacto(contactoField.getText());
        controller.modificarCliente(cliente);
    }

    private void eliminarCliente(ClienteController controller) {
        controller.eliminarCliente(Integer.parseInt(idField.getText()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteView clienteView = new ClienteView();
            clienteView.setVisible(true);
        });
    }
}


    /**
     * Creates new form ClienteVi
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