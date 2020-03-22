package com.apple.twitter.integration.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class SearchInfo {

	@NotNull(message="Please provide a search text")
	private String searchText;
	
	
	@NotNull(message="Please provide the number of results")
	@Max(value=200)
	private int numResults;


	
	
	public String getSearchText() {
		return searchText;
	}


	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}


	public int getNumResults() {
		return numResults;
	}


	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}
	
	
}
