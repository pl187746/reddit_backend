package pl.lodz.p.iis.ppkwu.reddit.backend;

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

public class RedditWorker {

	private Executor callbackExecutor;

	public RedditWorker(Executor callbackExecutor) {
		Objects.requireNonNull(callbackExecutor, "callbackExecutor");
		this.callbackExecutor = callbackExecutor;
	}

	public void loadCategoriesList(Callback<List<Category>> callback) throws NullPointerException {
		replyWithContent(callback, FixedCategories.getList());
	}

	public void loadSubredditNews(Subreddit subreddit, Category category, Callback<Page<News>> callback) {
		Callable<URL> callUrl = () -> UrlGenerator.subredditNewsUrl(subreddit, category);
		loadNewsFromUrl(callUrl, callback);
	}

	public void loadUserNews(User user, Callback<Page<News>> callback) {
		Callable<URL> callUrl = () -> UrlGenerator.userNewsUrl(user);
		loadNewsFromUrl(callUrl, callback);
	}

	public void loadNewsByKeywords(List<String> keywords, Callback<Page<News>> callback) {
		Callable<URL> callUrl = () -> UrlGenerator.newsByKeywordsUrl(keywords);
		loadNewsFromUrl(callUrl, callback);
	}

	public void loadNewsFromUrl(Callable<URL> callUrl, Callback<Page<News>> callback) {
		try {
			URL url = callUrl.call();
			loadNewsFromUrl(url, callback);
		} catch (Exception e) {
			replyWithErrorStatus(callback, ResultStatus.CONNECTION_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	public void loadNewsFromUrl(URL url, Callback<Page<News>> callback) {
		NewsLoader newsLoader = new NewsLoader(url);
		Result<Page<News>> result = (Result<Page<News>>) (Object) newsLoader.load();
		runCallback(callback, result);
	}

	private <R> void replyWithContent(Callback<R> callback, R content) {
		Result<R> result = new ResultBuilder<R>().withContent(content).build();
		runCallback(callback, result);
	}

	private <R> void replyWithErrorStatus(Callback<R> callback, ResultStatus errorStatus) {
		Result<R> result = new ResultBuilder<R>().withStatus(errorStatus).build();
		runCallback(callback, result);
	}

	private <R> void runCallback(Callback<R> callback, Result<R> result) {
		CallbackBinder<R> binding = new CallbackBinder<>(callback, result);
		callbackExecutor.execute(binding);
	}

}
