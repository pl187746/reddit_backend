package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UrlEncoderTest {

	@Test
	public void test() {
		String input = "123 abc ABC ąćę ĄĆĘ .*_- /+&% \\\'\"";
		String expected = "123%20abc%20ABC%20%C4%85%C4%87%C4%99%20%C4%84%C4%86%C4%98%20.*_-%20%2F%2B%26%25%20%5C%27%22";
		String output = UrlEncoder.encode(input);
		assertThat(output, is(expected));
	}

}
