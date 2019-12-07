package sv.edu.ues.services;

import java.util.Set;

import sv.edu.ues.model.Pet;


public interface PetService {

	Pet findById(Long id);
	Pet save(Pet pet);
	Set<Pet> findAll();
	
}
