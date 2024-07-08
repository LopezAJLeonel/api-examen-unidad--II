package edu.utvt.attendance.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Item {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @NotBlank
	    private String nombre;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "persona_id", columnDefinition = "binary(16)")
	    @JsonBackReference
	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	    private Persona persona;
	}




