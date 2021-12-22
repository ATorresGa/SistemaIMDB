package cl.ATorresGa.SistemaIMDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ATorresGa.SistemaIMDB.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findAll(); //metodo para encontrar todos
	
	List<Role> findByName(String name); //metodo para encontrarlo por el nombre
}
