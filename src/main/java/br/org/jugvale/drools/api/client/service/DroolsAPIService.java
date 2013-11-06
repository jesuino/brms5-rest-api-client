package br.org.jugvale.drools.api.client.service;

import java.util.List;

import br.org.jugvale.drools.api.client.model.Asset;
import br.org.jugvale.drools.api.client.model.Category;
import br.org.jugvale.drools.api.client.model.DroolsPackage;

public interface DroolsAPIService {

	public List<DroolsPackage> getPackages();
	public DroolsPackage getPackage(String title);	
	public List<Category> getCategories();
	public Category getCategory(String name);
	public List<Asset> getAssetsByPackage(DroolsPackage droolsPackage);
	public List<Asset> getAssetsByCategory(Category category);
    public Asset getAsset(String packageName, String assetName);
	
	public DroolsPackage createOrUpdatePackage(DroolsPackage droolsPackage);
	
	public Asset createAsset(String packageTitle, byte[] content, String assetName);
	public Asset createAsset(String packageTitle, String title, String summary);
	public Asset updateAsset(String packageTitle, Asset asset);

	
	public Asset updateAssetSource(String pkgName, String assetName, String newSourceCode);
	
	public void removePackage(String title);
	public void removeAsset(String pkgTitle, String assetName);
	
	
	// TODO: Add good documentation to each interface method
	
}
