package br.org.jugvale.drools.api.client;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.org.jugvale.drools.api.client.model.Asset;
import br.org.jugvale.drools.api.client.model.Category;
import br.org.jugvale.drools.api.client.model.DroolsPackage;
import br.org.jugvale.drools.api.client.model.Asset.AssetMetadata;

public class DroolsClientTest {

	DroolsClient client;

	// re-usable constants
	private final String PKG_TITLE = "mortgages";
	private final String CAT_PATH = "Home Mortgage/Test scenarios";
	private final String ASSET_NAME = "drools";

	@Before
	public void setStuff() {
		client = new DroolsClient("http://localhost:8080/jboss-brms", "admin",
				"admin");
	}

	// TODO: Improve tests
/*
	@Test
	public void testPackage() {
		DroolsPackage pkg = client.getPackage(PKG_TITLE);
		System.out.println(pkg);
		System.out.println("---------------------------");
	}

	@Test
	public void testCategory() {
		Category cat = client.getCategory(CAT_PATH);
		System.out.println(cat);
		System.out.println("---------------------------");
	}

	@Test
	public void testPackages() {
		List<DroolsPackage> packages = client.getPackages();
		System.out.println("Available Packages: \n--");
		for (DroolsPackage droolsPackage : packages) {
			System.out
					.printf("Title: \"%s\", Description: \"%s\", Last Contributor: \"%s\"\n",
							droolsPackage.getTitle(), droolsPackage
									.getDescription(), droolsPackage
									.getMetadata().getLastContributor());
		}
		System.out.println("---------------------------");
	}

	@Test
	public void testCategories() {
		List<Category> categories = client.getCategories();
		System.out.println("All categories:\n--");
		for (Category cat : categories) {
			System.out.println(cat);
		}
		System.out.println("---------------------------");
	}

	@Test
	public void testAsset() {
		Asset asset = client.getAsset(PKG_TITLE, ASSET_NAME);
		System.out.printf("Asset \"%s\":\n--\n", ASSET_NAME);
		System.out.println(asset);
		System.out.println("---------------------------");
	}

	@Test
	public void testAssetsByPackage() {
		List<Asset> assets = client.getAssetsByPackage(PKG_TITLE);
		System.out.printf("Assets for package \"%s\":\n--\n", PKG_TITLE);
		for (Asset asset : assets) {
			System.out.println(asset);
		}
		System.out.println("---------------------------");
	}

	@Test
	public void testAssetsByCategory() {
		List<Asset> assets = client.getAssetsByCategory(CAT_PATH);
		System.out.printf("Assets for category \"%s\":\n--\n", CAT_PATH);
		for (Asset asset : assets) {
			System.out.println(asset);
		}
	}
	
	@Test
	public void testPackageManipulation(){
		System.out.println("----- Adding, updating and deleting a package -----");
		String title = "my_test_package";
		String newDesc = "Just testing packages adition";
		String updatedDesc = "Updated description";
		DroolsPackage pkg = new DroolsPackage();
		pkg.setTitle(title);
		pkg.setDescription(newDesc);
		DroolsPackage newPkg = client.createOrUpdate(pkg);
		assertEquals(title, newPkg.getTitle());
		newPkg.setDescription(updatedDesc);
		DroolsPackage updatedPkg = client.createOrUpdate(newPkg);
		assertEquals(updatedDesc, updatedPkg.getDescription());
		client.removePackage(title);
		DroolsPackage shouldBeNull = client.getPackage(title);
		assertNull(shouldBeNull);
	}*/
	
	
	@Test
	public void testAssetManipulation(){
		System.out.println("----- Adding, updating and deleting an Asset -----");
		String pkgTitle = "testing";
		DroolsPackage pkg = new DroolsPackage();
		pkg.setTitle(pkgTitle);
		pkg.setDescription("a description");
		String assetName = "assetName";
		String updatedDescription = "this is an updated description";
		// create a package for our tests
		client.createOrUpdate(pkg);
		System.out.println("Creating asset");
		Asset createdAsset = client.createOrUpdate(pkgTitle, assetName, "no need for summary");
		assertEquals(assetName, createdAsset.getMetadata().getTitle());
		createdAsset.setDescription(updatedDescription);
	    System.out.println("Updating asset");
		Asset updatedAsset = client.updateAsset(pkgTitle, createdAsset);
		assertEquals(updatedDescription, updatedAsset.getDescription());
		
		// now we will remove the things we created
		client.removeAsset(pkgTitle, assetName);
		// we make sure we removed the asset
		assertNull(client.getAsset(pkgTitle, assetName));
		// now we remove the test package :)
		client.removePackage(pkgTitle);}
}