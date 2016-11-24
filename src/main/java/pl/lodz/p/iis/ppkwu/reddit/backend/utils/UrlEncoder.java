package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.nio.charset.Charset;

public class UrlEncoder {

	private static final Charset charset = Charset.forName("UTF-8");

	public static String encode(String string) {
		return encode(string.getBytes(charset));
	}

	public static String encode(byte[] bytes) {
		StringBuilder res = new StringBuilder(bytes.length);
		for(byte b : bytes) {
			if(legalChar(b)) {
				res.append((char)b);
			} else {
				res.append(String.format("%%%02X", b));
			}
		}
		return res.toString();
	}

	public static boolean legalChar(int c) {
		return (c >= '0' && c <= '9')
				|| (c >= 'A' && c <= 'Z')
				|| (c >= 'a' && c <= 'z')
				|| (c == '-')
				|| (c == '_')
				|| (c == '.')
				|| (c == '*');
	}

}
