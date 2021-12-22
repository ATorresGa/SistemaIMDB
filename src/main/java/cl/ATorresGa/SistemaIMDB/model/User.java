package cl.ATorresGa.SistemaIMDB.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import lombok.Data;


@Entity
@Data
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id" , nullable = false , unique = true)
	private Long id;
	
	@Size(min=5 , message = "Username must be present")
	private String username;
	@Size(min=10 , message = "Email must be present and in a valid format")
	private String email;
	@Size(min=8 , message = "Password must be greater than 8 characters")
	private String password;
	@Transient
	private String passwordConfirmation;
	
	//relaciones 
	//1:n show
	@OneToMany(mappedBy = "creatorShow" , fetch = FetchType.LAZY)
	private List<Show> userShow;
	
	//1:n rating
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	private List<Rating> ratings;
	
	//roles
	@ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles" , joinColumns = @JoinColumn(name="user_id") , inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
}
