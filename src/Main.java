import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("== Demo Hub IoT: Abstract classes + Interfaces + ArrayList ==");

        DispositivoIoT t1 = new SensorTemperatura("T-01", "Temp Bodega Norte");
        DispositivoIoT v1 = new SensorVibracion("V-01", "Vib Cinta 3");
        DispositivoIoT o1 = new CamaraOCR("O-01", "OCR Carril 2");

        Gateway gw = new Gateway();
        Arrays.asList(t1, v1, o1).forEach(gw::agregarDispositivo);

        gw.agregarRegla(new ReglaTemperaturaAlta(38.0));
        gw.agregarRegla(new ReglaVibracionAnomala(1.2));
        gw.agregarRegla(new ReglaOCRCalidadBaja(0.55));

        if (t1 instanceof Actualizable) {
            ((Actualizable) t1).actualizarFirmware("1.2.3");
        }else {
            System.out.println("El dispositivo no es actualizable");
        }

        gw.leerYEvaluar();
        gw.imprimirResumen();

        o1.setActivo(false);
        System.out.println("\n-- Simulación con OCR inactivo (para ver LecturaInvalidaException) --");
        gw.leerYEvaluar();
        gw.imprimirResumen();

        System.out.println("\n== Diagnósticos ==");
        System.out.println(t1 + " -> " + t1.diagnosticar());
        System.out.println(v1 + " -> " + v1.diagnosticar());
        System.out.println(o1 + " -> " + o1.diagnosticar());
    }
}
