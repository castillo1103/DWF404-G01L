package dwf.semana2.Analisis_de_Resultados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaControlador {

    @Autowired
    private TareaServicio servicio;

    @GetMapping
    public List<Tarea> listar() {
        return servicio.obtenerTodas();
    }

    @PostMapping
    public Tarea agregar(@RequestBody Tarea tarea) {
        return servicio.agregar(tarea);
    }

    @PutMapping("/{id}/completar")
    public String completar(@PathVariable Long id) {
        return servicio.completar(id) ? "Tarea completada" : "No encontrada";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        return servicio.eliminar(id) ? "Tarea eliminada" : "No encontrada";
    }

    @GetMapping("/buscar")
    public List<Tarea> buscar(@RequestParam String palabra) {
        return servicio.buscar(palabra);
    }

    @PutMapping("/{id}")
    public Tarea editar(@PathVariable Long id, @RequestBody Tarea t) {
        return servicio.editar(id, t.getDescripcion());
    }
}
