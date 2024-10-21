package com.example.examenG7.controller;

import com.example.examenG7.entity.PersonaEntity;
import com.example.examenG7.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas/v1")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/crearPersona")
    public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona) {
        PersonaEntity nuevoCliente = personaService.crearPersona(persona);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }


    @PutMapping("/actualizarPersona/{id}")
    public ResponseEntity<PersonaEntity> actualizarCliente(@PathVariable Long id, @RequestBody PersonaEntity persona) {
        PersonaEntity personaActualizado = personaService.actualizarPersona(id, persona);
        return new ResponseEntity<>(personaActualizado, HttpStatus.OK);
    }


    @GetMapping("/listar")
    public ResponseEntity buscartodos(){
        List<PersonaEntity> resul= personaService.buscarTodos();
        return ResponseEntity.ok(resul);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity buscarporDNI(@PathVariable long dni){
        PersonaEntity nuevoCliente= personaService.buscarPersonaXnumDocumento(dni);
        return ResponseEntity.ok(nuevoCliente);
    }



    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarDNI(@PathVariable long id){
        personaService.eliminarPersona(id);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
