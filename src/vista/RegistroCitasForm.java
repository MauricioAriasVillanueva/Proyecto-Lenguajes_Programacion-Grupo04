/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
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
        private final java.util.Map<String, String> especialidades =
        new java.util.HashMap<>();

    private final java.util.Map<String, String> dniPacientes =
        new java.util.HashMap<>();

    private final java.util.Map<String, String> idsMedicos =
        new java.util.HashMap<>();

    private final java.util.Map<String, String> codigosMedicamentos =
        new java.util.HashMap<>();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroCitasForm.class.getName());

    /**
     * Creates new form RegistroCitasForm
     */
  
    public RegistroCitasForm() {
        initComponents();
        setLocationRelativeTo(null);
        cargarDatosCitas();
        buscarCitas();

        jButton1.addActionListener(e -> buscarCitas());

        txtEspecialidad.setEditable(false);
        txtFechaCita.setEditable(false);
        txtHoraCita.setEditable(false);

        cargarDatosCitas();

        cmbMedico.addActionListener(e -> {
            String seleccionado =
                String.valueOf(cmbMedico.getSelectedItem());

            txtEspecialidad.setText(
                especialidades.getOrDefault(seleccionado, "")
            );
        });
    }

    
    
    private void leerRegistros(
            String archivo,
            java.util.function.Consumer<String[]> procesar) {

        java.io.File file = new java.io.File(archivo);

        if (!file.exists()) {
            return;
        }

        try (java.io.BufferedReader br =
                new java.io.BufferedReader(
                    new java.io.FileReader(file))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\s*\\|\\s*");
                procesar.accept(datos);
            }

        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error al leer " + archivo + ": " + e.getMessage());
        }
    }

    private void cargarDatosCitas() {

        cmbPaciente.removeAllItems();
        cmbMedico.removeAllItems();
        cmbMedicamento.removeAllItems();

        especialidades.clear();
        dniPacientes.clear();
        idsMedicos.clear();
        codigosMedicamentos.clear();

        leerRegistros("pacientes.txt", datos -> {

            if (datos.length < 3) return;

            String dni = "";

            for (String dato : datos) {
                if (dato.startsWith("DNI Cifrado:")) {
                    try {
                        String cifrado = dato.substring(
                            "DNI Cifrado:".length()).trim();

                        dni = util.CifradoDni.descifrar(cifrado);

                    } catch (IllegalStateException ex) {
                        dni = "";
                    }
                }
            }

            if (!dni.matches("\\d{8}")) return;

            String opcion = datos[2] + " " + datos[1]
                + " - DNI: " + dni;

            dniPacientes.put(opcion, dni);
            cmbPaciente.addItem(opcion);
        });

        leerRegistros("medicos.txt", datos -> {

            if (datos.length < 5) return;

            String cargo = datos[2].toLowerCase();

            if (!cargo.contains("doctor")
                    && !cargo.contains("médico")
                    && !cargo.contains("medico")) {
                return;
            }

            String opcion = datos[0] + " - " + datos[1];

            idsMedicos.put(opcion, datos[0]);
            especialidades.put(opcion, datos[3]);

            cmbMedico.addItem(opcion);
        });

        leerRegistros("medicamentos.txt", datos -> {

            if (datos.length < 4) return;

            String opcion = datos[0] + " - " + datos[1];

            for (String dato : datos) {
                if (dato.startsWith("Contenido:")) {
                    opcion += " - " + dato.substring(
                        "Contenido:".length()).trim();
                }
            }

            codigosMedicamentos.put(opcion, datos[0]);
            cmbMedicamento.addItem(opcion);
        });

        cmbPaciente.setSelectedIndex(-1);
        cmbMedico.setSelectedIndex(-1);
        cmbMedicamento.setSelectedIndex(-1);

        txtEspecialidad.setText("");
    }
    
    
    private void abrirCalendario() {

        final java.time.YearMonth[] mes = {
            java.time.YearMonth.now()
        };

        javax.swing.JDialog ventana =
            new javax.swing.JDialog(this, "Seleccionar fecha", true);

        javax.swing.JLabel titulo =
            new javax.swing.JLabel("", javax.swing.SwingConstants.CENTER);

        javax.swing.JButton anterior = new javax.swing.JButton("<");
        javax.swing.JButton siguiente = new javax.swing.JButton(">");

        javax.swing.JPanel encabezado =
            new javax.swing.JPanel(new java.awt.BorderLayout());

        encabezado.add(anterior, java.awt.BorderLayout.WEST);
        encabezado.add(titulo, java.awt.BorderLayout.CENTER);
        encabezado.add(siguiente, java.awt.BorderLayout.EAST);

        javax.swing.JPanel dias =
            new javax.swing.JPanel(new java.awt.GridLayout(0, 7, 4, 4));

        Runnable actualizar = () -> {

            dias.removeAll();

            titulo.setText(
                mes[0].getMonth().getDisplayName(
                    java.time.format.TextStyle.FULL,
                    new java.util.Locale("es", "PE"))
                + " " + mes[0].getYear()
            );

            for (String nombre : new String[]{
                    "Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do"}) {

                dias.add(new javax.swing.JLabel(
                    nombre, javax.swing.SwingConstants.CENTER));
            }

            int inicio = mes[0].atDay(1).getDayOfWeek().getValue();

            for (int i = 1; i < inicio; i++) {
                dias.add(new javax.swing.JLabel(""));
            }

            for (int numero = 1;
                    numero <= mes[0].lengthOfMonth(); numero++) {

                java.time.LocalDate fecha = mes[0].atDay(numero);

                javax.swing.JButton boton =
                    new javax.swing.JButton(String.valueOf(numero));

                boton.setEnabled(
                    !fecha.isBefore(java.time.LocalDate.now())
                );

                boton.addActionListener(e -> {
                    txtFechaCita.setText(
                        fecha.format(
                            java.time.format.DateTimeFormatter
                                .ofPattern("dd/MM/uuuu"))
                    );
                    ventana.dispose();
                });

                dias.add(boton);
            }

            dias.revalidate();
            dias.repaint();
            ventana.pack();
        };

        anterior.addActionListener(e -> {
            mes[0] = mes[0].minusMonths(1);
            actualizar.run();
        });

        siguiente.addActionListener(e -> {
            mes[0] = mes[0].plusMonths(1);
            actualizar.run();
        });

        javax.swing.JPanel principal =
            new javax.swing.JPanel(new java.awt.BorderLayout(8, 8));

        principal.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        principal.add(encabezado, java.awt.BorderLayout.NORTH);
        principal.add(dias, java.awt.BorderLayout.CENTER);

        ventana.setContentPane(principal);

        actualizar.run();

        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
    }
    
    
    private void buscarCitas() {

        String filtro = jTextField1.getText().trim()
            .toLowerCase(java.util.Locale.ROOT);

        javax.swing.table.DefaultTableModel tabla =
            (javax.swing.table.DefaultTableModel) jTable1.getModel();

        tabla.setRowCount(0);

        File archivo = new File("citas.txt");

        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader lector =
                new BufferedReader(new FileReader(archivo))) {

            String linea;
            int numero = 0;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                numero++;

                java.util.Map<String, String> campos =
                    new java.util.HashMap<>();

                for (String parte : linea.split("\\s*\\|\\s*")) {
                    int posicion = parte.indexOf(':');

                    if (posicion > 0) {
                        campos.put(
                            parte.substring(0, posicion).trim(),
                            parte.substring(posicion + 1).trim()
                        );
                    }
                }

                // Compatibilidad con citas antiguas y nuevas
                String dni = campos.getOrDefault("PAC", "");

                if (campos.containsKey("PAC_CIF")) {
                    try {
                        dni = util.CifradoDni.descifrar(
                            campos.get("PAC_CIF")
                        );
                    } catch (IllegalStateException ex) {
                        dni = "No recuperable";
                    }
                }

                String idMedico = campos.getOrDefault("MED", "");
                String precio = campos.getOrDefault("Costo", "");
                String fecha = campos.getOrDefault("Fecha", "");
                String hora = campos.getOrDefault("Hora", "");

                // Buscar nombre del paciente
                String paciente = dni;

                for (java.util.Map.Entry<String, String> dato
                        : dniPacientes.entrySet()) {

                    if (dato.getValue().equals(dni)) {
                        paciente = dato.getKey();
                        break;
                    }
                }

                // Buscar nombre del médico
                String medico = idMedico;

                for (java.util.Map.Entry<String, String> dato
                        : idsMedicos.entrySet()) {

                    if (dato.getValue().equals(idMedico)) {
                        medico = dato.getKey();
                        break;
                    }
                }

                String contenido = (
                    paciente + " " + dni + " " +
                    medico + " " + idMedico + " " +
                    fecha + " " + hora + " " + numero
                ).toLowerCase(java.util.Locale.ROOT);

                if (filtro.isEmpty() || contenido.contains(filtro)) {

                    tabla.addRow(new Object[]{
                        String.format("C-%04d", numero),
                        paciente,
                        medico,
                        precio,
                        fecha,
                        hora
                    });
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar citas: " + e.getMessage());
        }
    }

    
    private void seleccionarHora() {

        if (txtFechaCita.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Primero seleccione la fecha de la cita.");
            return;
        }

        java.time.LocalDate fecha;

        try {
            fecha = java.time.LocalDate.parse(
                txtFechaCita.getText(),
                java.time.format.DateTimeFormatter
                    .ofPattern("dd/MM/uuuu")
            );
        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                "La fecha seleccionada no es válida.");
            return;
        }

        java.time.LocalTime ahora = java.time.LocalTime.now();

        javax.swing.JSpinner horas = new javax.swing.JSpinner(
            new javax.swing.SpinnerNumberModel(
                ahora.getHour(), 0, 23, 1
            )
        );

        javax.swing.JSpinner minutos = new javax.swing.JSpinner(
            new javax.swing.SpinnerNumberModel(
                ahora.getMinute(), 0, 59, 1
            )
        );

        javax.swing.JPanel panel = new javax.swing.JPanel();

        panel.add(new javax.swing.JLabel("Hora:"));
        panel.add(horas);
        panel.add(new javax.swing.JLabel("Minutos:"));
        panel.add(minutos);

        int respuesta = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Seleccionar hora de cita",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (respuesta != JOptionPane.OK_OPTION) {
            return;
        }

        int hora = (Integer) horas.getValue();
        int minuto = (Integer) minutos.getValue();

        java.time.LocalDateTime cita =
            fecha.atTime(hora, minuto);

        if (!cita.isAfter(java.time.LocalDateTime.now())) {
            JOptionPane.showMessageDialog(this,
                "Seleccione una fecha y hora futuras.");
            return;
        }

        txtHoraCita.setText(
            String.format("%02d:%02d", hora, minuto)
        );
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        txtNombrePaciente = new javax.swing.JLabel();
        txtIdMedico = new javax.swing.JLabel();
        txtNombreMedicamento = new javax.swing.JLabel();
        txtCostoConsulta = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        btnGuardarCita = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbPaciente = new javax.swing.JComboBox<>();
        cmbMedico = new javax.swing.JComboBox<>();
        cmbMedicamento = new javax.swing.JComboBox<>();
        txtEspecialidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Diagnóstico = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtFechaCita = new javax.swing.JTextField();
        txtHoraCita = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtNombrePaciente.setText("DNI y Nombres del Paciente:");

        txtIdMedico.setText("ID y Nombres del Médico:");

        txtNombreMedicamento.setText("Codigo y Nombre del Medicamento:");

        txtCostoConsulta.setText("Costo Consulta (S/):");

        jTextField7.addActionListener(this::jTextField7ActionPerformed);

        btnGuardarCita.setText("Guardar Cita");
        btnGuardarCita.addActionListener(this::btnGuardarCitaActionPerformed);

        jLabel3.setText("Centro de Salud Daniel Alcides Carrión - Campoy");

        jLabel1.setText("Especialidad / Área:");

        jLabel2.setText("Fecha:");

        jLabel4.setText("Hora:");

        cmbPaciente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMedico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMedicamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Observación:");

        Diagnóstico.setColumns(20);
        Diagnóstico.setRows(5);
        jScrollPane1.setViewportView(Diagnóstico);

        jLabel6.setText("Busqueda de Citas:");

        jButton1.setText("Buscar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cita", "Paciente/DNI", "Médico", "Precio", "Fecha ", "hora"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton2.setText("jButton2");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("jButton3");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(370, 370, 370))))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombreMedicamento)
                                    .addComponent(txtNombrePaciente)
                                    .addComponent(txtIdMedico)
                                    .addComponent(cmbPaciente, 0, 247, Short.MAX_VALUE)
                                    .addComponent(cmbMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbMedicamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCostoConsulta)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7)
                            .addComponent(txtEspecialidad))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNombrePaciente)
                        .addGap(16, 16, 16)
                        .addComponent(cmbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdMedico)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCostoConsulta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnGuardarCita)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>                        

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnGuardarCitaActionPerformed(java.awt.event.ActionEvent evt) {                                               
    String paciente = (String) cmbPaciente.getSelectedItem();
    String medico = (String) cmbMedico.getSelectedItem();
    String medicamento = (String) cmbMedicamento.getSelectedItem();

    String costoTexto = jTextField7.getText().trim().replace(",", ".");
    String fecha = txtFechaCita.getText().trim();
    String hora = txtHoraCita.getText().trim();
    String observaciones = Diagnóstico.getText().trim();

    if (paciente == null || medico == null || medicamento == null
            || costoTexto.isEmpty() || fecha.isEmpty()
            || hora.isEmpty()) {

        JOptionPane.showMessageDialog(this,
            "Seleccione paciente, médico, medicamento, fecha y hora; "
            + "complete también el costo.");
        return;
    }

    try {
        double costo = Double.parseDouble(costoTexto);

        if (!Double.isFinite(costo) || costo < 0) {
            JOptionPane.showMessageDialog(this,
                "Ingrese un costo válido.");
            return;
        }

        model.CitaMedica cita = new model.CitaMedica(
            dniPacientes.get(paciente),
            idsMedicos.get(medico),
            codigosMedicamentos.get(medicamento),
            costo
        );

        cita.setFechaCita(fecha);
        cita.setHoraCita(hora);
        cita.setObservaciones(observaciones);
        cita.setDiagnostico(Diagnóstico.getText().trim());

        if (service.registrarCita(cita)) {

            JOptionPane.showMessageDialog(this,
                "Cita registrada correctamente.");

            cmbPaciente.setSelectedIndex(-1);
            cmbMedico.setSelectedIndex(-1);
            cmbMedicamento.setSelectedIndex(-1);

            txtEspecialidad.setText("");
            txtFechaCita.setText("");
            txtHoraCita.setText("");
            jTextField7.setText("");
            Diagnóstico.setText("");
            jTextField1.setText("");
            buscarCitas();

        } else {
            JOptionPane.showMessageDialog(this,
                "No se pudo guardar la cita.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
            "El costo debe ser numérico.");
    } catch (IllegalStateException e) {
        JOptionPane.showMessageDialog(this,
            "Error al guardar los datos cifrados: " + e.getMessage());
    }
    }                                              

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        abrirCalendario();
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        seleccionarHora();
    }                                        

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

    // Variables declaration - do not modify                     
    private javax.swing.JTextArea Diagnóstico;
    private javax.swing.JButton btnGuardarCita;
    private javax.swing.JComboBox<String> cmbMedicamento;
    private javax.swing.JComboBox<String> cmbMedico;
    private javax.swing.JComboBox<String> cmbPaciente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel txtCostoConsulta;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextField txtFechaCita;
    private javax.swing.JTextField txtHoraCita;
    private javax.swing.JLabel txtIdMedico;
    private javax.swing.JLabel txtNombreMedicamento;
    private javax.swing.JLabel txtNombrePaciente;
    // End of variables declaration                   
}
