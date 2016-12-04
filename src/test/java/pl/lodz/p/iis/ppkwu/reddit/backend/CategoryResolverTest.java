package pl.lodz.p.iis.ppkwu.reddit.backend;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;

public class CategoryResolverTest {

	@Test
	public void gorace() throws Exception {
		CategoryImpl category = CategoryResolver.findByName("Gorące");
		assertThat(category.relativeUrl(), equalTo(""));
	}

	@Test
	public void wschodzace() throws Exception {
		CategoryImpl category = CategoryResolver.findByName("Wschodzące");
		assertThat(category.relativeUrl(), equalTo("rising"));
	}

}
