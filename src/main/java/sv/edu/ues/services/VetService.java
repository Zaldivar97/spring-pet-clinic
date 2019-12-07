package sv.edu.ues.services;

import java.util.Set;

import sv.edu.ues.model.Vet;


public interface VetService {
	Vet save(Vet owner);
	Vet findById(Long id);
	Set<Vet> findAll();
}
