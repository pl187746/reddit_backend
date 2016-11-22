package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.Optional;

import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;

public class ResultImpl<R> implements Result<R> {

	private boolean succeeded;
	private ResultStatus resultStatus;
	private Optional<R> content;

	public ResultImpl(boolean succeeded, ResultStatus resultStatus, Optional<R> content) {
		this.succeeded = succeeded;
		this.resultStatus = resultStatus;
		this.content = content;
	}

	@Override
	public boolean succeeded() {
		return succeeded;
	}

	@Override
	public ResultStatus status() {
		return resultStatus;
	}

	@Override
	public Optional<R> content() {
		return content;
	}

}
