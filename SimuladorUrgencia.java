import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimuladorUrgencia {
    Hospital hospital;
    List<Paciente> pacientes;
    Map<Integer, List<Long>> tiemposPorCategoria = new HashMap<>();
    List<Paciente> fueraDeTiempo = new ArrayList<>();

    public SimuladorUrgencia(Hospital hospital, List<Paciente> pacientes) {
        this.hospital = hospital;
        this.pacientes = pacientes;
    }

    public void simular(int pacientesPorDia) {
        long tiempo = 0;
        int ingresados = 0;

        for (int minuto = 0; minuto < 1440; minuto++) {
            tiempo = minuto * 60;

            if (minuto % 10 == 0 && ingresados < pacientes.size()) {
                Paciente nuevo = pacientes.get(ingresados);
                hospital.registrarPaciente(nuevo);
                ingresados++;

                if (ingresados % 3 == 0) {
                    hospital.atenderSiguiente(tiempo);
                    hospital.atenderSiguiente(tiempo);
                }
            }

            if (minuto % 15 == 0) {
                hospital.atenderSiguiente(tiempo);
            }
        }

        for (Paciente p : hospital.pacientesAtendidos) {
            long espera = (p.tiempoEsperaActual(tiempo));
            tiemposPorCategoria.computeIfAbsent(p.categoria, k -> new ArrayList<>()).add(espera);

            long limite = switch (p.categoria) {
                case 1 -> 0;
                case 2 -> 30;
                case 3 -> 90;
                case 4 -> 180;
                default -> Long.MAX_VALUE;
            };
            if (espera > limite)
                fueraDeTiempo.add(p);
        }
    }

    public void imprimirEstadisticas() {
        for (int cat = 1; cat <= 5; cat++) {
            List<Long> tiempos = tiemposPorCategoria.getOrDefault(cat, new ArrayList<>());
            double promedio = tiempos.stream().mapToLong(Long::longValue).average().orElse(0);
            System.out.println("C" + cat + " promedio espera: " + promedio + " minutos");
        }
        System.out.println("Pacientes fuera de tiempo: " + fueraDeTiempo.size());
    }
}
