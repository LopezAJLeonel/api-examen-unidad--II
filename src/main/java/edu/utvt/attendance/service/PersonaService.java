package edu.utvt.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utvt.attendance.entities.Persona;
import edu.utvt.attendance.repositories.PersonaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonaService {
    
	@Autowired
    private PersonaRepository personaRepository;

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Optional<Persona> findById(UUID id) {
        return personaRepository.findById(id);
    }

    public Optional<Persona> findByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
        
    }
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public void deleteById(UUID id) {
        personaRepository.deleteById(id);
        
    
       
    }
}
