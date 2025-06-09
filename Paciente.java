import java.util.Stack;

public class Paciente implements Comparable<Paciente> {
    String nombre;
    String apellido;
    String id;
    int categoria; // 1 a 5 (C1 a C5)
    long tiempoLlegada;
    String estado; // en_espera, en_atencion, atendido
    String area; // SAPU, urgencia_adulto, infantil
    Stack<String> historialCambios = new Stack<>();

    public Paciente(String nombre, String apellido, String id, int categoria, long tiempoLlegada, String area) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.categoria = categoria;
        this.tiempoLlegada = tiempoLlegada;
        this.estado = "en_espera";
        this.area = area;
    }

    public long tiempoEsperaActual(long actual) {
        return (actual - tiempoLlegada) / 60;
    }

    public void registrarCambio(String descripcion) {
        historialCambios.push(descripcion);
    }

    public String obtenerUltimoCambio() {
        return historialCambios.isEmpty() ? null : historialCambios.pop();
    }

    @Override
    public int compareTo(Paciente otro) {
        if (this.categoria != otro.categoria) {
            return Integer.compare(this.categoria, otro.categoria);
        }
        return Long.compare(this.tiempoLlegada, otro.tiempoLlegada);
    }
}