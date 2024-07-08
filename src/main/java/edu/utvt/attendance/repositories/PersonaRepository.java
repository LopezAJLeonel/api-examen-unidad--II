package edu.utvt.attendance.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.utvt.attendance.entities.Persona;

import java.util.Optional;
import java.util.UUID;

public interface PersonaRepository extends JpaRepository<Persona, UUID> {
	Optional<Persona> findByNombre(String nombre);
}
