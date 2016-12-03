package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.nio.charset.Charset;

public class UrlEncoder {

	private static final Charset charset = Charset.forName("UTF-8");

	private UrlEncoder() {
		throw new UnsupportedOperationException();
	}

	public static String encode(String string) {
		return encode(string.getBytes(charset));
	}

	public static void encode(String string, StringBuilder out) {
		encode(string.getBytes(charset), out);
	}

	public static String encode(byte[] bytes) {
		StringBuilder res = new StringBuilder(bytes.length);
		encode(bytes, res);
		return res.toString();
	}

	public static void encode(byte[] bytes, StringBuilder out) {
		for (byte b : bytes) {
			if (legalChar(b)) {
				out.append((char) b);
			} else {
				out.append(String.format("%%%02X", b));
			}
		}
	}

	public static boolean legalChar(int c) {
		return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c == '-') || (c == '_')
				|| (c == '.') || (c == '*');
	}

}
