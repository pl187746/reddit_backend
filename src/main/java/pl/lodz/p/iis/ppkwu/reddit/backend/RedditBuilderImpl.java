package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.Optional;
import java.util.concurrent.Executor;

import pl.lodz.p.iis.ppkwu.reddit.api.Reddit;
import pl.lodz.p.iis.ppkwu.reddit.api.RedditBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.SameThreadExecutor;

public class RedditBuilderImpl implements RedditBuilder, Builder<Reddit> {

	private Executor callbackExecutor;

	@Override
	public Reddit build() {
		Executor callbackExecutor = Optional.ofNullable(this.callbackExecutor).orElseGet(SameThreadExecutor::get);
		return new RedditImpl(callbackExecutor);
	}

	@Override
	public RedditBuilder withCallbackExecutor(Executor callbackExecutor) {
		this.callbackExecutor = callbackExecutor;
		return this;
	}

}
