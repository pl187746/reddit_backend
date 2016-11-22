package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.List;
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
		super();
		this.callbackExecutor = callbackExecutor;
	}

	@Override
	public void loadCategoriesList(Callback<List<Category>> callback) throws NullPointerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadSubredditNews(Subreddit subreddit, Category category, Callback<Page<News>> callback)
			throws NullPointerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadUserNews(User user, Callback<Page<News>> callback) throws NullPointerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadNewsByKeywords(List<String> keywords, Callback<Page<News>> callback) throws NullPointerException {
		// TODO Auto-generated method stub

	}

	@Override
	public UserImpl userWithLogin(String login) {
		return new UserImpl(login);
	}

	@Override
	public SubredditImpl subredditWithName(String name) {
		return new SubredditImpl(name);
	}

}
