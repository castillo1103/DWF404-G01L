package dwf.semana2.CJ220498;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datos")
public class MiControlador{
    @Autowired
    private MiServicio miServicio;

    @GetMapping
    public List<String> obtenerDatos() {
        return miServicio.obtenerDatos();
    }
    @PostMapping
    public String agregarDato(@RequestBody String nuevoDato) {
        miServicio.agregarDato(nuevoDato);
        return "Dato agregado correctamente: " + nuevoDato;
    }
    @PutMapping
    public String actualizarDato(@RequestParam String datoExistente, @RequestBody String nuevo) {
        miServicio.actualizarDato(datoExistente, nuevo);
        return "Dato actualizado correctamente: " + nuevo;
    }
    @DeleteMapping
    public String eliminarDato(@RequestParam String dato) {
        miServicio.eliminarDato(dato);
        return "Dato eliminado correctamente: " + dato;
    }
}