package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

public class InvocationChecker {

	private static String[] PERMITTED_PREFIXES = { "pl.lodz.p.iis.ppkwu.reddit.backend.",
			"pl.lodz.p.iis.ppkwu.reddit.api." };

	private static int CALLER_OF_CALLER_OF_CHECKER = 3;

	private InvocationChecker() {
		throw new UnsupportedOperationException();
	}

	public static void checkInvocation() {
		StackTraceElement[] stackTrace = getStackTrace();
		StackTraceElement callerOfCaller = stackTrace[CALLER_OF_CALLER_OF_CHECKER];
		String className = callerOfCaller.getClassName();
		System.out.println(className);
		for (String prefix : PERMITTED_PREFIXES) {
			if (className.startsWith(prefix)) {
				return;
			}
		}
	}

	private static StackTraceElement[] getStackTrace() {
		try {
			throw new Throwable();
		} catch (Throwable thr) {
			return thr.getStackTrace();
		}
	}

}
