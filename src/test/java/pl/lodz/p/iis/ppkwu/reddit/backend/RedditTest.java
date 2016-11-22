package pl.lodz.p.iis.ppkwu.reddit.backend;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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

}
