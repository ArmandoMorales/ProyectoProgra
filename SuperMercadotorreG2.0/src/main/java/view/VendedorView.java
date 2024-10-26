/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VendedorView extends javax.swing.JFrame {
     public VendedorView() {
        setTitle("Vendedor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton ventasButton = new JButton("Ventas");
        ventasButton.setBounds(100, 50, 200, 25);
        panel.add(ventasButton);

        JButton clientesButton = new JButton("Clientes");
        clientesButton.setBounds(100, 100, 200, 25);
        panel.add(clientesButton);
        
        JButton CerrarButton = new JButton("Cerrar Sesión");
        CerrarButton.setBounds(100, 150, 200, 25);
        panel.add(CerrarButton);

        this.add(panel);

        // Acción para el botón de ventas
        ventasButton.addActionListener(e -> {
            VentaView ventasView = new VentaView();
            ventasView.setVisible(true);
            dispose();
        });

        // Acción para el botón de clientes
        clientesButton.addActionListener(e -> {
            ClienteView clientesView = new ClienteView();
            clientesView.setVisible(true);
            dispose();
        });
        
        // Acción para el botón de clientes
        CerrarButton.addActionListener(e -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        VendedorView vendedorView = new VendedorView();
        vendedorView.setVisible(true);
    }
}




    /*
    
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
