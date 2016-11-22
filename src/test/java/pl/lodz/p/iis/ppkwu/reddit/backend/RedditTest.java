package pl.lodz.p.iis.ppkwu.reddit.backend;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.api.User;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.ResultTest;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.SameThreadExecutor;

public class RedditTest {

	RedditImpl reddit;

	@Before
	public void setUp() {
		reddit = new RedditBuilderImpl()
			.withWorkerExecutor(SameThreadExecutor.get()) // efektywnie wymusza synchroniczne wywołania
			.withCallbackExecutor(SameThreadExecutor.get())
			.build();
	}

	@Test
	public void wywolujeCallback() {
		boolean[] ok = new boolean[1];
		reddit.loadCategoriesList(result -> {
			ok[0] = true;
		});
		assertTrue(ok[0]);
	}

	@Test
	public void rezultatPobieraniaListyKategoriiJestSpojny() {
		reddit.loadCategoriesList(ResultTest::rezultatJestSpojny);
	}

	@Test
	public void rezultatPobieraniaWpisowUzytkownikaJestSpojny() {
		User user = reddit.userWithLogin("qwerty");
		reddit.loadUserNews(user, ResultTest::rezultatJestSpojny);
	}

	@Test
	public void rezultatPobieraniaWpisowPoSlowachKluczowychJestSpojny() {
		List<String> keywords = new ArrayList<>();
		keywords.add("qwerty");
		keywords.add("uiop");
		reddit.loadNewsByKeywords(keywords, ResultTest::rezultatJestSpojny);
	}

	@Test
	public void rezultatPobieraniaWpisowPoPustejLiscieSlowKluczowychJestSpojny() {
		reddit.loadNewsByKeywords(Collections.emptyList(), ResultTest::rezultatJestSpojny);
	}

}
