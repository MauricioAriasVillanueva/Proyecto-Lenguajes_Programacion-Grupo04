package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class ComprobantesFrame extends JInternalFrame {
    private final java.util.Map<String, String> dniClientes =
        new java.util.HashMap<>();
    private static final String RUC_ESTABLECIMIENTO =
        "20602236596";

    private final java.util.Map<String, String> rucClientes =
        new java.util.HashMap<>();
    private final java.util.Map<String, Double> preciosCitas =
        new java.util.HashMap<>();
    private final java.util.Map<String, String> diagnosticosCitas =
        new java.util.HashMap<>();

    private final javax.swing.JTextArea txtPreviewMedico =
        new javax.swing.JTextArea();


    public ComprobantesFrame() {
        initComponents();
        prepararVistaMedica();
        txtDiagnostico.setEditable(false);
        btnCalendario.addActionListener(e -> abrirCalendarioComprobante());

        cmbCliente.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<?> lista,
                    Object valor,
                    int indice,
                    boolean seleccionado,
                    boolean enfocado) {

                super.getListCellRendererComponent(
                    lista, valor, indice, seleccionado, enfocado);

                if (valor != null) {
                    String texto = valor.toString();
                    int separador = texto.lastIndexOf(" - ");

                    setText(separador >= 0
                            ? texto.substring(0, separador)
                            : texto);
                }

                return this;
            }
        });

        cmbTipoComprobante.setSelectedItem("Boleta");

        // Primero cargar pacientes
        cargarClientes();

        // Después conectar los eventos
        cmbCliente.addActionListener(e -> {
            txtNotaMedico.setText("");
            actualizarDocumento();
            cargarCitasCliente();
            mostrarDiagnosticoSeleccionado();
            actualizarVistaCobro();
        });

        cmbTipoComprobante.addActionListener(e -> {
            actualizarDocumento();
        });

        cmbTipoAtencion.addActionListener(e -> {
            actualizarVistaCobro();
        });

        cmbCita.addActionListener(e -> {
            txtNotaMedico.setText("");
            mostrarDiagnosticoSeleccionado();
            actualizarVistaCobro();
        });

        actualizarDocumento();
        cargarCitasCliente();
        actualizarVistaCobro();

        txtNotaMedico.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    actualizarVistaMedica();
                }

                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    actualizarVistaMedica();
                }

                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    actualizarVistaMedica();
                }
            }
        );

        mostrarDiagnosticoSeleccionado();
    }
    


    
        private String recuperarDni(String[] datos) {

        for (String dato : datos) {

            String campo = dato.trim();

            if (campo.toLowerCase(java.util.Locale.ROOT)
                    .startsWith("dni cifrado:")) {

                String cifrado = campo.substring(
                    campo.indexOf(':') + 1
                ).trim();

                try {
                    String dni = util.CifradoDni.descifrar(cifrado);

                    if (dni.matches("\\d{8}")) {
                        return dni;
                    }

                } catch (RuntimeException ex) {
                    System.err.println(
                        "No se pudo recuperar el DNI cifrado.");
                }
            }

            // Compatibilidad con registros antiguos
            // que tengan un campo DNI explícito.
            if (campo.toLowerCase(java.util.Locale.ROOT)
                    .startsWith("dni:")) {

                String dni = campo.substring(
                    campo.indexOf(':') + 1
                ).trim();

                if (dni.matches("\\d{8}")) {
                    return dni;
                }
            }
        }

        return "";
    }
    
    
    private String obtenerCampoCita(String linea, String nombre) {

        for (String parte : linea.split("\\|")) {
            String campo = parte.trim();
            String prefijo = nombre + ":";

            if (campo.startsWith(prefijo)) {
                return campo.substring(prefijo.length()).trim();
            }
        }

        return "";
    }

    private void cargarCitasCliente() {

        cmbCita.removeAllItems();
        cmbCita.addItem("Seleccione cita");

        preciosCitas.clear();
        diagnosticosCitas.clear();

        String cliente = (String) cmbCliente.getSelectedItem();
        String dni = dniClientes.get(cliente);

        if (dni == null || dni.isEmpty()) {
            return;
        }

        java.io.File archivo = new java.io.File("citas.txt");

        if (!archivo.exists()) {
            return;
        }

        try (java.io.BufferedReader lector =
                new java.io.BufferedReader(
                    new java.io.FileReader(archivo))) {

            String linea;
            int numero = 0;

            while ((linea = lector.readLine()) != null) {

                String dniCita = obtenerCampoCita(linea, "PAC_CIF");

                if (!dniCita.isEmpty()) {
                    try {
                        dniCita = util.CifradoDni.descifrar(dniCita);
                    } catch (RuntimeException ex) {
                        continue;
                    }
                } else {
                    dniCita = obtenerCampoCita(linea, "PAC");
                }

                // Mostrar solo las citas del cliente elegido
                if (!dni.equals(dniCita)) {
                    continue;
                }

                String costoTexto = obtenerCampoCita(linea, "Costo")
                        .replace("S/", "")
                        .replace(",", ".")
                        .trim();

                double precio;

                try {
                    precio = Double.parseDouble(costoTexto);
                } catch (NumberFormatException ex) {
                    continue;
                }

                if (!Double.isFinite(precio) || precio < 0) {
                    continue;
                }

                numero++;

                String fecha = obtenerCampoCita(linea, "Fecha");
                String hora = obtenerCampoCita(linea, "Hora");

                if (fecha.isEmpty()) {
                    fecha = "Sin fecha";
                }

                String opcion = String.format(
                    java.util.Locale.US,
                    "Cita %d - %s %s - S/ %.2f",
                    numero, fecha, hora, precio
                );

                cmbCita.addItem(opcion);
                preciosCitas.put(opcion, precio);
                String diagnostico = "";
                String cifrado = obtenerCampoCita(linea, "Diagnostico CIF");

                if (!cifrado.isEmpty()) {
                    try {
                        diagnostico = util.CifradoDni.descifrar(cifrado);
                    } catch (RuntimeException ex) {
                        diagnostico = "";
                    }
                }

                diagnosticosCitas.put(opcion, diagnostico);
            }

        } catch (java.io.IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al cargar citas: " + ex.getMessage()
            );
        }

        cmbCita.setSelectedIndex(0);
    }

    
    private double obtenerPrecioAtencion() {

        String tipo = String.valueOf(
            cmbTipoAtencion.getSelectedItem()
        );

        switch (tipo) {
            case "Consulta general":
                return 12.00;

            case "Control de triaje":
                return 1.00;

            case "Curacion":
                return 7.00;

            case "Inyectable":
                return 1.00;

            case "Atencion de emergencia":
                return 12.00;

            case "Entrega de medicamentos":
                return -1.00; // Falta calcular el medicamento

            default:
                return -1.00;
        }
    }


    private void actualizarVistaCobro() {

        boolean factura = "Factura".equals(
            cmbTipoComprobante.getSelectedItem()
        );

        String cliente = String.valueOf(
            cmbCliente.getSelectedItem()
        );

        int separador = cliente.lastIndexOf(" - ");

        if (separador >= 0) {
            cliente = cliente.substring(0, separador);
        }

        String tipoAtencion = String.valueOf(
            cmbTipoAtencion.getSelectedItem()
        );

        String citaSeleccionada =
            (String) cmbCita.getSelectedItem();

        // 1. Precio del tipo de atención
        double precioAtencion = obtenerPrecioAtencion();

        // 2. Precio de la cita registrada en citas.txt
        Double precioCitaRegistrado =
            preciosCitas.get(citaSeleccionada);

        double precioCita = precioCitaRegistrado == null
            ? 0.0
            : precioCitaRegistrado;

        // 3. Total
        boolean precioDisponible = precioAtencion >= 0
            && Double.isFinite(precioAtencion);

        double total = precioDisponible
            ? precioAtencion + precioCita
            : 0.0;

        String textoAtencion = precioDisponible
            ? String.format(java.util.Locale.US,
                "S/ %.2f", precioAtencion)
            : "Pendiente";

        String textoCita = String.format(
            java.util.Locale.US, "S/ %.2f", precioCita
        );

        String textoTotal = precioDisponible
            ? String.format(java.util.Locale.US,
                "S/ %.2f", total)
            : "Pendiente de calcular";

        String comprobante = factura
            ? "FACTURA"
            : "BOLETA DE VENTA";

        String serie = factura
            ? "F001 - VISTA PREVIA"
            : "B001 - VISTA PREVIA";

        String documento = txtDocumento.getText();

        txtPreview.setText(
            "CENTRO DE SALUD DANIEL ALCIDES CARRION\n"
            + "CAMPOY - SAN JUAN DE LURIGANCHO\n"
            + "RUC: " + RUC_ESTABLECIMIENTO + "\n"
            + "----------------------------------------\n"
            + comprobante + "\n"
            + serie + "\n"
            + "----------------------------------------\n"
            + "Fecha: " + txtFecha.getText() + "\n"
            + "Cliente: " + cliente + "\n"
            + (factura ? "RUC: " : "DNI: ")
            + documento + "\n"
            + "----------------------------------------\n"
            + "DETALLE DEL COBRO\n\n"
            + "Tipo de atencion:\n"
            + tipoAtencion + "\n"
            + "Precio de atencion: " + textoAtencion + "\n\n"
            + "Cita medica:\n"
            + (precioCitaRegistrado == null
                ? "No seleccionada\n"
                : citaSeleccionada + "\n")
            + "Precio de cita: " + textoCita + "\n"
            + "----------------------------------------\n"
            + "TOTAL A PAGAR: " + textoTotal + "\n"
            + "----------------------------------------\n"
            + "SIMULACION ACADEMICA\n"
            + "SIN VALIDEZ TRIBUTARIA"
        );

        txtPreview.setCaretPosition(0);
    }
    
    
    private void prepararVistaMedica() {

        txtPreviewMedico.setEditable(false);
        txtPreviewMedico.setLineWrap(true);
        txtPreviewMedico.setWrapStyleWord(true);
        txtPreviewMedico.setFont(
            new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12)
        );

        javax.swing.JPanel superior =
            new javax.swing.JPanel(new java.awt.BorderLayout());

        superior.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                "Previsualización de boleta / factura")
        );

        superior.add(scrollPreview, java.awt.BorderLayout.CENTER);

        javax.swing.JPanel inferior =
            new javax.swing.JPanel(new java.awt.BorderLayout());

        inferior.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                "Diagnóstico y notas del médico")
        );

        inferior.add(
            new javax.swing.JScrollPane(txtPreviewMedico),
            java.awt.BorderLayout.CENTER
        );

        javax.swing.JPanel dosVistas =
            new javax.swing.JPanel(new java.awt.GridLayout(2, 1, 0, 8));

        dosVistas.add(superior);
        dosVistas.add(inferior);

        panelPreview.removeAll();
        panelPreview.setLayout(new java.awt.BorderLayout(5, 5));

        panelPreview.add(lblPreviewTitulo, java.awt.BorderLayout.NORTH);
        panelPreview.add(dosVistas, java.awt.BorderLayout.CENTER);

        panelPreview.revalidate();
        panelPreview.repaint();
    }

    private void actualizarVistaMedica() {

        String cita = String.valueOf(cmbCita.getSelectedItem());
        String cliente = String.valueOf(cmbCliente.getSelectedItem());

        int separador = cliente.lastIndexOf(" - ");
        if (separador >= 0) {
            cliente = cliente.substring(0, separador);
        }

        String diagnostico = txtDiagnostico.getText().trim();
        String nota = txtNotaMedico.getText().trim();

        txtPreviewMedico.setText(
            "CENTRO DE SALUD DANIEL ALCIDES CARRION\n"
            + "CAMPOY\n"
            + "------------------------------------\n"
            + "RESUMEN DE ATENCION MEDICA\n"
            + "------------------------------------\n"
            + "Paciente: " + cliente + "\n"
            + "Cita: " + cita + "\n\n"
            + "DIAGNOSTICO:\n"
            + (diagnostico.isEmpty()
                ? "No registrado en la cita"
                : diagnostico)
            + "\n\n"
            + "NOTAS DEL MEDICO:\n"
            + (nota.isEmpty() ? "Sin indicaciones" : nota)
            + "\n------------------------------------\n"
            + "DOCUMENTO MEDICO SEPARADO DEL COBRO"
        );

        txtPreviewMedico.setCaretPosition(0);
    }

    private void mostrarDiagnosticoSeleccionado() {

        String cita = (String) cmbCita.getSelectedItem();

        txtDiagnostico.setText(
            diagnosticosCitas.getOrDefault(cita, "")
        );

        actualizarVistaMedica();
    }
  
    
    private void cargarClientes() {

        cmbCliente.removeAllItems();
        cmbCliente.addItem("Seleccione paciente");

        dniClientes.clear();
        rucClientes.clear();

        java.nio.file.Path ruta =
            java.nio.file.Paths.get("pacientes.txt");

        if (!java.nio.file.Files.exists(ruta)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No se encontró pacientes.txt");
            return;
        }

        try (java.io.BufferedReader lector =
                java.nio.file.Files.newBufferedReader(
                    ruta, java.nio.charset.StandardCharsets.UTF_8)) {

            String linea;
            int numero = 0;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split("\\s*\\|\\s*");

                if (datos.length < 3) continue;

                numero++;

                String nombre =
                    datos[2].trim() + " " + datos[1].trim();

                String dni = recuperarDni(datos);
                String ruc = "";

                for (String dato : datos) {

                    dato = dato.trim();


                    if (dato.startsWith("RUC:")) {
                        ruc = dato.substring(4).trim();
                    }
                }

                String opcion = nombre;

                if (dni.matches("\\d{8}")) {
                    opcion += " - " + dni;
                } else {
                    opcion += " - Registro " + numero;
                }

                dniClientes.put(opcion, dni);
                rucClientes.put(opcion, ruc);

                cmbCliente.addItem(opcion);
            }

        } catch (java.io.IOException ex) {

            javax.swing.JOptionPane.showMessageDialog(this,
                "Error al cargar pacientes: " + ex.getMessage());
        }
    }

    private void actualizarDocumento() {

        String tipo =
            String.valueOf(cmbTipoComprobante.getSelectedItem());

        String cliente =
            String.valueOf(cmbCliente.getSelectedItem());

        boolean factura = tipo.equals("Factura");

        lblDocumento.setText(factura ? "RUC" : "DNI");

        btnEmitir.setText(
            factura ? "Emitir factura" : "Emitir boleta"
        );

        String documento = factura
            ? rucClientes.getOrDefault(cliente, "")
            : dniClientes.getOrDefault(cliente, "");

        txtDocumento.setText(
            documento.isEmpty() ? "No registrado" : documento
        );

        txtDocumento.setEditable(false);
        actualizarVistaCobro();
    }
    
    
    private String generarNumeroComprobante(String tipo)
            throws java.io.IOException {

        String serie = tipo.equals("Factura") ? "F001-" : "B001-";

        java.nio.file.Path archivo =
            java.nio.file.Paths.get("comprobantes.txt");

        int mayor = 0;

        if (java.nio.file.Files.exists(archivo)) {

            for (String linea : java.nio.file.Files.readAllLines(
                    archivo, java.nio.charset.StandardCharsets.UTF_8)) {

                String prefijo = "ID: " + serie;

                if (linea.startsWith(prefijo)) {
                    String resto = linea.substring(prefijo.length());
                    String numero = resto.split("\\s")[0];

                    try {
                        mayor = Math.max(
                            mayor, Integer.parseInt(numero)
                        );
                    } catch (NumberFormatException ex) {
                        // Ignorar registros con numeración incorrecta
                    }
                }
            }
        }

        return serie + String.format("%08d", mayor + 1);
    }


    private void emitirComprobante() {

        String tipo = String.valueOf(
            cmbTipoComprobante.getSelectedItem()
        );

        String cliente = (String) cmbCliente.getSelectedItem();

        String documento = txtDocumento.getText().trim();
        String fecha = txtFecha.getText().trim();

        String tipoAtencion = String.valueOf(
            cmbTipoAtencion.getSelectedItem()
        );

        String cita = (String) cmbCita.getSelectedItem();

        // 1. Validar cliente
        if (cliente == null
                || cliente.equals("Seleccione paciente")
                || !dniClientes.containsKey(cliente)) {

            javax.swing.JOptionPane.showMessageDialog(this,
                "Seleccione un paciente registrado.");
            return;
        }

        // 2. Validar documento
        boolean factura = tipo.equals("Factura");

        if (factura && !documento.matches("\\d{11}")) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Para emitir factura debe existir un RUC válido "
                + "de 11 dígitos.");
            return;
        }

        if (!factura && !documento.matches("\\d{8}")) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "El paciente debe tener un DNI registrado "
                + "de 8 dígitos.");
            return;
        }

        // 3. Validar fecha
        try {
            java.time.LocalDate seleccionada =
                java.time.LocalDate.parse(
                    fecha,
                    java.time.format.DateTimeFormatter
                        .ofPattern("dd/MM/uuuu")
                        .withResolverStyle(
                            java.time.format.ResolverStyle.STRICT)
                );

            if (seleccionada.isAfter(java.time.LocalDate.now())) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "La fecha de emisión no puede ser futura.");
                return;
            }

        } catch (java.time.format.DateTimeParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Seleccione una fecha válida en el calendario.");
            return;
        }

        // 4. Obtener importes
        double precioAtencion = obtenerPrecioAtencion();

        if (!Double.isFinite(precioAtencion)
                || precioAtencion < 0) {

            javax.swing.JOptionPane.showMessageDialog(this,
                "El precio de esta atención todavía "
                + "no está configurado.");
            return;
        }

        Double precioCitaRegistrado = preciosCitas.get(cita);

        double precioCita = precioCitaRegistrado == null
            ? 0.0 : precioCitaRegistrado;

        double total = precioAtencion + precioCita;

        if (!Double.isFinite(total) || total < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "El importe total no es válido.");
            return;
        }

        // Evitar sumar dos veces el mismo servicio
        if (precioCitaRegistrado != null) {

            int confirmacion =
                javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "Se cobrará la atención (S/ "
                    + String.format(java.util.Locale.US,
                        "%.2f", precioAtencion)
                    + ") más la cita (S/ "
                    + String.format(java.util.Locale.US,
                        "%.2f", precioCita)
                    + ").\n\nConfirme que son dos servicios "
                    + "distintos y no un cobro duplicado.",
                    "Confirmar importes",
                    javax.swing.JOptionPane.YES_NO_OPTION
                );

            if (confirmacion !=
                    javax.swing.JOptionPane.YES_OPTION) {
                return;
            }
        }

        // 5. Confirmar emisión
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(
            this,
            String.format(java.util.Locale.US,
                "¿Emitir %s por S/ %.2f?", tipo, total),
            "Confirmar emisión",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (respuesta != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // 6. Obtener número e información
            String numero = generarNumeroComprobante(tipo);

            actualizarVistaCobro();

            String textoComprobante = txtPreview.getText()
                .replace("B001 - VISTA PREVIA", numero)
                .replace("F001 - VISTA PREVIA", numero);

            String nombre = cliente;
            int separador = nombre.lastIndexOf(" - ");

            if (separador >= 0) {
                nombre = nombre.substring(0, separador);
            }

            String textoMedico =
                "CENTRO DE SALUD DANIEL ALCIDES CARRION\n"
                + "====================================\n"
                + "REFERENCIA: " + numero + "\n"
                + "Paciente: " + nombre + "\n"
                + "Fecha: " + fecha + "\n"
                + "Cita: " + (cita == null
                    ? "No seleccionada" : cita) + "\n\n"
                + "DIAGNOSTICO:\n"
                + txtDiagnostico.getText().trim() + "\n\n"
                + "NOTA DEL MEDICO:\n"
                + txtNotaMedico.getText().trim() + "\n";

            // 7. Cifrar ambos documentos antes de guardarlos
            String comprobanteCifrado =
                util.CifradoDni.cifrar(textoComprobante);

            String notaCifrada =
                util.CifradoDni.cifrar(textoMedico);

            // 8. Preparar registros
            String registroComprobante =
                "ID: " + numero
                + " | Tipo: " + tipo
                + " | Fecha: " + fecha
                + " | Total: S/ "
                + String.format(java.util.Locale.US, "%.2f", total)
                + " | Contenido CIF: " + comprobanteCifrado
                + System.lineSeparator();

            String registroMedico =
                "ID: " + numero
                + " | Contenido CIF: " + notaCifrada
                + System.lineSeparator();

            java.nio.file.Path archivoComprobantes =
                java.nio.file.Paths.get("comprobantes.txt");

            java.nio.file.Path archivoNotas =
                java.nio.file.Paths.get("notas_medicas.txt");

            // 9. Guardar comprobante
            java.nio.file.Files.write(
                archivoComprobantes,
                registroComprobante.getBytes(
                    java.nio.charset.StandardCharsets.UTF_8),
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND
            );

            // 10. Guardar información médica
            try {
                java.nio.file.Files.write(
                    archivoNotas,
                    registroMedico.getBytes(
                        java.nio.charset.StandardCharsets.UTF_8),
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND
                );

            } catch (java.io.IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "El comprobante " + numero
                    + " se guardó, pero falló la nota médica.\n"
                    + "Revise ambos archivos antes de intentar "
                    + "emitir nuevamente.\n"
                    + ex.getMessage());
                return;
            }

            // 11. Mostrar confirmación
            javax.swing.JOptionPane.showMessageDialog(this,
                tipo + " emitida correctamente.\n"
                + "Número: " + numero + "\n"
                + "Total: S/ "
                + String.format(java.util.Locale.US, "%.2f", total)
                + "\n\nSe actualizaron:\n"
                + "comprobantes.txt\n"
                + "notas_medicas.txt"
            );

        } catch (java.io.IOException
                | IllegalStateException ex) {

            javax.swing.JOptionPane.showMessageDialog(this,
                "No se pudo completar la emisión:\n"
                + ex.getMessage());
        }
    }


    
    
    private void abrirCalendarioComprobante() {

        final java.time.YearMonth[] mes = {
            java.time.YearMonth.now()
        };

        javax.swing.JDialog ventana = new javax.swing.JDialog(
            javax.swing.SwingUtilities.getWindowAncestor(this),
            "Seleccionar fecha",
            java.awt.Dialog.ModalityType.APPLICATION_MODAL
        );

        javax.swing.JLabel titulo = new javax.swing.JLabel(
            "", javax.swing.SwingConstants.CENTER
        );

        javax.swing.JButton anterior = new javax.swing.JButton("<");
        javax.swing.JButton siguiente = new javax.swing.JButton(">");

        javax.swing.JPanel encabezado =
            new javax.swing.JPanel(new java.awt.BorderLayout());

        encabezado.add(anterior, java.awt.BorderLayout.WEST);
        encabezado.add(titulo, java.awt.BorderLayout.CENTER);
        encabezado.add(siguiente, java.awt.BorderLayout.EAST);

        javax.swing.JPanel dias =
            new javax.swing.JPanel(new java.awt.GridLayout(0, 7, 5, 5));

        Runnable actualizar = () -> {

            dias.removeAll();

            titulo.setText(
                mes[0].getMonth().getDisplayName(
                    java.time.format.TextStyle.FULL,
                    new java.util.Locale("es", "PE")
                ) + " " + mes[0].getYear()
            );

            for (String nombre : new String[]{
                    "Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do"}) {

                dias.add(new javax.swing.JLabel(
                    nombre, javax.swing.SwingConstants.CENTER
                ));
            }

            int inicio =
                mes[0].atDay(1).getDayOfWeek().getValue();

            for (int i = 1; i < inicio; i++) {
                dias.add(new javax.swing.JLabel(""));
            }

            for (int numero = 1;
                    numero <= mes[0].lengthOfMonth(); numero++) {

                java.time.LocalDate fecha = mes[0].atDay(numero);

                javax.swing.JButton boton =
                    new javax.swing.JButton(String.valueOf(numero));

                // No permitir fechas futuras de emisión
                boton.setEnabled(
                    !fecha.isAfter(java.time.LocalDate.now())
                );

                boton.addActionListener(e -> {

                    txtFecha.setText(
                        fecha.format(
                            java.time.format.DateTimeFormatter
                                .ofPattern("dd/MM/uuuu")
                        )
                    );

                    actualizarVistaCobro();
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


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cmbTipoComprobante = new javax.swing.JComboBox<>();
        lblCliente = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        lblDocumento = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        btnCalendario = new javax.swing.JButton();
        lblTipoAtencion = new javax.swing.JLabel();
        cmbTipoAtencion = new javax.swing.JComboBox<>();
        lblCita = new javax.swing.JLabel();
        cmbCita = new javax.swing.JComboBox<>();
        lblDiagnostico = new javax.swing.JLabel();
        scrollDiagnostico = new javax.swing.JScrollPane();
        txtDiagnostico = new javax.swing.JTextArea();
        lblNotaMedico = new javax.swing.JLabel();
        scrollNotaMedico = new javax.swing.JScrollPane();
        txtNotaMedico = new javax.swing.JTextArea();
        btnEmitir = new javax.swing.JButton();
        panelPreview = new javax.swing.JPanel();
        lblPreviewTitulo = new javax.swing.JLabel();
        scrollPreview = new javax.swing.JScrollPane();
        txtPreview = new javax.swing.JTextArea();

        setTitle("Comprobantes");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        panelPrincipal.setBackground(new java.awt.Color(236, 248, 247));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(17, 94, 89));
        lblTitulo.setText("Emision de boleta / factura");

        panelDatos.setBackground(new java.awt.Color(255, 255, 255));
        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del comprobante"));

        lblTipo.setText("Comprobante");

        cmbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boleta", "Factura" }));

        lblCliente.setText("Cliente");

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione paciente" }));

        lblDocumento.setText("DNI");

        txtDocumento.setEditable(false);
        txtDocumento.setText("Documento protegido");

        lblFecha.setText("Fecha");

        txtFecha.setEditable(false);
        txtFecha.setText("Seleccionar fecha");

        btnCalendario.setText("...");

        lblTipoAtencion.setText("Tipo de atencion");

        cmbTipoAtencion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Consulta general", "Control de triaje", "Curacion", "Inyectable", "Atencion de emergencia", "Entrega de medicamentos" }));

        lblCita.setText("Cita medica");

        cmbCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione cita" }));

        lblDiagnostico.setText("Diagnostico");

        txtDiagnostico.setColumns(20);
        txtDiagnostico.setLineWrap(true);
        txtDiagnostico.setRows(4);
        txtDiagnostico.setWrapStyleWord(true);
        scrollDiagnostico.setViewportView(txtDiagnostico);

        lblNotaMedico.setText("Nota del medico");

        txtNotaMedico.setColumns(20);
        txtNotaMedico.setLineWrap(true);
        txtNotaMedico.setRows(4);
        txtNotaMedico.setWrapStyleWord(true);
        scrollNotaMedico.setViewportView(txtNotaMedico);

        btnEmitir.setBackground(new java.awt.Color(17, 94, 89));
        btnEmitir.setForeground(new java.awt.Color(255, 255, 255));
        btnEmitir.setText("Guardar boleta");
        btnEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDocumento)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addComponent(txtFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbTipoAtencion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCita, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(scrollNotaMedico)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipo)
                            .addComponent(lblCliente)
                            .addComponent(lblDocumento)
                            .addComponent(lblFecha)
                            .addComponent(lblTipoAtencion)
                            .addComponent(lblCita)
                            .addComponent(lblDiagnostico)
                            .addComponent(lblNotaMedico)
                            .addComponent(btnEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDocumento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTipoAtencion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTipoAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCita, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDiagnostico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNotaMedico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollNotaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPreview.setBackground(new java.awt.Color(255, 255, 255));
        panelPreview.setBorder(javax.swing.BorderFactory.createTitledBorder("Previsualizacion"));

        lblPreviewTitulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPreviewTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreviewTitulo.setText("CENTRO DE SALUD DANIEL ALCIDES CARRION - CAMPOY");

        txtPreview.setEditable(false);
        txtPreview.setColumns(20);
        txtPreview.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtPreview.setRows(5);
        txtPreview.setText("BOLETA B-000001\nFecha: 2026-10-09\nCliente: Paciente seleccionado\nDNI: Documento protegido\nTipo de atencion: Consulta general\n\nDETALLE DEL COBRO\nConsulta medica                 S/ 25.00\nMedicamento                     S/ 10.00\n----------------------------------------\nTOTAL                            S/ 35.00\n\nLa nota medica se guardara en otro TXT.");
        scrollPreview.setViewportView(txtPreview);

        javax.swing.GroupLayout panelPreviewLayout = new javax.swing.GroupLayout(panelPreview);
        panelPreview.setLayout(panelPreviewLayout);
        panelPreviewLayout.setHorizontalGroup(
            panelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPreviewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreviewTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPreview))
                .addContainerGap())
        );
        panelPreviewLayout.setVerticalGroup(
            panelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPreviewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPreviewTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPreview)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(panelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTitulo)
                .addGap(14, 14, 14)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmitirActionPerformed
    emitirComprobante();
    }//GEN-LAST:event_btnEmitirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCalendario;
    public javax.swing.JButton btnEmitir;
    public javax.swing.JComboBox<String> cmbCita;
    public javax.swing.JComboBox<String> cmbCliente;
    public javax.swing.JComboBox<String> cmbTipoAtencion;
    public javax.swing.JComboBox<String> cmbTipoComprobante;
    private javax.swing.JLabel lblCita;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDiagnostico;
    public javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNotaMedico;
    private javax.swing.JLabel lblPreviewTitulo;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipoAtencion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelPreview;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane scrollDiagnostico;
    private javax.swing.JScrollPane scrollNotaMedico;
    private javax.swing.JScrollPane scrollPreview;
    public javax.swing.JTextArea txtDiagnostico;
    public javax.swing.JTextField txtDocumento;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextArea txtNotaMedico;
    public javax.swing.JTextArea txtPreview;
    // End of variables declaration//GEN-END:variables
}
