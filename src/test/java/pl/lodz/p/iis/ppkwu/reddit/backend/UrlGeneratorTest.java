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
	public void subredditIKategoria() throws Exception {
		URL url = UrlGenerator.subredditNewsUrl(new SubredditImpl("pics"), new CategoryImpl("", "top"));
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/r/pics/top")));
	}

	@Test
	public void uzytkownik() throws Exception {
		URL url = UrlGenerator.userNewsUrl(new UserImpl("qwerty"));
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/user/qwerty/submitted")));
	}

	@Test
	public void slowaKluczowe() throws Exception {
		List<String> keywords = Arrays.asList("qwerty", "uiop");
		URL url = UrlGenerator.newsByKeywordsUrl(keywords);
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/search?q=qwerty+uiop")));
	}

	@Test
	public void subredditZeZnakami() throws Exception {
		URL url = UrlGenerator.subredditNewsUrl(new SubredditImpl("ąćę"), new CategoryImpl("", "top"));
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/r/%C4%85%C4%87%C4%99/top")));
	}

	@Test
	public void uzytkownikZeZnakami() throws Exception {
		URL url = UrlGenerator.userNewsUrl(new UserImpl("ąćę"));
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/user/%C4%85%C4%87%C4%99/submitted")));
	}

	@Test
	public void slowaKluczoweZeZnakami() throws Exception {
		List<String> keywords = Arrays.asList("abc 123", "xyz+5");
		URL url = UrlGenerator.newsByKeywordsUrl(keywords);
		assertThat(url, equalTo(new URL(UrlGenerator.baseUrl(), "/search?q=abc%20123+xyz%2B5")));
	}

}
