package com.edutecno.imdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edutecno.imdb.model.Show;

@Service
public interface ShowService {

	public Show findById(Long id);

	public List<Show> findAll();

	public void create(Show show);

	public void update(Show show);

	public void delete(Long id);
}
