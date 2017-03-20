package com.ooad.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooad.project.domain.Movie;
import org.apache.http.HttpClientConnection;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class OMDBApiService {
	@Autowired
	private BasicHttpClientConnectionManager connectionManager;
	@Autowired
	private HttpClientContext context;
	@Autowired
	private HttpRoute httpRoute;

	public Movie callOMDBApi(Movie movie) {
		HttpClientConnection connection = null;
		ConnectionRequest request = connectionManager.requestConnection(httpRoute, null);
		Movie fullMovie = new Movie();

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = createRequest(movie);
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			String response = EntityUtils.toString(httpResponse.getEntity());

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(response);

			fullMovie.setTitle(rootNode.at("/Title").asText());
			fullMovie.setId(rootNode.at("/imdbID").asText());
			fullMovie.setReleaseDate(rootNode.at("/Released").asText());

//			// wait for up to 10 seconds
//			connection = request.get(10, TimeUnit.SECONDS);
//
//			// check if the connection is open
//			if (!connection.isOpen()) {
//				// establish connection based on its route info
//				connectionManager.connect(connection, httpRoute, 1000, context);
//				// and mark it as route complete
//				connectionManager.routeComplete(connection, httpRoute, context);
//			}
//
//			// call service
//			// TODO: implement service call
//			HttpGet httpGet = createRequest("Rogue One");
//			System.out.println(httpGet);
//
//			if (null != httpGet) {
//				connection.sendRequestHeader(httpGet);
//
//				HttpResponse httpResponse = connection.receiveResponseHeader();
//				response = EntityUtils.toString(httpResponse.getEntity());
//				System.out.println(response);
//			}
		} catch (ConnectionPoolTimeoutException connectionPoolTimeoutException) {
			connectionPoolTimeoutException.printStackTrace();
		//} catch (HttpException httpException) {
		//	httpException.printStackTrace();
		} catch (UnknownHostException unknownHostException) {
			System.out.println(unknownHostException);
			//} catch (InterruptedException | ExecutionException | IOException ex) {
		} catch(IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// release resources
			//connectionManager.releaseConnection(connection, null, 1, TimeUnit.MINUTES);
		}

		return fullMovie;
	}

	private HttpGet createRequest(Movie movie) {
		HttpGet httpGet = null;

		try {
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("www.omdbapi.com")
					.setParameter("i", movie.getId())
					.setParameter("t", movie.getTitle())
					.build();

			httpGet = new HttpGet(uri);
		} catch (URISyntaxException uriSyntaxException) {
			uriSyntaxException.printStackTrace();
		}

		return httpGet;
	}
}
