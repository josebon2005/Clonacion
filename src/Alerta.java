import java.time.Instant;

public class Alerta {
    private final String dispositivoId;
    private final String dispositivoNombre;
    private final String regla;
    private final String mensaje;
    private final Severidad severidad;
    private final Instant timestamp;

    public Alerta(String dispositivoId, String dispositivoNombre, String regla, String mensaje, Severidad severidad) {
        this.dispositivoId = dispositivoId;
        this.dispositivoNombre = dispositivoNombre;
        this.regla = regla;
        this.mensaje = mensaje;
        this.severidad = severidad;
        this.timestamp = Instant.now();
    }

    public String getDispositivoId() { return dispositivoId; }
    public String getDispositivoNombre() { return dispositivoNombre; }
    public String getRegla() { return regla; }
    public String getMensaje() { return mensaje; }
    public Severidad getSeveridad() { return severidad; }
    public Instant getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + severidad + "] " + dispositivoNombre + " (" + dispositivoId + ") "
               + "regla=" + regla + " -> " + mensaje + " @ " + timestamp;
    }
}
