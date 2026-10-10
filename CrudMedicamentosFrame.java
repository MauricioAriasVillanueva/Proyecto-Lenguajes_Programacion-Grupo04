package view;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
public class CrudMedicamentosFrame extends javax.swing.JFrame {
    private final service.GestionSaludService service = new service.GestionSaludService();
    private void guardarMedicamentoEnTxt(String codigo, String nombre, String precio, String stock) {
    String rutaArchivo = "medicamentos.txt";

    try (FileWriter fw = new FileWriter(rutaArchivo, true);
         BufferedWriter bw = new BufferedWriter(fw)) {

        String registro = codigo + " | " + nombre + " | S/ " + precio + " | Stock: " + stock;
        bw.write(registro);
        bw.newLine();

        JOptionPane.showMessageDialog(this, 
            "Medicamento registrado correctamente en medicamentos.txt", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al guardar en el archivo: " + e.getMessage(), 
            "Error IO", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CrudMedicamentosFrame.class.getName());

    public CrudMedicamentosFrame() {
        initComponents();
        filtrarNumeros(txtPrecio, true);
        filtrarNumeros(txtStock, false);
        txtCodigo.setEditable(false);
        ((javax.swing.text.AbstractDocument)
            txtContenido.getDocument()).setDocumentFilter(
            new javax.swing.text.DocumentFilter() {

                @Override
                public void insertString(FilterBypass fb, int offset,
                        String texto, javax.swing.text.AttributeSet attrs)
                        throws javax.swing.text.BadLocationException {

                    if (texto != null && texto.matches("[0-9]+")) {
                        super.insertString(fb, offset, texto, attrs);
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length,
                        String texto, javax.swing.text.AttributeSet attrs)
                        throws javax.swing.text.BadLocationException {

                    if (texto != null && texto.matches("[0-9]*")) {
                        super.replace(fb, offset, length, texto, attrs);
                    }
                }
            }
        );
    setLocationRelativeTo(null);
    txtCodigo.setText(generarCodigoMedicamento());
    }   

    
    private void filtrarNumeros(
            javax.swing.JTextField campo, boolean decimal) {

        javax.swing.text.AbstractDocument documento =
            (javax.swing.text.AbstractDocument) campo.getDocument();

        documento.setDocumentFilter(
            new javax.swing.text.DocumentFilter() {

                private boolean esValido(
                        FilterBypass fb, int offset,
                        int length, String texto)
                        throws javax.swing.text.BadLocationException {

                    String actual = fb.getDocument().getText(
                        0, fb.getDocument().getLength());

                    String nuevo = new StringBuilder(actual)
                        .replace(offset, offset + length,
                            texto == null ? "" : texto)
                        .toString();

                    return decimal
                        ? nuevo.matches("\\d*(\\.\\d{0,2})?")
                        : nuevo.matches("\\d*");
                }

                @Override
                public void insertString(
                        FilterBypass fb, int offset,
                        String texto,
                        javax.swing.text.AttributeSet attrs)
                        throws javax.swing.text.BadLocationException {

                    if (esValido(fb, offset, 0, texto)) {
                        super.insertString(fb, offset, texto, attrs);
                    }
                }

                @Override
                public void replace(
                        FilterBypass fb, int offset,
                        int length, String texto,
                        javax.swing.text.AttributeSet attrs)
                        throws javax.swing.text.BadLocationException {

                    if (esValido(fb, offset, length, texto)) {
                        super.replace(fb, offset, length, texto, attrs);
                    }
                }
            });
    }

    
    private String generarCodigoMedicamento() {

        int maximo = 0;

        java.io.File archivo = new java.io.File("medicamentos.txt");

        if (archivo.exists()) {
            try (java.io.BufferedReader lector =
                    new java.io.BufferedReader(
                        new java.io.FileReader(archivo))) {

                String linea;

                while ((linea = lector.readLine()) != null) {

                    String codigo = linea.split("\\|")[0].trim();

                    if (codigo.matches("MED\\d+")) {
                        int numero = Integer.parseInt(
                            codigo.substring(3));

                        if (numero > maximo) {
                            maximo = numero;
                        }
                    }
                }

            } catch (java.io.IOException e) {
                throw new IllegalStateException(
                    "Error al leer medicamentos.txt", e);
            }
        }

        return String.format("MED%04d", maximo + 1);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtContenido = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicamentos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btnBuscarMedicamento = new javax.swing.JButton();
        txtBuscarMedicamento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jLabel8.setText("Centro de Salud Daniel Alcides Carrión - Campoy");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Código:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Precio S/:");

        jLabel4.setText("Stock:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        jLabel9.setText("Centro de Salud Daniel Alcides Carrión - Campoy");

        jLabel5.setText("Contenido:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "mg", "g", "mL" }));

        tblMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Precio", "Stock", "Contenido", "Unidad"
            }
        ));
        jScrollPane1.setViewportView(tblMedicamentos);

        jLabel6.setText("Busqueda de Medicamento");

        btnBuscarMedicamento.setText("Buscar");
        btnBuscarMedicamento.addActionListener(this::btnBuscarMedicamentoActionPerformed);

        jLabel7.setText("Registro de Medicamento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtBuscarMedicamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarMedicamento))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(165, 165, 165))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(362, 362, 362))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarMedicamento)
                    .addComponent(txtBuscarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGap(38, 38, 38)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGap(44, 44, 44)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGap(37, 37, 37)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addGap(34, 34, 34)
                            .addComponent(txtContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel5)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnGuardar)
                            .addGap(150, 150, 150)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    String codigo = txtCodigo.getText().trim();
    String nombre = txtNombre.getText().trim();
    String precio = txtPrecio.getText().trim();
    String stock = txtStock.getText().trim();
    String contenido = txtContenido.getText().trim();
    String unidad = String.valueOf(jComboBox1.getSelectedItem());

    // Validar campos obligatorios
    if (codigo.isEmpty() || nombre.isEmpty()
            || precio.isEmpty() || stock.isEmpty()
            || contenido.isEmpty()
            || unidad.equals("Seleccionar")) {

        JOptionPane.showMessageDialog(this,
            "Complete todos los campos y seleccione una unidad.");
        return;
    }

    try {
        double precioNum = Double.parseDouble(precio);
        int stockNum = Integer.parseInt(stock);
        int contenidoNum = Integer.parseInt(contenido);

        if (precioNum <= 0 || stockNum < 0 || contenidoNum <= 0
                || !Double.isFinite(precioNum)) {

            JOptionPane.showMessageDialog(this,
                "Verifique el precio, stock y contenido.");
            return;
        }

        model.Medicamento med = new model.Medicamento(
            codigo, nombre, precioNum, stockNum
        );

        // Guardar contenido y unidad
        med.setContenido(contenido);
        med.setUnidad(unidad);

        if (service.registrarMedicamento(med)) {

            JOptionPane.showMessageDialog(this,
                "Medicamento registrado correctamente.");

            txtCodigo.setText(generarCodigoMedicamento());
            txtNombre.setText("");
            txtPrecio.setText("");
            txtStock.setText("");
            txtContenido.setText("");
            jComboBox1.setSelectedIndex(0);

            txtCodigo.requestFocus();

        } else {
            JOptionPane.showMessageDialog(this,
                "Error al guardar el medicamento.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
            "Precio, stock y contenido deben ser valores numéricos válidos.");
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMedicamentoActionPerformed
    String busqueda = txtBuscarMedicamento.getText().trim().toLowerCase();

    if (busqueda.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Ingrese un código o nombre de medicamento.");
        return;
    }

    javax.swing.table.DefaultTableModel modelo =
        (javax.swing.table.DefaultTableModel) tblMedicamentos.getModel();

    modelo.setRowCount(0);

    try (java.io.BufferedReader lector =
            new java.io.BufferedReader(
                new java.io.FileReader("medicamentos.txt"))) {

        String linea;

        while ((linea = lector.readLine()) != null) {

            String[] datos = linea.split("\\s*\\|\\s*");

            if (datos.length < 4) {
                continue;
            }

            String codigo = datos[0];
            String nombre = datos[1];

            if (!codigo.toLowerCase().contains(busqueda)
                    && !nombre.toLowerCase().contains(busqueda)) {
                continue;
            }

            String precio = datos[2].replace("S/", "").trim();
            String stock = datos[3].replace("Stock:", "").trim();

            String contenido = "No registrado";
            String unidad = "-";

            for (int i = 4; i < datos.length; i++) {

                if (datos[i].startsWith("Contenido:")) {

                    String valor = datos[i]
                        .replace("Contenido:", "").trim();

                    String[] partes = valor.split("\\s+");

                    if (partes.length >= 2) {
                        contenido = partes[0];
                        unidad = partes[1];
                    }
                }
            }

            modelo.addRow(new Object[]{
                codigo, nombre, precio, stock, contenido, unidad
            });
        }

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "No se encontraron medicamentos.");
        }

    } catch (java.io.IOException e) {
        JOptionPane.showMessageDialog(this,
                    "Error al leer medicamentos.txt: " + e.getMessage());
                }
    }//GEN-LAST:event_btnBuscarMedicamentoActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new CrudMedicamentosFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMedicamento;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMedicamentos;
    private javax.swing.JTextField txtBuscarMedicamento;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtContenido;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
