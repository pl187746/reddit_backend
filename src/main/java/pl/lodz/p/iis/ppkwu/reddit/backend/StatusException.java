package pl.lodz.p.iis.ppkwu.reddit.backend;

import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;

class StatusException extends Exception {

	private static final long serialVersionUID = 7344975565511201932L;

	private final ResultStatus status;

	public StatusException(ResultStatus status, String msg, Throwable cause) {
		super(msg, cause);
		this.status = status;
	}

	public StatusException(ResultStatus status, String msg) {
		super(msg);
		this.status = status;
	}

	public StatusException(ResultStatus status, Throwable cause) {
		super(cause);
		this.status = status;
	}

	public StatusException(ResultStatus status) {
		this.status = status;
	}

	public ResultStatus getStatus() {
		return status;
	}

}