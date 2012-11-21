package uk.ac.prospects.w4.repository;

import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author vasileiosl
 *         Date: 21/11/12
 *         Time: 13:40
 */
public class GpSearch extends Search {
	private int maxSearchResults;

	public GpSearch() {
		super();
	}

	public GpSearch(String query) {
		super(query);
	}

	public GpSearch(QueryBuilder query) {
		super(query);
	}

	@Override
	public String getURI() {
		StringBuilder stringBuilder = new StringBuilder(super.getURI());
		stringBuilder.append("?size=" + maxSearchResults);
		return stringBuilder.toString();
	}

	public int getMaxSearchResults() {
		return maxSearchResults;
	}

	public void setMaxSearchResults(int maxSearchResults) {
		this.maxSearchResults = maxSearchResults;
	}
}
