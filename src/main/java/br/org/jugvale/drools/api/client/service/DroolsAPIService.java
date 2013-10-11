package br.org.jugvale.drools.api.client.service;

import java.util.List;

import br.org.jugvale.drools.api.client.model.Asset;
import br.org.jugvale.drools.api.client.model.Category;
import br.org.jugvale.drools.api.client.model.DroolsPackage;

public interface DroolsAPIService {

	public List<DroolsPackage> getPackages();	
	public List<Category> getCategories();
	public List<Asset> getAssetsByPackage(Package brmsPackage);
	public List<Asset> getAssetsByCategory(Category brmsPackage);
	
	public DroolsPackage createOrUpdate(DroolsPackage droolsPackage);
	public Category createOrUpdate(Category category);
	public Asset createOrUpdate(Asset asset);
	
	// TODO: Work in methods to remove resources
	
}
