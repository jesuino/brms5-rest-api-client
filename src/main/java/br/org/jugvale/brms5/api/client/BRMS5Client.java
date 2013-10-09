package br.org.jugvale.brms5.api.client;

import java.util.List;

import br.org.jugvale.brms5.api.client.model.Asset;

public class BRMS5Client {
	
	private String baseUrl, username, password;
	
	public BRMS5Client(String baseUrl, String username, String password){
		this.baseUrl = baseUrl; this.username = username; this.password = password;
	} 
	
	public List<Package> getPackages(){		
		return null;
	}
	
	public List<Asset> getAssetsByPackage(Package brmsPackage){
		return null;
	}
	
	
	
}
