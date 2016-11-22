package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;
import pl.lodz.p.iis.ppkwu.reddit.api.User;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;

public class UrlGenerator {

	private static final URL baseUrl;
	private static final URL subredditsUrl;
	private static final URL usersUrl;
	private static final String userSubmittedUrlSuffix = "/submitted";
	private static final String searchPrefix = "search?q=";

	static {
		try {
			baseUrl = new URL("https://reddit.com/");
			subredditsUrl = new URL(baseUrl, "r/");
			usersUrl = new URL(baseUrl, "user/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static URL baseUrl() {
		return baseUrl;
	}

	public static URL subredditNewsUrl(Subreddit subreddit, Category category) throws MalformedURLException {
		CategoryImpl catImpl = (CategoryImpl) category;
		return subredditNewsUrl(subreddit, catImpl);
	}

	public static URL subredditNewsUrl(Subreddit subreddit, CategoryImpl category) throws MalformedURLException {
		URL subUrl = new URL(subredditsUrl, subreddit.title() + "/");
		return new URL(subUrl, category.relativeUrl());
	}

	public static URL userNewsUrl(User user) throws MalformedURLException {
		URL userUrl = new URL(usersUrl, user.login());
		return new URL(userUrl, userSubmittedUrlSuffix);
	}

	public static URL newsByKeywordsUrl(List<String> keywords) throws MalformedURLException {
		String joinedKeywords = joinKeywords(keywords);
		String query = searchPrefix + joinedKeywords;
		return new URL(baseUrl, query);
	}

	public static String joinKeywords(List<String> keywords) {
		return String.join("+", (Iterable<String>) keywords.stream().filter(k -> k != null && !k.isEmpty())::iterator);
	}

}
