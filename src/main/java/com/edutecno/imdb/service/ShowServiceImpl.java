package com.edutecno.imdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutecno.imdb.dao.ShowRepository;
import com.edutecno.imdb.model.Show;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowRepository showRepository;

	public Show findById(Long id) {
		Optional<Show> optionalShow = showRepository.findById(id);
		if (optionalShow.isPresent()) {
			return optionalShow.get();
		} else {
			return new Show();
		}
	}

	public List<Show> findAll() {
		return showRepository.findAll();
	}

	public void create(Show show) {
		showRepository.save(show);
	}

	public void update(Show show) {
		showRepository.save(show);
	}

	public void delete(Long id) {
		showRepository.deleteById(id);
	}

	private void avg() {
		
	}
}
