package uk.ac.prospects.w4.repository;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashSet;

/**
 * @author vasileiosl
 *         Date: 21/11/12
 *         Time: 14:07
 */
@Component
public class CourseRepository {

	private String server;
	private String index;
	private String type;

	public void setServer(String server) {
		this.server = server;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String findAllCourses(int maxResults){

		// Configuration
		ClientConfig clientConfig = new ClientConfig();
		LinkedHashSet<String> servers = new LinkedHashSet<String>();
		servers.add(server);
		clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);

		// Construct a new Jest client according to configuration via factory
		JestClientFactory factory = new JestClientFactory();
		factory.setClientConfig(clientConfig);
		JestClient client = factory.getObject();

		QueryBuilder queryBuilder = QueryBuilders.queryString("title:*");

		GpSearch search = new GpSearch(queryBuilder);
		search.addIndex(index);
		search.addType(type);
		search.setMaxSearchResults(maxResults);

		JestResult result = null;
		try {
			result = client.execute(search);
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

		return result.getJsonString();
	}
}
