package com.edutecno.imdb.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.edutecno.imdb.model.Rating;

@Service
public interface RatingService {

	Rating findById(Long id);

	List<Rating> findAll();

	public void create(Rating rating);

	public void update(Rating rating);

	public void delete(Long id);

}
