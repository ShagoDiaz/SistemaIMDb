package com.edutecno.imdb.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id", nullable = false, unique = true)
	private Long id;

	@Size(min = 3, message = "Necesitas rellenar este campo")
	private String username;
	@Email(message = "Eso no parece una direccion de email")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Size(min = 8, message = "La contraseña debe tener minimo 8 caracteres")
	private String password;
	@Transient
	private String passwordConfirmation;

	@OneToMany(mappedBy = "creatorShow", fetch = FetchType.LAZY)
	List<Show> userShows;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	List<Rating> ratings;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

}
