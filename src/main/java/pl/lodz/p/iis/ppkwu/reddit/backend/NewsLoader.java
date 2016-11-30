package pl.lodz.p.iis.ppkwu.reddit.backend;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.UserImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.NewsBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.PageBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.UserBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.StatusException;

public class NewsLoader extends AbstractLoader<Page<News>, PageBuilder<News>> {

	public NewsLoader(URL url) {
		super(url, new PageBuilder<News>());
		checkInvocation();
	}

	protected void extract(Document document) throws StatusException {
		Elements news = getNewsFromDocument(document);
		if (null != news)
			for (Element newsElement : news) {

				NewsBuilder newsBuilder = new NewsBuilder();

				String title = getTitle(newsElement);
				newsBuilder.withTitle(title);

				UserImpl author = getAuthor(newsElement);
				newsBuilder.withAuthor(author);

				Optional<URL> url = getThumbnailUrl(newsElement);
				newsBuilder.withThumbnailUrl(url);

				contentBuilder.addEntry(newsBuilder.build());
			}

	}

	private Elements getNewsFromDocument(Document document) {
		return document.select("div.thing, div.search-result-link");
	}

	private Optional<URL> getThumbnailUrl(Element newsElement) {
		Optional<URL> thumbnailUrl = Optional.empty();
		Elements thumbnailElements = newsElement.select("a.thumbnail img");
		if (!thumbnailElements.isEmpty()) {
			try {
				URL url = new URL(thumbnailElements.first().absUrl("src"));
				thumbnailUrl = Optional.of(url);
			} catch (MalformedURLException e) {
			}
		}
		return thumbnailUrl;
	}

	private String getTitle(Element newsElement) throws StatusException {
		Elements titleElements = newsElement.select("a.title, a.search-title");
		if (titleElements.isEmpty()) {
			throw new StatusException(ResultStatus.DATA_ERROR, "no title");
		}
		return titleElements.first().text();
	}

	private UserImpl getAuthor(Element newsElement) throws StatusException {
		Elements authorElements = newsElement.select("a.author");
		if (authorElements.isEmpty()) {
			throw new StatusException(ResultStatus.DATA_ERROR, "no author");
		}
		UserBuilder userBuilder = new UserBuilder();
		userBuilder.withLogin(authorElements.first().text());
		return userBuilder.build();
	}

}
