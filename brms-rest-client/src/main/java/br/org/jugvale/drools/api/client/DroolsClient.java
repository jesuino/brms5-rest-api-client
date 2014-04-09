package br.org.jugvale.drools.api.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
 * Boilerplate class for the real client. Here we will include validation,
 * authentication and a few more stuff.
 * 
 * The goal of this class is to make easier to move from a REST impl to another
 * kind of impl(new drools doesn't have the same REST API)
 * 
 * @author william
 * 
 */
public class DroolsClient {

	private String username, password, baseUrl;

	DroolsAPIService service;

	// we will keep them loaded like a cache
	private List<DroolsPackage> packages;
	private List<Category> categories;

	// TODO: Execeptions handling for 404 and 401

	public DroolsClient(String baseUrl, String username, String password) {
		this.baseUrl = baseUrl;
		this.username = username;
		this.password = password;
		createDroolsAPIService();
	}

	public List<DroolsPackage> getPackages(boolean refresh) {
		if (packages == null || refresh) {
			packages = service.getPackages();
		}
		return packages;
	}

	public List<DroolsPackage> getPackages() {
		return getPackages(false);
	}

	public List<Category> getCategories(boolean refresh) {
		if (categories == null || refresh) {
			categories = service.getCategories();
		}
		return categories;
	}

	public List<Category> getCategories() {
		return getCategories(false);
	}

	public Category getCategory(String name) {
		return service.getCategory(name);
	}

	// TODO caching of assets
	public List<Asset> getAssetsByPackage(String droolsPackageTitle) {
		DroolsPackage pkg = service.getPackage(droolsPackageTitle);
		return getAssetsByPackage(pkg);
	}

	public List<Asset> getAssetsByPackage(DroolsPackage droolsPackage) {
		return service.getAssetsByPackage(droolsPackage);
	}

	public List<Asset> getAssetsByCategory(Category category) {
		return service.getAssetsByCategory(category);
	}

	public List<Asset> getAssetsByCategory(String categoryName) {
		Category cat = service.getCategory(categoryName);
		return getAssetsByCategory(cat);
	}

	public Asset getAsset(String pkgName, String assetName) {
		return service.getAsset(pkgName, assetName);
	}

	public DroolsPackage getPackage(String name) {
		return service.getPackage(name);
	}

	public DroolsPackage createOrUpdate(DroolsPackage droolsPackage) {
		return service.createOrUpdatePackage(droolsPackage);
	}

	public Asset createOrUpdate(String pkgTitle, String title, String summary) {
		return service.createAsset(pkgTitle, title, summary);
	}

	public void removePackage(String title) {
		service.removePackage(title);
	}

	public Asset updateAsset(String pkgTitle, Asset asset) {
		return service.updateAsset(pkgTitle, asset);
	}

	public void removeAsset(String pkgTitle, String assetName) {
		service.removeAsset(pkgTitle, assetName);
	}

	public String updateAssetSource(String pkgTitle, String assetName,
			String newSourceCode) {
		return service.updateAssetSource(pkgTitle, assetName, newSourceCode);
	}
	
	public Asset createAsset(String jarFilePojoUrl, String pkgtitle, String assetName){
		Path path = Paths.get(jarFilePojoUrl);
		try {
			byte[] content = Files.readAllBytes(path);
			return service.createAsset(pkgtitle, content, assetName);
		} catch (IOException e) {
			throw new RuntimeException("Error while reading file: "+jarFilePojoUrl);
		}
	}

	public String getSourceCode(String pkgTitle, String assetName) {
		return service.getSourceCode(pkgTitle, assetName);
	}

	private void createDroolsAPIService() {
		Credentials credentials = new UsernamePasswordCredentials(username,
				password);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		// TODO: Check host from AuthScope. Is this right?
		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope("localhost", 8080), credentials);
		service = new DroolsAPIServiceImpl(baseUrl,
				new ApacheHttpClient4Engine(httpClient));
	}
}