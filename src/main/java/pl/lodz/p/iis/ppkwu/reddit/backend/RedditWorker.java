package pl.lodz.p.iis.ppkwu.reddit.backend;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import pl.lodz.p.iis.ppkwu.reddit.api.Callback;
import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;
import pl.lodz.p.iis.ppkwu.reddit.api.User;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.ResultBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.CallbackBinder;

class RedditWorker {

	private Executor callbackExecutor;

	RedditWorker(Executor callbackExecutor) {
		Objects.requireNonNull(callbackExecutor, "callbackExecutor");
		this.callbackExecutor = callbackExecutor;
		checkInvocation();
	}

	void loadCategoriesList(Callback<List<Category>> callback) {
		CategoryLoader categoryLoader = new CategoryLoader();
		Result<List<Category>> result = categoryLoader.load();
		runCallback(callback, result);
	}

	void loadSubredditNews(Subreddit subreddit, Category category, Callback<Page<News>> callback) {
		Callable<URL> callUrl = () -> UrlGenerator.subredditNewsUrl(subreddit, category);
		loadNewsFromUrl(callUrl, callback);
	}

	void loadUserNews(User user, Callback<Page<News>> callback) {
		Callable<URL> callUrl = () -> UrlGenerator.userNewsUrl(user);
		loadNewsFromUrl(callUrl, callback);
	}

	void loadNewsByKeywords(List<String> keywords, Callback<Page<News>> callback) {
		Callable<URL> callUrl = () -> UrlGenerator.newsByKeywordsUrl(keywords);
		loadNewsFromUrl(callUrl, callback);
	}

	void loadNewsFromUrl(Callable<URL> callUrl, Callback<Page<News>> callback) {
		try {
			URL url = callUrl.call();
			loadNewsFromUrl(url, callback);
		} catch (Exception e) {
			replyWithErrorStatus(callback, ResultStatus.CONNECTION_ERROR);
		}
	}

	void loadNewsFromUrl(URL url, Callback<Page<News>> callback) {
		NewsLoader newsLoader = new NewsLoader(url);
		Result<Page<News>> result = newsLoader.load();
		runCallback(callback, result);
	}

	private <R> void replyWithErrorStatus(Callback<R> callback, ResultStatus errorStatus) {
		Result<R> result = new ResultBuilder<R>().withStatus(errorStatus).build();
		runCallback(callback, result);
	}

	private <R> void runCallback(Callback<R> callback, Result<R> result) {
		try {
			CallbackBinder<R> binding = new CallbackBinder<>(callback, result);
			callbackExecutor.execute(binding);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
