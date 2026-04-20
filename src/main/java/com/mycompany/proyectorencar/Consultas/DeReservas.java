/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectorencar.Consultas;

import com.mycompany.proyectorencar.ArchivoUtil;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class DeReservas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DeReservas.class.getName());

    /**
     * Creates new form DeReservas
     */
    public DeReservas() {
    initComponents();
    
    jComboBox1.setSelectedItem("Todos");
    actualizarCampos();
    
    jComboBox1.addActionListener(e -> actualizarCampos());
    jButton1.addActionListener(e -> buscar());
    jButton2.addActionListener(e -> limpiar());
    
    cargarTodos();
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                java.awt.Window[] windows = java.awt.Window.getWindows();
                boolean encontrado = false;
                for (java.awt.Window w : windows) {
                    if (w instanceof com.mycompany.proyectorencar.MenuPrincipal && w.isVisible()) {
                        w.setVisible(true);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    new com.mycompany.proyectorencar.MenuPrincipal("Invitado", 1, "Invitado", "Invitado").setVisible(true);
                }
            }
        });
}

private void actualizarCampos() {
    String tipo = (String) jComboBox1.getSelectedItem();
    
    jTextField1.setVisible(false);
    jLabel3.setVisible(false);
    jTextField2.setVisible(false);
    jLabel4.setVisible(false);
    
    if (tipo.equals("Por Fechas")) {
        jTextField1.setVisible(true);
        jLabel3.setVisible(true);
        jLabel3.setText("Desde (YYYY-MM-DD)");
        jTextField2.setVisible(true);
        jLabel4.setVisible(true);
        jLabel4.setText("Hasta (YYYY-MM-DD)");
    } else if (tipo.equals("Por Días")) {
        jTextField1.setVisible(true);
        jLabel3.setVisible(true);
        jLabel3.setText("Cantidad de Días");
    }
}

private void cargarTodos() {
    try {
        List<String> lineas = ArchivoUtil.leerArchivo("reservas.txt");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length >= 10) {
                model.addRow(new String[]{
                    datos[0], datos[1], datos[2], datos[3],
                    datos[4], datos[5], datos[6], datos[7],
                    datos[8], datos[9]
                });
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void buscar() {
    String tipo   = (String) jComboBox1.getSelectedItem();
    String valor1 = jTextField1.getText().trim();
    String valor2 = jTextField2.getText().trim();
    
    if (tipo.equals("Todos")) {
        cargarTodos();
        return;
    }
    
    if (tipo.equals("Por Fechas") && (valor1.isEmpty() || valor2.isEmpty())) {
        javax.swing.JOptionPane.showMessageDialog(this, "Ingrese ambas fechas.");
        return;
    }
    
    if (tipo.equals("Por Días") && valor1.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Ingrese la cantidad de días.");
        return;
    }
    
    List<String> lineas = ArchivoUtil.leerArchivo("reservas.txt");
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    
    for (String linea : lineas) {
        String[] datos = linea.split(";");
        if (datos.length < 10) continue;
        
        boolean agregar = false;
        
        if (tipo.equals("Por Fechas")) {
            try {
                String fechaReserva = datos[4].trim();
                if (fechaReserva.compareTo(valor1) >= 0 && 
                    fechaReserva.compareTo(valor2) <= 0) {
                    agregar = true;
                }
            } catch (Exception e) {
                // ignora
            }
        } else if (tipo.equals("Por Días")) {
            try {
                int dias    = Integer.parseInt(datos[8].trim());
                int filtro  = Integer.parseInt(valor1);
                if (dias == filtro) agregar = true;
            } catch (Exception e) {
                // ignora
            }
        }
        
        if (agregar) {
            model.addRow(new String[]{
                datos[0], datos[1], datos[2], datos[3],
                datos[4], datos[5], datos[6], datos[7],
                datos[8], datos[9]
            });
        }
    }
    
    if (model.getRowCount() == 0) {
        javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron resultados.");
    }
}

private void limpiar() {
    jTextField1.setText("");
    jTextField2.setText("");
    jComboBox1.setSelectedIndex(0);
    cargarTodos();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(30, 159, 117));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Consultas de Reservas");
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tipo de Consulta");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Por Fechas", "Por Días" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Fecha Desde");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Fecha Hasta");

        jButton1.setText("Buscar");

        jButton2.setText("Limpiar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Reserva", "Matrícula", "Cédula", "ID Oferta", "Fecha Reserva", "Fecha Salida", "Fecha Entrada", "Observación", "Días", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(477, 477, 477)
                .addComponent(jButton2)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(23, Short.MAX_VALUE))
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DeReservas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
