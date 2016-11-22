package pl.lodz.p.iis.ppkwu.reddit.backend;

import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;

public class SubredditImpl implements Subreddit {

	private final String title;

	public SubredditImpl(String title) {
		this.title = title;
	}

	@Override
	public String title() {
		return title;
	}

}
