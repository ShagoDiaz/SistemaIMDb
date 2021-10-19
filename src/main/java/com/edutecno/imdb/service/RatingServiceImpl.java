package com.edutecno.imdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutecno.imdb.dao.RatingRepository;
import com.edutecno.imdb.model.Rating;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository ratingDao;

	public Rating findById(Long id) {
		return ratingDao.findById(id).get();
	}

	public List<Rating> findAll() {
		return ratingDao.findAll();
	}

	public void create(Rating rating) {
		ratingDao.save(rating);
	}

	public void update(Rating rating) {
		ratingDao.save(rating);
	}

	public void delete(Long id) {
		ratingDao.deleteById(id);
	}
}
