package com.ooad.project.web;

import com.ooad.project.domain.Movie;
import com.ooad.project.service.OMDBApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
	private final OMDBApiService omdbApiService;

	@Autowired
	public MovieController(OMDBApiService omdbApiService) {
		this.omdbApiService = omdbApiService;
	}

	@GetMapping("/id/{id}")
	public Movie getByIMDBId(@PathVariable String id) {
		Movie movie = new Movie();
		movie.setId(id);

		return omdbApiService.process(movie);
	}

	@GetMapping("/title/{title}")
	public Movie getByTitle(@PathVariable String title) {
		Movie movie = new Movie();
		movie.setTitle(title);

		return omdbApiService.process(movie);
	}
}
