package br.org.jugvale.drools.api.client.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlRootElement(name="package")
public class DroolsPackage {
	private List<String> assets;
	private String checkInComment;
	private String description;
	private PackageMetadata metadata;
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

	public PackageMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(PackageMetadata metadata) {
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
	
	@Override
	public String toString() {		
		return ToStringBuilder.reflectionToString(this);
	}
	
	@XmlRootElement(name="metadata")
	public static class PackageMetadata {

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
}
