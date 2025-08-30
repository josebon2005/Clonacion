import java.util.Optional;

public class ReglaOCRCalidadBaja implements ReglaDeteccion {
    private final double umbral;

    public ReglaOCRCalidadBaja(double umbral) { this.umbral = umbral; }

    @Override
    public Optional<Alerta> evaluar(DispositivoIoT d, double lectura) {
        if (!"OCR".equals(d.getTipo())) return Optional.empty();
        if (lectura < umbral) {
            Severidad sev = (lectura < umbral / 2.0) ? Severidad.ALTA : Severidad.MEDIA;
            String msg = String.format("Calidad OCR baja: %.2f (umbral %.2f)", lectura, umbral);
            return Optional.of(new Alerta(d.getId(), d.getNombre(), nombre(), msg, sev));
        }
        return Optional.empty();
    }

    @Override
    public String nombre() { return "ReglaOCRCalidadBaja"; }
}
