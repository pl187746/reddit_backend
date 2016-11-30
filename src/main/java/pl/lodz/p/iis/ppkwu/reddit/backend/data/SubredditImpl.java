package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;

public class SubredditImpl implements Subreddit {

	private final String title;

	public SubredditImpl(String title) {
		this.title = Objects.requireNonNull(title, "title");
		checkInvocation();
	}

	@Override
	public String title() {
		return title;
	}

}
