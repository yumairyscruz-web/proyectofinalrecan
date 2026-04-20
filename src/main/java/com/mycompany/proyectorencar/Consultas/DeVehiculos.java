/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectorencar.Consultas;

import com.mycompany.proyectorencar.ArchivoUtil;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DeVehiculos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DeVehiculos.class.getName());

    /**
     * Creates new form DeVehiculos
     */
    public DeVehiculos() {
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
    
    if (tipo.equals("Todos") || tipo.equals("Disponibles") || tipo.equals("Rentados")) {
        // ocultar campo
    } else if (tipo.equals("Por Precio")) {
        jTextField1.setVisible(true);
        jLabel3.setVisible(true);
        jLabel3.setText("Precio Máximo");
    } else {
        jTextField1.setVisible(true);
        jLabel3.setVisible(true);
        jLabel3.setText("Valor del Filtro");
    }
}

private String[] formatearDatos(String[] datos) {
    if (datos.length < 14) return datos;
    
    String tipoVeh   = datos[3].equals("0") ? "Turístico" : "Normal";
    String tipoMotor = datos[4].equals("0") ? "Diésel" : "Gasolina";
    String status    = datos[13].equals("true") ? "Disponible" : "Rentado";
    
    return new String[] {
        datos[0],   // Matrícula
        datos[1],   // Marca
        datos[2],   // Modelo
        tipoVeh,    // Tipo Vehículo
        tipoMotor,  // Tipo Motor
        datos[5],   // Gama
        datos[6],   // Descripción
        datos[7],   // Precio
        datos[11],  // Color
        status      // Status
    };
}

private void cargarTodos() {
    try {
        List<String> lineas = ArchivoUtil.leerArchivo("vehiculos.txt");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            model.addRow(formatearDatos(datos));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void buscar() {
    String tipo  = (String) jComboBox1.getSelectedItem();
    String valor = jTextField1.getText().trim();
    
    List<String> lineas = ArchivoUtil.leerArchivo("vehiculos.txt");
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    
    for (String linea : lineas) {
        String[] datos = linea.split(";");
        if (datos.length < 14) continue;
        
        boolean agregar = false;
        String status   = datos[13].trim();
        String matricula = datos[0].trim();
        
        if (tipo.equals("Todos")) {
            agregar = true;
        } else if (tipo.equals("Por Matrícula")) {
            if (matricula.equalsIgnoreCase(valor)) agregar = true;
        } else if (tipo.equals("Por Marca")) {
            if (datos[1].toLowerCase().contains(valor.toLowerCase())) agregar = true;
        } else if (tipo.equals("Por Gama")) {
            if (datos[5].toLowerCase().contains(valor.toLowerCase())) agregar = true;
        } else if (tipo.equals("Disponibles")) {
            if (status.equalsIgnoreCase("true")) agregar = true;
        } else if (tipo.equals("Rentados")) {
            if (status.equalsIgnoreCase("false")) agregar = true;
        } else if (tipo.equals("Por Precio")) {
            try {
                double precio = Double.parseDouble(valor);
                double precioVehiculo = Double.parseDouble(datos[7]);
                if (precioVehiculo <= precio) agregar = true;
            } catch (Exception e) {
                // ignora
            }
        }
        
        if (agregar) model.addRow(formatearDatos(datos));
    }
}

private void limpiar() {
    jTextField1.setText("");
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
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
        jLabel1.setText("Consulta de Vehículos");
        jLabel1.setOpaque(true);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Por Matrícula", "Disponibles", "Rentados", "Por Marca", "Por Gama", "Por Precio" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tipo de Consulta");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Valor del Filtro");

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
                "Matrícula", "Marca", "Modelo", "Tipo Vehículo", "Tipo Motor", "Gama", "Descripción", "Precio", "Color", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(74, 74, 74)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(122, 122, 122)))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1))
                .addContainerGap())
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new DeVehiculos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
