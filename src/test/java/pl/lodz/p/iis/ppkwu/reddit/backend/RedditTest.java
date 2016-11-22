package pl.lodz.p.iis.ppkwu.reddit.backend;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
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
	public void rezultatJestSpojny() {
		reddit.loadCategoriesList(result -> {
			assertNotNull(result);
			assertNotNull(result.status());
			assertNotNull(result.content());
			assertThat(result.succeeded(), is(ResultStatus.SUCCEEDED.equals(result.status())));
			assertThat(result.content().isPresent(), is(result.succeeded()));
		});
	}

}
