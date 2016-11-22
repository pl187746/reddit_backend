package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.util.concurrent.Executor;

public class SameThreadExecutor implements Executor {

	private static final SameThreadExecutor instance = new SameThreadExecutor();
	
	private SameThreadExecutor() {
		super();
	}
	
	public static SameThreadExecutor get() {
		return instance;
	}
	
	@Override
	public void execute(Runnable command) {
		command.run();
	}

}
