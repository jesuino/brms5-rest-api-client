package br.org.jugvale.drools.api.client.service;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ClientHttpEngine;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import br.org.jugvale.drools.api.client.model.Asset;
import br.org.jugvale.drools.api.client.model.Category;
import br.org.jugvale.drools.api.client.model.DroolsPackage;

/**
 * 
 * The class that actually deals with HTTP Requests to communicate
 * 
 * @author wsiqueir
 * 
 */
public class DroolsAPIServiceImpl implements DroolsAPIService {
	// TODO: use logging
	
	ResteasyClient client;

	private final String MEDIA_TYPE = MediaType.APPLICATION_XML;	
	
	private final String REST_CONTEXT = "rest";		
	private final String PACKAGES_URI = "packages";
	private final String CATEGORIES_URI = "categories";
	
	private String baseUrl;

	public DroolsAPIServiceImpl(String baseUrl,
			ClientHttpEngine clientHttpEngine) {
		this.baseUrl = baseUrl;
		client = new ResteasyClientBuilder().httpEngine(clientHttpEngine)
				.build();
	}

	public List<DroolsPackage> getPackages() {
		String url = getCompleteUrl(PACKAGES_URI);
		System.out.printf("Retrieving packages from %s\n", url);		
		return client
				.target(url)
				.request(MEDIA_TYPE)				
				.get(new GenericType<List<DroolsPackage>>(){});		
	}
	public DroolsPackage getPackage(String name) {
		String url = getCompleteUrl(PACKAGES_URI, name);
		System.out.printf("Retrieving package from %s\n", url);		
		return client
				.target(url)
				.request(MEDIA_TYPE)
				.get(DroolsPackage.class);			
	}

	public List<Category> getCategories() {
		String url = getCompleteUrl(CATEGORIES_URI);
		System.out.printf("Retrieving categories from %s\n", url);			
		return client
				.target(url)
				.request(MEDIA_TYPE)				
				.get(new GenericType<List<Category>>(){});
	}

	public Category getCategory(String name) {
		String url = getCompleteUrl(CATEGORIES_URI, name);
		System.out.printf("Retrieving category from %s\n", url);		
		return client
				.target(url)
				.request(MEDIA_TYPE)
				.get(Category.class);		
	}
	public List<Asset> getAssetsByPackage(Package brmsPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Asset> getAssetsByCategory(Category brmsPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	public DroolsPackage createOrUpdate(DroolsPackage droolsPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	public Category createOrUpdate(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	public Asset createOrUpdate(Asset asset) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getCompleteUrl(String...resourceUri){
		UriBuilder finalUri = UriBuilder.fromPath(baseUrl).path(REST_CONTEXT);		
		for (int i = 0; i < resourceUri.length; i++) {
			finalUri.path(resourceUri[i]);
		}
		return finalUri.build().toString();		
	}



}
