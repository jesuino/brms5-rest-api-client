package br.org.jugvale.drools.api.client.service;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
	// TODO: add error handling
	ResteasyClient client;

	private final String MEDIA_TYPE = MediaType.APPLICATION_XML;	
	
	//TODO: change to URI templates?
	private final String REST_CONTEXT = "rest";		
	private final String PACKAGES_URI = "packages";
	private final String CATEGORIES_URI = "categories";
	private final String ASSETS_URI = "assets";
	
	// TODO: can we simplify the final URLs?
	private final String CATEGORIES_URL;
	private final String PACKAGES_URL;
	
	private String baseUrl;

	public DroolsAPIServiceImpl(String baseUrl,
			ClientHttpEngine clientHttpEngine) {
		this.baseUrl = baseUrl;
		client = new ResteasyClientBuilder().httpEngine(clientHttpEngine)
				.build();
		// initializing URL fields
		 CATEGORIES_URL = getCompleteUrl(CATEGORIES_URI);
		 PACKAGES_URL= getCompleteUrl(PACKAGES_URI);
	}

	public List<DroolsPackage> getPackages() {
		System.out.printf("Retrieving packages from %s\n", PACKAGES_URL);		
		return client
				.target(PACKAGES_URL)
				.request(MEDIA_TYPE)				
				.get(new GenericType<List<DroolsPackage>>(){});		
	}
	public DroolsPackage getPackage(String name) {
		String url = getCompleteUrl(PACKAGES_URI, name);
		System.out.printf("Retrieving package from %s\n", url);		
		Response r = client
				.target(url)
				.request(MEDIA_TYPE).buildGet().invoke();
		return getEntityFromResponse(r, DroolsPackage.class);			
	}

	public List<Category> getCategories() {
		System.out.printf("Retrieving categories from %s\n", CATEGORIES_URL);			
		return client
				.target(CATEGORIES_URL)
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
	public List<Asset> getAssetsByPackage(DroolsPackage droolsPackage) {
		//TODO: check nulability?
		String url = getCompleteUrl(PACKAGES_URI, droolsPackage.getTitle(),  ASSETS_URI);
		System.out.printf("Retrieving assets for package %s, from uri %s\n",droolsPackage.getTitle(), url);	
		return client
				.target(url)
				.request(MEDIA_TYPE)
				.get(new GenericType<List<Asset>>(){});	
	}

	public List<Asset> getAssetsByCategory(Category droolsCategory) {
		//TODO: check nulability?
		String url = getCompleteUrl(CATEGORIES_URI, droolsCategory.getPath(),  ASSETS_URI);
		System.out.printf("Retrieving assets for category \"%s\", from uri %s\n",droolsCategory.getPath(), url);
		return client
				.target(url)
				.request(MEDIA_TYPE)
				.get(new GenericType<List<Asset>>(){});	
	}

	public Asset getAsset(String packageName, String assetName) {
		String url = getCompleteUrl(PACKAGES_URI, packageName,  ASSETS_URI, assetName);
				System.out.printf("Retrieving asset \"%s\" of package \"%s\", from uri %s\n",assetName, packageName, url);
				return client
						.target(url)
						.request(MEDIA_TYPE)
						.get(Asset.class);
	}
	
	public DroolsPackage createOrUpdatePackage(DroolsPackage droolsPackage) {
		DroolsPackage updatedPkg = null;
		Entity<DroolsPackage> pkgEntity = Entity.xml(droolsPackage);
		String pkgTitle = droolsPackage.getTitle();
		if(getPackage(pkgTitle) != null){
			System.out.println("Updating package "+ droolsPackage.getTitle());
			Response r = client
					.target(getCompleteUrl(PACKAGES_URI, pkgTitle))
					.request(MEDIA_TYPE)
					.put(pkgEntity);
			// if updated, return newest version of the package
			if(r.getStatus() == 204){
				updatedPkg =  getPackage(pkgTitle);
			}
		}else{
			System.out.println("Adding package "+ droolsPackage.getTitle());
			updatedPkg = client
					.target(PACKAGES_URL)
					.request(MEDIA_TYPE)					
					.post(pkgEntity).readEntity(DroolsPackage.class);
		}
		return updatedPkg;
	}
	
	public void removePackage(String title) {
		System.out.printf("Removing package \"%s\" \n", title);
		client
		    .target(getCompleteUrl(PACKAGES_URI, title))
		    .request()
		    .delete();		
	}
	
	public Asset updateAsset(String packageName, Asset asset) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Asset createAsset(String packageTitle, byte[] content,
			String assetName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Asset updateAssetSource(String pkgName, String assetName,
			String newSourceCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Asset createOrUpdateAsset(String packageTitle, Asset asset) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void removeAsset(String pkgTitle, String assetName) {
		// TODO Auto-generated method stub
		
	}
	
	private String getCompleteUrl(String...resourceUri){
		UriBuilder finalUri = UriBuilder.fromPath(baseUrl).path(REST_CONTEXT);		
		for (int i = 0; i < resourceUri.length; i++) {
			finalUri.path(resourceUri[i]);
		}
		return finalUri.build().toString();		
	}

	private <T> T getEntityFromResponse(Response r, Class<T> clazz){
		int responseCode = r.getStatus();
		T entity;
		switch (responseCode) {
			case 404:
				entity =  null;
				break;
			case 200:
				entity =  r.readEntity(clazz);	
				break;
			default:
				// TODO: improve this exception
				throw new RuntimeException();
		}
		r.close();
		return entity;
	}
}
