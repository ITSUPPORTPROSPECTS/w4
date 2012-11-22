package uk.ac.prospects.w4.repository;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

	public String findAllCourses(CourseSearchArgument argument){

		// Configuration
		ClientConfig clientConfig = new ClientConfig();
		LinkedHashSet<String> servers = new LinkedHashSet<String>();
		servers.add(server);
		clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);

		// Construct a new Jest client according to configuration via factory
		JestClientFactory factory = new JestClientFactory();
		factory.setClientConfig(clientConfig);
		JestClient client = factory.getObject();

		QueryBuilder queryBuilder = buildQuery(argument);

		GpSearch search = new GpSearch(queryBuilder);
		search.addIndex(index);
		search.addType(type);
		search.setMaxSearchResults(argument.getMaxResults());

		JestResult result = null;
		try {
			result = client.execute(search);
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

		return result.getJsonString();
	}

	private QueryBuilder buildQuery(CourseSearchArgument argument){
		StringBuilder sb = new StringBuilder();

		// Add the provider or get all courses that have a one
		sb.append("provid:" + (StringUtils.hasText(argument.getProviderId()) ? argument.getProviderId() : "*"));

		if (StringUtils.hasText(argument.getCourseTitle())){
			sb.append(" AND title:" +  argument.getCourseTitle());
		}
		if (StringUtils.hasText(argument.getProviderTitle())){
			sb.append(" AND provtitle:" + argument.getProviderTitle());
		}
		if (StringUtils.hasText(argument.getStartDate())){
			sb.append(" AND presentations.start:" + argument.getStartDate());
		}

		QueryBuilder queryBuilder = QueryBuilders.queryString(sb.toString());
		return queryBuilder;
	}
}
