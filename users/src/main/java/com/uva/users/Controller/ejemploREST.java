package com.uva.users.Controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.uva.users.Exception.VinoException;
import com.uva.users.Model.Vino;
import com.uva.users.Repository.VinoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/ejemploVinos")
class ejemploREST {

    private final VinoRepository repository;

    ejemploREST(VinoRepository repository) {
        this.repository = repository;
    }

    // No se ha incluido el atributo path. Si en el ejemplo que tienes está,
    // añadirle
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newVino(@RequestBody Vino newVino) {
        try {
            repository.save(newVino);
            return "Nuevo registro creado";
        } catch (Exception e) {
            // Se deja esta parte comentada como alternativa a la gestión de errores
            // propuesta
            // throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error
            // alcrear el nuevo registro.");
            // Se usa este sistema de gestión de errores porque es más sencillo hacer que el
            // cliente reciba el mensaje de error
            throw new VinoException("Error al crear el nuevo registro.");
        }
    }

    @GetMapping()
    public List<Vino> getVinos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Vino getVinos(@PathVariable() Integer id) {
        Vino vino = repository.findById(id).orElseThrow(() -> new VinoException("Sin resultado"));
        return vino;
    }

    @DeleteMapping("/{id}")
    public void deleteVino(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String putVino(@PathVariable Integer id, @RequestBody Vino newVino) {
        Vino oldBVino = repository.findById(id).orElseThrow(() -> new VinoException("Sin resultado"));
        newVino.setId(oldBVino.getId());
        repository.save(newVino);
        return "Registro Actualizado";
    }
    

    @PutMapping("/id")
    public String putVinos(@RequestBody String body) {
        return "Contenido cuerpo petición: " + body;
    }

    @PutMapping("/{id}")
    public String putVinos(@RequestBody String body, @PathVariable("id") Long identificator) {
        return "Realizada operación Put. Con Id: " + identificator +
                ". Contenido del cuerpo de la petición: " + body;
    }

    /*
     * @GetMapping(value = { "/cartaVinos", "/cartaVinos/{nombre}" })
     * public String getCarta(@PathVariable(required = false) String nombre) {
     * if (nombre != null) {
     * return "vino con nombre: " + nombre;
     * } else {
     * return "Toda la carta de vinos";
     * }
     * }
     */

    @GetMapping("/cartaVinos")
    public String getCartaConQuery(@RequestParam String nombre) {
        return "Nombre del vino: " + nombre;
    }

}