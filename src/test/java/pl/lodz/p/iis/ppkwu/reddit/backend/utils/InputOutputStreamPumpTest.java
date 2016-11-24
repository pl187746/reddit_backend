package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class InputOutputStreamPumpTest {

	@Test
	public void test() throws IOException {
		int sampleSize = (int) (3.5 * InputOutputStreamPump.DEFAULT_BUFFER_SIZE);
		Random rand = new Random();
		byte[] input = new byte[sampleSize];
		rand.nextBytes(input);
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(input)) {
			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
				int pumped = InputOutputStreamPump.pumpAll(inputStream, outputStream);
				assertThat(pumped, is(sampleSize));
				byte[] output = outputStream.toByteArray();
				assertTrue(Arrays.equals(input, output));
			}
		}
	}

}
