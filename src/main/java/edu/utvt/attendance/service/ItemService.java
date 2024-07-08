package edu.utvt.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utvt.attendance.entities.Item;
import edu.utvt.attendance.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    
	@Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
    
    public List<Item> findByPersonaId(UUID personaId) {
        return itemRepository.findByPersonaId(personaId);
    }
    
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public Optional<Item> findByNombre(String nombre) {
        return itemRepository.findByNombre(nombre);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
          
    }

	}
