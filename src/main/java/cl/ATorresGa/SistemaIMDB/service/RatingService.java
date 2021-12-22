package cl.ATorresGa.SistemaIMDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ATorresGa.SistemaIMDB.model.Rating;
import cl.ATorresGa.SistemaIMDB.repository.RatingRepository;

@Service
public class RatingService {
	@Autowired
	private RatingRepository ratingRepository;// se instancia al repositorio de rating
	
	//create
	public void addRating(Rating rating) {
		ratingRepository.save(rating);// se agrega un rating a la bd
	}
	
	//read all
	public List<Rating> findAllRating(){
		return ratingRepository.findAll();// se buscan todos los rating
	}
	public List<Rating> findAllRatingByShowId(Long id){
		return ratingRepository.findAllRatingByShowId(id);//se buscan todos los rating que tengan la show id entregada
	}
	
	//read one
	public Rating findFirtsByOrderByDesc() {
		return ratingRepository.findFirstByOrderByIdDesc();// se busca al ultimo rating ingresado
	}
	public Rating findRatingByUserId(Long id) {
		return ratingRepository.findRatingByUserId(id);// se busca el rating que haya hecho un usuario
	}
	public Rating findRatingById(Long id) {
		return ratingRepository.findRatingById(id);// se busca el rating por su id
	}
}
