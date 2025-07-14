package dwf.semana2.Analisis_de_Resultados;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TareaServicio {

        private final List<Tarea> tareas = new ArrayList<>();
        private final AtomicLong idGen = new AtomicLong();

        public TareaServicio() {
        agregar(new Tarea(null, "Estudiar Java"));
        agregar(new Tarea(null, "Leer documentación de Spring Boot"));
        agregar(new Tarea(null, "Probar API con Postman"));
         }
        public List<Tarea> obtenerTodas() {
            return tareas;
        }

        public Tarea agregar(Tarea tarea) {
            tarea.setId(idGen.incrementAndGet());
            tareas.add(tarea);
            return tarea;
        }

        public boolean completar(Long id) {
            return tareas.stream().filter(t -> t.getId().equals(id))
                    .peek(t -> t.setCompletada(true)).findFirst().isPresent();
        }

        public boolean eliminar(Long id) {
            return tareas.removeIf(t -> t.getId().equals(id));
        }

        public List<Tarea> buscar(String palabra) {
            return tareas.stream()
                    .filter(t -> t.getDescripcion().toLowerCase().contains(palabra.toLowerCase()))
                    .collect(Collectors.toList());
        }

        public Tarea editar(Long id, String nuevaDescripcion) {
            for (Tarea t : tareas) {
                if (t.getId().equals(id)) {
                    t.setDescripcion(nuevaDescripcion);
                    return t;
                }
            }
            return null;
        }
}
