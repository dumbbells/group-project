package com.ooad.project.web;

import com.ooad.project.domain.Movie;
import com.ooad.project.service.OMDBApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
	private final OMDBApiService omdbApiService;

	@Autowired
	public MovieController(OMDBApiService omdbApiService) {
		this.omdbApiService = omdbApiService;
	}

	@PostMapping
	public Movie search(@RequestBody Movie movie) {
		return omdbApiService.process(movie);
	}
}
