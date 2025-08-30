import java.util.Random;

public abstract class DispositivoIoT {
    private final String id;
    private final String nombre;
    private boolean activo = true;
    protected final Random rnd = new Random();

    public DispositivoIoT(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public abstract double leerDato() throws LecturaInvalidaException;
    public abstract String diagnosticar();
    public abstract String getTipo();

    public final double medir() throws LecturaInvalidaException {
        if (!activo) throw new LecturaInvalidaException("Dispositivo inactivo: " + id);
        try {
            return leerDato();
        } catch (LecturaInvalidaException ex) {
            return leerDato();
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return getTipo() + "[" + id + " - " + nombre + "]";
    }
}
