package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import pl.lodz.p.iis.ppkwu.reddit.api.Callback;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;

public class CallbackBinder<R> implements Runnable {

	private final Callback<R> callback;
	private final Result<R> result;

	public CallbackBinder(Callback<R> callback, Result<R> result) {
		super();
		this.callback = callback;
		this.result = result;
	}

	@Override
	public void run() {
		callback.finished(result);
	}

	public Callback<R> getCallback() {
		return callback;
	}

	public Result<R> getResult() {
		return result;
	}

}
