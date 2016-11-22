package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.api.Callback;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;

public class CallbackBinder<R> implements Runnable {

	private final Callback<R> callback;
	private final Result<R> result;

	public CallbackBinder(Callback<R> callback, Result<R> result) {
		this.callback = Objects.requireNonNull(callback, "callback");
		this.result = Objects.requireNonNull(result, "reult");
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
