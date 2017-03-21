package com.ooad.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooad.project.domain.Movie;
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

public class OMDBApiService {
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Calls the OMDB API service to retrieve movie information based on the given <code>Movie</code> object
	 *
	 * @param movie Movie to request information for
	 *
	 * @return      Full movie information
	 */
	public Movie callOMDBApi(Movie movie) {
		CloseableHttpResponse httpResponse = null;
		Movie fullMovie = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		// adapt to request
		HttpGet httpGet = adaptRequest(movie);

		try {
			// call OMDB API
			httpResponse = httpClient.execute(httpGet);
		} catch(IOException ioException) {
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
	 *
	 * @return      Http Get Request for OMDB API
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
	 * @param httpResponse  OMDB API response to parse
	 *
	 * @return              Full Movie retrieved from OMDB API
	 */
	private Movie adaptResponse(HttpResponse httpResponse) {
		Movie movie = new Movie();

		if (null != httpResponse) {
			try {
				JsonNode rootNode = objectMapper.readTree(EntityUtils.toString(httpResponse.getEntity()));

				movie.setTitle(rootNode.at("/Title").asText());
				movie.setId(rootNode.at("/imdbID").asText());
				movie.setReleaseDate(rootNode.at("/Released").asText());
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

		return movie;
	}
}
