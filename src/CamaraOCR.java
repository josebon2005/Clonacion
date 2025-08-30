public class CamaraOCR extends DispositivoIoT implements Enviable {
    public CamaraOCR(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double leerDato() throws LecturaInvalidaException {
        if (rnd.nextDouble() < 0.03) throw new LecturaInvalidaException("Lectura fallida OCR");
        return rnd.nextDouble();
    }

    @Override
    public String diagnosticar() {
        return "CamaraOCR: lente limpio, enfoque correcto.";
    }

    @Override
    public String getTipo() { return "OCR"; }

    @Override
    public void enviar(double dato) {
        System.out.println("Enviando OCR_QUALITY=" + String.format("%.2f", dato) + " a backend...");
    }
}
