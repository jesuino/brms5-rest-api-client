package br.org.jugvale.drools.api.client;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.org.jugvale.drools.api.client.model.Category;
import br.org.jugvale.drools.api.client.model.DroolsPackage;

public class DroolsClientTest {

	DroolsClient client;

	@Before
	public void setStuff() {
		client = new DroolsClient("http://localhost:8080/jboss-brms", "admin", "admin");
	}

	// TODO: Improve tests
	
	@Test
	public void testPackage(){
		DroolsPackage pkg = client.getPackage("mortgages");
		System.out.println(pkg);	
	}
	
	@Test
	public void testCategory(){
		Category cat = client.getCategory("Home Mortgage");
		System.out.println(cat);	
	}
	
	@Test
	public void testPackages() {
		List<DroolsPackage> packages = client.getPackages();	
		System.out.println("Available Packages: \n");		
		for (DroolsPackage droolsPackage : packages) {
			System.out.println("--");
			System.out.println(droolsPackage.getTitle());
			System.out.println(droolsPackage.getDescription());	
		}
	}	
	
	@Test
	public void testCategories(){
		List<Category> categories = client.getCategories();
		System.out.println("All categories: \n");
		for (Category cat: categories) {
			System.out.println(cat);
		}
	}
}
