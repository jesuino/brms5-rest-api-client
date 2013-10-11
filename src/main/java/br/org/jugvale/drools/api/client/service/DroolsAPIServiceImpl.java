package br.org.jugvale.drools.api.client.service;

import java.util.List;

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
	ResteasyClient client;

	public DroolsAPIServiceImpl(String baseUrl, ClientHttpEngine clientHttpEngine) {
		client = new ResteasyClientBuilder().httpEngine(clientHttpEngine)
				.build();
	}

	public List<DroolsPackage> getPackages() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return null;
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

}
