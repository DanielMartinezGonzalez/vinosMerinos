package com.uva.users.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ejemploVinos")
class ejemploREST {
    @GetMapping()
    public String getVinos() {
        return "Mensaje desde getVinos";
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

   /* @GetMapping(value = { "/cartaVinos", "/cartaVinos/{nombre}" })
    public String getCarta(@PathVariable(required = false) String nombre) {
        if (nombre != null) {
            return "vino con nombre: " + nombre;
        } else {
            return "Toda la carta de vinos";
        }
    }*/

    @GetMapping("/cartaVinos")
    public String getCartaConQuery(@RequestParam String nombre) {
        return "Nombre del vino: " + nombre;
    }

}