package br.org.jugvale.brms5.api.client.model;

import java.util.List;

public class Package {
	private List<String> assets;
	private String checkInComment;
	private String description;
	private Metadata metadata;
	private String sourceLink;
	private String title;
	private int version;
	
	public List<String> getAssets() {
		return assets;
	}
	public void setAssets(List<String> assets) {
		this.assets = assets;
	}
	public String getCheckInComment() {
		return checkInComment;
	}
	public void setCheckInComment(String checkInComment) {
		this.checkInComment = checkInComment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public String getSourceLink() {
		return sourceLink;
	}
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}	
}
