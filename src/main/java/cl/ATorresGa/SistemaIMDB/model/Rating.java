package cl.ATorresGa.SistemaIMDB.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ratings")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "rating_id", nullable = false, unique = true)
	private Long id;
	@Max(5)
	@Min(1)
	private int rating;
	
	@Column(name = "show_id")
	private Long showId;
	
	public Rating() {
	}

// RELACIONES
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;



	@ManyToMany(mappedBy = "ratings")
	private List<Show> shows;


}
