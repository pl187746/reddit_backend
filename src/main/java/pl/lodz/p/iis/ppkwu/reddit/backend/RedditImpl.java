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
import pl.lodz.p.iis.ppkwu.reddit.api.Reddit;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;
import pl.lodz.p.iis.ppkwu.reddit.api.User;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.PageImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.ResultImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.SubredditImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.UserImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.CallbackBinder;

public class RedditImpl implements Reddit {

	private Executor callbackExecutor;

	public RedditImpl(Executor callbackExecutor) {
		Objects.requireNonNull(callbackExecutor, "callbackExecutor");
		this.callbackExecutor = callbackExecutor;
	}

	@Override
	public void loadCategoriesList(Callback<List<Category>> callback) throws NullPointerException {
		Objects.requireNonNull(callback, "callback");
		fakeEmptyList(callback);
	}

	@Override
	public void loadSubredditNews(Subreddit subreddit, Category category, Callback<Page<News>> callback)
			throws NullPointerException {
		Objects.requireNonNull(subreddit, "subreddit");
		Objects.requireNonNull(category, "category");
		Objects.requireNonNull(callback, "callback");
		fakeEmptyPage(callback);
	}

	@Override
	public void loadUserNews(User user, Callback<Page<News>> callback) throws NullPointerException {
		Objects.requireNonNull(user, "user");
		Objects.requireNonNull(callback, "callback");
		fakeEmptyPage(callback);
	}

	@Override
	public void loadNewsByKeywords(List<String> keywords, Callback<Page<News>> callback) throws NullPointerException {
		Objects.requireNonNull(keywords, "keywords");
		Objects.requireNonNull(callback, "callback");
		fakeEmptyPage(callback);
	}

	@Override
	public UserImpl userWithLogin(String login) {
		Objects.requireNonNull(login, "login");
		return new UserImpl(login);
	}

	@Override
	public SubredditImpl subredditWithName(String name) {
		Objects.requireNonNull(name, "name");
		return new SubredditImpl(name);
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
