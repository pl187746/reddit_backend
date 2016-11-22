package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import pl.lodz.p.iis.ppkwu.reddit.api.Callback;
import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.Reddit;
import pl.lodz.p.iis.ppkwu.reddit.api.Subreddit;
import pl.lodz.p.iis.ppkwu.reddit.api.User;

public class RedditImpl implements Reddit {

	private Executor callbackExecutor;

	public RedditImpl(Executor callbackExecutor) {
		Objects.requireNonNull(callbackExecutor, "callbackExecutor");
		this.callbackExecutor = callbackExecutor;
	}

	@Override
	public void loadCategoriesList(Callback<List<Category>> callback) throws NullPointerException {
		Objects.requireNonNull(callback, "callback");
		// TODO Auto-generated method stub

	}

	@Override
	public void loadSubredditNews(Subreddit subreddit, Category category, Callback<Page<News>> callback)
			throws NullPointerException {
		Objects.requireNonNull(subreddit, "subreddit");
		Objects.requireNonNull(category, "category");
		Objects.requireNonNull(callback, "callback");
		// TODO Auto-generated method stub

	}

	@Override
	public void loadUserNews(User user, Callback<Page<News>> callback) throws NullPointerException {
		Objects.requireNonNull(user, "user");
		Objects.requireNonNull(callback, "callback");
		// TODO Auto-generated method stub

	}

	@Override
	public void loadNewsByKeywords(List<String> keywords, Callback<Page<News>> callback) throws NullPointerException {
		Objects.requireNonNull(keywords, "keywords");
		Objects.requireNonNull(callback, "callback");
		// TODO Auto-generated method stub

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

}
