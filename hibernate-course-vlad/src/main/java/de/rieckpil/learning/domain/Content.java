package de.rieckpil.learning.domain;

import java.util.List;

public class Content {

	private String mainContent;
	private String header;
	private List<String> tags;

	public Content() {

	}

	public Content(String mainContent, String header, List<String> tags) {
		super();
		this.mainContent = mainContent;
		this.header = header;
		this.tags = tags;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
