import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AreaAtencion {
    String nombre;
    PriorityQueue<Paciente> pacientesHeap;
    int capacidadMaxima;

    public AreaAtencion(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.pacientesHeap = new PriorityQueue<>();
    }

    public void ingresarPaciente(Paciente p) {
        if (!estaSaturada()) {
            pacientesHeap.add(p);
        }
    }

    public Paciente atenderPaciente() {
        return pacientesHeap.poll();
    }

    public boolean estaSaturada() {
        return pacientesHeap.size() >= capacidadMaxima;
    }

    public List<Paciente> obtenerPacientesPorHeapSort() {
        List<Paciente> copia = new ArrayList<>(pacientesHeap);
        copia.sort(null); // usa compareTo de Paciente
        return copia;
    }
}
