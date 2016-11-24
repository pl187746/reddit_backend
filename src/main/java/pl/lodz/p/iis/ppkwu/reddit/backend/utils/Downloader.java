package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {

	public static final String USER_AGENT = "Mozilla/5.0 (pl.lodz.p.iis.ppkwu.reddit)";

	private Downloader() {
		throw new UnsupportedOperationException();
	}

	public static byte[] download(URL url) throws IOException {
		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
			download(url, buffer);
			return buffer.toByteArray();
		}
	}

	public static int download(URL url, OutputStream destination) throws IOException {
		URLConnection connection = connect(url);
		return download(connection, destination);
	}

	public static int download(URLConnection connection, OutputStream destination) throws IOException {
		try (InputStream inputStream = connection.getInputStream()) {
			return InputOutputStreamPump.pumpAll(inputStream, destination);
		}
	}

	private static URLConnection connect(URL url) throws IOException {
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.connect();
		return connection;
	}
}
