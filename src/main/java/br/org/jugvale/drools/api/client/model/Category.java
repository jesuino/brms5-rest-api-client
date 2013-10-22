package br.org.jugvale.drools.api.client.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Category {
	private String path;
	private String refLink;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRefLink() {
		return refLink;
	}

	public void setRefLink(String refLink) {
		this.refLink = refLink;
	}
	
	@Override
	public String toString() {
		
		return "["+ path+","+ refLink+"]";
	}
}
