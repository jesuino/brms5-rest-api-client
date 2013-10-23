package br.org.jugvale.drools.api.client.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlRootElement
public class Asset {
	private String binaryLink;
	private String checkInComment;
	private String description;
	private String refLink;
	private String sourceLink;
	private String type;
	private int version;
	private AssetMetadata metadata;

	public AssetMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(AssetMetadata metadata) {
		this.metadata = metadata;
	}

	public String getBinaryLink() {
		return binaryLink;
	}

	public void setBinaryLink(String binaryLink) {
		this.binaryLink = binaryLink;
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

	public String getRefLink() {
		return refLink;
	}

	public void setRefLink(String refLink) {
		this.refLink = refLink;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@XmlRootElement(name = "metadata")
	public static class AssetMetadata {
		private String binaryContentAttachmentFileName;
		private Date created;
		private boolean disabled;
		private String createdBy;
		// enum?
		private String format;
		private Date lastModified;
		private String note;
		private String title;
		private String uuid;

		public String getBinaryContentAttachmentFileName() {
			return binaryContentAttachmentFileName;
		}

		public void setBinaryContentAttachmentFileName(
				String binaryContentAttachmentFileName) {
			this.binaryContentAttachmentFileName = binaryContentAttachmentFileName;
		}

		public Date getCreated() {
			return created;
		}

		public void setCreated(Date created) {
			this.created = created;
		}

		public boolean isDisabled() {
			return disabled;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public Date getLastModified() {
			return lastModified;
		}

		public void setLastModified(Date lastModified) {
			this.lastModified = lastModified;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
