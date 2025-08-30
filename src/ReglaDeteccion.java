import java.util.Optional;

public interface ReglaDeteccion {
    Optional<Alerta> evaluar(DispositivoIoT dispositivo, double lectura);
    String nombre();
}
