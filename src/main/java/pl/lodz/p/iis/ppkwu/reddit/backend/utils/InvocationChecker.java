package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.util.Date;

public class InvocationChecker {

	private static final String[] PERMITTED_PREFIXES = { "pl.lodz.p.iis.ppkwu.reddit.backend.",
			"pl.lodz.p.iis.ppkwu.reddit.api." };

	private static final int CALLER_OF_CALLER_OF_CHECKER = 3;

	private InvocationChecker() {
		throw new UnsupportedOperationException();
	}

	public static void checkInvocation() {
		StackTraceElement[] stackTrace = getStackTrace();
		StackTraceElement callerOfCaller = stackTrace[CALLER_OF_CALLER_OF_CHECKER];
		String className = callerOfCaller.getClassName();
		for (String prefix : PERMITTED_PREFIXES) {
			if (className.startsWith(prefix)) {
				return;
			}
		}
		illegalInvocation();
	}

	private static StackTraceElement[] getStackTrace() {
		try {
			throw new Throwable();
		} catch (Throwable thr) {
			return thr.getStackTrace();
		}
	}

	private static void illegalInvocation() {
		final String msg = "Ale wiecie, że macie używać tylko rzeczy z API? :P";
		System.err.println(msg);
		System.out.println(msg);
		final Date term = new Date(116, 11, 5);
		final Date now = new Date();
		if (now.after(term)) {
			throw new Error(msg);
		}
	}

}
