package model;

public class CitaMedica {
    private String dniPaciente;
    private String idMedico;
    private String codMedicamento;
    private double costoAtencion;

    public CitaMedica(String dniPaciente, String idMedico, String codMedicamento, double costoAtencion) {
        this.dniPaciente = dniPaciente;
        this.idMedico = idMedico;
        this.codMedicamento = codMedicamento;
        this.costoAtencion = costoAtencion;
    }

    public String getDniPaciente() { return dniPaciente; }
    public String getIdMedico() { return idMedico; }
    public String getCodMedicamento() { return codMedicamento; }
    public double getCostoAtencion() { return costoAtencion; }

    public String toTxt() {
        return "PAC: " + dniPaciente + " | MED: " + idMedico + " | MEDICA: " + codMedicamento + " | Costo: S/ " + costoAtencion;
    }
}