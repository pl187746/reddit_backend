package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;
import pl.lodz.p.iis.ppkwu.reddit.api.User;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.InvalidNameException;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.UrlEncoder;

public class UrlGenerator {

	private static final URL baseUrl;
	private static final URL subredditsUrl;
	private static final URL usersUrl;
	private static final String userSubmittedUrlSuffix = "/submitted";
	private static final String searchPrefix = "search?q=";

	static {
		try {
			baseUrl = new URL("https://www.reddit.com/");
			subredditsUrl = new URL(baseUrl, "r/");
			usersUrl = new URL(baseUrl, "user/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private UrlGenerator() {
		throw new UnsupportedOperationException();
	}

	public static URL baseUrl() {
		return baseUrl;
	}

	static URL subredditNewsUrl(Subreddit subreddit, Category category)
			throws MalformedURLException, InvalidNameException {
		CategoryImpl catImpl = CategoryResolver.resolve(category);
		return subredditNewsUrl(subreddit, catImpl);
	}

	static URL subredditNewsUrl(Subreddit subreddit, CategoryImpl category) throws MalformedURLException {
		URL subUrl;
		if (subreddit.title().isEmpty()) {
			subUrl = baseUrl;
		} else {
			subUrl = new URL(subredditsUrl, UrlEncoder.encode(subreddit.title()) + "/");
		}
		return new URL(subUrl, category.relativeUrl());
	}

	static URL userNewsUrl(User user) throws MalformedURLException, InvalidNameException {
		if (user.login().isEmpty()) {
			throw new InvalidNameException("empty user name");
		}
		return new URL(usersUrl, UrlEncoder.encode(user.login()) + userSubmittedUrlSuffix);
	}

	static URL newsByKeywordsUrl(List<String> keywords) throws MalformedURLException {
		String joinedKeywords = joinKeywords(keywords);
		String query = searchPrefix + joinedKeywords;
		return new URL(baseUrl, query);
	}

	static String joinKeywords(List<String> keywords) {
		StringBuilder builder = new StringBuilder();
		keywords.stream().filter(k -> k != null && !k.isEmpty()).forEachOrdered(k -> {
			UrlEncoder.encode(k, builder);
			builder.append('+');
		});
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

}
