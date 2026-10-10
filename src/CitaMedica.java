package model;

public class CitaMedica {
    private String dniPaciente;
    private String idMedico;
    private String codMedicamento;
    private double costoAtencion;
    private String fechaCita = "";
    private String horaCita = "";
    private String observaciones = "";
    private String diagnostico = "";

    public CitaMedica(String dniPaciente, String idMedico, String codMedicamento, double costoAtencion) {
        this.dniPaciente = dniPaciente;
        this.idMedico = idMedico;
        this.codMedicamento = codMedicamento;
        this.costoAtencion = costoAtencion;
    }
    
        public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getDniPaciente() { return dniPaciente; }
    public String getIdMedico() { return idMedico; }
    public String getCodMedicamento() { return codMedicamento; }
    public double getCostoAtencion() { return costoAtencion; }

    public String toTxt() {
        String pacienteCifrado =
            util.CifradoDni.cifrar(dniPaciente);

        String observacionesCifradas =
            observaciones.isEmpty() ? ""
            : util.CifradoDni.cifrar(observaciones);

        return "PAC_CIF: " + pacienteCifrado
            + " | MED: " + idMedico
            + " | MEDICA: " + codMedicamento
            + " | Costo: S/ " + costoAtencion
            + " | Fecha: " + fechaCita
            + " | Hora: " + horaCita
            + " | Diagnostico CIF: "
            + (diagnostico.isEmpty() ? "" : util.CifradoDni.cifrar(diagnostico))
            + " | Observaciones CIF: " + observacionesCifradas;
    }
}
