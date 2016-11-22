package pl.lodz.p.iis.ppkwu.reddit.backend;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.SubredditImpl;

public class UrlGeneratorTest {

	@Test
	public void subredditIKategoria() throws MalformedURLException {
		URL url = UrlGenerator.subredditNewsUrl(new SubredditImpl("pics"), new CategoryImpl("", "top"));
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/r/pics/top")));
	}

}
