/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SupervisorView extends javax.swing.JFrame {
   public SupervisorView() {
        setTitle("Supervisor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton gestionProductosButton = new JButton("Gestión de Productos");
        gestionProductosButton.setBounds(100, 50, 250, 25);
        panel.add(gestionProductosButton);

        JButton gestionInventariosButton = new JButton("Gestión de Inventarios");
        gestionInventariosButton.setBounds(100, 100, 250, 25);
        panel.add(gestionInventariosButton);

        JButton gestionReportesButton = new JButton("Gestión de Reportes de Ventas");
        gestionReportesButton.setBounds(100, 150, 250, 25);
        panel.add(gestionReportesButton);
        
        JButton gestionCerrarButton = new JButton("Cerrar Sesión");
        gestionCerrarButton.setBounds(100, 200, 250, 25);
        panel.add(gestionCerrarButton);

        this.add(panel);

        // Acción para el botón de gestión de productos
        gestionProductosButton.addActionListener(e -> {
            ProductoView productoView = new ProductoView();
            productoView.setVisible(true);
            dispose();
        });

        // Acción para el botón de gestión de inventarios
        gestionInventariosButton.addActionListener(e -> {
            InventarioView inventarioView = new InventarioView();
            inventarioView.setVisible(true);
            dispose();
        });

        // Acción para el botón de gestión de reportes de ventas
        gestionReportesButton.addActionListener(e -> {
            ReportesView reportesView = new ReportesView();
            reportesView.setVisible(true);
            dispose();
        });
        
         // Acción para el botón de Cerrar Sesión
        gestionCerrarButton.addActionListener(e -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SupervisorView supervisorView = new SupervisorView();
        supervisorView.setVisible(true);
    }
}


    /*
     * Creates new form SupervisorView
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
/*/
