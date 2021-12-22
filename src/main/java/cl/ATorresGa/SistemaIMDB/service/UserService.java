package cl.ATorresGa.SistemaIMDB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.ATorresGa.SistemaIMDB.model.User;
import cl.ATorresGa.SistemaIMDB.repository.RoleRepository;
import cl.ATorresGa.SistemaIMDB.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository; //instancia de UserRepository para utilizar sus metodos
	
	@Autowired
	private RoleRepository roleRepository; //instancia de RoleRepository para utilizar sus metodos 
	
	@Autowired(required = false)
	private BCryptPasswordEncoder bCryptPasswordEncoder; //instanciacion de clase para poder encriptar la contraseña del usuario
	
	//create
	public void saveWithUserRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));// se le setea al user la clave que recibe por el objeto usuario
		user.setRoles(roleRepository.findByName("ROLE_USER")); // se le entrega el role de usuario al usuario
		userRepository.save(user);// se guarda el usuario en la bd
	}
	public void saveWithAdminRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));// se le setea al user la clave que recibe por el objeto usuario
		user.setRoles(roleRepository.findByName("ROLE_ADMIN")); // se le entrega el role de administrador al usuario
		userRepository.save(user);// se guarda al usuario en la bd
	}
	public void createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));//se setea al usuario la clave que recibe por el objeto y se encripta
		userRepository.save(user);
	}
	
	//read one 
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);// se retorna el usuario que se busca por el username
	}
	
	public User findUserByUserId(Long id) {
		Optional<User>optionalUser= userRepository.findById(id);// se busca al usuario por la id y se almcena en una variable
		if (optionalUser.isPresent()) {//se consulta si acaso usuario opcional tiene un usuario
			return optionalUser.get();//se retorna al usuario encontrado
		}else {
			return null;//en el caso de que no se encuentre al usuario de retorna null
		}
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);// se ejecuta el metodo del repositorio y se retorna el usuario encontrado
	}
	
	//read all
	public List<User> findAllUsers(){
		return userRepository.findAll();// se buscan todos los usuarios
	}
	
	//update
	public void updateUser(User user) {
		userRepository.save(user);//cuando se recibe un usuario con id este sobre escribe al usurio que tenga esa misma id
	}
	
	//delete
	public void deleteUser(Long id) {
		userRepository.deleteById(id);//se elimina el usuario que corresponda a la id que recibe
	}
}
