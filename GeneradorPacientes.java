import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorPacientes {
    static String[] nombres = { "Ana", "Luis", "Carlos", "Marta", "Pedro" };
    static String[] apellidos = { "Pérez", "Gómez", "Soto", "Díaz", "Morales" };

    public static List<Paciente> generarPacientes(int N, long timestampBase) {
        List<Paciente> lista = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            String nombre = nombres[rand.nextInt(nombres.length)];
            String apellido = apellidos[rand.nextInt(apellidos.length)];
            String id = "P" + i;

            // Distribución de categorías
            int r = rand.nextInt(100);
            int categoria = (r < 10) ? 1 : (r < 25) ? 2 : (r < 43) ? 3 : (r < 70) ? 4 : 5;

            long llegada = timestampBase + i * 600;
            String area = (i % 3 == 0) ? "SAPU" : (i % 3 == 1) ? "urgencia_adulto" : "infantil";

            lista.add(new Paciente(nombre, apellido, id, categoria, llegada, area));
        }

        return lista;
    }
}
