package br.org.jugvale.drools.api.client;

import java.util.List;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

import br.org.jugvale.drools.api.client.model.Asset;
import br.org.jugvale.drools.api.client.model.Category;
import br.org.jugvale.drools.api.client.model.DroolsPackage;
import br.org.jugvale.drools.api.client.service.DroolsAPIService;
import br.org.jugvale.drools.api.client.service.DroolsAPIServiceImpl;

/**
 * Boilerplate class for the real client. Here we will include validation, authentication and a few more stuff
 * @author william
 *
 */
public class DroolsClient{
	
	private String username, password, baseUrl;	
	
	DroolsAPIService service;
	
	// we will keep them loaded like a cache
	private List<DroolsPackage> packages;
	private List<Category> categories;
	
	
	// TODO: Execeptions handling for 404 and 401
	
	public DroolsClient(String baseUrl, String username, String password){
		this.baseUrl = baseUrl; this.username = username; this.password = password;
		createDroolsAPIService();		
	}	

	public List<DroolsPackage> getPackages(boolean refresh){
		if(packages == null || refresh){
			packages = service.getPackages();
		}
		return packages;
	}
	
	public List<DroolsPackage> getPackages(){		
		return getPackages(false);
	}	
	
	public List<Category> getCategories(boolean refresh){		
		if(categories == null || refresh){
			categories = service.getCategories();
		}
		return categories;
	}

	public List<Category> getCategories(){		
		return getCategories(false);
	}	
	
	// TODO caching of assets
	public List<Asset> getAssetsByPackage(Package brmsPackage){
		return null;
	}
	
	public List<Asset> getAssetsByCategory(Category brmsPackage){
		return null;
	}
	
	public DroolsPackage getPackage(String name) {		
		return service.getPackage(name);
	}
	
	private void createDroolsAPIService() {
		Credentials credentials = new UsernamePasswordCredentials(username, password);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		// TODO: Check host from AuthScope. Is this right?
		httpClient.getCredentialsProvider().setCredentials(new AuthScope("localhost", 8080), credentials);
		service  = new DroolsAPIServiceImpl(baseUrl, new ApacheHttpClient4Engine(httpClient));		
	}

}