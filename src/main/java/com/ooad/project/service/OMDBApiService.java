package com.ooad.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooad.project.domain.Movie;
import com.ooad.project.domain.Review;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OMDBApiService {
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Calls the OMDB API service to retrieve movie information based on the given <code>Movie</code> object
	 *
	 * @param movie Movie to request information for
	 * @return Full movie information
	 */
	public Movie process(Movie movie) {
		CloseableHttpResponse httpResponse = null;
		Movie fullMovie = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		// adapt to request
		HttpGet httpGet = adaptRequest(movie);

		try {
			// call OMDB API
			httpResponse = httpClient.execute(httpGet);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		// adapt from response
		fullMovie = adaptResponse(httpResponse);

		return fullMovie;
	}

	/**
	 * Creates an Http Get Request  to retrieve movie information
	 *
	 * @param movie Movie to create request for
	 * @return Http Get Request for OMDB API
	 */
	private HttpGet adaptRequest(Movie movie) {
		HttpGet httpGet = null;

		try {
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("www.omdbapi.com")
					.setParameter("i", movie.getId())
					.setParameter("t", movie.getTitle())
					.setParameter("tomatoes", Boolean.TRUE.toString())
					.build();

			httpGet = new HttpGet(uri);
		} catch (URISyntaxException uriSyntaxException) {
			uriSyntaxException.printStackTrace();
		}

		return httpGet;
	}

	/**
	 * Adapts the <code>httpResponse</code> to a <code>Movie</code>
	 *
	 * @param httpResponse OMDB API response to parse
	 * @return Full Movie retrieved from OMDB API
	 */
	private Movie adaptResponse(HttpResponse httpResponse) {
		Movie movie = new Movie();

		if (null != httpResponse) {
			try {
				JsonNode rootNode = objectMapper.readTree(EntityUtils.toString(httpResponse.getEntity()));

				movie.setActors(mapStringList(rootNode.at("/Actors")));
				movie.setAwards(rootNode.at("/Awards").asText());
				movie.setContentRating(rootNode.at("/Rated").asText());
				movie.setContentType(rootNode.at("/Type").asText());
				movie.setCountry(rootNode.at("/Country").asText());
				movie.setDirectors(mapStringList(rootNode.at("/Director")));
				movie.setGenres(mapStringList(rootNode.at("/Genre")));
				movie.setId(rootNode.at("/imdbID").asText());
				movie.setImdbRating(rootNode.at("/imdbRating").asDouble());
				movie.setLanguage(rootNode.at("/Language").asText());
				movie.setMetascore(rootNode.at("/Metascore").asDouble());
				movie.setPlot(rootNode.at("/Plot").asText());
				movie.setReleaseDate(rootNode.at("/Released").asText());
				movie.setReviews(mapReviews(rootNode.at("/Ratings")));
				movie.setRunTime(rootNode.at("/Runtime").asText());
				movie.setTitle(rootNode.at("/Title").asText());
				movie.setWriters(mapStringList(rootNode.at("/Writer")));
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

		return movie;
	}

	/**
	 * Maps the review values from the given <code>JsonNode</code> to
	 * a list of <code>Reviews</code>
	 *
	 * @param reviewsNode JsonNode to map review values from
	 */
	private List<Review> mapReviews(JsonNode reviewsNode) {
		List<Review> reviews = null;

		// make sure we have a review list
		if (reviewsNode.isArray()) {
			reviews = new ArrayList<>();

			// add all review information to list
			for (JsonNode reviewNode : reviewsNode) {
				Review review = new Review();
				review.setSource(reviewNode.at("/Source").asText());
				review.setScore(reviewNode.at("/Value").asText());

				reviews.add(review);
			}
		}

		return reviews;
	}

	/**
	 * Maps the actor values from the given <code>JsonNode</code> to
	 * a list of <code>Strings</code>
	 *
	 * @param node JsonNode to map values from
	 */
	private List<String> mapStringList(JsonNode node) {
		// convert node to string
		String stringList = node.asText();

		// convert string to list
		return new ArrayList<>(Arrays.asList(stringList.split(", ")));
	}
}
