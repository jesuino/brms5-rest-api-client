package br.org.jugvale.drools.api.client;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.org.jugvale.drools.api.client.model.Asset;
import br.org.jugvale.drools.api.client.model.Category;
import br.org.jugvale.drools.api.client.model.DroolsPackage;

public class DroolsClientTest {

	DroolsClient client;

	// re-usable constants
	private final String PKG_TITLE = "mortgages";
	private final String CAT_PATH = "Home Mortgage/Test scenarios";

	@Before
	public void setStuff() {
		client = new DroolsClient("http://localhost:8080/jboss-brms", "admin",
				"admin");
	}

	// TODO: Improve tests

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
	public void testAssetsByPackage() {
		List<Asset> assets = client.getAssetsByPackage(PKG_TITLE);
		System.out.printf("Assets for package \"%s\":\n--", PKG_TITLE);
		for (Asset asset : assets) {
			System.out.println(asset);
		}
		System.out.println("---------------------------");

	}

	@Test
	public void testAssetsByCategory() {
		List<Asset> assets = client.getAssetsByCategory(CAT_PATH);
		System.out.printf("Assets for category \"%s\":\n--", CAT_PATH);
		for (Asset asset : assets) {
			System.out.println(asset);
		}
	}
}
