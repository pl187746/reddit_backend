package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import java.net.URL;
import java.util.Optional;

import pl.lodz.p.iis.ppkwu.reddit.api.News;

public class NewsImpl implements News {

	private final String title;
	private final UserImpl author;
	private final Optional<URL> thumbnailUrl;

	public NewsImpl(String title, UserImpl author, Optional<URL> thumbnailUrl) {
		this.title = title;
		this.author = author;
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public UserImpl author() {
		return author;
	}

	@Override
	public Optional<URL> thumbnailUrl() {
		return thumbnailUrl;
	}

}