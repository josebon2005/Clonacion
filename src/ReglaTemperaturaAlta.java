import java.util.Optional;

public class ReglaTemperaturaAlta implements ReglaDeteccion {
    private final double umbralC;

    public ReglaTemperaturaAlta(double umbralC) { this.umbralC = umbralC; }

    @Override
    public Optional<Alerta> evaluar(DispositivoIoT d, double lectura) {
        if (!"TEMP".equals(d.getTipo())) return Optional.empty();
        if (lectura > umbralC) {
            Severidad sev = (lectura > umbralC + 10) ? Severidad.CRITICA : Severidad.ALTA;
            String msg = String.format("Temperatura alta: %.2f°C (umbral %.2f°C)", lectura, umbralC);
            return Optional.of(new Alerta(d.getId(), d.getNombre(), nombre(), msg, sev));
        }
        return Optional.empty();
    }

    @Override
    public String nombre() { return "ReglaTemperaturaAlta"; }
}
