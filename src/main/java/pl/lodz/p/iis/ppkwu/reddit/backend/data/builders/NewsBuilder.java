package pl.lodz.p.iis.ppkwu.reddit.backend.data.builders;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import pl.lodz.p.iis.ppkwu.reddit.backend.data.NewsImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.UserImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;

public class NewsBuilder implements Builder<NewsImpl> {

	private String title;
	private UserImpl author;
	private Optional<URL> thumbnailUrl = Optional.empty();

	public NewsBuilder withTitle(String title) {
		this.title = Objects.requireNonNull(title);
		return this;
	}

	public NewsBuilder withAuthor(UserImpl author) {
		this.author = Objects.requireNonNull(author);
		return this;
	}

	public NewsBuilder withThumbnailUrl(Optional<URL> thumbnailUrl) {
		this.thumbnailUrl = Objects.requireNonNull(thumbnailUrl);
		return this;
	}

	public NewsBuilder withThumbnailUrl(URL thumbnailUrl) {
		return withThumbnailUrl(Optional.ofNullable(thumbnailUrl));
	}

	@Override
	public NewsImpl build() {
		return new NewsImpl(title, author, thumbnailUrl);
	}

}
