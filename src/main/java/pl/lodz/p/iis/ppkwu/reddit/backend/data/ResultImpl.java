package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import java.util.Objects;
import java.util.Optional;

import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;

public class ResultImpl<R> implements Result<R> {

	private final ResultStatus resultStatus;
	private final Optional<R> content;

	public ResultImpl(ResultStatus resultStatus, Optional<R> content) {
		this.resultStatus = Objects.requireNonNull(resultStatus, "resultStatus");
		this.content = Objects.requireNonNull(content, "content");
	}

	@Override
	public boolean succeeded() {
		return ResultStatus.SUCCEEDED.equals(this.status());
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
