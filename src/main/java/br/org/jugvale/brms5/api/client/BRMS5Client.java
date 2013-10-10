package br.org.jugvale.brms5.api.client;

import java.util.List;

import br.org.jugvale.brms5.api.client.model.Asset;
import br.org.jugvale.brms5.api.client.model.Category;

public interface BRMS5Client {

	public List<Package> getPackages();	
	public List<Category> getCategories();
	public List<Asset> getAssetsByPackage(Package brmsPackage);
	public List<Asset> getAssetsByCategory(Category brmsPackage);
}
