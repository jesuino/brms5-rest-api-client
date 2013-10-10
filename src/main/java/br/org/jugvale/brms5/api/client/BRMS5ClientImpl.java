package br.org.jugvale.brms5.api.client;

import java.util.List;

import br.org.jugvale.brms5.api.client.model.Asset;
import br.org.jugvale.brms5.api.client.model.Category;

public class BRMS5ClientImpl implements BRMS5Client{
	
	private String baseUrl, username, password;
	
	public BRMS5ClientImpl(String baseUrl, String username, String password){
		this.baseUrl = baseUrl; this.username = username; this.password = password;
	} 
	
	public List<Package> getPackages(){		
		return null;
	}
	public List<Category> getCategories(){		
		return null;
	}
	public List<Asset> getAssetsByPackage(Package brmsPackage){
		return null;
	}
	public List<Asset> getAssetsByCategory(Category brmsPackage){
		return null;
	}
	
}
