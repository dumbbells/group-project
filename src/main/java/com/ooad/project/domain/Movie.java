package com.ooad.project.domain;

import java.util.List;

public class Movie {
	private List<String> actors;
	private String awards;
	private String contentRating;
	private String contentType;
	private String country;
	private List<String> directors;
	private List<String> genres;
	private String id;
	private Double imdbRating;
	private String language;
	private Double metascore;
	private String plot;
	private String releaseDate;
	private List<Review> reviews;
	private String runTime;
	private String title;
	private List<String> writers;

	public List<String> getActors() {
		return actors;
	}

	public String getAwards() {
		return awards;
	}

	public String getContentRating() {
		return contentRating;
	}

	public String getContentType() {
		return contentType;
	}

	public String getCountry() {
		return country;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public List<String> getGenres() {
		return genres;
	}

	public String getId() {
		return id;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public String getLanguage() {
		return language;
	}

	public Double getMetascore() {
		return metascore;
	}

	public String getPlot() {
		return plot;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public String getRunTime() {
		return runTime;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getWriters() {
		return writers;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setMetascore(Double metascore) {
		this.metascore = metascore;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriters(List<String> writers) {
		this.writers = writers;
	}

}
