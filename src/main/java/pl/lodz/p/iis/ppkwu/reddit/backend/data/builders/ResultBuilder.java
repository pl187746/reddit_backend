package pl.lodz.p.iis.ppkwu.reddit.backend.data.builders;

import java.util.Objects;
import java.util.Optional;

import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.ResultImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;

public class ResultBuilder<R> implements Builder<ResultImpl<R>> {

	private ResultStatus resultStatus;
	private Optional<R> content = Optional.empty();

	public ResultBuilder<R> withStatus(ResultStatus status) {
		this.resultStatus = Objects.requireNonNull(status);
		if(!ResultStatus.SUCCEEDED.equals(status)) {
			this.content = Optional.empty();
		}
		return this;
	}

	public ResultBuilder<R> withContent(Optional<R> content) {
		this.content = Objects.requireNonNull(content);
		if(content.isPresent()) {
			this.resultStatus = ResultStatus.SUCCEEDED;
		}
		return this;
	}

	public ResultBuilder<R> withContent(R content) {
		return withContent(Optional.ofNullable(content));
	}

	@Override
	public ResultImpl<R> build() {
		return new ResultImpl<>(resultStatus, content);
	}

}
