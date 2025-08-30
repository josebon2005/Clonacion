import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Gateway {
    private final ArrayList<DispositivoIoT> dispositivos = new ArrayList<>();
    private final ArrayList<ReglaDeteccion> reglas = new ArrayList<>();
    private final ArrayList<Alerta> alertas = new ArrayList<>();

    public void agregarDispositivo(DispositivoIoT d) { dispositivos.add(d); }
    public void agregarRegla(ReglaDeteccion r) { reglas.add(r); }
    public List<Alerta> getAlertas() { return alertas; }

    public void leerYEvaluar() {
        alertas.clear();
        for (DispositivoIoT d : dispositivos) {
            try {
                double lectura = d.medir();
                if (d instanceof Enviable) {
                    ((Enviable) d).enviar(lectura);
                }
                for (ReglaDeteccion r : reglas) {
                    r.evaluar(d, lectura).ifPresent(alertas::add);
                }
            } catch (LecturaInvalidaException ex) {
                alertas.add(new Alerta(
                        d.getId(), d.getNombre(), "LecturaInvalida",
                        "No se pudo leer: " + ex.getMessage(),
                        Severidad.MEDIA
                ));
            }
        }
        ordenarAlertas();
    }

    private void ordenarAlertas() {
        alertas.sort(Comparator
                .comparing(Alerta::getSeveridad, (a, b) -> Integer.compare(rank(b), rank(a)))
                .thenComparing(Alerta::getTimestamp));
    }

    private static int rank(Severidad s) {
        switch (s) {
            case CRITICA: return 4;
            case ALTA: return 3;
            case MEDIA: return 2;
            default: return 1;
        }
    }

    public void imprimirResumen() {
        System.out.println("\n==== RESUMEN DE ALERTAS ====");
        if (alertas.isEmpty()) {
            System.out.println("Sin alertas.");
            return;
        }
        for (Alerta a : alertas) {
            System.out.println(a);
        }
    }
}
