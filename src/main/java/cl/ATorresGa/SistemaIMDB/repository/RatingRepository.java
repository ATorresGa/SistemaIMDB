package cl.ATorresGa.SistemaIMDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ATorresGa.SistemaIMDB.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
	Rating findRatingById(Long id);//metodo para encontrar la valoracion de un usuario
	Rating findFirstByOrderByIdDesc(); //metodo para encontrar el ultimo ingresado
	List<Rating> findAllRatingByShowId(Long id); // meteodo para encontrar todos los rating de ese show
	Rating findRatingByUserId(Long id);//metodo para encontrar todos los rating del usuario
}
