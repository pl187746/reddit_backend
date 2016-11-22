package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import pl.lodz.p.iis.ppkwu.reddit.api.Callback;
import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;
import pl.lodz.p.iis.ppkwu.reddit.api.User;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.PageBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.ResultBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.CallbackBinder;

public class RedditWorker {

	private Executor callbackExecutor;

	public RedditWorker(Executor callbackExecutor) {
		Objects.requireNonNull(callbackExecutor, "callbackExecutor");
		this.callbackExecutor = callbackExecutor;
	}

	public void loadCategoriesList(Callback<List<Category>> callback) throws NullPointerException {
		Result<List<Category>> result = new ResultBuilder<List<Category>>()
				.withContent(FixedCategories.getList())
				.build();
		runCallback(callback, result);
	}

	public void loadSubredditNews(Subreddit subreddit, Category category, Callback<Page<News>> callback) {
		fakeEmptyPage(callback);
	}

	public void loadUserNews(User user, Callback<Page<News>> callback) {
		fakeEmptyPage(callback);
	}

	public void loadNewsByKeywords(List<String> keywords, Callback<Page<News>> callback) {
		fakeEmptyPage(callback);
	}

	private <C> void fakeEmptyPage(Callback<Page<C>> callback) {
		Page<C> page = new PageBuilder<C>().build();
		fakeResult(callback, page);
	}

	private <R> void fakeResult(Callback<R> callback, R content) {
		Result<R> result = new ResultBuilder<R>().withContent(content).build();
		runCallback(callback, result);
	}

	private <R> void runCallback(Callback<R> callback, Result<R> result) {
		CallbackBinder<R> binding = new CallbackBinder<>(callback, result);
		callbackExecutor.execute(binding);
	}

}
