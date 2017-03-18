package com.ooad.project.web;

import com.ooad.project.domain.Movie;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

//	@RequestMapping("/search/{id}")
//	public String search(@PathVariable String id) {
//		return id;
//	}

	@RequestMapping("/search")
	public Movie search(@RequestBody Movie movie) {
		return movie;
	}
}
