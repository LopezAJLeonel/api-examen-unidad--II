package edu.utvt.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.utvt.attendance.entities.Persona;
import edu.utvt.attendance.service.PersonaService;
import jakarta.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity<?> getAllPersonas() {
        List<Persona> personas = personaService.findAll();
        if (personas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(personas);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable UUID id) {
        return personaService.findById(id)
                .map(persona -> ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tiene acceso a esta información"))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Persona> getPersonaByNombre(@PathVariable String nombre) {
        return personaService.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = personaService.save(persona);
        return ResponseEntity.ok(nuevaPersona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable UUID id, @Valid @RequestBody Persona personaDetails) {
        return personaService.findById(id)
                .map(persona -> {
                    persona.setNombre(personaDetails.getNombre());
                    persona.setEdad(personaDetails.getEdad());
                    persona.setFechaNacimiento(personaDetails.getFechaNacimiento());
                    persona.setUniversidad(personaDetails.getUniversidad());
                    persona.setCorreo(personaDetails.getCorreo());
                    Persona updatedPersona = personaService.save(persona);
                    return ResponseEntity.ok(updatedPersona);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable UUID id) {
        Optional<Persona> persona = personaService.findById(id);
        if (persona.isPresent()) {
            try {
                personaService.deleteById(id);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}