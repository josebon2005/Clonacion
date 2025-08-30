public class SensorTemperatura extends DispositivoIoT implements Enviable, Actualizable {
    public SensorTemperatura(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double leerDato() throws LecturaInvalidaException {
        if (rnd.nextDouble() < 0.02) throw new LecturaInvalidaException("Lectura fallida TEMP");
        return 15 + rnd.nextDouble() * 35;
    }

    @Override
    public String diagnosticar() {
        return "SensorTemperatura OK. Último chequeo sin anomalías.";
    }

    @Override
    public String getTipo() { return "TEMP"; }

    @Override
    public void enviar(double dato) {
        System.out.println("Enviando TEMP=" + String.format("%.2f", dato) + "°C al servidor...");
    }

    @Override
    public void actualizarFirmware(String version) {
        System.out.println("Actualizando firmware TEMP -> v" + version + " ... listo.");
    }
}
