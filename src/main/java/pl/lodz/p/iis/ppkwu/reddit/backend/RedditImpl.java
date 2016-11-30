package pl.lodz.p.iis.ppkwu.reddit.backend;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

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
import pl.lodz.p.iis.ppkwu.reddit.backend.data.SubredditImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.UserImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.SubredditBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.UserBuilder;

public class RedditImpl implements Reddit {

	private RedditWorker worker;
	private Executor workerExecutor;

	public RedditImpl(RedditWorker worker, Executor workerExecutor) {
		this.worker = Objects.requireNonNull(worker, "worker");
		this.workerExecutor = Objects.requireNonNull(workerExecutor, "workerExecutor");
		checkInvocation();
	}

	@Override
	public void loadCategoriesList(Callback<List<Category>> callback) throws NullPointerException {
		Objects.requireNonNull(callback, "callback");
		workerExecutor.execute(() -> {
			worker.loadCategoriesList(callback);
		});
	}

	@Override
	public void loadSubredditNews(Subreddit subreddit, Category category, Callback<Page<News>> callback)
			throws NullPointerException {
		Objects.requireNonNull(subreddit, "subreddit");
		Objects.requireNonNull(category, "category");
		Objects.requireNonNull(callback, "callback");
		workerExecutor.execute(() -> {
			worker.loadSubredditNews(subreddit, category, callback);
		});
	}

	@Override
	public void loadUserNews(User user, Callback<Page<News>> callback) throws NullPointerException {
		Objects.requireNonNull(user, "user");
		Objects.requireNonNull(callback, "callback");
		workerExecutor.execute(() -> {
			worker.loadUserNews(user, callback);
		});
	}

	@Override
	public void loadNewsByKeywords(List<String> keywords, Callback<Page<News>> callback) throws NullPointerException {
		Objects.requireNonNull(keywords, "keywords");
		Objects.requireNonNull(callback, "callback");
		workerExecutor.execute(() -> {
			worker.loadNewsByKeywords(keywords, callback);
		});
	}

	@Override
	public UserImpl userWithLogin(String login) {
		Objects.requireNonNull(login, "login");
		return new UserBuilder().withLogin(login).build();
	}

	@Override
	public SubredditImpl subredditWithName(String name) {
		Objects.requireNonNull(name, "name");
		return new SubredditBuilder().withTitle(name).build();
	}

}
