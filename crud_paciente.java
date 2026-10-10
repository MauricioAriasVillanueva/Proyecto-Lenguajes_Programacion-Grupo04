
package view;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class crud_paciente extends javax.swing.JFrame {
    private final service.GestionSaludService service = new service.GestionSaludService();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(crud_paciente.class.getName());

    
    public crud_paciente() {
    initComponents();
    setLocationRelativeTo(null);
    soloNumeros(jTextField1, 8);
    soloNumeros(jTextField5, 15);
    jTextField4.setEditable(false);
    soloLetras(jTextField2); // Apellidos
    soloLetras(jTextField3); // Nombres
    }
    
    private void soloNumeros(javax.swing.JTextField campo, int limite) {

    javax.swing.text.AbstractDocument documento =
        (javax.swing.text.AbstractDocument) campo.getDocument();

    documento.setDocumentFilter(new javax.swing.text.DocumentFilter() {

        @Override
        public void replace(FilterBypass fb, int offset, int length,
                String texto, javax.swing.text.AttributeSet attrs)
                throws javax.swing.text.BadLocationException {

            if (texto == null) {
                texto = "";
            }

            int nuevaLongitud = fb.getDocument().getLength()
                    - length + texto.length();

            if (texto.matches("[0-9]*") && nuevaLongitud <= limite) {
                super.replace(fb, offset, length, texto, attrs);
            }
        }

        @Override
        public void insertString(FilterBypass fb, int offset,
                String texto, javax.swing.text.AttributeSet attrs)
                throws javax.swing.text.BadLocationException {

            replace(fb, offset, 0, texto, attrs);
        }
    });
}
    private void soloLetras(javax.swing.JTextField campo) {

    javax.swing.text.AbstractDocument documento =
        (javax.swing.text.AbstractDocument) campo.getDocument();

    documento.setDocumentFilter(new javax.swing.text.DocumentFilter() {

        @Override
        public void replace(FilterBypass fb, int offset, int length,
                String texto, javax.swing.text.AttributeSet attrs)
                throws javax.swing.text.BadLocationException {

            if (texto != null && texto.matches("[\\p{L} ]*")) {
                super.replace(fb, offset, length, texto, attrs);
            }
        }

        @Override
        public void insertString(FilterBypass fb, int offset,
                String texto, javax.swing.text.AttributeSet attrs)
                throws javax.swing.text.BadLocationException {

            replace(fb, offset, 0, texto, attrs);
        }
    });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnFechaNacimiento = new javax.swing.JButton();
        cmbSeguro = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DNI :");

        jLabel2.setText("Apellidos :");

        jLabel3.setText("Nombres :");

        jLabel4.setText("Fecha de Nacimiento:");

        jLabel5.setText("Seguro :");

        jLabel6.setText("Telefono :");

        jButton1.setText("Enviar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        jTextField4.addActionListener(this::jTextField4ActionPerformed);

        jLabel7.setText("Registro de Datos ");

        jLabel8.setText("Centro de Salud Daniel Alcides Carrión - Campoy");

        btnFechaNacimiento.setText("Seleccionar fecha");
        btnFechaNacimiento.addActionListener(this::btnFechaNacimientoActionPerformed);

        cmbSeguro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "SIS", "EsSalud", "EPS", "Seguro privado", "Sanidad PNP", "Sanidad FF. AA.", "Sin seguro", "Otro" }));
        cmbSeguro.addActionListener(this::cmbSeguroActionPerformed);

        jButton2.setText("Buscar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jLabel9.setText("Búsqueda de pacientes");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Apellidos", "Nombres", "Sexo", "Fecha de nacimiento", "Seguro"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setText("Sexo:");

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Masculino", "Femenino" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(378, 378, 378))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3)
                            .addComponent(jTextField2)
                            .addComponent(jTextField1)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(cmbSeguro, 0, 218, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(btnFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(159, 159, 159))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(0, 32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    String dni = jTextField1.getText().trim();
    String apellidos = jTextField2.getText().trim();
    String nombres = jTextField3.getText().trim();
    String fechaNacimiento = jTextField4.getText().trim();
    String edad;

    try {
        java.time.LocalDate nacimiento = java.time.LocalDate.parse(
            fechaNacimiento,
            java.time.format.DateTimeFormatter
                .ofPattern("dd/MM/uuuu")
                .withResolverStyle(java.time.format.ResolverStyle.STRICT)
        );

        if (nacimiento.isAfter(java.time.LocalDate.now())) {
            JOptionPane.showMessageDialog(this, "Fecha de nacimiento inválida.");
            return;
        }

        edad = String.valueOf(
            java.time.Period.between(
                nacimiento, java.time.LocalDate.now()
            ).getYears()
        );

    } catch (java.time.format.DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Seleccione una fecha de nacimiento válida.");
        return;
    }
    String telefono = jTextField5.getText().trim();
    String seguro = cmbSeguro.isEditable()
        ? String.valueOf(cmbSeguro.getEditor().getItem()).trim()
        : String.valueOf(cmbSeguro.getSelectedItem()).trim();

    // 1. Validar campos vacíos
    if (dni.isEmpty() || apellidos.isEmpty() || nombres.isEmpty() || edad.isEmpty() || telefono.isEmpty() || seguro.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 2. Validar que DNI, Edad y Teléfono sean solo números
    if (!dni.matches("\\d{8}")) {
    JOptionPane.showMessageDialog(this, "El DNI debe contener exactamente 8 dígitos numéricos.", "DNI Inválido", JOptionPane.ERROR_MESSAGE);
    jTextField1.requestFocus();
    return;
    }

    if (!telefono.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.", "Teléfono Inválido", JOptionPane.ERROR_MESSAGE);
        jTextField5.requestFocus();
        return;
    }

    // 3. Guardado en pacientes
    model.Paciente pac = (model.Paciente) factory.UsuarioFactory.crearPersona("paciente", new String[]{dni, apellidos, nombres, edad, telefono, seguro});    // Guardado delegado al servicio (sigue guardando en pacientes.txt)
    pac.setFechaNacimiento(fechaNacimiento);
    String sexo = String.valueOf(cmbSexo.getSelectedItem());

    if (!sexo.equals("Masculino") && !sexo.equals("Femenino")) {
        JOptionPane.showMessageDialog(this, "Seleccione el sexo del paciente.");
        return;
    }

    pac.setSexo(sexo);
    if (service.registrarPaciente(pac)) {
    JOptionPane.showMessageDialog(this, "Paciente registrado correctamente en pacientes.txt.");
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextField5.setText("");
    cmbSeguro.setSelectedIndex(0);
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextField5.setText("");

    cmbSeguro.setSelectedIndex(0);
    cmbSexo.setSelectedIndex(0);

    jTextField1.requestFocus();
    jTextField1.requestFocus(); 
    }  else {
        JOptionPane.showMessageDialog(this, "Error al guardar el paciente.", "Error IO", JOptionPane.ERROR_MESSAGE);
        }
    }                                        

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        String[] meses = {
    "Enero", "Febrero", "Marzo", "Abril",
    "Mayo", "Junio", "Julio", "Agosto",
    "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    javax.swing.JComboBox<Integer> dia = new javax.swing.JComboBox<>();
    javax.swing.JComboBox<String> mes = new javax.swing.JComboBox<>(meses);
    javax.swing.JComboBox<Integer> anio = new javax.swing.JComboBox<>();

    int anioActual = java.time.LocalDate.now().getYear();

    for (int i = anioActual; i >= 1900; i--) {
        anio.addItem(i);
    }

    anio.setSelectedItem(anioActual - 20);

    Runnable actualizarDias = () -> {
        Integer seleccionado = (Integer) dia.getSelectedItem();

        int maximo = java.time.YearMonth.of(
            (Integer) anio.getSelectedItem(),
            mes.getSelectedIndex() + 1
        ).lengthOfMonth();

        dia.removeAllItems();

        for (int i = 1; i <= maximo; i++) {
            dia.addItem(i);
        }

        dia.setSelectedItem(
            seleccionado != null && seleccionado <= maximo
                ? seleccionado : 1
        );
    };

    mes.addActionListener(e -> actualizarDias.run());
    anio.addActionListener(e -> actualizarDias.run());
    actualizarDias.run();

    javax.swing.JPanel panel =
        new javax.swing.JPanel(new java.awt.GridLayout(3, 2, 8, 8));

    panel.add(new javax.swing.JLabel("Día:"));
    panel.add(dia);
    panel.add(new javax.swing.JLabel("Mes:"));
    panel.add(mes);
    panel.add(new javax.swing.JLabel("Año:"));
    panel.add(anio);

    int opcion = javax.swing.JOptionPane.showConfirmDialog(
        this, panel, "Fecha de nacimiento",
        javax.swing.JOptionPane.OK_CANCEL_OPTION
    );

    if (opcion == javax.swing.JOptionPane.OK_OPTION) {

        java.time.LocalDate nacimiento = java.time.LocalDate.of(
            (Integer) anio.getSelectedItem(),
            mes.getSelectedIndex() + 1,
            (Integer) dia.getSelectedItem()
        );

        if (nacimiento.isAfter(java.time.LocalDate.now())) {
            javax.swing.JOptionPane.showMessageDialog(
                this, "La fecha no puede ser futura.");
            return;
        }

    jTextField4.setText(
        nacimiento.format(
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
        )
    );
    }
    }                                                  

    private void cmbSeguroActionPerformed(java.awt.event.ActionEvent evt) {                                          
    String seguro = String.valueOf(cmbSeguro.getSelectedItem());

    if (seguro.equals("Otro")) {
        cmbSeguro.setEditable(true);
        cmbSeguro.getEditor().setItem("");
        cmbSeguro.getEditor().getEditorComponent().requestFocusInWindow();
    } else if (java.util.Arrays.asList(
            "Seleccionar", "SIS", "EsSalud", "EPS",
            "Seguro privado", "Sanidad PNP",
            "Sanidad FF. AA.", "Sin seguro"
        ).contains(seguro)) {
        cmbSeguro.setEditable(false);
    }
    }                                         

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    String busqueda = jTextField6.getText().trim().toLowerCase();

    if (busqueda.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Ingrese un nombre, apellido o DNI.");
        return;
    }

    javax.swing.table.DefaultTableModel modelo =
        (javax.swing.table.DefaultTableModel) jTable1.getModel();

    modelo.setRowCount(0);

    String hashBuscado = busqueda.matches("\\d{8}")
        ? util.SeguridadUtil.encriptarSHA256(busqueda)
        : "";

    try (java.io.BufferedReader lector =
            new java.io.BufferedReader(
                new java.io.FileReader("pacientes.txt"))) {

        lector.lines()
            .map(linea -> linea.split("\\s*\\|\\s*"))
            .filter(datos -> datos.length >= 6)
            .filter(datos ->
                datos[1].toLowerCase().contains(busqueda)
                || datos[2].toLowerCase().contains(busqueda)
                || datos[0].equals(hashBuscado))
            .forEach(datos -> {

                String dni = "No recuperable";
                String fecha = "No registrada";
                String sexo = "No registrado";

                for (String dato : datos) {

                    if (dato.startsWith("Fecha Nacimiento:")) {
                        fecha = dato.replace(
                            "Fecha Nacimiento:", "").trim();
                    }

                    if (dato.startsWith("Sexo:")) {
                        sexo = dato.replace(
                            "Sexo:", "").trim();
                    }

                    if (dato.startsWith("DNI Cifrado:")) {
                        String dniCifrado = dato.replace(
                            "DNI Cifrado:", "").trim();

                        try {
                            dni = util.CifradoDni.descifrar(dniCifrado);
                        } catch (IllegalStateException e) {
                            dni = "Error de cifrado";
                        }
                    }
                }

                modelo.addRow(new Object[]{
                    dni,
                    datos[1],
                    datos[2],
                    sexo,
                    fecha,
                    datos[5]
                });
            });

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "No se encontraron pacientes.");
        }

    } catch (java.io.IOException e) {
        JOptionPane.showMessageDialog(this,
            "Error al leer pacientes.txt: " + e.getMessage());
    }
    }                                        

   
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
        java.awt.EventQueue.invokeLater(() -> new crud_paciente().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnFechaNacimiento;
    private javax.swing.JComboBox<String> cmbSeguro;
    private javax.swing.JComboBox<String> cmbSexo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration                   
}
