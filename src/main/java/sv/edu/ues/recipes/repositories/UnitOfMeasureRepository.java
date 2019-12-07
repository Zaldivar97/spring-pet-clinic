package sv.edu.ues.recipes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import sv.edu.ues.recipes.model.UnitOfMesure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMesure, Long>{

	Optional<UnitOfMesure> findByDescription(String value);
	
}
