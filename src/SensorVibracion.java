public class SensorVibracion extends DispositivoIoT implements Enviable {
    public SensorVibracion(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double leerDato() throws LecturaInvalidaException {
        if (rnd.nextDouble() < 0.02) throw new LecturaInvalidaException("Lectura fallida VIB");
        return rnd.nextDouble() * 2.5;
    }

    @Override
    public String diagnosticar() {
        return "SensorVibracion estable; sin resonancias detectadas.";
    }

    @Override
    public String getTipo() { return "VIB"; }

    @Override
    public void enviar(double dato) {
        System.out.println("Enviando VIB=" + String.format("%.2f", dato) + "g al servidor...");
    }
}
