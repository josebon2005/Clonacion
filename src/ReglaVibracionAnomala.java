import java.util.Optional;

public class ReglaVibracionAnomala implements ReglaDeteccion {
    private final double maxG;

    public ReglaVibracionAnomala(double maxG) { this.maxG = maxG; }

    @Override
    public Optional<Alerta> evaluar(DispositivoIoT d, double lectura) {
        if (!"VIB".equals(d.getTipo())) return Optional.empty();
        if (lectura > maxG) {
            Severidad sev = (lectura > maxG * 1.5) ? Severidad.CRITICA : Severidad.ALTA;
            String msg = String.format("Vibración anómala: %.2fg (umbral %.2fg)", lectura, maxG);
            return Optional.of(new Alerta(d.getId(), d.getNombre(), nombre(), msg, sev));
        }
        return Optional.empty();
    }

    @Override
    public String nombre() { return "ReglaVibracionAnomala"; }
}
