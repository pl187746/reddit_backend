package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.backend.UrlGenerator;

public class DownloaderTest {

	@Test
	public void test() throws IOException {
		byte[] data = Downloader.download(UrlGenerator.baseUrl());
		String str = new String(data);
		assertTrue(str.contains("reddit"));
	}

}
