package dwf.semana2.CJ220498;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class MiServicio {
    private final List<String> datos = new ArrayList<>();

    public MiServicio() {
        // Inicializar con algunos datos
        datos.add("Elemento 1");
        datos.add("Elemento 2");
    }
    public List<String> obtenerDatos() {
        return datos;
    }
    public void agregarDato(String nuevoDato) {
        datos.add(nuevoDato);
    }
    public void actualizarDato(String datoExistente, String nuevo){
        int index = datos.indexOf(datoExistente);
        if (index != -1) {
            datos.set(index, nuevo);
        } else {
            throw new IllegalArgumentException("Dato no encontrado: " + datoExistente);

        }
    }
    public void eliminarDato(String dato) {
        if (!datos.remove(dato)) {
            throw new IllegalArgumentException("Dato no encontrado: " + dato);
        }
    }
}
