package com.uva.users.Controller;

import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.uva.users.Exception.VinoException;
import com.uva.users.Model.Bodega;
import com.uva.users.Model.Vino;
import com.uva.users.Model.VinoConRelacion;
import com.uva.users.Repository.BodegaRepository;
import com.uva.users.Repository.VinoConRelacionRepository;
import com.uva.users.Repository.VinoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/ejemploVinos")
class ejemploREST {

    private final VinoRepository repository;
    private final VinoConRelacionRepository repository2;
    private final BodegaRepository repository3;

    ejemploREST(VinoRepository repository, VinoConRelacionRepository repository2, BodegaRepository repository3) {
        this.repository = repository;
        this.repository2 = repository2;
        this.repository3 = repository3;
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

    @PostMapping(value = "bodega", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newBodega(@RequestBody Bodega newBodega) {
    
        try {
            repository3.save((newBodega));
            return "Nuevo registro creado";
        } catch (Exception e) {
            throw new VinoException("Error al crear el nuevo registro");
        }
    }

    @GetMapping("bodega")
    public List<Bodega> getBodegas() {
        return repository3.findAll();
    }

    @GetMapping("conRelacion")
    public List<VinoConRelacion> getVinosConRelacion() {
        return repository2.findAll();
    }
    
    
    
    @PostMapping(value = "conRelacion", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newVinoConRelacion(@RequestBody VinoConRelacion newVinoConRelacion) {

        try {
            repository2.save(newVinoConRelacion);
            return "Nuevo registro creado";
        } catch (Exception e) {
            throw new VinoException("Error al crear nuevo registro");
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
    public void deleteVino(@PathVariable Integer id) {
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

    @GetMapping("/VinoPorNombre/{nombre}")
    public Vino getVinoPorNombre_Comercial(@PathVariable String nombre) {
        Vino vino = repository.findByNombreComercial(nombre)
                .orElseThrow(() -> new VinoException("no se ha encontrado el vino de nombre " + nombre));
        return vino;
    }

    @GetMapping("/VinoPorPrecio")
    public List<Vino> getVinoPorPrecio(@RequestParam Float precio1, @RequestParam Float precio2) {
        List<Vino> vinos = repository.findByPrecioBetween(precio1, precio2);
        return vinos;
    }

    @DeleteMapping("/BorrarPorDenominacion_Categoria")
    public String deletePorDenominacion_Categoria(@RequestBody String json) {
        try {
            JSONObject jsonObjeto = new JSONObject(json);
            String denominacion = jsonObjeto.getString("denominacion");
            String categoria = jsonObjeto.getString("categoria");
            boolean existe = repository.existsVinoByDenominacionAndCategoria(denominacion, categoria);
            if (existe) {
                List<Vino> borrados = repository.deleteByDenominacionAndCategoria(denominacion, categoria);
                return "Numero de registros borrados: " + borrados;
            } else {
                return "No existen vinos de la categoría y denominación";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "Error al ejecutar el método deletePorDenominacion_Categoria";
        }
    }
}