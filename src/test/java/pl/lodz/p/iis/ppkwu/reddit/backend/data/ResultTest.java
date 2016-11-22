package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import java.util.Optional;

import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;

public class ResultTest {

	@Test(expected = AssertionError.class)
	public void nieMoznaStworzycPomyslnegoRezultatuBezZawartosci() {
		new ResultImpl<Integer>(ResultStatus.SUCCEEDED, Optional.empty());
	}

	@Test(expected = AssertionError.class)
	public void nieMoznaStworzycNiepomyslnegoRezultatuZZawartoscia() {
		new ResultImpl<Integer>(ResultStatus.CONNECTION_ERROR, Optional.of(42));
	}

}
