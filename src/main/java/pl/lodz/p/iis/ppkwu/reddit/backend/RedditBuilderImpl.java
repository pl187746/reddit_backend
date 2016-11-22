package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.concurrent.Executor;

import pl.lodz.p.iis.ppkwu.reddit.api.Reddit;
import pl.lodz.p.iis.ppkwu.reddit.api.RedditBuilder;

public class RedditBuilderImpl implements RedditBuilder {

	private Executor callbackExecutor;

	@Override
	public Reddit build() {
		return new RedditImpl(callbackExecutor);
	}

	@Override
	public RedditBuilder withCallbackExecutor(Executor callbackExecutor) {
		this.callbackExecutor = callbackExecutor;
		return this;
	}

}
