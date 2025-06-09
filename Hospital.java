import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Hospital {
    Map<String, Paciente> pacientesTotales = new HashMap<>();
    PriorityQueue<Paciente> colaAtencion = new PriorityQueue<>();
    Map<String, AreaAtencion> areasAtencion = new HashMap<>();
    List<Paciente> pacientesAtendidos = new ArrayList<>();

    public Hospital() {
        areasAtencion.put("SAPU", new AreaAtencion("SAPU", 100));
        areasAtencion.put("urgencia_adulto", new AreaAtencion("urgencia_adulto", 100));
        areasAtencion.put("infantil", new AreaAtencion("infantil", 100));
    }

    public void registrarPaciente(Paciente p) {
        pacientesTotales.put(p.id, p);
        colaAtencion.add(p);
    }

    public void reasignarCategoria(String id, int nuevaCategoria) {
        Paciente p = pacientesTotales.get(id);
        if (p != null) {
            colaAtencion.remove(p);
            p.registrarCambio("Reasignado de C" + p.categoria + " a C" + nuevaCategoria);
            p.categoria = nuevaCategoria;
            colaAtencion.add(p);
        }
    }

    public Paciente atenderSiguiente(long actual) {
        Paciente p = colaAtencion.poll();
        if (p != null) {
            p.estado = "atendido";
            pacientesAtendidos.add(p);
            areasAtencion.get(p.area).ingresarPaciente(p);
        }
        return p;
    }

    public List<Paciente> obtenerPacientesPorCategoria(int categoria) {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente p : colaAtencion) {
            if (p.categoria == categoria)
                resultado.add(p);
        }
        return resultado;
    }

    public AreaAtencion obtenerArea(String nombre) {
        return areasAtencion.get(nombre);
    }
}
