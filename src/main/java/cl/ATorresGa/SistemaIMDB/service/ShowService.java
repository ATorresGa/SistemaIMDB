package cl.ATorresGa.SistemaIMDB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ATorresGa.SistemaIMDB.model.Show;
import cl.ATorresGa.SistemaIMDB.repository.ShowRepository;

@Service
public class ShowService {
	@Autowired
	private ShowRepository showRepository;// se instancia el repositorio de show
	
	//create 
	public void saveShow(Show show) {
		showRepository.save(show);// se guarda el show entregado en la bd
	}
	
	//update
	public void updateShow(Show show) {
		showRepository.save(show);//show al tener un id esta sobre escribe al ya existente
	}
	
	//delete
	public void deleteShow(Long id) {
		showRepository.deleteById(id);//se elimina el show al cual corresponda la id entregada
	}
	
	//find one
	public Show findById(Long id) {
		Optional<Show>optionalShow= showRepository.findById(id);// se busca el show y se almacena en una variable opcional
		if (optionalShow.isPresent()) {
			return optionalShow.get();// si la variable opcional tiene datos se retorna el que encontro
		}else {
			return null;// en caso contrario se retorna un null
		}
	}
	// FIND ALL
		public List<Show> findAllShows() {
			return showRepository.findAll();
		}
}
