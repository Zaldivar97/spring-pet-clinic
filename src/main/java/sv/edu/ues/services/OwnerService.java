package sv.edu.ues.services;

import java.util.Set;

import sv.edu.ues.model.Owner;

public interface OwnerService {

	Owner findByLastName(String lastname);
	Owner save(Owner owner);
	Owner findById(Long id);
	Set<Owner> findAll();
	
}
