package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.ResultBuilder;

public class ResultTest {

	@Test(expected = AssertionError.class)
	public void nieMoznaStworzycPomyslnegoRezultatuBezZawartosci() {
		new ResultImpl<Integer>(ResultStatus.SUCCEEDED, Optional.empty());
	}

	@Test(expected = AssertionError.class)
	public void nieMoznaStworzycNiepomyslnegoRezultatuZZawartoscia() {
		new ResultImpl<Integer>(ResultStatus.CONNECTION_ERROR, Optional.of(42));
	}

	@Test
	public void pomyslnyRezlutatJestSpojny() {
		Result<Integer> result = new ResultBuilder<Integer>().withContent(42).build();
		rezultatJestSpojny(result);
	}

	@Test
	public void niepomyslnyRezlutatJestSpojny() {
		Result<Integer> result = new ResultBuilder<Integer>().withStatus(ResultStatus.DATA_ERROR).build();
		rezultatJestSpojny(result);
	}

	public static <R> void rezultatJestSpojny(Result<R> result) {
		assertNotNull(result);
		assertNotNull(result.status());
		assertNotNull(result.content());
		assertThat(result.succeeded(), is(ResultStatus.SUCCEEDED.equals(result.status())));
		assertThat(result.content().isPresent(), is(result.succeeded()));
	}

}
