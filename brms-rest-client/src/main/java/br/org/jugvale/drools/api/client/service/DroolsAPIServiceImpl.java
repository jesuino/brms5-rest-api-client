package br.org.jugvale.drools.api.client.service;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.plugins.providers.atom.Entry;
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
	private static String ASSET_SOURCE_URI = "source";
	
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
		Response r = client
						.target(url)
						.request(MEDIA_TYPE)
						.buildGet().invoke();
		return getEntityFromResponse(r,Asset.class);
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
	public Asset createAsset(String packageTitle, String title, String summary) {
		// Basic stuff to create an Asset
		Entry entry = new Entry();
		entry.setTitle(title);
		entry.setSummary(summary);        
        Entity<Entry> entryEntity = Entity.entity(entry, MediaType.APPLICATION_ATOM_XML);
		String url = getCompleteUrl(PACKAGES_URI, packageTitle, ASSETS_URI);
		Response r = client
			.target(url)
			.request(MediaType.APPLICATION_ATOM_XML)
			.post(entryEntity);
		System.out.println("response code: " + r.getStatus());
		r.close();
		return getAsset(packageTitle, title);
	}
	
	public Asset updateAsset(String packageTitle, Asset asset) {
		String title = asset.getMetadata().getTitle();
		String url = getCompleteUrl(PACKAGES_URI, packageTitle, ASSETS_URI, title);
		System.out.println(url);
		Asset updateAsset = null;	
		Entity<Asset> assetXmlEntity = Entity.xml(asset);
		Response r = 
		     client
				.target(url)
				.request(MediaType.APPLICATION_XML)
				.accept(MEDIA_TYPE)
				.put(assetXmlEntity);
		// TODO: deal with errors
		if(r.getStatus() == 204){
			updateAsset = getAsset(packageTitle, title);
		}
		r.close();
		return	updateAsset;
	}
	
	public void removePackage(String title) {
		System.out.printf("Removing package \"%s\" \n", title);
		client
		    .target(getCompleteUrl(PACKAGES_URI, title))
		    .request()
		    .delete();		
	}
	public void removeAsset(String pkgTitle, String assetName) {
		System.out.printf("Removing asset \"%s\" from package \"%s\" \n", assetName, pkgTitle);
		client
		    .target(getCompleteUrl(PACKAGES_URI, pkgTitle, ASSETS_URI, assetName))
		    .request()
		    .delete();			
	}
	
	public Asset createAsset(String pkgTitle, byte[] content,
			String assetName) {
		Entity<byte[]> contentEntity = Entity.entity(content, MediaType.APPLICATION_OCTET_STREAM);
		client
			.target(getCompleteUrl(PACKAGES_URI, pkgTitle, ASSETS_URI))
			.request(MediaType.APPLICATION_OCTET_STREAM)
			.accept(MediaType.APPLICATION_ATOM_XML)
			.header("Slug", assetName)
			.post(contentEntity);		
		return getAsset(pkgTitle, assetName);
	}
	
	public String updateAssetSource(String pkgTitle, String assetName,
			String newSourceCode) {
		Entity<String> sourceEntity = Entity.xml(newSourceCode);
		client
			.target(getCompleteUrl(PACKAGES_URI, pkgTitle, ASSETS_URI, assetName, ASSET_SOURCE_URI))
			.request(MEDIA_TYPE)
			.put(sourceEntity)
			.close();
		return getSourceCode(pkgTitle, assetName);
	}
	//TODO: Test this method
	public String getSourceCode(String pkgTitle, String assetName){
		String url = getCompleteUrl(PACKAGES_URI, pkgTitle, ASSETS_URI, assetName, ASSET_SOURCE_URI);
			Response r = client
					.target(url)
					.request()
					.accept(MediaType.TEXT_PLAIN)
					.get();	
		return getEntityFromResponse(r, String.class);
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
		System.out.println(responseCode);
		T entity;
		switch (responseCode) {
			case 404:
				entity =  null;
				break;
			case 200: case 204:
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
