package cl.ATorresGa.SistemaIMDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ATorresGa.SistemaIMDB.model.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long>{
	List<Show> findAll(); // metodo para encontrar todos
}
