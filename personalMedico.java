
package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class personalMedico extends javax.swing.JFrame {
    private final service.GestionSaludService service = new service.GestionSaludService();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(personalMedico.class.getName());

    /**
     * Creates new form personalMedico
     */
    public personalMedico() {
        initComponents();
        setLocationRelativeTo(null);

        soloCodigo(jTextField1);
        soloLetras(jTextField2);
        soloLetras(jTextField3);
        tblPersonal.setDefaultEditor(Object.class, null);

        cargarPersonalMedico();

        new javax.swing.Timer(
            60000, e -> cargarPersonalMedico()
        ).start();
    }
    
    private void soloCodigo(javax.swing.JTextField campo) {
        aplicarFiltro(campo, "[A-Za-z0-9-]*");
    }

    private void soloLetras(javax.swing.JTextField campo) {
        aplicarFiltro(campo, "[\\p{L} ]*");
    }

    private void aplicarFiltro(javax.swing.JTextField campo, String patron) {

        javax.swing.text.AbstractDocument documento =
            (javax.swing.text.AbstractDocument) campo.getDocument();

        documento.setDocumentFilter(new javax.swing.text.DocumentFilter() {

            private boolean valido(FilterBypass fb, int offset,
                    int length, String texto)
                    throws javax.swing.text.BadLocationException {

                String actual = fb.getDocument().getText(
                    0, fb.getDocument().getLength());

                String nuevo = new StringBuilder(actual)
                    .replace(offset, offset + length,
                        texto == null ? "" : texto)
                    .toString();

                return nuevo.matches(patron);
            }

            @Override
            public void insertString(FilterBypass fb, int offset,
                    String texto, javax.swing.text.AttributeSet attrs)
                    throws javax.swing.text.BadLocationException {

                if (valido(fb, offset, 0, texto)) {
                    super.insertString(fb, offset, texto, attrs);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset,
                    int length, String texto,
                    javax.swing.text.AttributeSet attrs)
                    throws javax.swing.text.BadLocationException {

                if (valido(fb, offset, length, texto)) {
                    super.replace(fb, offset, length, texto, attrs);
                }
            }
        });
    }
    
    
    private String estadoTurno(String turno) {

        int hora = java.time.LocalTime.now().getHour();

        switch (turno.trim().toLowerCase()) {
            case "mañana":
                return (hora >= 7 && hora < 15)
                    ? "En turno" : "Fuera de turno";

            case "tarde":
                return (hora >= 15 && hora < 23)
                    ? "En turno" : "Fuera de turno";

            case "noche":
                return (hora >= 23 || hora < 7)
                    ? "En turno" : "Fuera de turno";

            default:
                return "Sin horario";
        }
    }

    private void cargarPersonalMedico() {

        javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel)
            tblPersonal.getModel();

        modelo.setRowCount(0);

        java.io.File archivo = new java.io.File("medicos.txt");

        if (!archivo.exists()) {
            return;
        }

        try (java.io.BufferedReader lector =
                new java.io.BufferedReader(
                    new java.io.FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split("\\s*\\|\\s*");

                if (datos.length < 5) {
                    continue;
                }

                modelo.addRow(new Object[]{
                    datos[0],
                    datos[1],
                    datos[2],
                    datos[3],
                    datos[4],
                    estadoTurno(datos[4])
                });
            }

        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error al leer medicos.txt: " + e.getMessage());
        }
    }
    
    
    private boolean idDuplicado(String id) throws java.io.IOException {

        java.io.File archivo = new java.io.File("medicos.txt");

        if (!archivo.exists()) {
            return false;
        }

        try (java.io.BufferedReader lector =
                new java.io.BufferedReader(
                    new java.io.FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split("\\|", 2);

                if (datos.length > 0 &&
                    datos[0].trim().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }

        return false;
    }



    
    private void guardarPersonalEnTxt(String id, String nombre, String rol, String especialidad, String turno) {
        String rutaArchivo = "medicos.txt";

        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            String registro = id + " | " + nombre + " | " + rol + " | " + especialidad + " | " + turno;
            bw.write(registro);
            bw.newLine();

            JOptionPane.showMessageDialog(this,
                "Personal médico registrado correctamente en medicos.txt",
                "Registro Exitoso",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error al guardar en el archivo medicos.txt: " + e.getMessage(),
                "Error IO",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPersonal = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID / Codigo :");

        jLabel2.setText("Nombres Completos :");

        jLabel3.setText("Rol / Cargo :");

        jLabel4.setText("Especialidad / Area  :");

        jLabel5.setText("Turno asignado :");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor/a", "Enfermera/o", "Administrativo/a" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mañana", "Tarde", "Noche" }));

        jButton1.setText("Guardar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel6.setText("Gestión de Personal Médico - C.S. Campoy");

        tblPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID / Código", "Nombre completo", "Rol / Cargo", "Especialidad", "Turno", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tblPersonal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(25, 25, 25)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(jTextField2)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
    }                                           

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
    }                                           

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           

    }                                          

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String id = jTextField1.getText().trim();
        String nombre = jTextField2.getText().trim();
        String rol = jComboBox1.getSelectedItem().toString();
        String especialidad = jTextField3.getText().trim();
        String turno = jComboBox2.getSelectedItem().toString();

        // 1. Validar campos vacíos
        if (id.isEmpty() || nombre.isEmpty() || especialidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, complete todos los campos obligatorios.", 
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Validar que el ID sea estrictamente numérico
        if (!id.matches("[A-Za-z0-9-]+")) {
            JOptionPane.showMessageDialog(this, 
                "El ID debe contener únicamente letras, números o guiones.", 
                "ID Inválido", 
                JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocus();
            return;
        }
        
        
    try {
        if (idDuplicado(id)) {
            JOptionPane.showMessageDialog(this,
                "El ID/Código ya pertenece a un trabajador registrado.");
            jTextField1.requestFocus();
            return;
        }
    } catch (java.io.IOException e) {
        JOptionPane.showMessageDialog(this,
            "No se pudo verificar el ID: " + e.getMessage());
        return;
    }


        // 3. Guardar el registro si pasa las validaciones
    model.Medico med = (model.Medico) factory.UsuarioFactory.crearPersona("medico", new String[]{id, nombre, rol, especialidad, turno});

    if (service.registrarMedico(med)) {
    JOptionPane.showMessageDialog(this, "Personal médico registrado correctamente en medicos.txt", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField1.requestFocus();
    cargarPersonalMedico();
        } else {
        JOptionPane.showMessageDialog(this, "Error al guardar en el archivo medicos.txt", "Error IO", JOptionPane.ERROR_MESSAGE);
        }
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        java.awt.EventQueue.invokeLater(() -> new personalMedico().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblPersonal;
    // End of variables declaration                   
}
