/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import Controller.ReporteController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportesView extends JFrame {
    private JButton exportarExcelButton;
    private JButton exportarPDFButton;
    private JButton backButton;

    public ReportesView() {
        setTitle("Reportes de Ventas");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        exportarExcelButton = new JButton("Exportar a Excel");
        exportarExcelButton.setBounds(50, 50, 200, 25);
        panel.add(exportarExcelButton);

        exportarPDFButton = new JButton("Exportar a PDF");
        exportarPDFButton.setBounds(50, 100, 200, 25);
        panel.add(exportarPDFButton);

        backButton = new JButton("Atrás");
        backButton.setBounds(50, 150, 200, 25);
        panel.add(backButton);

        this.add(panel);

        ReporteController controller = new ReporteController();

        // Acción para el botón de exportar a Excel
        exportarExcelButton.addActionListener((ActionEvent e) -> {
            String filePath = "C:/Users/arman/Downloads/ventas_del_dia.xlsx";
            controller.exportarAExcel(filePath);
            JOptionPane.showMessageDialog(null, "Ventas exportadas a Excel correctamente!");
        });

        // Acción para el botón de exportar a PDF
        exportarPDFButton.addActionListener((ActionEvent e) -> {
            String filePath = "C:/Users/nebra/Downloads/ventas_del_dia.pdf";
            controller.exportarAPDF(filePath);
            JOptionPane.showMessageDialog(null, "Ventas exportadas a PDF correctamente!");
        });

        // Acción para el botón de volver atrás
        backButton.addActionListener((ActionEvent e) -> {
            SupervisorView supervisorView = new SupervisorView();
            supervisorView.setVisible(true);
            dispose();
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportesView reportesView = new ReportesView();
            reportesView.setVisible(true);
        });
    }
}


  

    /**
     * Creates new form ReportesView
    @SuppressWarnings("unchecked")
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
