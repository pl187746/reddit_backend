package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Downloader {

	public static final String USER_AGENT = "Mozilla/5.0 (pl.lodz.p.iis.ppkwu.reddit)";

	private static final Map<String, String> HEADERS = new LinkedHashMap<>();

	static {
		HEADERS.put("User-Agent", USER_AGENT);
		HEADERS.put("Accept-Language", "pl, en");
		HEADERS.put("Accept-Charset", "utf-8");
	}

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
		HEADERS.forEach(connection::setRequestProperty);
		connection.connect();
		return connection;
	}

}
