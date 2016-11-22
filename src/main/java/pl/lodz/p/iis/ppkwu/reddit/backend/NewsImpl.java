package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.net.URL;
import java.util.Optional;

import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.User;

public class NewsImpl implements News {

	private String title;
	private UserImpl author;
	private Optional<URL> thumbnailUrl;

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
	public User author() {

		return author;
	}

	@Override
	public Optional<URL> thumbnailUrl() {

		return thumbnailUrl;
	}

}
