package com.edutecno.imdb.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "show")
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "show_id", nullable = false, unique = true)
	private Long id;
	@Size(min = 1, message = "Necesitas rellenar este campo")
	private String showTitle;
	@Size(min = 1, message = "Necesitas rellenar este campo")
	private String showNetwork;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creatorShow;

	@OneToMany(mappedBy = "show", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Rating> ratings;

	@Transient
	private int avgRating;
}
