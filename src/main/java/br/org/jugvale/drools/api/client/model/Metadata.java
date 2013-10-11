package br.org.jugvale.drools.api.client.model;

import java.util.Date;

public class Metadata {

	private boolean achived;
	private Date created;
	private Date lastModified;
	private String lastContributor;	
	private String uuid;
	private String state;
	public boolean isAchived() {
		return achived;
	}
	public void setAchived(boolean achived) {
		this.achived = achived;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getLastContributor() {
		return lastContributor;
	}
	public void setLastContributor(String lastContributor) {
		this.lastContributor = lastContributor;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	} 	
}
