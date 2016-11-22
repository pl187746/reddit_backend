package pl.lodz.p.iis.ppkwu.reddit.backend;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.SubredditImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.UserImpl;

public class UrlGeneratorTest {

	@Test
	public void subredditIKategoria() throws MalformedURLException {
		URL url = UrlGenerator.subredditNewsUrl(new SubredditImpl("pics"), new CategoryImpl("", "top"));
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/r/pics/top")));
	}

	@Test
	public void uzytkownik() throws MalformedURLException {
		URL url = UrlGenerator.userNewsUrl(new UserImpl("qwerty"));
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/user/qwerty/submitted")));
	}

	@Test
	public void slowaKluczowe() throws MalformedURLException {
		List<String> keywords = Arrays.asList("qwerty", "uiop");
		URL url = UrlGenerator.newsByKeywordsUrl(keywords);
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/search?q=qwerty+uiop")));
	}

}
