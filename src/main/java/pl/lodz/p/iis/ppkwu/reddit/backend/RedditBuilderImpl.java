package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pl.lodz.p.iis.ppkwu.reddit.api.RedditBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.SameThreadExecutor;

public class RedditBuilderImpl implements RedditBuilder, Builder<RedditImpl> {

	private Executor callbackExecutor;
	private Executor workerExecutor;

	@Override
	public RedditImpl build() {
		Executor workerExecutor = Optional
				.ofNullable(this.workerExecutor)
				.orElseGet(Executors::newCachedThreadPool);
		Executor callbackExecutor = Optional
				.ofNullable(this.callbackExecutor)
				.orElseGet(SameThreadExecutor::get);
		RedditWorker worker = new RedditWorker(callbackExecutor);
		return new RedditImpl(worker, workerExecutor);
	}

	@Override
	public RedditBuilder withCallbackExecutor(Executor callbackExecutor) {
		this.callbackExecutor = callbackExecutor;
		return this;
	}

	public RedditBuilder withWorkerExecutor(Executor workerExecutor) {
		this.workerExecutor = workerExecutor;
		return this;
	}
}
