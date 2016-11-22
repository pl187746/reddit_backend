package pl.lodz.p.iis.ppkwu.reddit.backend.data.builders;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.backend.data.SubredditImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;

public class SubredditBuilder implements Builder<SubredditImpl> {

	private String title;

	public SubredditBuilder withTitle(String title) {
		this.title = Objects.requireNonNull(title);
		return this;
	}

	@Override
	public SubredditImpl build() {
		return new SubredditImpl(title);
	}

}
