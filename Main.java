import java.util.List;

public class Main {
    public static void main(String[] args) {
        long base = 0L;
        int N = 144;

        Hospital hospital = new Hospital();
        List<Paciente> pacientes = GeneradorPacientes.generarPacientes(N, base);
        SimuladorUrgencia simulador = new SimuladorUrgencia(hospital, pacientes);
        simulador.simular(N);
        simulador.imprimirEstadisticas();
    }
}
