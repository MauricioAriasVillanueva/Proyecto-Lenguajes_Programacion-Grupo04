/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this templat
 */
package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class RegistroCitasForm extends javax.swing.JFrame {
    
    private final service.GestionSaludService service = new service.GestionSaludService();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroCitasForm.class.getName());

    /**
     * Creates new form RegistroCitasForm
     */
    public RegistroCitasForm() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    // Método auxiliar para buscar registros dentro de los archivos .txt
    private String buscarEnArchivo(String nombreArchivo, String claveBusqueda) {
        File file = new File(nombreArchivo);
        if (!file.exists()) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(claveBusqueda)) {
                    return linea;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer " + nombreArchivo + ": " + e.getMessage());
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDniPaciente = new javax.swing.JLabel();
        txtNombrePaciente = new javax.swing.JLabel();
        txtIdMedico = new javax.swing.JLabel();
        txtNombreMedico = new javax.swing.JLabel();
        txtCodMedicamento = new javax.swing.JLabel();
        txtNombreMedicamento = new javax.swing.JLabel();
        txtCostoConsulta = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        btnBuscarMedico = new javax.swing.JButton();
        btnBuscarMedicamento = new javax.swing.JButton();
        btnGuardarCita = new javax.swing.JButton();
        btnVolverMenu = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtDniPaciente.setText("DNI Paciente:");

        txtNombrePaciente.setText("Nombre Paciente:");

        txtIdMedico.setText("ID Médico:");

        txtNombreMedico.setText("Nombre Médico:");

        txtCodMedicamento.setText("Cod. Medicamento:");

        txtNombreMedicamento.setText("Nombre Medicamento:");

        txtCostoConsulta.setText("Costo Consulta (S/):");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jTextField2.setEditable(false);

        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        jTextField4.setEditable(false);

        jTextField6.setEditable(false);
        jTextField6.addActionListener(this::jTextField6ActionPerformed);

        jTextField7.addActionListener(this::jTextField7ActionPerformed);

        btnBuscarPaciente.setText("Buscar Paciente.");
        btnBuscarPaciente.addActionListener(this::btnBuscarPacienteActionPerformed);

        btnBuscarMedico.setText("Buscar Médico.");
        btnBuscarMedico.addActionListener(this::btnBuscarMedicoActionPerformed);

        btnBuscarMedicamento.setText("Buscar Medicamento.");
        btnBuscarMedicamento.addActionListener(this::btnBuscarMedicamentoActionPerformed);

        btnGuardarCita.setText("Guardar Cita");
        btnGuardarCita.addActionListener(this::btnGuardarCitaActionPerformed);

        btnVolverMenu.setText("Volver al Menú.");
        btnVolverMenu.addActionListener(this::btnVolverMenuActionPerformed);

        jLabel3.setText("Centro de Salud Daniel Alcides Carrión - Campoy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreMedicamento)
                            .addComponent(txtCostoConsulta))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombrePaciente)
                            .addComponent(txtIdMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMedico)
                            .addComponent(txtCodMedicamento)
                            .addComponent(txtDniPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarMedicamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarMedico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btnGuardarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(txtDniPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtNombrePaciente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarPaciente))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdMedico)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnBuscarMedico)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreMedico))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodMedicamento)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnBuscarMedicamento)))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreMedicamento)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostoConsulta)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCita)
                    .addComponent(btnVolverMenu))
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
    String dni = jTextField1.getText().trim();
    if (dni.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Ingrese el DNI del paciente.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    String resultado = service.buscarPaciente(dni);
    if (resultado != null) {
    jTextField2.setText(resultado);
    javax.swing.JOptionPane.showMessageDialog(this, "Paciente encontrado.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Paciente no encontrado en pacientes.txt", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            jTextField2.setText("");
        }
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void btnBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicoActionPerformed
    String id = jTextField3.getText().trim();
    if (id.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(this, "Ingrese el ID del médico.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
    }
    String resultado = service.buscarMedico(id);
    if (resultado != null) {
    jTextField4.setText(resultado);
    javax.swing.JOptionPane.showMessageDialog(this, "Médico encontrado.");
    } else {
    javax.swing.JOptionPane.showMessageDialog(this, "Médico no encontrado en medicos.txt", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    jTextField4.setText("");
    }
    }//GEN-LAST:event_btnBuscarMedicoActionPerformed

    private void btnBuscarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicamentoActionPerformed
    String cod = jTextField5.getText().trim();
    if (cod.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(this, "Ingrese el código del medicamento.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
    }
    String resultado = service.buscarMedicamento(cod);
    if (resultado != null) {
    jTextField6.setText(resultado);
    javax.swing.JOptionPane.showMessageDialog(this, "Medicamento encontrado.");
    } else {
    javax.swing.JOptionPane.showMessageDialog(this, "Medicamento no encontrado en medicamentos.txt", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    jTextField6.setText("");
    }
    }//GEN-LAST:event_btnBuscarMedicamentoActionPerformed

    private void btnGuardarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCitaActionPerformed
    String dniPac = jTextField1.getText().trim();
    String idMed = jTextField3.getText().trim();
    String codMedica = jTextField5.getText().trim();
    String costoStr = jTextField7.getText().trim();

    if (jTextField2.getText().isEmpty() || jTextField4.getText().isEmpty() || jTextField6.getText().isEmpty() || costoStr.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(this, "Debe buscar y validar previamente al Paciente, Médico y Medicamento.", "Datos Incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
    }

    try {
    double costo = Double.parseDouble(costoStr);

    model.CitaMedica cita = new model.CitaMedica(dniPac, idMed, codMedica, costo);
    if (service.registrarCita(cita)) {
        javax.swing.JOptionPane.showMessageDialog(this, "Cita agendada correctamente en citas");
        jTextField1.setText(""); jTextField2.setText("");
        jTextField3.setText(""); jTextField4.setText("");
        jTextField5.setText(""); jTextField6.setText("");
        jTextField7.setText("");
        jTextField1.requestFocus();
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar la cita.", "Error IO", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    } catch (NumberFormatException ex) {
    javax.swing.JOptionPane.showMessageDialog(this, "El costo debe ser un valor numérico.", "Error Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnGuardarCitaActionPerformed

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
    menu_frame menu = new menu_frame();
    menu.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnVolverMenuActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new RegistroCitasForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMedicamento;
    private javax.swing.JButton btnBuscarMedico;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnGuardarCita;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel txtCodMedicamento;
    private javax.swing.JLabel txtCostoConsulta;
    private javax.swing.JLabel txtDniPaciente;
    private javax.swing.JLabel txtIdMedico;
    private javax.swing.JLabel txtNombreMedicamento;
    private javax.swing.JLabel txtNombreMedico;
    private javax.swing.JLabel txtNombrePaciente;
    // End of variables declaration//GEN-END:variables
}
