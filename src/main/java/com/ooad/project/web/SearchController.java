package com.ooad.project.web;

import com.ooad.project.domain.Movie;
import com.ooad.project.service.OMDBApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
	@Autowired
	OMDBApiService omdbApiService;

	@RequestMapping("/search")
	public Movie search(@RequestBody Movie movie) {
		Movie fullMovie = omdbApiService.callOMDBApi(movie);

		return fullMovie;
	}
}
