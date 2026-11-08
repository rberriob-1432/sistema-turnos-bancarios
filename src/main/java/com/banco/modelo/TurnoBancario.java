package main.java.com.banco.modelo;
import java.time.LocalDateTime;
import java.util.Objects;

public class TurnoBancario {

    private final int numeroTurno;
    private String nombreCliente;
    private String documento;
    private TipoServicio tipoServicio;
    private EstadoTurno estado;
    private final LocalDateTime fechaRegistro;

    public TurnoBancario(
            int numeroTurno,
            String nombreCliente,
            String documento,
            TipoServicio tipoServicio) {

        if (numeroTurno <= 0) {
            throw new IllegalArgumentException("Número de turno inválido.");
        }

        this.numeroTurno = numeroTurno;
        setNombreCliente(nombreCliente);
        setDocumento(documento);
        setTipoServicio(tipoServicio);

        this.estado = EstadoTurno.PENDIENTE;
        this.fechaRegistro = LocalDateTime.now();
    }

    public int getNumeroTurno() {
        return numeroTurno;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido.");
        }

        this.nombreCliente = nombreCliente;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento inválido.");
        }

        this.documento = documento;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = Objects.requireNonNull(tipoServicio);
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = Objects.requireNonNull(estado);
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof TurnoBancario otro)) {
            return false;
        }

        return numeroTurno == otro.numeroTurno;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numeroTurno);
    }

    @Override
    public String toString() {
        return "Turno: " + numeroTurno
                + " | Cliente: " + nombreCliente
                + " | Documento: " + documento
                + " | Servicio: " + tipoServicio
                + " | Estado: " + estado
                + " | Fecha: " + fechaRegistro;
    }
}