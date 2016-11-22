package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;

import pl.lodz.p.iis.ppkwu.reddit.api.Callback;
import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;
import pl.lodz.p.iis.ppkwu.reddit.api.User;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.PageImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.ResultImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.CallbackBinder;

public class RedditWorker {

	private Executor callbackExecutor;

	public RedditWorker(Executor callbackExecutor) {
		Objects.requireNonNull(callbackExecutor, "callbackExecutor");
		this.callbackExecutor = callbackExecutor;
	}

	public void loadCategoriesList(Callback<List<Category>> callback) throws NullPointerException {
		fakeEmptyList(callback);
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
		Page<C> page = new PageImpl<>(Collections.emptyList());
		fakeResult(callback, page);
	}
	
	private <C> void fakeEmptyList(Callback<List<C>> callback) {
		List<C> list = Collections.emptyList();
		fakeResult(callback, list);
	}
	
	private <R> void fakeResult(Callback<R> callback, R content) {
		Result<R> result = new ResultImpl<>(ResultStatus.SUCCEEDED, Optional.of(content));
		runCallback(callback, result);
	}

	private <R> void runCallback(Callback<R> callback, Result<R> result) {
		CallbackBinder<R> binding = new CallbackBinder<>(callback, result);
		callbackExecutor.execute(binding);
	}

}
