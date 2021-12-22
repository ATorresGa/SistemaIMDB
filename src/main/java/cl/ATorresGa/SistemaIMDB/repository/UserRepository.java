package cl.ATorresGa.SistemaIMDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ATorresGa.SistemaIMDB.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findAll();//metodo para encontrar todos
	User findUserByUsername(String username); // metodo para encontrar por username
	User findUserByEmail(String email); // metodo para encontrarlo por el email
}
