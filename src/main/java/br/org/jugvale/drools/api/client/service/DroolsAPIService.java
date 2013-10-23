package br.org.jugvale.drools.api.client.service;

import java.util.List;

import br.org.jugvale.drools.api.client.model.Asset;
import br.org.jugvale.drools.api.client.model.Category;
import br.org.jugvale.drools.api.client.model.DroolsPackage;

public interface DroolsAPIService {

	public List<DroolsPackage> getPackages();
	public DroolsPackage getPackage(String name);	
	public List<Category> getCategories();
	public Category getCategory(String name);
	public List<Asset> getAssetsByPackage(DroolsPackage droolsPackage);
	public List<Asset> getAssetsByCategory(Category category);
    public Asset getAsset(String packageName, String assetName);
	
	public DroolsPackage createOrUpdate(DroolsPackage droolsPackage);
	public Category createOrUpdate(Category category);
	public Asset createOrUpdate(Asset asset);
	
	// TODO: Work in methods to remove resources
	
}
